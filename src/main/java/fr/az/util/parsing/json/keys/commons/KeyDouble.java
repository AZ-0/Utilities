package fr.az.util.parsing.json.keys.commons;

import fr.az.util.parsing.json.JSONParsingException;

public class KeyDouble extends KeyNumber<Double>
{
	private static final long serialVersionUID = 3688347908963408218L;

	public static final KeyDouble KEY = new KeyDouble();

	public KeyDouble() {}
	public KeyDouble(Double min) { super(min); }
	public KeyDouble(Double min, Double max) { super(min, max); }

	public KeyDouble(String name) { super(name); }
	public KeyDouble(String name, Double min) { super(name, min); }
	public KeyDouble(String name, Double min, Double max) { super(name, min, max); }

	@Override
	public Double parse(Number n) throws JSONParsingException {
		return this.check(n).doubleValue(); }
}
