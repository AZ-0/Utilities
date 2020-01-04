package fr.az.util.parsing.json.keys.commons.array;

import fr.az.util.parsing.IParser;
import fr.az.util.parsing.json.keys.commons.KeyLong;

public class LongArrayKey extends NumberArrayKey<Long>
{
	private static final long serialVersionUID = -1026938008451165418L;

	public static LongArrayKey KEY = new LongArrayKey("value");

	public LongArrayKey(String name) { this(name, n -> n.longValue()); }
	public LongArrayKey(String name, IParser<Number, Long, ?> parser) { super(name, parser); }

	public LongArrayKey(String name, Long min) { super(name, new KeyLong(min)); }
	public LongArrayKey(String name, Long min, Long max) { super(name, new KeyLong(min, max)); }
}
