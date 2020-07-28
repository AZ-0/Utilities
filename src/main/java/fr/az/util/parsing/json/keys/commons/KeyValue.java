package fr.az.util.parsing.json.keys.commons;

import fr.az.util.parsing.json.keys.Key;

/**
 * A mother class for very simples types, such as raw types or {@linkplain String}.
 * These classes parse nothing, as they return what they get. By convention, classes directly extending {@linkplain KeyValue}
 * or {@linkplain KeyNumber} are named "value" and are intended to be extended from rather than used as they are, tough the latter is possible.
 * @author A~Z
 *
 * @param <T> an {@linkplain Object} type
 */
public class KeyValue<T extends Object> implements Key<T, T>
{
	private static final long serialVersionUID = 6047427904199278229L;

	public static final KeyValue<Object> KEY = new KeyValue<>(Object.class);

	private final Class<T> expected;
	private final String name;

	public KeyValue(Class<T> expected)
	{
		this(expected, "value");
	}

	public KeyValue(Class<T> expected, String name)
	{
		this.expected = expected;
		this.name = name;
	}

	@Override public T parse(T t) { return t; }
	@Override public Class<T> expectedType() { return this.expected; }
	@Override public String getKey() { return this.name; }
}
