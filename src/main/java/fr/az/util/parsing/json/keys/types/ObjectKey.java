package fr.az.util.parsing.json.keys.types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

import fr.az.util.parsing.json.JSONParsingException;
import fr.az.util.parsing.json.keys.Key;
import fr.az.util.parsing.json.keys.structure.Structure;

/**
 * Parse a {@linkplain JSONObject} into a {@linkplain Map}{@literal <}{@linkplain Key},{@linkplain Object}{@literal >}
 * [where the values are their keys parsing result], then build a T object from this Map.
 * @author A~Z
 * @see ObjectKey#parse(JSONObject) {@literal parse(JSONObject)}
 * @see ObjectKey#build(Map) {@literal build(Map<Key,Object>)}
 * @param <T> the built object type
 */
@SuppressWarnings({ "rawtypes" })
public interface ObjectKey<T> extends CascadingKey<JSONObject, T>
{
	List<Key> EMPTY_KEY_LIST = Collections.emptyList();

	/**
	 * Build a T object with all the values got during the {@linkplain JSONObject} parsing
	 * @param values a {@literal Map<Key, Object>}
	 * @see ObjectKey#parse(JSONObject)
	 * @return a T object
	 * @throws JSONParsingException if semantic is incorrect at runtime
	 */
	T build(List<Structure> structures) throws JSONParsingException;

	/**
	 * @param cascade
	 * @param source
	 * @return
	 * @throws JSONParsingException
	 * @see ObjectKey#parse(JSONObject)
	 */
	@Override
	default T parse(Map<Key, Object> cascade, JSONObject source) throws JSONParsingException
	{
		List<Structure> structures = this.getStructures();
		Set<String> parsed = new HashSet<>();

		for (Structure structure : structures)
		{
			this.parse(structure.getKeys(), source, parsed, cascade, structure.getValues());
			structure.process(this, source, parsed, cascade);
		}

		Set<String> keys = new HashSet<>(source.keySet());
		keys.removeAll(parsed);

		if (!keys.isEmpty())
			throw new JSONParsingException(this, '\''+ String.join("', '", keys) +"' are invalid children for '"+ this.getKey() +'\'');

		try { return this.build(structures); }
		catch (JSONParsingException e) { throw new JSONParsingException(this, e); }
		catch (Exception e) { throw new JSONParsingException(this, e); }
	}

	/**
	 * Inner parsing process, use {@link ObjectKey#parse(JSONObject)} or {@link ObjectKey#parse(Map, JSONObject)} instead
	 * @param keys the requested keys
	 * @param source the parsed object's source
	 * @param parsed a set of parsed key names
	 * @param cascade the cascade
	 * @param values a map of parsed key values
	 * @throws JSONParsingException
	 */
	@SuppressWarnings("unchecked")
	default void parse(List<Key> keys, JSONObject source, Set<String> parsed, Map<Key, Object> cascade, Map<Key, Object> values) throws JSONParsingException
	{
		for (Key k : keys)
			if (source.has(k.getKey()) && !parsed.contains(k.getKey()))
				try
				{
					try
					{
						if (k.isCascadingKey())
							values.put(k, k.asCascadingKey().parse(cascade, source.get(k.getKey())));
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
	 * Get a {@linkplain List} of structures which define how the keys within this compound should be used.
	 * @return a {@literal List<Structure>}
	 */
	List<Structure> getStructures();

	/**
	 * @return a {@link List} of keys registered for the cascade
	 * @see Structure#isCascading()
	 */
	default List<Key> getCascadeKeys()
	{
		final ArrayList<Key> cascading = new ArrayList<>();

		for (Structure structure : this.getStructures())
			if (structure.isCascading())
				cascading.addAll(structure.getKeys());

		return cascading;
	}

	@Override default boolean isObjectKey() { return true; }
	@Override default ObjectKey<T> asObjectKey() { return this; }
}
