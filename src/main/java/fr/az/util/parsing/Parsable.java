package fr.az.util.parsing;

import java.io.Serializable;

/**
 * This interface simply defines an Object that has a valid String representation, hence being parsable from said String.
 * @author A~Z
 */
@FunctionalInterface
public interface Parsable extends Serializable
{
	/**
	 * @return a valid String representation of this Object
	 */
	String asParsableString();
}
