package fr.az.util.parsing.json.keys.commons;

import fr.az.util.parsing.json.JSONParsingException;

public class KeyByte extends KeyNumber<Byte>
{
	private static final long serialVersionUID = 3907621503010420564L;

	public static final KeyByte KEY = new KeyByte();

	public KeyByte() {}
	public KeyByte(Byte min) { super(min); }
	public KeyByte(Byte min, Byte max) { super(min, max); }

	public KeyByte(String name) { super(name); }
	public KeyByte(String name, Byte min) { super(name, min); }
	public KeyByte(String name, Byte min, Byte max) { super(name, min, max); }

	@Override
	public Byte parse(Number n) throws JSONParsingException {
		return this.check(n).byteValue(); }
}
