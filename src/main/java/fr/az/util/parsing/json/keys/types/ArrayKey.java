package fr.az.util.parsing.json.keys.types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;

import fr.az.util.parsing.json.JSONParsingException;
import fr.az.util.parsing.json.keys.Key;

/**
 * This class handles the parsing of arrays
 * @author A~Z
 *
 * @param <E> Array's elements input type
 * @param <O> Output type
 */
public interface ArrayKey<E, O> extends Key<JSONArray, List<O>>
{
	/**
	 * Parse a JSONArray containing any number of O objects.
	 * If the array contains a single element, it is possible to only write this element instead.
	 * @param cascade
	 * @param obj
	 * @return
	 * @throws JSONParsingException
	 * @see ArrayKey#parse(Object)
	 */
	@Override @SuppressWarnings({ "unchecked" })
	default List<O> parse(JSONArray array) throws JSONParsingException
	{
		List<O> list = new ArrayList<>();

		for (int i = 0; i < array.length(); i++)
		{
			AbstractArrayKey.ITERATIONS.put(this, i);
			try { list.add(this.parseSingle((E) array.get(i))); }
			catch (JSONParsingException e) { throw e; }
			catch (Exception e) { throw new JSONParsingException(this, e); }
		}

		return list;
	}

	/**
	 * Parse a single I element of the array into a O object
	 * @param an I input
	 * @return an O object
	 * @throws JSONParsingException if syntax or semantic is incorrect
	 */
	O parseSingle(E input) throws JSONParsingException;

	@Override default boolean isArrayKey() { return true; }
	@Override default ArrayKey<E, O> asArrayKey() { return this; }

	public static abstract class AbstractArrayKey<E, O> extends AbstractKey<JSONArray, List<O>> implements ArrayKey<E, O>
	{
		private static final long serialVersionUID = 7907977459215641821L;

		private static final HashMap<ArrayKey<?, ?>, Integer> ITERATIONS = new HashMap<>();

		@Override public String toString() { return this.getKey() +"["+ AbstractArrayKey.ITERATIONS.get(this) +"]"; }
	}
}
