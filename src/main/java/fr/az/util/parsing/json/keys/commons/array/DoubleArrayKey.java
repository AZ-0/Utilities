package fr.az.util.parsing.json.keys.commons.array;

import fr.az.util.parsing.IParser;
import fr.az.util.parsing.json.keys.commons.KeyDouble;

public class DoubleArrayKey extends NumberArrayKey<Double>
{
	private static final long serialVersionUID = -8481367067392194932L;

	public static final DoubleArrayKey KEY = new DoubleArrayKey("value");

	public DoubleArrayKey(String name) { this(name, n -> n.doubleValue()); }
	public DoubleArrayKey(String name, IParser<Number, Double, ?> parser) { super(name, parser); }

	public DoubleArrayKey(String name, Double min) { super(name, new KeyDouble(min)); }
	public DoubleArrayKey(String name, Double min, Double max) { super(name, new KeyDouble(min, max)); }
}
