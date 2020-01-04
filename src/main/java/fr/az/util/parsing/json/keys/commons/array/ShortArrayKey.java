package fr.az.util.parsing.json.keys.commons.array;

import fr.az.util.parsing.IParser;
import fr.az.util.parsing.json.keys.commons.KeyShort;

public class ShortArrayKey extends NumberArrayKey<Short>
{
	private static final long serialVersionUID = 4665190124876893988L;

	public static final ShortArrayKey KEY = new ShortArrayKey("value");

	public ShortArrayKey(String name) { this(name, n -> n.shortValue()); }
	public ShortArrayKey(String name, IParser<Number, Short, ?> parser) { super(name, parser); }

	public ShortArrayKey(String name, Short min) { super(name, new KeyShort(min)); }
	public ShortArrayKey(String name, Short min, Short max) { super(name, new KeyShort(min, max)); }
}
