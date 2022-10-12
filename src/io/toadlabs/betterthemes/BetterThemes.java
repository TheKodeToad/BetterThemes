package io.toadlabs.betterthemes;

import io.toadlabs.betterthemes.theme.*;
import lombok.*;
import org.eclipse.ui.plugin.*;
import org.osgi.framework.*;

public final class BetterThemes extends AbstractUIPlugin {

	public static final String PLUGIN_ID = "io.toadlabs.betterthemes";
//	public static final String THEME_ID = "io.toadlabs.betterthemes.dark";
	public static final String ADAPTER_EXT_ID = "io.toadlabs.betterthemes.adapter";

	private static BetterThemes instance;
	@Getter
	private ThemeManager themes;

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		instance = this;
		themes = new ThemeManager();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		instance = null;
		super.stop(context);
	}

	public static BetterThemes get() {
		return instance;
	}

}
