package fr.az.util.parsing.property;

import fr.az.util.parsing.ParsingException;
import fr.az.util.parsing.string.Parser;

public class PropertyBuilder<T>
{
	private final String id;
	private final T value;
	private final Class<T> clazz;
	private final Parser<T> parser;

	public PropertyBuilder(String id, T value, Class<T> valueClass, Parser<T> parser)
	{
		this.id = id;
		this.value = value;
		this.clazz = valueClass;
		this.parser = parser;
	}

	public Property<T> buildDefault() { return this.build(this.getDefaultValue()); }
	public Property<T> buildRaw(String value) throws ParsingException
	{
		try { return this.build(this.parser.parse(value)); }
		catch (ParsingException e) { throw new ParsingException("At property '"+ this.id +"': "+ e.getMessage()); }
	}

	public Property<T> build(T value) { return new Property<>(this.id, value, this.clazz);	}

	public String getId() { return this.id; }
	public T getDefaultValue() { return this.value; }
	public Parser<T> getParser() { return this.parser; }
}
