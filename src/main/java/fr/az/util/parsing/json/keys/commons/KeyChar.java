package fr.az.util.parsing.json.keys.commons;

import fr.az.util.parsing.json.JSONParsingException;
import fr.az.util.parsing.json.keys.Key;

public class KeyChar implements Key<String, Character>
{
	private static final long serialVersionUID = 676471801691367854L;

	public static final KeyChar KEY = new KeyChar();

	private final String name;

	public KeyChar() { this("value"); }
	public KeyChar(String name) { this.name = name; }

	@Override
	public Character parse(String from) throws JSONParsingException
	{
		if (from.length() != 1)
			throw new JSONParsingException(this, "Expected a single character, not '" + from + '\'');

		return from.charAt(0);
	}

	@Override public String getKey() { return this.name; }
}
