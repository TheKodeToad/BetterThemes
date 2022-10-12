package io.toadlabs.betterthemes.theme;

import java.io.*;
import java.util.*;

import io.toadlabs.betterthemes.*;
import io.toadlabs.betterthemes.adapter.*;
import io.toadlabs.betterthemes.preferences.Preferences;
import lombok.*;
import org.eclipse.core.runtime.*;
import org.eclipse.core.runtime.preferences.*;
import org.eclipse.ui.*;
import org.osgi.service.prefs.*;

/**
 * Class to manage installed themes.
 */
public final class ThemeManager {

	@Getter
	private final File folder;
	private final Map<ThemeAdapter, String> adapters = new HashMap<>();
	private final TreeMap<String, ThemeData> themes = new TreeMap<>();

	{
		folder = BetterThemes.get().getStateLocation().append("themes").toFile();

		if(!folder.exists()) {
			folder.mkdirs();
		}

		if(!folder.isDirectory()) {
			throw new UnsupportedOperationException("Expected themes folder");
		}

		tryLoad();
		for(IConfigurationElement element : Platform.getExtensionRegistry().getConfigurationElementsFor(BetterThemes.ADAPTER_EXT_ID)) {
			if(!element.getName().equals("adapter")) {
				continue;
			}

			try {
				Object adapter = element.createExecutableExtension("class");

				if(!(adapter instanceof ThemeAdapter)) {
					continue;
				}

				adapters.put((ThemeAdapter) adapter, element.getAttribute("plugin"));
			}
			catch(CoreException error) {
				BetterThemes.get().getLog().error("Could not register adapter " + element.getAttribute("class"), error);
			}
		}
	}

	public void tryLoad() {
		try {
			load();
		}
		catch(Throwable error) {
			BetterThemes.get().getLog().error("Could not load themes", error);
		}
	}

	public void load() {
		themes.clear();

		try {
			for(String theme : new String[] {
				"one_dark",
				"one_light",
				"visual_studio_dark",
				"darkest_dark",
				"solarized_dark",
				"solarized_light"
			}) {
				register(ThemeData.fromResource("themes/" + theme.concat(".json")));
			}
		}
		catch(ThemeParseException | IOException error) {
			BetterThemes.get().getLog().error("Could not load default themes", error);
		}

		for(File file : folder.listFiles()) {
			if(!(file.getPath().endsWith(".json") || file.getPath().endsWith(".jsonc"))) {
				continue;
			}

			try {
				register(ThemeData.fromFile(file));
			}
			catch(ThemeParseException | IOException error) {
				BetterThemes.get().getLog().error("Could not load user theme", error);
			}
		}
	}

	private void register(ThemeData theme) {
		if(!themes.containsKey(theme.getName())) {
			themes.put(theme.getName(), theme);
		}
	}

	public Collection<ThemeData> getEntries() {
		return themes.values();
	}

	public ThemeData[] getEntriesArray() {
		return getEntries().toArray(ThemeData[]::new);
	}

	public String getActiveName() {
		return Preferences.get().getString(Preferences.THEME);
	}

	public ThemeData getActive() {
		if(!themes.containsKey(getActiveName())) {
			panic();
		}

		return themes.get(getActiveName());
	}

	public void setActive(String name) {
		if(!themes.containsKey(name)) {
			panic();
			return;
		}

		Preferences.get().setValue(Preferences.THEME, name);
	}

	private void panic() {
		Preferences.get().setToDefault(Preferences.THEME);
	}

	public void apply(IWorkbench workbench) {
		ThemeData active = getActive();
		adapters.forEach((adapter, plugin) -> {
			if(plugin != null) {
				if(Platform.getBundle(plugin) == null) {
					return;
				}
			}
			else if(adapter.getPreferencesId() == null) {
				return;
			}

			try {
				adapter.apply(active, InstanceScope.INSTANCE
						.getNode(adapter.getPreferencesId() == null ? plugin : adapter.getPreferencesId()));
			}
			catch(BackingStoreException error) {
				BetterThemes.get().getLog().error("Could not apply theme", error);
			}
		});
	}

	public static ThemeManager get() {
		return BetterThemes.get().getThemes();
	}

	/**
	 * Adds a theme to the theme folder.
	 * @param filepath The file path.
	 * @return Whether the theme was successfully added.
	 */
	public String add(String filepath) throws IOException, ThemeParseException {
		File file = new File(filepath);

		ThemeData theme = ThemeData.fromFile(file);

		if(themes.containsKey(theme.getName())) {
			ThemeData existing = themes.get(theme.getName());

			if(existing.getFile().isEmpty()) {
				return null;
			}

			if(!existing.getFile().get().delete()) {
				return null;
			}
		}

		try(FileInputStream in = new FileInputStream(file);
				FileOutputStream out = new FileOutputStream(new File(folder, file.getName()))) {
			in.transferTo(out);
		}

		return theme.getName();
	}

}
