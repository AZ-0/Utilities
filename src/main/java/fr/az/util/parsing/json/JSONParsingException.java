package fr.az.util.parsing.json;

import fr.az.util.parsing.ParsingException;
import fr.az.util.parsing.json.keys.Key;
import fr.az.util.parsing.json.keys.types.ArrayKey;
import fr.az.util.parsing.json.keys.types.ObjectKey;
import fr.az.util.parsing.json.keys.types.RootKey;

/**
 * This exception intends to catch any exception thrown whilst parsing json, as well as indicating a semantically wrong one.
 * It will be thrown and caught by each {@linkplain ObjectKey} and each {@linkplain ArrayKey} successively for the purpose of building a trace.
 * However, the handling of this exception depends entirely on the {@linkplain RootKey} that will receive its final instance.
 *
 * @see JSONParsingException#getTrace()
 * @author A~Z
 *
 */
public class JSONParsingException extends ParsingException
{
	private static final long serialVersionUID = -7251507461751933941L;

	private final Key<?,?> key;
	private final String trace;

	/**
	 * Constructs a new {@linkplain JSONParsingException}
	 * @param at the {@link Key} initializing the trace
	 * @param reason the reason this exception was thrown
	 */
	public JSONParsingException(Key<?,?> at, String reason) {
		this(at, reason, at.toString()); }

	public JSONParsingException(Key<?, ?> at, Throwable child)
	{
		this(at, child.getMessage(), at.toString());
		child.printStackTrace();
	}

	/**
	 * Constructs a new {@linkplain JSONParsingException}
	 * @param at the new root of the trace
	 * @param child a JSONMalformatedException which trace will be completed with the new root and reason conserved
	 */
	public JSONParsingException(Key<?,?> at, JSONParsingException child)
	{
		this(at, child.getMessage(), at.toString() +'.'+ child.getTrace());
	}


	private JSONParsingException(Key<?,?> at, String reason, String trace)
	{
		super(reason);
		this.key = at;
		this.trace = trace;
	}

	/**
	 * @see JSONParsingException#getTrace()
	 * @return the key of this exception, namely the root of the trace
	 */
	public Key<?,?> getKey() { return this.key; }

	/**
	 * @return the exception trace, in the format of a JSONPointer
	 */
	public String getTrace() { return this.trace; }
}
