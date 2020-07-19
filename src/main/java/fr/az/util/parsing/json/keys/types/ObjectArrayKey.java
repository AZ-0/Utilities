package fr.az.util.parsing.json.keys.types;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import fr.az.util.parsing.json.JSONParsingException;
import fr.az.util.parsing.json.keys.structure.Structure;

/**
 * Parse an array of {@linkplain JSONObject}
 * @author A~Z
 * @see ObjectKey
 * @param <T> the object built by a single JSONObject
 */
public abstract class ObjectArrayKey<T> implements ArrayKey<JSONObject, T>
{
	private static final long serialVersionUID = 9204202544208385246L;

	private final ObjectKey<T> parser = new ObjectKey<>()
	{
		private static final long serialVersionUID = 8862878584229594393L;

		@Override
		public T build(List<Structure> structures) throws JSONParsingException
		{
			return ObjectArrayKey.this.build(structures);
		}

		@Override public List<Structure> getStructures() { return ObjectArrayKey.this.getStructures(); }
		@Override public String getKey() { return ObjectArrayKey.this.getKey(); }
		@Override public String toString() { return ObjectArrayKey.this.toString(); }
	};

	/**
	 * Build a T object with a {@linkplain Map}
	 * @param values a {@literal Map<Key, Object>}
	 * @see ObjectKey#build(Map)
	 * @return T
	 */
	public abstract T build(List<Structure> structures) throws JSONParsingException;

	/**
	 * @see ObjectKey#getStructures()
	 * @return a {@literal List<Structure>}
	 */
	public abstract List<Structure> getStructures();

	@Override public ObjectKey<T> getElementParser() { return this.parser; }
	@Override public boolean isObjectArrayKey() { return true; }
	@Override public ObjectArrayKey<T> asObjectArrayKey() { return this; }

	@Override public String toString() { return this.getKey(); }
}
