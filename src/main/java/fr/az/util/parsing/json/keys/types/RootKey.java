package fr.az.util.parsing.json.keys.types;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

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
	default Set<T>	loadSet	(File root) { return this.load(root, HashSet  ::new);	  }
	default List<T> loadList(File root) { return this.load(root, ArrayList::new); }

	default <C extends Collection<T>> C load(File root, Supplier<C> collection)
	{
		File folder = new File(root, this.getFolder());
		C loaded = collection.get();

		if (folder.exists())
		{
			this.logInfo("Loading " + this.getKey() +":");

			for (File json : folder.listFiles())
			{
				this.logInfo(" - "+ json.getName());
				T parsed = this.loadFile(json.toPath());

				if (parsed != null)
					loaded.add(parsed);
			}
		} else
			this.logError("Found no folder of path: "+ folder.getAbsolutePath());

		return loaded;
	}

	default T loadFile(Path file)
	{
		try
		{
			String content = String.join("\n", Files.readAllLines(file));
			return this.parse(content, file.getFileName().toString());
		}
		catch (IOException e)
		{
			this.logError("Failed with exception: "+ e.getClass().getSimpleName());
			return null;
		}
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
			this.handleException(new JSONParsingException(this, "Malformated JSON: "+ e.getMessage()), file);
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
		catch (JSONParsingException e) { this.handleException(e, file); }
		catch (Throwable t) { this.handleException(new JSONParsingException(this, t), file); }

		return null;
	}

	void handleException(JSONParsingException e, String file);
	void logInfo(String info);
	void logError(String error);
	String getFolder();
}
