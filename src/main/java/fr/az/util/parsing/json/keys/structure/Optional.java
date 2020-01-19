package fr.az.util.parsing.json.keys.structure;

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

	public Optional(Key... keys) { super(keys); }
	public Optional(Collection<Key> keys) { super(keys); }

	@Override
	public void process(ObjectKey<?> parser, JSONObject source, Set<String> parsed, Map<Key, Object> cascade) throws JSONParsingException
	{
		Map<Key, Object> values = this.getValues();

		//Save values for cascade
		cascade.putAll(values);

		//Retrieve values from cascade
		for (Key k : parser.getCascadeKeys())
			if (!values.containsKey(k) && cascade.containsKey(k))
				values.put(k, cascade.get(k));
	}
}
