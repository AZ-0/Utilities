package fr.az.util.parsing.string;

import static fr.az.util.misc.Regex.COUPLE_ELEMENT;
import static fr.az.util.misc.Regex.ELEMENT;
import static fr.az.util.misc.Regex.LIST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.Matcher;

import fr.az.util.misc.Regex;
import fr.az.util.misc.tuples.Tuple2;
import fr.az.util.parsing.ParsingException;

public class MapParser<K, V> implements Parser<Map<K, V>>
{
	private static final long serialVersionUID = 5528774769658238786L;

	public static final String INPUT = "{input}";
	public static final String DEFAULT_ERROR_MESSAGE = "The string '"+ INPUT +"' is not a map";

	public static final <K,V> Supplier<Map<K,V>> defaultSupplier() { return HashMap<K,V>::new; }

	private Parser<K> keyParser;
	private Parser<V> valueParser;
	private String error;
	private Supplier<Map<K,V>> map;

	public MapParser(Parser<K> keyParser, Parser<V> valueParser) { this(keyParser, valueParser, DEFAULT_ERROR_MESSAGE); }

	public MapParser(Parser<K> keyParser, Parser<V> valueParser, String errorMessage) {
		this(keyParser, valueParser, MapParser.defaultSupplier(), errorMessage); }

	public MapParser(Parser<K> keyParser, Parser<V> valueParser, Supplier<Map<K,V>> mapSupplier) {
		this(keyParser, valueParser, mapSupplier, DEFAULT_ERROR_MESSAGE); }

	public MapParser(Parser<K> keyParser, Parser<V> valueParser, Supplier<Map<K,V>> mapSupplier, String errorMessage)
	{
		this.keyParser = keyParser;
		this.error = errorMessage;
		this.map = mapSupplier;
	}

	@Override
	public Map<K, V> parse(String from) throws ParsingException
	{
		if (!from.matches("^"+ LIST +"$"))
			throw new ParsingException(this.error.replace(INPUT, from));

		Map<K,V> map = this.map.get();
		List<String> couples = new ArrayList<>();

		Matcher extractor = COUPLE_ELEMENT.matcher(from.substring(1, from.length() -1));
		while (extractor.find())
			couples.add(extractor.group());

		try
		{
			for (String couple : couples)
			{
				extractor = COUPLE_ELEMENT.matcher(couple);
				map.put(this.parseKey(Regex.unquote(extractor.group("key"))), this.parseValue(Regex.unquote(extractor.group("value"))));
			}
		}
		catch (ParsingException e) {
			throw new ParsingException("in "+ Regex.quote(from) +": "+ e.getMessage()); }

		return map;
	}

	protected Tuple2<K, V> parseCouple(String couple) throws ParsingException
	{
		Matcher matcher = ELEMENT.matcher(couple);
		matcher.find(); String key = matcher.group();
		matcher.find(); String value = matcher.group();

		return new Tuple2<>(this.parseKey(Regex.unquote(key)), this.parseValue(Regex.unquote(value)));
	}

	protected K parseKey(String key) throws ParsingException { return this.keyParser.parse(key); }
	protected V parseValue(String value) throws ParsingException { return this.valueParser.parse(value); }

	public Parser<K> getKeyParser() { return this.keyParser; }
	public Parser<V> getValueParser() { return this.valueParser; }
	public String getErrorMessage() { return this.error; }
	public Supplier<Map<K,V>> getMapSupplier() { return this.map; }
}
