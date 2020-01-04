package fr.az.util.parsing.json.keys.commons;

import fr.az.util.parsing.json.JSONParsingException;

public class KeyShort extends KeyNumber<Short>
{
	private static final long serialVersionUID = 9111192911516938132L;

	public static final KeyShort KEY = new KeyShort();

	public KeyShort() {}
	public KeyShort(Short min) { super(min); }
	public KeyShort(Short min, Short max) { super(min, max); }

	public KeyShort(String name) { super(name); }
	public KeyShort(String name, Short min) { super(name, min); }
	public KeyShort(String name, Short min, Short max) { super(name, min, max); }

	@Override
	public Short parse(Number n) throws JSONParsingException {
		return this.check(n).shortValue(); }
}
