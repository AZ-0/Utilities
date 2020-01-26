package fr.az.util.parsing.json.keys;

import fr.az.util.parsing.IParser;
import fr.az.util.parsing.json.JSONParsingException;
import fr.az.util.parsing.json.keys.types.ArrayKey;
import fr.az.util.parsing.json.keys.types.CascadingKey;
import fr.az.util.parsing.json.keys.types.ObjectArrayKey;
import fr.az.util.parsing.json.keys.types.ObjectKey;

/**
 * This class is the mother of all json keys for this system. As there are child classes handling things correctly already,
 * it is not recommended to directly extends this class. <br />
 * Tough this class is not namely a singleton one, it is intended to be used as such and by convention posses a final static field [e.g: key]
 * holding its isntance.
 * @author A~Z
 *
 * @param <I> Input Type [Usually handled by the keys in {@linkplain fr.az.util.parsing.json.keys.types}]
 * @param <O> Output Type
 */
public interface Key<I,O> extends IParser<I, O, JSONParsingException>
{
	/**
	 * Parse the input type into an instance of the output type
	 * @param obj the input Object
	 * @return an output object
	 * @throws JSONParsingException if syntax or semantic is incorrect, or an exception is thrown
	 */
	@Override
	O parse(I from) throws JSONParsingException;

	/**
	 * @return this key name, should be a constant
	 */
	String getKey();

	default boolean isCascadingKey() { return false; }
	default boolean isObjectKey() { return false; }
	default boolean isArrayKey() { return false; }
	default boolean isObjectArrayKey() { return false; }

	default CascadingKey<I, O> asCascadingKey() { return null; }
	default ObjectKey<O> asObjectKey() { return null; }
	default ArrayKey<?, ?> asArrayKey() { return null; }
	default ObjectArrayKey<?> asObjectArrayKey() { return null; }

	public static abstract class AbstractKey<I, O> implements Key<I, O>
	{
		private static final long serialVersionUID = -7165844987966510743L;

		@Override public String toString() { return this.getKey(); }
	}
}