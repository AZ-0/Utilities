package fr.az.util.parsing.json.keys.commons.array;

import fr.az.util.parsing.IParser;
import fr.az.util.parsing.json.keys.commons.KeyByte;

public class ByteArrayKey extends NumberArrayKey<Byte>
{
	private static final long serialVersionUID = -3230529071736255871L;

	public static final ByteArrayKey KEY = new ByteArrayKey("value");

	public ByteArrayKey(String name) { this(name, n -> n.byteValue()); }
	public ByteArrayKey(String name, IParser<Number, Byte, ?> parser) { super(name, parser); }

	public ByteArrayKey(String name, Byte min) { super(name, new KeyByte(min)); }
	public ByteArrayKey(String name, Byte min, Byte max) { super(name, new KeyByte(min, max)); }
}
