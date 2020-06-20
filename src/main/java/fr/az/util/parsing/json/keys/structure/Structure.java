package fr.az.util.parsing.json.keys.structure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

import fr.az.util.parsing.json.JSONParsingException;
import fr.az.util.parsing.json.keys.Key;
import fr.az.util.parsing.json.keys.types.CascadingKey;
import fr.az.util.parsing.json.keys.types.ObjectKey;

@SuppressWarnings("rawtypes")
public abstract class Structure implements Serializable
{
	private static final long serialVersionUID = -6165099490743167451L;

	private final List<Key> keys; //Immutable
	private final Map<Key, Object> values = new HashMap<>(); //Mutable

	public Structure(Key... keys)
	{
		this.keys = Arrays.asList(keys);
	}

	public Structure(Collection<? extends Key> keys)
	{
		this.keys = Collections.unmodifiableList(new ArrayList<>(keys));
	}

	public abstract void process(ObjectKey<?> parser, JSONObject source, Set<String> parsed, Map<Key, Object> cascade) throws JSONParsingException;

	/**
	 * @return an immutable list of this Structure's key
	 */
	public List<Key> getKeys() { return this.keys; }

	/**
	 * @return a mutable map of this Structure's parsing result. Any change on the returned map reflects in the Structure
	 */
	public Map<Key, Object> getValues() { return this.values; }

	/**
	 * @return wether this structure's keys are registered for the cascade
	 * @see CascadingKey
	 */
	public boolean isCascading() { return false; }
}
