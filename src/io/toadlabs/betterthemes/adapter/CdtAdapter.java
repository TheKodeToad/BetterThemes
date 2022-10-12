package io.toadlabs.betterthemes.adapter;

import io.toadlabs.betterthemes.theme.*;
import org.eclipse.core.runtime.preferences.*;
import org.osgi.service.prefs.*;

public class CdtAdapter extends ThemeAdapter {

	@Override
	public void apply(ThemeData theme, IEclipsePreferences preferences) throws BackingStoreException {
		map(preferences, theme, ThemeKey.FOREGROUND, "c_default");
		map(preferences, theme, ThemeKey.FOREGROUND, "pp_default");
		map(preferences, theme, ThemeKey.COMMENT, "c_single_line_comment");
		map(preferences, theme, ThemeKey.MULTILINE_COMMENT, "c_multi_line_comment");
		map(preferences, theme, ThemeKey.TASK_TAG, "c_comment_task_tag");
		map(preferences, theme, ThemeKey.KEYWORD, "c_keyword");
		map(preferences, theme, ThemeKey.KEYWORD, "c_type");
		map(preferences, theme, ThemeKey.DIRECTIVE, "pp_directive");
		map(preferences, theme, ThemeKey.NUMBER, "c_numbers");
		map(preferences, theme, ThemeKey.STRING, "c_string");
		map(preferences, theme, ThemeKey.STRING, "pp_header");
		map(preferences, theme, ThemeKey.BRACKET, "c_braces");
		map(preferences, theme, ThemeKey.OPERATOR, "c_operators");
		mapSemantic(preferences, theme, ThemeKey.CLASS, "semanticHighlighting.class");
		mapSemantic(preferences, theme, ThemeKey.CLASS, "semanticHighlighting.typedef");
		mapSemantic(preferences, theme, ThemeKey.ENUM, "semanticHighlighting.enumClass");
		mapSemantic(preferences, theme, ThemeKey.ENUM, "semanticHighlighting.enum");
		mapSemantic(preferences, theme, ThemeKey.METHOD, "semanticHighlighting.method");
		mapSemantic(preferences, theme, ThemeKey.METHOD, "semanticHighlighting.externalSDK");
		mapSemantic(preferences, theme, ThemeKey.METHOD, "semanticHighlighting.function");
		mapSemantic(preferences, theme, ThemeKey.STATIC_METHOD, "semanticHighlighting.staticMethod");
		mapSemantic(preferences, theme, ThemeKey.METHOD_DECLARATION, "semanticHighlighting.methodDeclaration");
		mapSemantic(preferences, theme, ThemeKey.METHOD_DECLARATION, "semanticHighlighting.functionDeclaration");
		mapSemantic(preferences, theme, ThemeKey.FIELD, "semanticHighlighting.field");
		mapSemantic(preferences, theme, ThemeKey.FIELD, "semanticHighlighting.globalVariable");
		mapSemantic(preferences, theme, ThemeKey.STATIC_FIELD, "semanticHighlighting.staticField");
		mapSemantic(preferences, theme, ThemeKey.CONSTANT, "semanticHighlighting.enumerator");
		mapSemantic(preferences, theme, ThemeKey.LOCAL_VARIABLE, "semanticHighlighting.localVariable");
		mapSemantic(preferences, theme, ThemeKey.LOCAL_VARIABLE_DECLARATION, "semanticHighlighting.localVariableDeclaration");
		mapSemantic(preferences, theme, ThemeKey.ARGUMENT, "semanticHighlighting.parameterVariable");
		mapSemantic(preferences, theme, ThemeKey.TEMPLATE_PARAMETER, "semanticHighlighting.templateParameter");
		mapSemantic(preferences, theme, ThemeKey.MACRO, "semanticHighlighting.macroSubstitution");
		mapSemantic(preferences, theme, ThemeKey.MACRO_DECLARATION, "semanticHighlighting.macroDefinition");
		mapSemantic(preferences, theme, ThemeKey.NAMESPACE, "semanticHighlighting.namespace");
		map(preferences, theme, ThemeKey.DOC, "org.eclipse.cdt.internal.ui.text.doctools.doxygen.single");
		map(preferences, theme, ThemeKey.DOC, "org.eclipse.cdt.internal.ui.text.doctools.doxygen.multi");
		map(preferences, theme, ThemeKey.DOC_TAG, "org.eclipse.cdt.internal.ui.text.doctools.doxygen.recognizedTag");
		putStandardColour(preferences, "matchingBracketsColor", theme.get(ThemeKey.MATCHING_BRACKET));
		super.apply(theme, preferences);
	}

}
