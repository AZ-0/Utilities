package fr.az.util.parsing.json.keys.structure;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

import fr.az.util.parsing.json.JSONParsingException;
import fr.az.util.parsing.json.keys.Key;
import fr.az.util.parsing.json.keys.types.ObjectKey;

@SuppressWarnings("rawtypes")
public class Mandatory extends Structure
{
	private static final long serialVersionUID = 6286436828362666538L;

	public Mandatory(Key... keys) { super(keys); }
	public Mandatory(Collection<? extends Key> keys) { super(keys); }

	@Override
	public void process(ObjectKey<?> parser, JSONObject source, Set<String> parsed, Map<Key, Object> cascade) throws JSONParsingException
	{
		for (Key key : this.getKeys())
			if (!parsed.contains(key.getKey()))
				throw new JSONParsingException(parser, "Missing mandatory key: "+ key.getKey());
	}
}
