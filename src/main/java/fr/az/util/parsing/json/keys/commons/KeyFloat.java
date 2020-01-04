package fr.az.util.parsing.json.keys.commons;

import fr.az.util.parsing.json.JSONParsingException;

public class KeyFloat extends KeyNumber<Float>
{
	private static final long serialVersionUID = 1306150167351744617L;

	public final static KeyFloat KEY = new KeyFloat();

	public KeyFloat() {}
	public KeyFloat(Float min) { super(min); }
	public KeyFloat(Float min, Float max) { super(min, max); }

	public KeyFloat(String name) { super(name); }
	public KeyFloat(String name, Float min) { super(name, min); }
	public KeyFloat(String name, Float min, Float max) { super(name, min, max); }

	@Override public Float parse(Number n) throws JSONParsingException {
		return this.check(n).floatValue(); }
}
