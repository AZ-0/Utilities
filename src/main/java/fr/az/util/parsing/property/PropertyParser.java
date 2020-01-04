package fr.az.util.parsing.property;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.az.util.parsing.ParsingException;
import fr.az.util.parsing.SimpleParser;

public class PropertyParser implements SimpleParser<List<Property<?>>, ParsingException>
{
	private static final long serialVersionUID = 2496059234616076902L;
	private static final Pattern ID = Pattern.compile("^[a-zA-Z0-9._-]+ *: *");

	private final PropertyHolder holder;

	public PropertyParser(PropertyHolder holder)
	{
		this.holder = holder;
	}

	@Override
	public List<Property<?>> parse(String from) throws ParsingException
	{
		String[] lines = from.split("\n+");
		HashMap<String, String> extracted = new HashMap<>();

		for (String line : lines)
		{
			if (line.charAt(0) == '#')
				continue;

			Matcher m = ID.matcher(line);
			if (m.find())
			{
				String id = m.group();
				extracted.put(id.replaceAll(" *: *","").trim().trim(), line.substring(id.length()));
			} else
				throw new ParsingException("Missing property id for line: '"+ line);
		}

		HashMap<String, Property<?>> parsed = new HashMap<>();

parse:	for (PropertyBuilder<?> builder : this.holder.getPropertyBuilders())
		{
			for (Entry<String, String> line : extracted.entrySet())
				if (line.getKey().equals(builder.getId()))
				{
					parsed.put(line.getKey(), builder.buildRaw(line.getValue()));
					continue parse;
				}

			parsed.put(builder.getId(), builder.buildDefault());
		}

		for (String id : extracted.keySet())
			if (!parsed.containsKey(id))
				throw new ParsingException("Unknown property: "+ id);

		return new ArrayList<>(parsed.values());
	}
}
