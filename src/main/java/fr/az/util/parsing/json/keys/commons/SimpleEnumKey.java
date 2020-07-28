package fr.az.util.parsing.json.keys.commons;

import java.util.function.Supplier;

import fr.az.util.parsing.json.keys.types.EnumKey;

public class SimpleEnumKey<E extends Enum<E>> implements EnumKey<E>
{
	private static final long serialVersionUID = -8241732881464993850L;

	private final Supplier<E[]> values;
	private final String name;
	private final boolean ignoreCase;

	public SimpleEnumKey(Class<E> enumClass, String name)
	{
		this(enumClass, name, true);
	}

	public SimpleEnumKey(Class<E> enumClass, String name, boolean ignoreCase)
	{
		this(enumClass::getEnumConstants, name, ignoreCase);
	}

	public SimpleEnumKey(E[] values, String name)
	{
		this(values, name, true);
	}

	public SimpleEnumKey(E[] values, String name, boolean ignoreCase)
	{
		this(() -> values, name, ignoreCase);
	}

	public SimpleEnumKey(Supplier<E[]> values, String name)
	{
		this(values, name, true);
	}

	public SimpleEnumKey(Supplier<E[]> values, String name, boolean ignoreCase)
	{
		this.values = values;
		this.name = name;
		this.ignoreCase = ignoreCase;
	}

	@Override public boolean ignoreCase() { return this.ignoreCase; }
	@Override public String name(E element) { return element.name(); }
	@Override public E[] getValues() { return this.values.get(); }
	@Override public String getKey() { return this.name; }
}
