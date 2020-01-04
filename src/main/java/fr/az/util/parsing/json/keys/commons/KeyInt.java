package fr.az.util.parsing.json.keys.commons;

import fr.az.util.parsing.json.JSONParsingException;

public class KeyInt extends KeyNumber<Integer>
{
	private static final long serialVersionUID = -7425712489952936145L;

	public static final KeyInt KEY = new KeyInt();

	public KeyInt() {}
	public KeyInt(Integer min) { super(min); }
	public KeyInt(Integer min, Integer max) { super(min, max); }

	public KeyInt(String name) { super(name); }
	public KeyInt(String name, Integer min) { super(name, min); }
	public KeyInt(String name, Integer min, Integer max) { super(name, min, max); }

	@Override
	public Integer parse(Number n) throws JSONParsingException
	{
		return this.check(n).intValue();
	}
}
