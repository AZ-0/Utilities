package fr.az.util.parsing.json.keys.commons;

public class KeyBoolean extends KeyValue<Boolean>
{
	private static final long serialVersionUID = -4929779934137775218L;

	public static final KeyBoolean KEY = new KeyBoolean();

	public KeyBoolean()				{ super(Boolean.class); }
	public KeyBoolean(String name)	{ super(Boolean.class, name); }
}
