package io.toadlabs.betterthemes.preferences;

import io.toadlabs.betterthemes.*;
import lombok.experimental.*;
import org.eclipse.jface.preference.*;

@UtilityClass
public class Preferences {

	public final String THEME = "themeId";
	
	public IPreferenceStore get() {
		return BetterThemes.get().getPreferenceStore();
	}
	
}
