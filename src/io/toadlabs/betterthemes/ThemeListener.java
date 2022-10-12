package io.toadlabs.betterthemes;

import io.toadlabs.betterthemes.theme.*;
import org.eclipse.ui.*;
import org.osgi.service.event.*;

public final class ThemeListener implements EventHandler {

	@Override
	public void handleEvent(Event event) {
		// Always re-apply after the global theme changes.
		ThemeManager.get().apply(PlatformUI.getWorkbench());
	}

}
