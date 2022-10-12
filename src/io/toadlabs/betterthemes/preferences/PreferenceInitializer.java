package io.toadlabs.betterthemes.preferences;

import io.toadlabs.betterthemes.*;
import org.eclipse.core.runtime.preferences.*;
import org.eclipse.jface.preference.*;

public final class PreferenceInitializer extends AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = BetterThemes.get().getPreferenceStore();
		store.setDefault(Preferences.THEME, "Atom One Dark");
	}

}
