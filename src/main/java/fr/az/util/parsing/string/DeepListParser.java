package fr.az.util.parsing.string;

import static fr.az.util.misc.Regex.LIST;
import static fr.az.util.misc.Regex.MAP;

import java.util.List;
import java.util.function.Supplier;

import fr.az.util.parsing.ParsingException;

public class DeepListParser extends ListParser<Object>
{
	private static final long serialVersionUID = -6356813081030230688L;

	public DeepListParser() { super(o -> o); }
	public DeepListParser(Supplier<List<Object>> listSupplier) { super(o -> o, listSupplier); }

	@Override
	protected Object parseElement(String element) throws ParsingException
	{
		if (LIST.matcher(element).matches())
			return DEEP_LIST.parse(element);

		if (MAP.matcher(element).matches())
			return DEEP_MAP.parse(element);

		return element.trim();
	}
}
