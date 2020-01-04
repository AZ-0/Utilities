package fr.az.util.parsing;

/**
 * This class provides a bridge between {@linkplain IParser}{@literal <String, I>} and {@linkplain IParser}{@literal <String, I>}.
 * It is basically possible to build a {@linkplain IParser}{@literal <String, I>} with a SimpleParser using this code:
 * <code><blockquote>(s) -> parser.parse(s)</blockquote></code> where parser is a SimpleParser.
 * @author A~Z
 *
 * @param <O>
 */
@FunctionalInterface
public interface SimpleParser<O, P extends ParsingException> extends IParser<Parsable, O, P>
{
	static <O, P extends ParsingException> SimpleParser<O, P> asSimpleParser(IParser<String, O, P> parser) { return s -> parser.parse(s); }

	/** Same as <code>simpleParser.parse(parsable.asParsableString())</code> */
	@Override default O parse(Parsable parsable) throws P { return this.parse(parsable.asParsableString()); }

	/**
	 * Parse a I from a String
	 * @param from a String
	 * @return the parsed I
	 * @throws ParsingException if an error occured while parsing
	 */
	O parse(String from) throws P;

	/** @return an {@linkplain IParser} parsing String into I */
	default IParser<String, O, P> asStringParser() { return (s) -> this.parse(s); }
}
