package fr.az.util.parsing.json.keys.types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import fr.az.util.parsing.IParser;
import fr.az.util.parsing.json.JSONParsingException;
import fr.az.util.parsing.json.keys.Key;

/**
 * This class handles the parsing of arrays
 * @author A~Z
 *
 * @param <E> Array's elements input type
 * @param <O> Output type
 */
public interface ArrayKey<E, O> extends CascadingKey<JSONArray, List<O>>
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
	@Override @SuppressWarnings({ "unchecked", "rawtypes" })
	default List<O> parse(Map<Key, Object> cascade, JSONArray array) throws JSONParsingException
	{
		List<O> list = new ArrayList<>();

		for (int i = 0; i < array.length(); i++)
		{
			AbstractArrayKey.ITERATIONS.put(this, i);
			try
			{
				IParser<E, O, ?> parser = this.getElementParser();

				if (parser instanceof CascadingKey)
					list.add(((CascadingKey<E, O>) parser).parse(cascade, (E) array.get(i)));
				else
					list.add(parser.parse((E) array.get(i)));
			}
			catch (JSONParsingException e) { throw e; }
			catch (Throwable t) { throw new JSONParsingException(this, t); }
		}

		return list;
	}


	/**
	 * Get a {@linkplain IParser} parsing for every single I element of the array into a O object
	 * @return a IParser
	 */
	IParser<E, O, ?> getElementParser();

	@Override default boolean isArrayKey() { return true; }
	@Override default ArrayKey<E, O> asArrayKey() { return this; }

	public static abstract class AbstractArrayKey<E, O> extends AbstractKey<JSONArray, List<O>> implements ArrayKey<E, O>
	{
		private static final long serialVersionUID = 7907977459215641821L;

		private static final HashMap<ArrayKey<?, ?>, Integer> ITERATIONS = new HashMap<>();

		@Override public String toString() { return this.getKey() +"["+ AbstractArrayKey.ITERATIONS.get(this) +"]"; }
	}
}
