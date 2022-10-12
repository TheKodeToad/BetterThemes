package io.toadlabs.betterthemes;

import org.osgi.service.event.*;

public class ThemeListener implements EventHandler {

	@Override
	public void handleEvent(Event event) {
//		ITheme theme = (ITheme) event.getProperty(IThemeEngine.Events.THEME);
//		if(theme.getId().equals(BetterThemes.THEME_ID)) {
//			ThemeManager.get().applyNow(PlatformUI.getWorkbench());
//		}
	}

}
