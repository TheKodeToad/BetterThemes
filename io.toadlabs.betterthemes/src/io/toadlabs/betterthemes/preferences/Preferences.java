package io.toadlabs.betterthemes.preferences;

import io.toadlabs.betterthemes.*;
import org.eclipse.jface.preference.*;

public interface Preferences {

	String THEME = "themeId";

	static IPreferenceStore get() {
		return BetterThemes.get().getPreferenceStore();
	}

}
