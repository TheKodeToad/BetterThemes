package io.toadlabs.betterthemes.theme;

import java.io.*;
import java.util.*;

import com.google.gson.*;
import com.google.gson.stream.*;
import io.toadlabs.betterthemes.*;
import io.toadlabs.betterthemes.utils.*;
import org.eclipse.swt.graphics.*;

public final class ThemeData {

	private final Optional<File> file;
	private final ThemeType type;
	private final String name;
	private final Map<String, String> variables = new HashMap<>();
	private final Map<ThemeKey, ThemeStyle> properties = new HashMap<>();

	// required because resource leak
	public static ThemeData fromFile(File file) throws IOException, ThemeParseException {
		try(FileInputStream in = new FileInputStream(file)) {
			return new ThemeData(file, in);
		}
	}

	public static ThemeData fromResource(String path) throws IOException, ThemeParseException {
		try(InputStream in = BetterThemes.get().getBundle().getResource(path).openStream()) {
			return new ThemeData(null, in);
		}
	}

	public static ThemeData fromInputStream(InputStream in) throws IOException, ThemeParseException {
		return new ThemeData(null, in);
	}

	private ThemeData(File file, InputStream in) throws IOException, ThemeParseException {
		this.file = Optional.ofNullable(file);
		try {
			JsonReader reader = new JsonReader(new InputStreamReader(in));
			reader.setLenient(true);
			JsonObject data;

			try {
				data = JsonParser.parseReader(reader).getAsJsonObject();
			}
			catch(JsonIOException | JsonSyntaxException error) {
				throw new ThemeParseException(error);
			}

			if(!data.has("type")) {
				throw new ThemeParseException("Expected theme type");
			}

			String type = data.remove("type").getAsString();
			if("dark".equals(type)) {
				this.type = ThemeType.DARK;
			}
			else if("light".equals(type)) {
				this.type = ThemeType.LIGHT;
			}
			else {
				throw new ThemeParseException("Expected light or dark for theme type");
			}

			if(!data.has("name")) {
				throw new ThemeParseException("Expected theme name");
			}
			String name = data.remove("name").getAsString();

			if(file == null) {
				name += " (built-in)";
			}

			this.name = name;

			if(data.has("variables")) {
				JsonObject variables = data.remove("variables").getAsJsonObject();
				for(Map.Entry<String, JsonElement> entry : variables.entrySet()) {
					this.variables.put(entry.getKey(), substitute(entry.getValue().getAsString()));
				}
			}

			for(Map.Entry<String, JsonElement> entry : data.entrySet()) {
				JsonObject value;

				if(!entry.getValue().isJsonObject()) {
					if(entry.getValue().isJsonPrimitive() && entry.getValue().getAsJsonPrimitive().isString()) {
						value = new JsonObject();
						value.addProperty("color", entry.getValue().getAsString());
					}
					else {
						throw new ThemeParseException("Expected a string or object for property");
					}
				}
				else {
					value = entry.getValue().getAsJsonObject();
				}

				if(!value.has("color")) {
					value.addProperty("color", value.remove("colour").getAsString());
				}

				ThemeKey key = ThemeKey.byId(entry.getKey());

				if(key == null) {
					continue;
				}

				RGB colour = null;

				String colourString = value.get("color").getAsString();

				if(colourString.indexOf('&') == 0) {
					ThemeKey colourReference = ThemeKey.byId(colourString.substring(1));
					if(properties.containsKey(colourReference)) {
						colour = properties.get(colourReference).getRgb();
					}
				}

				try {
					if(colour == null) {
						colour = Utils.cssColour(substitute(value.get("color").getAsString()));
					}
				}
				catch(IllegalArgumentException error) {
					throw new ThemeParseException(error);
				}

				int flags = 0;

				if(Utils.isTrue(value.get("bold"))) {
					flags |= ThemeStyle.BOLD;
				}

				if(Utils.isTrue(value.get("italic"))) {
					flags |= ThemeStyle.ITALIC;
				}

				if(Utils.isTrue(value.get("strikethrough"))) {
					flags |= ThemeStyle.STRIKETHOUGH;
				}

				if(Utils.isTrue(value.get("underline"))) {
					flags |= ThemeStyle.UNDERLINE;
				}

				properties.put(key, new ThemeStyle(colour, flags));
			}
		}
		catch(UnsupportedOperationException | IllegalStateException error) {
			throw new ThemeParseException(error);
		}
	}

	private String substitute(String string) throws ThemeParseException {
		if(string == null) {
			return null;
		}

		StringBuilder result = new StringBuilder();

		outer:
		for(int i = 0; i < string.length(); i++) {
			char character = string.charAt(i);

			var:
			if(character == '@') {
				boolean end = false;
				if(++i > string.length() - 1) {
					break var;
				}

				character = string.charAt(i);
				StringBuilder variableName = new StringBuilder();

				if(!Character.isJavaIdentifierStart(character) || character == '-') {
					character = string.charAt(--i);
					break var;
				}

				variableName.append(character);

				end |= ++i > string.length() - 1;

				if(!end) {
					character = string.charAt(i);

					while(Character.isJavaIdentifierPart(character) || character == '-') {
						if(++i > string.length() - 1) {
							variableName.append(character);
							end = true;
							break;
						}

						variableName.append(character);
						character = string.charAt(i);
					}
				}

				String value = variables.get(variableName.toString());
				if(value != null) {
					result.append(value);
				}
				else {
					throw new ThemeParseException("Cannot find variable " + variableName.toString());
				}

				if(end) {
					break outer;
				}
			}

			result.append(character);
		}

		return result.toString();
	}

	@Override
	public String toString() {
		return "Theme#" + name;
	}

	public boolean has(ThemeKey key) {
		return properties.containsKey(key);
	}

	public ThemeStyle get(ThemeKey key) {
		if(key == null) {
			throw new NullPointerException();
		}

		ThemeStyle result = null;

		while(result == null) {
			if(key == null) {
				return ThemeStyle.BLACK;
			}

			result = properties.get(key);
			key = key.getInheritsFrom();
		}

		return result;
	}

	public String getName() {
		return name;
	}

	public Optional<File> getFile() {
		return file;
	}

	public ThemeType getType() {
		return type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(file, name, properties, type, variables);
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}

		if(!(obj instanceof ThemeData)) {
			return false;
		}

		ThemeData other = (ThemeData) obj;
		return Objects.equals(file, other.file) && Objects.equals(name, other.name)
				&& Objects.equals(properties, other.properties) && type == other.type
				&& Objects.equals(variables, other.variables);
	}

}
