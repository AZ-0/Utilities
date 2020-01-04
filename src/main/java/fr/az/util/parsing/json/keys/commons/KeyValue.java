package fr.az.util.parsing.json.keys.commons;

import fr.az.util.parsing.json.keys.Key.AbstractKey;

/**
 * A mother class for very simples types, such as raw types or {@linkplain String}.
 * These classes parse nothing, as they return what they get. By convention, classes directly extending {@linkplain KeyValue}
 * or {@linkplain KeyNumber} are named "value" and are intended to be extended from rather than used as they are, tough the latter is possible.
 * @author A~Z
 *
 * @param <T> an {@linkplain Object} type
 */
public class KeyValue<T extends Object> extends AbstractKey<T, T>
{
	private static final long serialVersionUID = 6047427904199278229L;

	public static final KeyValue<Object> KEY = new KeyValue<>();

	private final String name;

	public KeyValue() { this("value"); }
	public KeyValue(String name) { this.name = name; }

	@Override public T parse(T t) { return t; }
	@Override public String getKey() { return this.name; }
}
