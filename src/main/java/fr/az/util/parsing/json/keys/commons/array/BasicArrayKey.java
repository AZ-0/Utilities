package fr.az.util.parsing.json.keys.commons.array;

import fr.az.util.parsing.IParser;

public class BasicArrayKey<T> extends SimpleArrayKey<T, T>
{
	private static final long serialVersionUID = -4605922963017090623L;

	public static final BasicArrayKey<Object> KEY = new BasicArrayKey<>("value");

	public BasicArrayKey(String name)
	{
		super(name, t -> t);
	}

	public BasicArrayKey(String name, IParser<T, ? extends T, ?> parser)
	{
		super(name, t -> parser.parse(t));
	}
}
