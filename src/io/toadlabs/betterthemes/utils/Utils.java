package io.toadlabs.betterthemes.utils;

import java.io.*;
import java.nio.charset.*;

import com.google.gson.*;
import io.toadlabs.betterthemes.theme.*;
import lombok.experimental.*;
import org.eclipse.e4.ui.css.swt.theme.*;
import org.eclipse.e4.ui.model.application.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.ui.*;

@UtilityClass
public class Utils {

	public IThemeEngine getThemeEngine(IWorkbench workbench) {
		MApplication app = workbench.getService(MApplication.class);
		return app.getContext().get(IThemeEngine.class);
	}

	public RGB cssColour(String value) {
		org.silentsoft.csscolor4j.Color colour = org.silentsoft.csscolor4j.Color.valueOf(value);
		// convert to RGA to use less memory
		return new RGB(colour.getRed(), colour.getGreen(), colour.getBlue());
	}

	public String cssStyle(ThemeStyle style) {
		if(style == null) {
			return null;
		}

		StringBuilder builder = new StringBuilder();
		builder.append("color:" + cssColour(style.getRgb()));
		if(style.isBold()) {
			builder.append(";font-weight:bold");
		}
		if(style.isItalic()) {
			builder.append(";font-style:italic");
		}
		if(style.isUnderline() || style.isStrikethrough()) {
			builder.append(";text-decoration:");
			// fizzbuzz
			boolean underline = style.isUnderline();
			if(underline) {
				builder.append("underline");
			}
			if(style.isStrikethrough()) {
				if(underline) {
					builder.append(' ');
				}

				builder.append("line-through");
			}
		}
		return builder.toString();
	}

	public String cssColour(ThemeStyle style) {
		if(style == null) {
			return null;
		}

		return cssColour(style.getRgb());
	}

	public String cssColour(RGB rgb) {
		if(rgb == null) {
			return null;
		}

		return String.format("#%02x%02x%02x", rgb.red, rgb.green, rgb.blue);
	}

	public String eclipseColour(ThemeStyle style) {
		if(style == null) {
			return null;
		}

		return eclipseFormat(style.getRgb());
	}

	public String eclipseFormat(RGB rgb) {
		if(rgb == null) {
			return null;
		}

		return String.format("%d,%d,%d", rgb.red, rgb.green, rgb.blue);
	}

	public static boolean isTrue(JsonElement element) {
		if(element == null) {
			return false;
		}

		return element.getAsBoolean();
	}

	public static String toString(InputStream in) throws IOException {
		return new String(in.readAllBytes(), StandardCharsets.UTF_8);
	}

}
