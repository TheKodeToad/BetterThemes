package io.toadlabs.betterthemes.theme;

import lombok.*;
import org.eclipse.swt.graphics.*;

@AllArgsConstructor
public class ThemeStyle {

	public static final int BOLD = 1, ITALIC = 2, STRIKETHOUGH = 4, UNDERLINE = 8;

	public static final ThemeStyle BLACK = new ThemeStyle(new RGB(0, 0, 0), 0);
	public static final ThemeStyle WHITE = new ThemeStyle(new RGB(255, 255, 255), 0);

	@Getter
	@Setter(AccessLevel.PROTECTED)
	private RGB rgb;
	@Getter
	private final int flags;

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

		result.append('{');

		if(rgb != null) {
			result.append("r=").append(rgb.red).append(", ");
			result.append("g=").append(rgb.green).append(", ");
			result.append("b=").append(rgb.blue).append(", ");
		}

		result.append("flags=");

		if(isBold()) {
			result.append('b');
		}

		if(isItalic()) {
			result.append('i');
		}

		if(isStrikethrough()) {
			result.append('s');
		}

		if(isUnderline()) {
			result.append('u');
		}

		result.append("}");

		return result.toString();
	}

	public boolean isBold() {
		return (flags & BOLD) > 0;
	}

	public boolean isItalic() {
		return (flags & ITALIC) > 0;
	}

	public boolean isStrikethrough() {
		return (flags & STRIKETHOUGH) > 0;
	}

	public boolean isUnderline() {
		return (flags & UNDERLINE) > 0;
	}

}
