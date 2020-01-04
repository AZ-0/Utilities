package fr.az.util.parsing.json.keys.commons.array;

import fr.az.util.parsing.IParser;
import fr.az.util.parsing.json.JSONParsingException;
import fr.az.util.parsing.json.keys.types.ArrayKey.AbstractArrayKey;

public class SimpleArrayKey<E, O> extends AbstractArrayKey<E, O>
{
	private static final long serialVersionUID = 5609020547991672229L;

	public static final SimpleArrayKey<Object, Object> KEY = new SimpleArrayKey<>("value", o -> o);

	private final String name;
	protected IParser<E, O, ?> parser;

	public SimpleArrayKey(String name, IParser<E, O, ?> parser)
	{
		this.name = name;
		this.parser = parser;
	}

	@Override
	public O parseSingle(E input) throws JSONParsingException
	{
		try
		{
			return this.parser.parse(input);
		}
		catch (Throwable t)
		{
			throw new JSONParsingException(this, "Could not parse '"+ input +"': "+ t.getClass().getSimpleName() +": "+ t.getMessage());
		}
	}

	@Override public String getKey() { return this.name; }
}