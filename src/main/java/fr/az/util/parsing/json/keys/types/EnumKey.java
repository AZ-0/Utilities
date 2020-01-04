package fr.az.util.parsing.json.keys.types;

import fr.az.util.parsing.json.JSONParsingException;
import fr.az.util.parsing.json.keys.Key;

/**
 * Parse an Enum value according to its name
 * @author A~Z
 *
 * @param <E> the Enum type
 */
public interface EnumKey<E extends Enum<E>> extends Key<String, E>
{
	/**
	 * Parse the Enum value
	 */
	@Override
	default E parse(String name) throws JSONParsingException
	{
		E[] array = this.getValues();

		for (E element : array)
			if (this.ignoreCase())
			{
				if (element.name().equals(name))
					return element;
			} else if (element.name().equalsIgnoreCase(name))
				return element;

		throw new JSONParsingException(this, "Unknown enum constant: "+ name);
	}

	/**
	 * @return the E {@linkplain Class}
	 */
	E[] getValues();

	/**
	 * Wether this EnumKey ignore the case of provided argument
	 * @return {@code true} by default
	 */
	default boolean ignoreCase() { return true; }
}
