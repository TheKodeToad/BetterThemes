package io.toadlabs.betterthemes.adapter;

import io.toadlabs.betterthemes.theme.*;
import io.toadlabs.betterthemes.utils.*;
import org.eclipse.core.runtime.preferences.*;
import org.osgi.service.prefs.*;

public abstract class ThemeAdapter {

	public String getPreferencesId() {
		return null;
	}

	public void apply(ThemeData theme, IEclipsePreferences preferences) throws BackingStoreException {
		preferences.flush();
	}

	public static void putSemantic(IEclipsePreferences preferences, String name, ThemeStyle style) {
		putStandardColour(preferences, name + ".color", style);
		preferences.putBoolean(name + ".bold", style.isBold());
		preferences.putBoolean(name + ".italic", style.isItalic());
		preferences.putBoolean(name + ".underline", style.isUnderline());
		preferences.putBoolean(name + ".strikethough", style.isStrikethrough());
	}

	public static void put(IEclipsePreferences preferences, String name, ThemeStyle style) {
		putStandardColour(preferences, name, style);
		preferences.putBoolean(name + "_bold", style.isBold());
		preferences.putBoolean(name + "_italic", style.isItalic());
		preferences.putBoolean(name + "_underline", style.isUnderline());
		preferences.putBoolean(name + "_strikethough", style.isStrikethrough());
	}

	public static void putStandardColour(IEclipsePreferences preferences, String name, ThemeStyle style) {
		preferences.put(name, Utils.eclipseColour(style));
	}

	public static void map(IEclipsePreferences preferences, ThemeData theme, ThemeKey key, String target) {
		put(preferences, target, theme.get(key));
	}

	public static void mapSemantic(IEclipsePreferences preferences, ThemeData theme, ThemeKey key, String target) {
		preferences.putBoolean(target + ".enabled", true);
		mapSemanticWithoutEnable(preferences, theme, key, target);
	}

	public static void mapSemanticOrDisable(IEclipsePreferences preferences, ThemeData theme, ThemeKey key, String target) {
		preferences.putBoolean(target + ".enabled", theme.has(key));
		mapSemanticWithoutEnable(preferences, theme, key, target);
	}

	public static void mapSemanticWithoutEnable(IEclipsePreferences preferences, ThemeData theme, ThemeKey key, String target) {
		putSemantic(preferences, target, theme.get(key));
	}

}
