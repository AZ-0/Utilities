package fr.az.util.parsing.string;

import static fr.az.util.misc.Regex.LIST;
import static fr.az.util.misc.Regex.MAP;

import java.util.Map;
import java.util.function.Supplier;

import fr.az.util.parsing.ParsingException;

public class DeepMapParser extends MapParser<Object, Object>
{
	private static final long serialVersionUID = 8665987487535567512L;

	public DeepMapParser() { super(null, null); }
	public DeepMapParser(String errorMessage) { super(null, null, errorMessage); }
	public DeepMapParser(Supplier<Map<Object, Object>> mapSupplier) { super(null, null, mapSupplier); }
	public DeepMapParser(Supplier<Map<Object, Object>> mapSupplier, String errorMessage) { super(null, null, mapSupplier, errorMessage); }

	@Override protected Object parseKey(String key) throws ParsingException { return this.parseElement(key); }
	@Override protected Object parseValue(String value) throws ParsingException { return this.parseElement(value); }

	protected Object parseElement(String element) throws ParsingException
	{
		if (LIST.matcher(element).matches())
			return DEEP_LIST.parse(element);

		if (MAP.matcher(element).matches())
			return DEEP_MAP.parse(element);

		return element.trim();
	}
}
