package fr.az.util.misc;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class Regex
{
	public static final char ELEMENT_SEPARATOR_CHAR = ',';
	public static final String ELEMENT_SEPARATOR = "("+ ELEMENT_SEPARATOR_CHAR +" ?)";

	public static final Pattern QUOTED_ELEMENT = Pattern.compile("(\".*\")");
	public static final Pattern LIST_ELEMENT = Pattern.compile("(\\[.*\\])");
	public static final Pattern MAP_ELEMENT = Pattern.compile("({.*})");
	public static final Pattern SIMPLE_ELEMENT = Pattern.compile("([^"+ ELEMENT_SEPARATOR_CHAR +"=\\[\\]{}]*)");

	public static final Pattern STRING = Pattern.compile("("+ QUOTED_ELEMENT +'|'+ SIMPLE_ELEMENT +')');
	public static final Pattern ELEMENT = Pattern.compile("("+ STRING +'|'+ LIST_ELEMENT +'|'+ MAP_ELEMENT +')');
	public static final Pattern COUPLE_ELEMENT = Pattern.compile("("+ ELEMENT +"="+ ELEMENT +')');

	public static final Pattern LIST = Pattern.compile("(\\[("+ ELEMENT +'('+ ELEMENT_SEPARATOR + ELEMENT +")*\\)?])");
	public static final Pattern MAP = Pattern.compile("({("+ COUPLE_ELEMENT +'('+ ELEMENT_SEPARATOR + COUPLE_ELEMENT +")*)?})");

	private static final LinkedHashMap<String, String> ESCAPED = new LinkedHashMap<>();

	static
	{
		ESCAPED.put("\\", "\\\\");
		ESCAPED.put("\'", "\\\'");
		ESCAPED.put("\"", "\\\"");
		ESCAPED.put("\b", "\\\b");
		ESCAPED.put("\f", "\\\f");
		ESCAPED.put("\n", "\\\n");
		ESCAPED.put("\r", "\\\r");
		ESCAPED.put("\t", "\\\t");
	}

	public static String escape(String s)
	{
		for (Entry<String, String> entry : ESCAPED.entrySet())
			s = s.replace(entry.getKey(), entry.getValue());

		return s;
	}

	public static String unescape(String s)
	{
		for (Entry<String, String> entry : ESCAPED.entrySet())
			s = s.replace(entry.getValue(), entry.getKey());

		return s;
	}

	/**
	 * Normalizes a String that is quoted, or doesn't do anything if it isn't
	 * @param s a String
	 * @return an unquoted String
	 */
	public static String unquote(String s)
	{
		return QUOTED_ELEMENT.matcher(s).matches() ? Regex.unescape(s.substring(1, s.length() -1)) : s;
	}

	/**
	 * Quotes the provided String
	 * @param string a String
	 * @return a quoted String
	 */
	public static String quote(String s)
	{
		return '"'+ Regex.escape(s) + '"';
	}
}
