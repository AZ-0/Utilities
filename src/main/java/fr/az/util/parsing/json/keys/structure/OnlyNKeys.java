package fr.az.util.parsing.json.keys.structure;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

import fr.az.util.parsing.json.JSONParsingException;
import fr.az.util.parsing.json.keys.Key;
import fr.az.util.parsing.json.keys.types.ObjectKey;

@SuppressWarnings("rawtypes")
public class OnlyNKeys extends Structure
{
	private static final long serialVersionUID = 8460630789280452249L;

	private int amount;
	private String errorMessage;

	{
		this.updateErrorMessage();
	}

	public OnlyNKeys(int amount, Key... keys)
	{
		super(keys);
		this.amount = amount;
	}

	public OnlyNKeys(int amount, Collection<Key> keys)
	{
		super(keys);
		this.amount = amount;
	}

	@Override
	public void process(ObjectKey<?> parser, JSONObject source, Set<String> parsed, Map<Key, Object> cascade) throws JSONParsingException
	{
		if (this.values().size() != this.amount)
			throw new JSONParsingException(parser, this.errorMessage + this.keys());
	}

	private void updateErrorMessage()
	{
		this.errorMessage = "Expected exactly "+ this.amount +" key"+ (this.amount != 1 ? "s" : "") +" from this list: ";
	}

	public void setAmount(int amount) { this.amount = amount; this.updateErrorMessage(); }
	public int getAmount() { return this.amount; }
}
