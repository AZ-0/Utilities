package fr.az.util.parsing.json.keys.commons;

import fr.az.util.parsing.json.JSONParsingException;

public class KeyLong extends KeyNumber<Long>
{
	private static final long serialVersionUID = -2579544107766099945L;

	public static final KeyLong KEY = new KeyLong();

	public KeyLong() {}
	public KeyLong(Long min) { super(min); }
	public KeyLong(Long min, Long max) { super(min, max); }

	public KeyLong(String name) { super(name); }
	public KeyLong(String name, Long min) { super(name, min); }
	public KeyLong(String name, Long min, Long max) { super(name, min, max); }

	@Override
	public Long parse(Number n) throws JSONParsingException {
		return this.check(n).longValue(); }
}
