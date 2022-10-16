package io.toadlabs.betterthemes.adapter;

import io.toadlabs.betterthemes.theme.*;
import org.eclipse.core.runtime.preferences.*;
import org.osgi.service.prefs.*;

public final class DebugUiAdapter extends ThemeAdapter {

	@Override
	public void apply(ThemeData theme, IEclipsePreferences preferences) throws BackingStoreException {
		putStandardColour(preferences, "org.eclipse.debug.ui.consoleBackground", theme.get(ThemeKey.BACKGROUND));
		putStandardColour(preferences, "org.eclipse.debug.ui.outColor", theme.get(ThemeKey.FOREGROUND));
		putStandardColour(preferences, "org.eclipse.debug.ui.errorColor", theme.get(ThemeKey.ERROR));
		super.apply(theme, preferences);
	}

}
