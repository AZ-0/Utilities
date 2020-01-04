package fr.az.util.parsing.json.keys.commons.array;

import fr.az.util.parsing.IParser;
import fr.az.util.parsing.json.keys.commons.KeyInt;

public class IntArrayKey extends NumberArrayKey<Integer>
{
	private static final long serialVersionUID = -1849883453711835993L;

	public static final IntArrayKey KEY = new IntArrayKey("value");

	public IntArrayKey(String name) { this(name, n -> n.intValue()); }
	public IntArrayKey(String name, IParser<Number, Integer, ?> parser) { super(name, parser); }

	public IntArrayKey(String name, Integer min) { super(name, new KeyInt(min)); }
	public IntArrayKey(String name, Integer min, Integer max) { super(name, new KeyInt(min, max)); }
}
