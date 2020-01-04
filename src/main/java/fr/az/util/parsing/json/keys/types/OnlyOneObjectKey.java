package fr.az.util.parsing.json.keys.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import fr.az.util.parsing.json.JSONParsingException;
import fr.az.util.parsing.json.keys.Key;

/**
 * You use this class when you want your key to contain one and only one key from a list, for instance in the case there are multiple
 * implementations of the same class.
 * @author AZ
 *
 * @param <T> the constructed type
 */
@SuppressWarnings("rawtypes")
public interface OnlyOneObjectKey<T> extends ObjectKey<T>
{
	List<Key<?, ? extends T>> getKeys();

	@Override @SuppressWarnings("unchecked")
	default T build(Map<Key, Object> mandatory, Map<Key, Object> optional) throws JSONParsingException
	{
		List<Key<?, ? extends T>> keys = this.getKeys();

		if (optional.size() != 1)
			throw new JSONParsingException(this, "Expected exactly one key from this list: " + keys);

		for (Key<?, ? extends T> key : keys)
			if (optional.containsKey(key))
				return (T) optional.get(key);

		throw new JSONParsingException(this, "Missing one key from this list: " + keys);
	}

	@Override default List<Key> getMandatory() { return EMPTY_KEY_LIST; }
	@Override default List<Key> getOptional() { return new ArrayList<>(this.getKeys()); }

	public static abstract class AbstractOnlyOneObjectKey<T> extends AbstractKey<JSONObject, T> implements OnlyOneObjectKey<T>
	{
		private static final long serialVersionUID = -4139221444552396268L;

		private final List<Key<?, ? extends T>> keys;

		public AbstractOnlyOneObjectKey(List<Key<?, ? extends T>> keys)
		{
			if (keys == null)
				System.err.println("NULL KEYS: "+ this.getKey());

			this.keys = keys;
		}

		@Override
		public List<Key<?, ? extends T>> getKeys()
		{
			if (this.keys == null)
				System.err.println("RETURNING NULL KEYS: "+ this.getKey());

			return this.keys;
		}
	}
}
