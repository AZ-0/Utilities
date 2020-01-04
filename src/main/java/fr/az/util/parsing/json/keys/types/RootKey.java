package fr.az.util.parsing.json.keys.types;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	static <T> List<T> load(File rootFile, RootKey<T> root)
	{
		File folder = new File(rootFile.getAbsolutePath() + File.separatorChar + root.getFolder());
		List<T> loaded = new ArrayList<>();

		if (folder.exists())
		{
			root.logInfo("§6Loading " + root.getKey() +":");
			for (File json : folder.listFiles())
			{
				root.logInfo("§b - "+ json.getName());
				try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(json))))
				{
					T result = root.parse(reader.lines().collect(Collectors.joining("\n")), json.getName());
					if (result != null) loaded.add(result);
				} catch (IOException e) { root.logError("&4Failed with exception: "+ e.getClass().getSimpleName()); }
			}
		} else
			root.logError("§4Found no folder of path: "+ folder.getPath());

		return loaded;
	}

	default List<T> load(File rootFile) { return RootKey.load(rootFile, this); }

	/**
	 * Effectively calls {@linkplain RootKey#parse(JSONObject)} with a {@linkplain JSONObject} instanciated from the specified content.
	 * @see RootKey#parse(JSONObject, boolean)
	 * @param content a valid {@linkplain String} representation of a JSONObject
	 * @param suppressExceptions if true, does nothing upon {@linkplain JSONParsingException} throwing
	 * @return T
	 */
	default T parse(String content, String file)
	{
		try { return this.parse(new JSONObject(content), file); }
		catch (JSONException e) { this.handleException(new JSONParsingException(this, "Malformated JSON: "+ e.getMessage()), file); }

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
		try { return this.parse(content); }
		catch (JSONParsingException e) { this.handleException(e, file); }
		catch (Exception e) { this.handleException(new JSONParsingException(this, e.getMessage()), file); }

		return null;
	}

	void handleException(JSONParsingException e, String file);
	void logInfo(String info);
	void logError(String error);
	String getFolder();
}
