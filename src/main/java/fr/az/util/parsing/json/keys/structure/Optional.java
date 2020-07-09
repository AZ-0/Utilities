package fr.az.util.parsing.json.keys.structure;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

import fr.az.util.parsing.json.JSONParsingException;
import fr.az.util.parsing.json.keys.Key;
import fr.az.util.parsing.json.keys.types.ObjectKey;

@SuppressWarnings("rawtypes")
public class Optional extends Structure
{
	private static final long serialVersionUID = 6380392168153293796L;

	private boolean fillCascade;
	private boolean retrieveCascade;

	public Optional(Key... keys)
	{
		this(true, false, keys);
	}

	public Optional(Collection<? extends Key> keys)
	{
		this(true, false, keys);
	}

	public Optional(boolean fillCascade, boolean retrieveCascade, Key... keys)
	{
		this(fillCascade, retrieveCascade, Arrays.asList(keys));
	}

	public Optional(boolean fillCascade, boolean retrieveCascade, Collection<? extends Key> keys)
	{
		super(keys);
		this.fillCascade = fillCascade;
		this.retrieveCascade = retrieveCascade;
	}

	@Override
	public void process(ObjectKey<?> parser, JSONObject source, Set<String> parsed, Map<Key, Object> cascade) throws JSONParsingException
	{
		Map<Key, Object> values = this.values();

		//Save values for cascade
		if (this.fillCascade)
			cascade.putAll(values);

		//Retrieve values from cascade
		if (this.retrieveCascade)
			for (Key k : parser.getCascadeKeys())
				if (cascade.containsKey(k))
					values.putIfAbsent(k, cascade.get(k));
	}
}
