package io.toadlabs.betterthemes.adapter;

import io.toadlabs.betterthemes.theme.*;
import org.eclipse.core.runtime.preferences.*;
import org.osgi.service.prefs.*;

public final class YamlEditorAdapter extends ThemeAdapter {

	@Override
	public void apply(ThemeData theme, IEclipsePreferences preferences) throws BackingStoreException {
		putStandardColour(preferences, "colorNormalText", theme.get(ThemeKey.FOREGROUND));
		putStandardColour(preferences, "colorYamlKeywords", theme.get(ThemeKey.KEYWORD));
		putStandardColour(preferences, "colorBooleans", theme.get(ThemeKey.KEYWORD));
		putStandardColour(preferences, "colorDoubleStrings", theme.get(ThemeKey.STRING));
		putStandardColour(preferences, "colorSingleStrings", theme.get(ThemeKey.STRING));
		putStandardColour(preferences, "colorComments", theme.get(ThemeKey.COMMENT));
		putStandardColour(preferences, "colorMappings", theme.get(ThemeKey.KEY));
		putStandardColour(preferences, "colorBlock", theme.get(ThemeKey.FOREGROUND));
		putStandardColour(preferences, "colorTemplateVariable", theme.get(ThemeKey.TEMPLATE_PARAMETER));
		putStandardColour(preferences, "matchingBracketsColor", theme.get(ThemeKey.BRACKET));

		super.apply(theme, preferences);
	}

}
