package fr.az.util.parsing.json.keys.types;

import java.util.HashMap;
import java.util.Map;

import fr.az.util.parsing.json.JSONParsingException;
import fr.az.util.parsing.json.keys.Key;

/**
 * An interface that represents a key that pass on the cascade to its children.<br />
 * The cascade is a map holding values for each used optional key, meaning if said key is used thereafter and unspecified, its default value is
 * provided by the cascade instead. For each layer of JSON (arrays [] or compounds {}), a copy of this map is instanciated so as to make the
 * changes visible only for the children of a key. If an optional key is specified again, its value replaces the registered one at this layer of
 * the cascade.
 * <p>
 * {@link ObjectKey}, {@link ArrayKey}, {@link ObjectArrayKey} and their derivative are cascading keys.
 *
 * @author AZ
 *
 * @param <I> same as Key
 * @param <O> same as Key
 * @see Key
 */
public interface CascadingKey<I, O> extends Key<I, O>
{
	@Override default O parse(I source) throws JSONParsingException { return this.parse(new HashMap<>(), source); }

	/**
	 * Parse the input into an instance of the output type, according to the specified cascade
	 *
	 * @param cascade the cascade
	 * @param source the input
	 * @return a parsed input
	 */
	O parse(@SuppressWarnings("rawtypes") Map<Key, Object> cascade, I source) throws JSONParsingException;

	@Override default boolean isCascadingKey() { return true; }
	@Override default CascadingKey<I, O> asCascadingKey() { return this; }
}
