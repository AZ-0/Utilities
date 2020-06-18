package fr.az.util.parsing.json.keys.commons;

import java.util.function.Predicate;
import java.util.regex.Pattern;

import fr.az.util.parsing.json.JSONParsingException;
import fr.az.util.parsing.json.keys.Key;

public class KeyString implements Key<String, String>
{
	private static final long serialVersionUID = 2114108507097938539L;
	private static final Pattern DEFAULT_PATTERN = Pattern.compile(".*");
	private static final String DEFAULT_NAME = "value";

	public static KeyString KEY = new KeyString();

	private final String name;
	private final String pattern;
	private final Predicate<String> regex;

	public KeyString() { this(DEFAULT_NAME, DEFAULT_PATTERN); }
	public KeyString(String name) { this(name, DEFAULT_PATTERN); }
	public KeyString(Pattern regex) { this(DEFAULT_NAME, regex); }
	public KeyString(String name, Pattern regex)
	{
		this.name = name;
		this.pattern = regex.pattern();
		this.regex = regex.asPredicate();
	}

	@Override
	public String parse(String from) throws JSONParsingException
	{
		from = from.trim();

		if (!this.regex.test(from))
			throw new JSONParsingException(this, "The input character chain should match " + this.pattern);

		return from;
	}

	@Override
	public String getKey() { return this.name; }
	public String getPattern() { return this.pattern; }
	public Predicate<String> getRegex() { return this.regex; }
}
