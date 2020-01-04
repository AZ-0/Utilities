package fr.az.util.parsing.string;

import fr.az.util.parsing.ParsingException;
import fr.az.util.parsing.SimpleParser;

/**
 * This class provides a basic implementation for common types, as well as some methods used while parsing a String
 * @author AZ
 *
 * @param <T>
 */
@FunctionalInterface
public interface Parser<T> extends SimpleParser<T, ParsingException>
{
	String INPUT = "{input}";
	String ERROR_MESSAGE = "{error.message}";
	String ERROR_NAME = "{error.name}";
	String DEFAULT_ERROR_MESSAGE = "Could not parse '"+ INPUT +"' because of "+ ERROR_NAME +": "+ ERROR_MESSAGE;
	String DEFAULT_PARSING_ERROR_MESSAGE = "Could not parse '"+ INPUT +"': "+ ERROR_MESSAGE;

	Parser<Boolean> RAW_BOOLEAN = Boolean::valueOf;
	Parser<Byte> RAW_BYTE = Byte::valueOf;
	Parser<Short> RAW_SHORT = Short::valueOf;
	Parser<Integer> RAW_INTEGER = Integer::valueOf;
	Parser<Long> RAW_LONG = Long::valueOf;
	Parser<Float> RAW_FLOAT = Float::valueOf;
	Parser<Double> RAW_DOUBLE = Double::valueOf;
	Parser<String> RAW_STRING = String::trim;

	NumberParser<Byte> SIMPLE_BYTE = new NumberParser<>(RAW_BYTE);
	NumberParser<Short> SIMPLE_SHORT = new NumberParser<>(RAW_SHORT);
	NumberParser<Integer> SIMPLE_INTEGER = new NumberParser<>(RAW_INTEGER);
	NumberParser<Long> SIMPLE_LONG = new NumberParser<>(RAW_LONG);
	NumberParser<Float> SIMPLE_FLOAT = new NumberParser<>(RAW_FLOAT);
	NumberParser<Double> SIMPLE_DOUBLE = new NumberParser<>(RAW_DOUBLE);

	NumberParser<Byte> POSITIVE_BYTE = new NumberParser<>(RAW_BYTE, (byte) 0);
	NumberParser<Short> POSITIVE_SHORT = new NumberParser<>(RAW_SHORT, (short) 0);
	NumberParser<Integer> POSITIVE_INTEGER = new NumberParser<>(RAW_INTEGER, 0);
	NumberParser<Long> POSITIVE_LONG = new NumberParser<>(RAW_LONG, 0L);
	NumberParser<Float> POSITIVE_FLOAT = new NumberParser<>(RAW_FLOAT, 0f);
	NumberParser<Double> POSITIVE_DOUBLE = new NumberParser<>(RAW_DOUBLE, 0d);

	StringParser SIMPLE_STRING = new StringParser();
	ListParser<String> SIMPLE_LIST = new ListParser<>(RAW_STRING);
	MapParser<String, String> SIMPLE_MAP = new MapParser<>(RAW_STRING, RAW_STRING);

	DeepListParser DEEP_LIST = new DeepListParser();
	DeepMapParser DEEP_MAP = new DeepMapParser();

	Parser<Character> SIMPLE_CHAR = (s) ->
	{
		if (s == null || s.length() != 1)
			throw new ParsingException("Expected a single character, not: "+ s);

		return s.charAt(0);
	};

	static ParsingException produceException(String message, String input) { return new ParsingException(message.replace(INPUT, input)); }
	static ParsingException produceException(String message, String input, Exception e)
	{
		return new ParsingException(message.replace(ERROR_MESSAGE, e.getMessage())
				.replace(ERROR_NAME, e.getClass().getSimpleName())
				.replace(INPUT, input));
	}
}
