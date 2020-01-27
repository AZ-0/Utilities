package fr.az.util.parsing;

import java.io.Serializable;
import java.util.function.Function;

/**
 * The basic definition of a parser. It extends <code>Function{@literal <I,O>}</code>
 * @author A~Z
 *
 * @param <I> the input value type
 * @param <O> the output value type
 */
@FunctionalInterface
public interface IParser<I, O, T extends Throwable> extends Function<I, O>, Serializable
{
	/**
	 * Parse an I into an O
	 * @param from the provided I
	 * @return the parsed O
	 * @throws ParsingException if an error occured while parsing
	 */
	O parse(I from) throws T;

	/**
	 * This method tries to parse an I into an O but only prints the stack trace in the case an exception occurs.
	 * @return the parsed O, or null if an exception occured
	 */
	@Override
	default O apply(I from) { try { return this.parse(from); } catch(Throwable t) { t.printStackTrace(); return null; } }
}
