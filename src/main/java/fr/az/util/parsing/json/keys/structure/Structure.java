package fr.az.util.parsing.json.keys.structure;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
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

	private final Set<Key> keys; //Immutable
	private final Map<Key, Object> values = new HashMap<>(); //Mutable

	public Structure(Key... keys)
	{
		Set<Key> keySet = new HashSet<>();

		for (Key key : keys)
			keySet.add(key);

		this.keys = Collections.unmodifiableSet(keySet);
	}

	public Structure(Collection<? extends Key> keys)
	{
		this.keys = Collections.unmodifiableSet(new HashSet<>(keys));
	}

	public abstract void process(ObjectKey<?> parser, JSONObject source, Set<String> parsed, Map<Key, Object> cascade) throws JSONParsingException;

	public void clear() { this.values.clear(); }

	/**
	 * @return an immutable list of this Structure's key
	 */
	public Set<Key> keys() { return this.keys; }

	/**
	 * @return a mutable map of this Structure's parsing result. Any change on the returned map reflects in the Structure
	 */
	public Map<Key, Object> values() { return this.values; }

	@SuppressWarnings("unchecked")
	public <K extends Key, V> Map<K, V> cast()
	{
		Map<K, V> casted = new HashMap<>();
		this.values.forEach((k, v) -> casted.put((K) k, (V) v));
		return casted;
	}

	/**
	 * @return wether this structure's keys are registered for the cascade
	 * @see CascadingKey
	 */
	public boolean isCascading() { return false; }

	@Override
	public String toString()
	{
		return this.getClass().getSimpleName() +"{keys="+ this.keys() +", values="+ this.valuesToString() +'}';
	}

	public String valuesToString()
	{
		StringBuilder builder = new StringBuilder('{');

		boolean multiple = false;

		for (Entry<Key, Object> e : this.values.entrySet())
		{
			if (multiple)
				builder.append(", ");

			multiple = true;

			builder.append(e.getKey().getKey());
			builder.append(": ");
			builder.append(e.getValue());
		}

		return builder.toString();
	}
}
