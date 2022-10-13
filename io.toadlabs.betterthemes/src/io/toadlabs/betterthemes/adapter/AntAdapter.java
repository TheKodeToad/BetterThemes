package io.toadlabs.betterthemes.adapter;

import io.toadlabs.betterthemes.theme.*;
import org.eclipse.core.runtime.preferences.*;
import org.osgi.service.prefs.*;

public final class AntAdapter extends ThemeAdapter {

	@Override
	public void apply(ThemeData theme, IEclipsePreferences preferences) throws BackingStoreException {
		map(preferences, theme, ThemeKey.FOREGROUND, "org.eclipse.ant.ui.textColor");
		map(preferences, theme, ThemeKey.COMMENT, "org.eclipse.ant.ui.commentsColor");
		map(preferences, theme, ThemeKey.STRING, "org.eclipse.ant.ui.constantStringsColor");
		map(preferences, theme, ThemeKey.XML_DIRECTIVE, "org.eclipse.ant.ui.processingInstructionsColor");
		map(preferences, theme, ThemeKey.XML_DIRECTIVE, "org.eclipse.ant.ui.dtdColor");
		map(preferences, theme, ThemeKey.XML_TAG, "org.eclipse.ant.ui.tagsColor");
		super.apply(theme, preferences);
	}

}
