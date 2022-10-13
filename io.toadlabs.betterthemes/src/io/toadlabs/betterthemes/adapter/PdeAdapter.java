package io.toadlabs.betterthemes.adapter;

import io.toadlabs.betterthemes.theme.*;
import org.eclipse.core.runtime.preferences.*;
import org.osgi.service.prefs.*;

public final class PdeAdapter extends ThemeAdapter {

	@Override
	public void apply(ThemeData theme, IEclipsePreferences preferences) throws BackingStoreException {
		map(preferences, theme, ThemeKey.FOREGROUND, "editor.color.default");
		map(preferences, theme, ThemeKey.OPERATOR, "editor.color.header_assignment");
		map(preferences, theme, ThemeKey.COMMENT, "editor.color.xml_comment");
		map(preferences, theme, ThemeKey.STRING, "editor.color.string");
		map(preferences, theme, ThemeKey.STRING, "editor.color.externalized_string");
		map(preferences, theme, ThemeKey.STRING, "editor.color.header_value");
		map(preferences, theme, ThemeKey.XML_DIRECTIVE, "editor.color.instr");
		map(preferences, theme, ThemeKey.XML_TAG, "editor.color.tag");
		map(preferences, theme, ThemeKey.KEY, "editor.color.header_attributes");
		map(preferences, theme, ThemeKey.KEY, "editor.color.header_osgi");

		super.apply(theme, preferences);
	}

}
