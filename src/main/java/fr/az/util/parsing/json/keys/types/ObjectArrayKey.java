package fr.az.util.parsing.json.keys.types;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import fr.az.util.parsing.json.JSONParsingException;
import fr.az.util.parsing.json.keys.Key;
import fr.az.util.parsing.json.keys.types.ArrayKey.AbstractArrayKey;

/**
 * Parse an array of {@linkplain JSONObject}
 * @author A~Z
 * @see ObjectKey
 * @param <T> the object built by a single JSONObject
 */
@SuppressWarnings({ "rawtypes" })
public abstract class ObjectArrayKey<T> extends AbstractArrayKey<JSONObject, T>
{
	private static final long serialVersionUID = 9204202544208385246L;

	private final ObjectKey<T> parser = new ObjectKey<T>()
	{
		private static final long serialVersionUID = 8862878584229594393L;

		@Override
		public T build(Map<Key, Object> mandatory, Map<Key, Object> optional) throws JSONParsingException
		{
			return ObjectArrayKey.this.build(mandatory, optional);
		}

		@Override public List<Key> getMandatory() { return ObjectArrayKey.this.getMandatory(); }
		@Override public List<Key> getOptional() { return ObjectArrayKey.this.getOptional(); }
		@Override public String getKey() { return ObjectArrayKey.this.getKey(); }
		@Override public String toString() { return ObjectArrayKey.this.toString(); }
	};

	private Map<Key, Object> cascade = new HashMap<>();

	/**
	 * Build a T object with a {@linkplain Map}
	 * @param values a {@literal Map<Key, Object>}
	 * @see ObjectKey#build(Map)
	 * @return T
	 */
	public abstract T build(Map<Key, Object> mandatory, Map<Key, Object> optional) throws JSONParsingException;

	/**
	 * Parse a single T object instanciating a new temporary {@linkplain ObjectKey}
	 */
	@Override
	public T parseSingle(JSONObject obj) throws JSONParsingException
	{
		return this.parser.parse(this.cascade, obj);
	}

	void setCascade(Map<Key, Object> cascade) { this.cascade = cascade; }

	/**
	 * @see ObjectKey#getMandatory()
	 * @return a {@literal List<Key>}
	 */
	public abstract List<Key> getMandatory();

	/**
	 * @see ObjectKey#getOptional()
	 * @return a {@literal List<Key>}
	 */
	public abstract List<Key> getOptional();

	@Override public boolean isObjectArrayKey() { return true; }
	@Override public ObjectArrayKey<T> asObjectArrayKey() { return this; }
}
