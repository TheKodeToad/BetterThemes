package io.toadlabs.betterthemes.adapter;

import io.toadlabs.betterthemes.theme.*;
import org.eclipse.core.runtime.preferences.*;
import org.osgi.service.prefs.*;

public final class JsonEditAdapter extends ThemeAdapter {

	@Override
	public void apply(ThemeData theme, IEclipsePreferences preferences) throws BackingStoreException {
		map(preferences, theme, ThemeKey.FOREGROUND, "styleDefault");
		map(preferences, theme, ThemeKey.KEY, "styleKey");
		map(preferences, theme, ThemeKey.STRING, "styleText");
		map(preferences, theme, ThemeKey.KEYWORD, "styleBoolean");
		map(preferences, theme, ThemeKey.KEYWORD, "styleNull");
		map(preferences, theme, ThemeKey.COMMENT, "styleComment");
		map(preferences, theme, ThemeKey.NUMBER, "styleNumber");
		putStandardColour(preferences, "matchingBracketsColor", theme.get(ThemeKey.BRACKET));

		super.apply(theme, preferences);
	}

	public static void map(IEclipsePreferences preferences, ThemeData theme, ThemeKey key, String target) {
		ThemeStyle style = theme.get(key);
		putStandardColour(preferences, target + ".color", style);
		preferences.putBoolean(target + ".isBold", style.isBold());
		preferences.putBoolean(target + ".isItalic", style.isItalic());
	}

}
