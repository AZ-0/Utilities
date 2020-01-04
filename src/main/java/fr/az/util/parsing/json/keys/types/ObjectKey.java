package fr.az.util.parsing.json.keys.types;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

import fr.az.util.parsing.json.JSONParsingException;
import fr.az.util.parsing.json.keys.Key;

/**
 * Parse a {@linkplain JSONObject} into a {@linkplain Map}{@literal <}{@linkplain Key},{@linkplain Object}{@literal >}
 * [where the values are their keys parsing result], then build a T object from this Map.
 * @author A~Z
 * @see ObjectKey#parse(JSONObject) {@literal parse(JSONObject)}
 * @see ObjectKey#build(Map) {@literal build(Map<Key,Object>)}
 * @param <T> the built object type
 */
@SuppressWarnings({ "rawtypes" })
public interface ObjectKey<T> extends Key<JSONObject, T>
{
	List<Key> EMPTY_KEY_LIST = Collections.emptyList();

	/**
	 * Build a T object with all the values got during the {@linkplain JSONObject} parsing
	 * @param values a {@literal Map<Key, Object>}
	 * @see ObjectKey#parse(JSONObject)
	 * @return a T object
	 * @throws JSONParsingException if semantic is incorrect at runtime
	 */
	T build(Map<Key, Object> mandatory, Map<Key, Object> optional) throws JSONParsingException;

	/**
	 * Parse a T object from a JSONObject in several steps
	 * <ol>
	 * <li>Get and parse each mandatory key</li>
	 * <li>For each optional key:
	 * 	<ul>
	 * 		<li>Check key presence</li>
	 * 		<li>Get key</li>
	 * 		<li>Parse key value</li>
	 * 	</ul>
	 * </li>
	 * <li>Check key duplication or unrequited presence</li>
	 * <li>Check each <i>parsed</i> key blacklist</li>
	 * <li>Run T build</li>
	 * </ol>
	 * @param source a {@link JSONObject}
	 * @return T the parsed value
	 * @throws JSONParsingException
	 */
	@Override default T parse(JSONObject source) throws JSONParsingException { return this.parse(new HashMap<>(), source); }

	/**
	 * @param cascade
	 * @param source
	 * @return
	 * @throws JSONParsingException
	 * @see ObjectKey#parse(JSONObject)
	 */
	default T parse(Map<Key, Object> cascade, JSONObject source) throws JSONParsingException
	{
		Map<Key, Object> mandatory = new HashMap<>(), optional = new HashMap<>();
		Set<String> parsed = new HashSet<>();

		List<Key> mandatoryKeys = this.getMandatory();
		this.parse(mandatoryKeys, source, parsed, cascade, mandatory);

		for (Key key : mandatoryKeys)
			if (!parsed.contains(key.getKey()))
				throw new JSONParsingException(this, "Missing mandatory key: "+ key.getKey());

		this.parse(this.getOptional(), source, parsed, cascade, optional);

		//Save values for cascade
		cascade.putAll(optional);

		//Retrieve values from cascade
		for (Key k : this.getCascadeKeys())
			if (!optional.containsKey(k) && cascade.containsKey(k))
				optional.put(k, cascade.get(k));

		Set<String> keys = source.keySet();
		keys.removeAll(parsed);

		if (!keys.isEmpty())
			throw new JSONParsingException(this, '\''+ String.join("', '", keys) +"' are invalid children for '"+ this.getKey() +'\'');

		try { return this.build(mandatory, optional); }
		catch (JSONParsingException e) { throw new JSONParsingException(this, e); }
		catch (Exception e) { throw new JSONParsingException(this, e); }
	}

	@SuppressWarnings("unchecked")
	default void parse(List<Key> keys, JSONObject source, Set<String> parsed, Map<Key, Object> cascade, Map<Key, Object> values) throws JSONParsingException
	{
		for (Key k : keys)
			if (source.has(k.getKey()) && !parsed.contains(k.getKey()))
				try
				{
					try
					{
						if (k.isObjectArrayKey())
							k.asObjectArrayKey().setCascade(new HashMap<>(cascade));

						if (k.isObjectKey())
							values.put(k, k.asObjectKey().parse(new HashMap<>(cascade), source.getJSONObject(k.getKey())));
						else
							values.put(k, k.parse(source.get(k.getKey())));

						parsed.add(k.getKey());
					}
					catch (JSONParsingException e) { throw e; }
					catch(Exception e)	{ throw new JSONParsingException(k, e); }
				}
				catch(JSONParsingException e) { throw new JSONParsingException(this, e); }
	}

	/**
	 * Get a {@linkplain List} of keys that are required to build a T object
	 * @return a {@literal List<Key>}
	 */
	List<Key> getMandatory();

	/**
	 * Get a {@linkplain List} of keys that provide additional but non-necessary informations on the T object.
	 * This may be used to compute necessary keys at runtime [for instance either two of three keys, and such]
	 * @return a {@literal List<Key>}
	 */
	List<Key> getOptional();

	/**
	 * Register keys for cascade values: if the optional key is missing it will be replaced by the previous computed cascade value.
	 * You may only register optional keys.
	 * @return a {@link List}
	 */
	default List<Key> getCascadeKeys() { return this.getOptional(); }

	@Override default boolean isObjectKey() { return true; }
	@Override default ObjectKey<T> asObjectKey() { return this; }
}
