package fr.az.util.parsing.json.keys.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;

import fr.az.util.parsing.json.JSONParsingException;
import fr.az.util.parsing.json.keys.Key;
import fr.az.util.parsing.json.keys.structure.OnlyNKeys;
import fr.az.util.parsing.json.keys.structure.Structure;

/**
 * You use this class when you want your key to contain one and only one key from a list, for instance in the case there are multiple
 * implementations of the same class.
 * @author AZ
 *
 * @param <T> the constructed type
 */
public interface OnlyOneObjectKey<T> extends ObjectKey<T>
{
	List<Key<?, ? extends T>> getKeys();

	@Override @SuppressWarnings("unchecked")
	default T build(List<Structure> structures) throws JSONParsingException
	{
		return (T) structures.get(0).getValues().values().stream().findAny().get();
	}

	@Override default List<Structure> getStructures() { return Arrays.asList(new OnlyNKeys(1, new ArrayList<>(this.getKeys()))); }

	public static abstract class AbstractOnlyOneObjectKey<T> extends AbstractKey<JSONObject, T> implements OnlyOneObjectKey<T>
	{
		private static final long serialVersionUID = -4139221444552396268L;

		private final List<Key<?, ? extends T>> keys;

		public AbstractOnlyOneObjectKey(List<Key<?, ? extends T>> keys)
		{
			this.keys = keys;
		}

		@Override
		public List<Key<?, ? extends T>> getKeys()
		{
			return this.keys;
		}
	}
}
