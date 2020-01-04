package fr.az.util.parsing.json.keys.commons.array;

import fr.az.util.parsing.IParser;
import fr.az.util.parsing.json.keys.commons.KeyFloat;

public class FloatArrayKey extends NumberArrayKey<Float>
{
	private static final long serialVersionUID = -1789212283446168159L;

	public static final FloatArrayKey KEY = new FloatArrayKey("value");

	public FloatArrayKey(String name) { this(name, n -> n.floatValue()); }
	public FloatArrayKey(String name, IParser<Number, Float, ?> parser) { super(name, parser); }

	public FloatArrayKey(String name, Float min) { super(name, new KeyFloat(min)); }
	public FloatArrayKey(String name, Float min, Float max) { super(name, new KeyFloat(min, max)); }
}