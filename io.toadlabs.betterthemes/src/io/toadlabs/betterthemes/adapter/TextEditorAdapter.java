package io.toadlabs.betterthemes.adapter;

import io.toadlabs.betterthemes.theme.*;
import org.eclipse.core.runtime.preferences.*;
import org.eclipse.swt.graphics.*;
import org.osgi.service.prefs.*;

public class TextEditorAdapter extends ThemeAdapter {

	@Override
	public void apply(ThemeData theme, IEclipsePreferences preferences) throws BackingStoreException {
		preferences.putBoolean("AbstractTextEditor.Color.Background.SystemDefault", false);
		preferences.putBoolean("AbstractTextEditor.Color.Foreground.SystemDefault", false);
		putStandardColour(preferences, "AbstractTextEditor.Color.Background", theme.get(ThemeKey.BACKGROUND));
		putStandardColour(preferences, "AbstractTextEditor.Color.Foreground", theme.get(ThemeKey.FOREGROUND));
		preferences.putBoolean("AbstractTextEditor.Color.SelectionBackground.SystemDefault", false);
		preferences.putBoolean("AbstractTextEditor.Color.SelectionForeground.SystemDefault", false);
		putStandardColour(preferences, "AbstractTextEditor.Color.FindScope", theme.get(ThemeKey.SELECTION_BACKGROUND));
		putStandardColour(preferences, "AbstractTextEditor.Color.SelectionBackground", theme.get(ThemeKey.SELECTION_BACKGROUND));
		putStandardColour(preferences, "AbstractTextEditor.Color.SelectionForeground", theme.get(ThemeKey.SELECTION_FOREGROUND));
		putStandardColour(preferences, "currentLineColor", theme.get(ThemeKey.CURRENT_LINE));
		putStandardColour(preferences, "lineNumberColor", theme.get(ThemeKey.LINE_NUMBER));
		putStandardColour(preferences, "printMarginColor", theme.get(ThemeKey.LINE_NUMBER));
		putStandardColour(preferences, "occurrenceIndicationColor", theme.get(ThemeKey.OCCURRENCE));
		putStandardColour(preferences, "LSP4EReadOccurrenceIndicationColor", theme.get(ThemeKey.OCCURRENCE));
		putStandardColour(preferences, "org.eclipse.cdt.ui.occurrenceIndicationColor", theme.get(ThemeKey.OCCURRENCE));
		putStandardColour(preferences, "writeOccurrenceIndicationColor", theme.get(ThemeKey.WRITE_OCCURRENCE));
		putStandardColour(preferences, "LSP4EWriteOccurrenceIndicationColor", theme.get(ThemeKey.WRITE_OCCURRENCE));
		putStandardColour(preferences, "org.eclipse.cdt.ui.writeOccurrenceIndicationColor", theme.get(ThemeKey.WRITE_OCCURRENCE));
		putStandardColour(preferences, "TextOccurrenceIndicationColor", theme.get(ThemeKey.TEXT_OCCURRENCE));
		putStandardColour(preferences, "LSP4ETextOccurrenceIndicationColor", theme.get(ThemeKey.WRITE_OCCURRENCE));

		if(theme.has(ThemeKey.ADDED_LINE)) {
			putStandardColour(preferences, "additionIndicationColor", theme.get(ThemeKey.ADDED_LINE));
		}
		else {
			preferences.remove("additionIndicationColor");
		}

		if(theme.has(ThemeKey.MODIFIED_LINE)) {
			putStandardColour(preferences, "changeIndicationColor", theme.get(ThemeKey.MODIFIED_LINE));
		}
		else {
			preferences.remove("changeIndicationColor");
		}

		if(theme.has(ThemeKey.REMOVED_LINE)) {
			putStandardColour(preferences, "deletionIndicationColor", theme.get(ThemeKey.REMOVED_LINE));
		}
		else {
			if(theme.getType() == ThemeType.LIGHT) {
				preferences.remove("deletionIndicationColor");
			}
			else {
				putStandardColour(preferences, "deletionIndicationColor", new ThemeStyle(new RGB(224, 226, 228), 0));
			}
		}

		super.apply(theme, preferences);
	}

}
