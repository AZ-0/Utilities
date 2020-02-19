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
	 * Parse an I object into an O object.
	 * @param from the provided I object.
	 * @return the parsed O object.
	 * @throws T if an error occured while parsing.
	 */
	O parse(I from) throws T;

	/**
	 * Parse an I object into an O object, and wraps any occuring {@linkplain Throwable} into a {@linkplain ParsingException}.
	 * @param from the provided I object.
	 * @return the parsed O objecT.
	 * @throws ParsingException wrap any exception that may occurs while parsing.
	 * @see #parse(I)
	 */
	default O safeParse(I from) throws ParsingException
	{
		try
		{
			return this.parse(from);
		} catch (Throwable t)
		{
			throw new ParsingException(t);
		}
	}

	/**
	 * This method tries to parse an I into an O but only prints the stack trace in the case an exception occurs.
	 * @return the parsed O, or null if an exception occured
	 */
	@Override
	default O apply(I from) { try { return this.parse(from); } catch(Throwable t) { t.printStackTrace(); return null; } }
}
