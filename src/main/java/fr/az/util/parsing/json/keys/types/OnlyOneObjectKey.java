package fr.az.util.parsing.json.keys.types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
		return (T) structures.get(0).values().values().iterator().next();
	}

	@Override
	default List<Structure> getStructures()
	{
		return Collections.singletonList(new OnlyNKeys(1, new ArrayList<>(this.getKeys())));
	}
}
