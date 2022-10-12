package io.toadlabs.betterthemes.adapter;

import io.toadlabs.betterthemes.theme.*;
import org.eclipse.core.runtime.preferences.*;
import org.osgi.service.prefs.*;

public class JdtAdapter extends ThemeAdapter {

	@Override
	public void apply(ThemeData theme, IEclipsePreferences preferences) throws BackingStoreException {
		map(preferences, theme, ThemeKey.FOREGROUND, "java_default");
		map(preferences, theme, ThemeKey.COMMENT, "java_single_line_comment");
		map(preferences, theme, ThemeKey.MULTILINE_COMMENT, "java_multi_line_comment");
		map(preferences, theme, ThemeKey.TASK_TAG, "java_comment_task_tag");
		map(preferences, theme, ThemeKey.KEYWORD, "java_keyword");
		map(preferences, theme, ThemeKey.KEYWORD, "java_keyword_return");
		mapSemantic(preferences, theme, ThemeKey.KEYWORD, "semanticHighlighting.restrictedKeywords");
		mapSemantic(preferences, theme, ThemeKey.NUMBER, "semanticHighlighting.number");
		map(preferences, theme, ThemeKey.STRING, "java_string");
		map(preferences, theme, ThemeKey.BRACKET, "java_bracket");
		map(preferences, theme, ThemeKey.OPERATOR, "java_operator");
		mapSemantic(preferences, theme, ThemeKey.CLASS, "semanticHighlighting.class");
		mapSemantic(preferences, theme, ThemeKey.ABSTRACT_CLASS, "semanticHighlighting.abstractClass");
		mapSemantic(preferences, theme, ThemeKey.INTERFACE, "semanticHighlighting.interface");
		mapSemantic(preferences, theme, ThemeKey.ENUM, "semanticHighlighting.enum");
		mapSemantic(preferences, theme, ThemeKey.METHOD, "semanticHighlighting.method");
		mapSemantic(preferences, theme, ThemeKey.STATIC_METHOD, "semanticHighlighting.staticMethodInvocation");
		mapSemantic(preferences, theme, ThemeKey.ABSTRACT_METHOD, "semanticHighlighting.abstractMethodInvocation");
		mapSemanticOrDisable(preferences, theme, ThemeKey.INHERITED_METHOD, "semanticHighlighting.inheritedMethodInvocation");
		mapSemantic(preferences, theme, ThemeKey.METHOD_DECLARATION, "semanticHighlighting.methodDeclarationName");
		mapSemantic(preferences, theme, ThemeKey.FIELD, "semanticHighlighting.field");
		mapSemantic(preferences, theme, ThemeKey.STATIC_FIELD, "semanticHighlighting.staticField");
		mapSemantic(preferences, theme, ThemeKey.CONSTANT, "semanticHighlighting.staticFinalField");
		mapSemanticOrDisable(preferences, theme, ThemeKey.INHERITED_FIELD, "semanticHighlighting.inheritedField");
		mapSemantic(preferences, theme, ThemeKey.LOCAL_VARIABLE, "semanticHighlighting.localVariable");
		mapSemantic(preferences, theme, ThemeKey.LOCAL_VARIABLE_DECLARATION, "semanticHighlighting.localVariableDeclaration");
		mapSemantic(preferences, theme, ThemeKey.ARGUMENT, "semanticHighlighting.parameterVariable");
		mapSemantic(preferences, theme, ThemeKey.ANNOTATION, "semanticHighlighting.annotation");
		mapSemantic(preferences, theme, ThemeKey.ANNOTATION_KEY, "semanticHighlighting.annotationElementReference");
		mapSemantic(preferences, theme, ThemeKey.TEMPLATE_PARAMETER, "semanticHighlighting.typeParameter");
		mapSemanticOrDisable(preferences, theme, ThemeKey.TEMPLATE_ARGUMENT, "semanticHighlighting.typeArgument");
		mapSemanticOrDisable(preferences, theme, ThemeKey.DEPRECATED, "semanticHighlighting.deprecatedMember");
		map(preferences, theme, ThemeKey.DOC, "java_doc_default");
		map(preferences, theme, ThemeKey.DOC_TAG, "java_doc_keyword");
		map(preferences, theme, ThemeKey.DOC_LINK, "java_doc_link");
		map(preferences, theme, ThemeKey.DOC_XML_TAG, "java_doc_tag");
		putStandardColour(preferences, "matchingBracketsColor", theme.get(ThemeKey.MATCHING_BRACKET));
		super.apply(theme, preferences);
	}

}
