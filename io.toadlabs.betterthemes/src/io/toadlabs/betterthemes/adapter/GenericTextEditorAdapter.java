package io.toadlabs.betterthemes.adapter;

import io.toadlabs.betterthemes.theme.*;
import org.eclipse.core.runtime.preferences.*;
import org.osgi.service.prefs.*;

public final class GenericTextEditorAdapter extends ThemeAdapter {

	@Override
	public void apply(ThemeData theme, IEclipsePreferences preferences) throws BackingStoreException {
		putStandardColour(preferences, "matchingBracketsColor", theme.get(ThemeKey.MATCHING_BRACKET));
		super.apply(theme, preferences);
	}

}
