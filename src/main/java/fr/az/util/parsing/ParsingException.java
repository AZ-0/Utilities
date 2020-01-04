package fr.az.util.parsing;

/**
 * This is used to define exception occuring while parsing
 * @author A~Z
 */
public class ParsingException extends Exception
{
	private static final long serialVersionUID = -2248076085412390511L;

	public ParsingException(String reason) { super(reason); }
}
