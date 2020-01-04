package fr.az.util.parsing.string;

import static fr.az.util.misc.Regex.ELEMENT;
import static fr.az.util.misc.Regex.LIST;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.regex.Matcher;

import fr.az.util.misc.Regex;
import fr.az.util.parsing.ParsingException;

public class ListParser<T> implements Parser<List<T>>
{
	private static final long serialVersionUID = -980382635190577215L;

	public static final String NOT_LIST_MESSAGE = "The string '"+ INPUT +"' is not a list";

	public static final <T> Supplier<List<T>> defaultSupplier() { return ArrayList<T>::new; }

	private final Parser<T> parser;
	private final Supplier<List<T>> list;

	public ListParser(Parser<T> elementParser) { this(elementParser, ListParser.defaultSupplier()); }
	public ListParser(Parser<T> elementParser, Supplier<List<T>> listSupplier)
	{
		this.parser = elementParser;
		this.list = listSupplier;
	}

	@Override
	public List<T> parse(String from) throws ParsingException
	{
		if (!from.matches('^'+ LIST.pattern() +'$'))
			throw Parser.produceException(NOT_LIST_MESSAGE, from);

		List<T> list = this.list.get();
		Matcher matcher = ELEMENT.matcher(from.substring(1, from.length() -1));

		try
		{
			while (matcher.find())
				list.add(this.parseElement(Regex.unquote(matcher.group())));
		}
		catch (ParsingException e) { throw Parser.produceException(DEFAULT_PARSING_ERROR_MESSAGE, from, e); }
		catch (Exception e) { throw Parser.produceException(DEFAULT_ERROR_MESSAGE, from, e); }

		return list;
	}

	protected T parseElement(String element) throws ParsingException { return this.parser.parse(element); }

	public Parser<T> getSingleElementParser() { return this.parser; }
	public Supplier<List<T>> getListSupplier() { return this.list; }
}
