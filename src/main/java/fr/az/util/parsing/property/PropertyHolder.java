package fr.az.util.parsing.property;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class PropertyHolder implements Serializable
{
	private static final long serialVersionUID = -6500477573475456792L;

	private final List<PropertyBuilder<?>> propertyBuilders;
	private final HashMap<String, Property<?>> properties = new HashMap<>();

	public PropertyHolder(PropertyBuilder<?>... propertyBuilders) { this.propertyBuilders = Arrays.asList(propertyBuilders); }
	public PropertyHolder(List<PropertyBuilder<?>> propertyBuilders) { this.propertyBuilders = Collections.unmodifiableList(propertyBuilders); }

	public abstract void updateProperties();

	protected void setProperties(Collection<Property<?>> properties) { properties.forEach(this::setProperty); }
	protected void setProperty(Property<?> property)
	{
		Property<?> target = this.properties.putIfAbsent(property.getID(), property);

		if (target != null)
			target.setValue(property);
	}

	@SuppressWarnings("unchecked")
	public <T> Property<T> getProperty(String id) { return (Property<T>) this.properties.get(id); }
	public Map<String, Property<?>> getProperties() { return Collections.unmodifiableMap(this.properties); }
	protected List<PropertyBuilder<?>> getPropertyBuilders() { return this.propertyBuilders; }
}
