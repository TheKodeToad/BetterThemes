package io.toadlabs.betterthemes;

import java.io.*;

import io.toadlabs.betterthemes.theme.*;
import org.eclipse.ui.plugin.*;
import org.osgi.framework.*;

public final class BetterThemes extends AbstractUIPlugin {

	public static final String PLUGIN_ID = "io.toadlabs.betterthemes";
	public static final String ADAPTER_EXT_ID = "io.toadlabs.betterthemes.adapter";

	private static BetterThemes instance;

	private ThemeManager themes;

	public ThemeManager getThemes() {
		if(themes == null) {
			themes = new ThemeManager();
		}

		return themes;
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		instance = this;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		instance = null;
		super.stop(context);
	}

	public static BetterThemes get() {
		return instance;
	}

	public static File getData() {
		return get().getStateLocation().toFile();
	}

}
