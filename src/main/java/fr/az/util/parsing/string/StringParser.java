package fr.az.util.parsing.string;

import fr.az.util.parsing.ParsingException;

public class StringParser implements Parser<String>
{
	private static final long serialVersionUID = -4940336454011117198L;

	public static final String INPUT = "{input}";
	public static final String REGEX = "{regex}";

	public static final String DEFAULT_REGEX = ".*";
	public static final String DEFAULT_ERROR_MESSAGE = "The string '"+ INPUT +"' doesn't match regex '"+ REGEX +'\'';
	public static final boolean DEFAULT_TRIM = true;

	private final String regex;
	private final String error;
	private final boolean trim;

	public StringParser() { this(DEFAULT_REGEX); }
	public StringParser(String regex) { this(regex, DEFAULT_ERROR_MESSAGE); }
	public StringParser(String regex, String errorMessage) { this(regex, errorMessage, DEFAULT_TRIM); }

	public StringParser(boolean trim) { this(DEFAULT_REGEX, DEFAULT_TRIM); }
	public StringParser(String regex, boolean trim) { this(regex, DEFAULT_ERROR_MESSAGE, trim); }
	public StringParser(String regex, String errorMessage, boolean trim)
	{
		this.regex = regex;
		this.error = errorMessage.replace(REGEX, regex);
		this.trim = trim;
	}

	@Override
	public String parse(String from) throws ParsingException
	{
		if (this.trim)
			from = from.trim();

		if (!from.matches(this.regex))
			throw new ParsingException(this.error.replace(INPUT, from));

		return from;
	}

	public String getRegex() { return this.regex; }
	public String getError() { return this.error; }
	public boolean getTrim() { return this.trim; }
}
