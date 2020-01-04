package fr.az.util.parsing.property;

public class Property<T>
{
	private final String id;
	private T value;
	private Class<T> clazz;

	Property(String id, T value, Class<T> valueClass)
	{
		this.id = id;
		this.value = value;
		this.clazz = valueClass;
	}

	@SuppressWarnings("unchecked")
	protected void setValue(Property<?> property)
	{
		if (this.clazz.isInstance(property.value))
			this.value = (T) property.value;
	}

	protected void setValue(T value) { this.value = value; }

	public String getID() { return this.id; }
	public T getValue() { return this.value; }
}
