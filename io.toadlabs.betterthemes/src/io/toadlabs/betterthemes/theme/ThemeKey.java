package io.toadlabs.betterthemes.theme;

import java.util.*;

public final class ThemeKey {

	private final String id;
	private final ThemeKey inheritsFrom;

	private static final Map<String, ThemeKey> REGISTRY = new HashMap<>();

	public static ThemeKey register(ThemeKey key) {
		REGISTRY.put(key.id, key);
		return key;
	}

	public static ThemeKey register(String id, ThemeKey inheritsFrom) {
		return register(new ThemeKey(id, inheritsFrom));
	}

	public static ThemeKey byId(String id) {
		return REGISTRY.get(id);
	}

	public static final ThemeKey
			FOREGROUND					= register("foreground", null),
			BACKGROUND					= register("background", null),
			SELECTION_BACKGROUND 		= register("selectionBackground", BACKGROUND),
			SELECTION_FOREGROUND 		= register("selectionForeground", FOREGROUND),
			CURRENT_LINE				= register("currentLine", BACKGROUND),
			ERROR						= register("error", FOREGROUND),
			OCCURRENCE					= register("occurrence", CURRENT_LINE),
			WRITE_OCCURRENCE			= register("writeOccurrence", OCCURRENCE),
			TEXT_OCCURRENCE				= register("textOccurrence", OCCURRENCE),
			ADDED_LINE					= register("addedLine", BACKGROUND),
			MODIFIED_LINE				= register("modifiedLine", BACKGROUND),
			REMOVED_LINE				= register("removedLine", BACKGROUND),
			MATCHING_BRACKET			= register("matchingBracket", FOREGROUND),
			FIND_SCOPE					= register("findScope", SELECTION_BACKGROUND),
			SEARCH_RESULT				= register("searchResult", OCCURRENCE),
			FILTERED_SEARCH_RESULT		= register("filteredSearchResult", SEARCH_RESULT),
			CURRENT_INSTRUCTION_POINTER = register("debugCurrentInstructionPointer", CURRENT_LINE),
			DEBUG_CALL_STACK 			= register("debugCallStack", CURRENT_INSTRUCTION_POINTER),
			LINE_NUMBER					= register("lineNumber", FOREGROUND),
			COMMENT						= register("comment", FOREGROUND),
			MULTILINE_COMMENT			= register("multilineComment", COMMENT),
			TASK_TAG					= register("taskTag", COMMENT),
			KEYWORD						= register("keyword", FOREGROUND),
			NUMBER						= register("number", FOREGROUND),
			STRING						= register("string", FOREGROUND),
			BRACKET						= register("bracket", FOREGROUND),
			OPERATOR					= register("operator", FOREGROUND),
			CLASS						= register("class", FOREGROUND),
			ABSTRACT_CLASS				= register("abstractClass", CLASS),
			INTERFACE					= register("interface", ABSTRACT_CLASS),
			ENUM						= register("enum", CLASS),
			METHOD						= register("method", FOREGROUND),
			STATIC_METHOD				= register("staticMethod", METHOD),
			ABSTRACT_METHOD				= register("abstractMethod", METHOD),
			INHERITED_METHOD			= register("inheritedMethod", METHOD),
			METHOD_DECLARATION			= register("methodDeclaration", METHOD),
			FIELD						= register("field", FOREGROUND),
			STATIC_FIELD				= register("staticField", FIELD),
			CONSTANT					= register("constant", STATIC_FIELD),
			INHERITED_FIELD				= register("inheritedField", FIELD),
			LOCAL_VARIABLE				= register("localVariable", FOREGROUND),
			LOCAL_VARIABLE_DECLARATION	= register("localVariableDeclaration", LOCAL_VARIABLE),
			ARGUMENT					= register("argument", LOCAL_VARIABLE),
			ANNOTATION					= register("annotation", CLASS),
			ANNOTATION_KEY				= register("annotationKey", FIELD),
			TEMPLATE_PARAMETER			= register("templateParameter", CLASS),
			TEMPLATE_ARGUMENT			= register("templateArgument", CLASS),
			MACRO						= register("macro", METHOD),
			MACRO_DECLARATION			= register("macroDeclaration", MACRO),
			NAMESPACE					= register("namespace", FOREGROUND),
			DIRECTIVE					= register("directive", KEYWORD),
			DEPRECATED					= register("deprecated", FOREGROUND),
			DOC							= register("doc", COMMENT),
			DOC_TAG						= register("docTag", DOC),
			DOC_LINK					= register("docLink", DOC),
			DOC_XML_TAG					= register("docXmlTag", DOC),
			XML_DIRECTIVE				= register("xmlDirective", DIRECTIVE),
			XML_TAG						= register("xmlTag", FOREGROUND),
			XML_ATTRIBUTE				= register("xmlAttribute", FOREGROUND),
			KEY							= register("key", FIELD);

	public ThemeKey(String id, ThemeKey inheritsFrom) {
		this.id = id;
		this.inheritsFrom = inheritsFrom;
	}

	public String getId() {
		return id;
	}

	public ThemeKey getInheritsFrom() {
		return inheritsFrom;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, inheritsFrom);
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}

		if(!(obj instanceof ThemeKey)) {
			return false;
		}

		ThemeKey other = (ThemeKey) obj;
		return Objects.equals(id, other.id) && Objects.equals(inheritsFrom, other.inheritsFrom);
	}

}
