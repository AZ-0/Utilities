package fr.az.util.parsing.json.keys.types;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.json.JSONException;
import org.json.JSONObject;

import fr.az.util.parsing.json.JSONParsingException;

/**
 * The root of a key AST.
 *
 * @author A~Z
 *
 * @param <T> the object to build
 */
public interface RootKey<T> extends ObjectKey<T>
{
	default T loadFile(Path file) throws IOException
	{
		String content = String.join("\n", Files.readAllLines(file));
		return this.parse(content, file.getFileName().toString());
	}

	/**
	 * Effectively calls {@linkplain RootKey#parse(JSONObject)} with a {@linkplain JSONObject} instanciated from the specified content.
	 * @see RootKey#parse(JSONObject, boolean)
	 * @param content a valid {@linkplain String} representation of a JSONObject
	 * @param suppressExceptions if true, does nothing upon {@linkplain JSONParsingException} throwing
	 * @return T
	 */
	default T parse(String content, String file)
	{
		try
		{
			return this.parse(new JSONObject(content), file);
		}
		catch (JSONException e)
		{
			this.onError(new JSONParsingException(this, "Malformated JSON: "+ e.getMessage()), file);
		}

		return null;
	}

	/**
	 * Build a T object from {@linkplain JSONObject}
	 * @see ObjectKey#parse(JSONObject)
	 * @param content a valid JSONObject representation of a T object
	 * @param suppressExceptions if true, does nothing upon {@linkplain JSONParsingException} throwing
	 * @return T an object recursively built from a JSONObject
	 */
	default T parse(JSONObject content, String file)
	{
		try
		{
			return this.parse(content);
		}
		catch (JSONParsingException e) { this.onError(e, file); }
		catch (Throwable t) { this.onError(new JSONParsingException(this, t), file); }

		return null;
	}

	void onError(JSONParsingException e, String file);
}
