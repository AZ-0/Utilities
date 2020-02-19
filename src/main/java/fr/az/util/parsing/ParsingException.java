package fr.az.util.parsing;

/**
 * This is used to define exception occuring while parsing
 * @author A~Z
 */
public class ParsingException extends Exception
{
	private static final long serialVersionUID = -2248076085412390511L;

	private boolean caughtParsing;

	/**
	 * Constructor
	 * @param reason
	 * @see Throwable#Throwable(Throwable)
	 */
	public ParsingException(String reason)
	{
		super(reason);
		this.caughtParsing = true;
	}

	/**
	 * Constructor
	 * @param t the Throwable
	 * @see Throwable#Throwable(Throwable)
	 */
	public ParsingException(Throwable t)
	{
		super(t);
	}

	@Override
	public Throwable initCause(Throwable throwable)
	{
		if (throwable instanceof ParsingException)
			this.caughtParsing = true;

		return super.initCause(throwable);
	}

	/**
	 * @return wether the cause of this exception is a ParsingException, or true if this exception has no cause.
	 */
	public boolean hasCaughtParsingException() { return this.caughtParsing; }
}
