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

	public static ThemeKey byId(String id) {
		return REGISTRY.get(id);
	}

	public static final ThemeKey
			FOREGROUND					= register(new ThemeKey("foreground", null)),
			BACKGROUND					= register(new ThemeKey("background", null)),
			SELECTION_BACKGROUND 		= register(new ThemeKey("selectionBackground", BACKGROUND)),
			SELECTION_FOREGROUND 		= register(new ThemeKey("selectionForeground", FOREGROUND)),
			CURRENT_LINE				= register(new ThemeKey("currentLine", BACKGROUND)),
			LINE_NUMBERS				= register(new ThemeKey("lineNumbers", FOREGROUND)),
			COMMENT						= register(new ThemeKey("comment", FOREGROUND)),
			MULTILINE_COMMENT			= register(new ThemeKey("multilineComment", COMMENT)),
			TASK_TAG					= register(new ThemeKey("taskTag", COMMENT)),
			KEYWORD						= register(new ThemeKey("keyword", FOREGROUND)),
			NUMBER						= register(new ThemeKey("number", FOREGROUND)),
			STRING						= register(new ThemeKey("string", FOREGROUND)),
			BRACKET						= register(new ThemeKey("bracket", FOREGROUND)),
			OPERATOR					= register(new ThemeKey("operator", FOREGROUND)),
			CLASS						= register(new ThemeKey("class", FOREGROUND)),
			ABSTRACT_CLASS				= register(new ThemeKey("abstractClass", CLASS)),
			INTERFACE					= register(new ThemeKey("interface", ABSTRACT_CLASS)),
			ENUM						= register(new ThemeKey("enum", CLASS)),
			METHOD						= register(new ThemeKey("method", FOREGROUND)),
			STATIC_METHOD				= register(new ThemeKey("staticMethod", METHOD)),
			ABSTRACT_METHOD				= register(new ThemeKey("abstractMethod", METHOD)),
			INHERITED_METHOD			= register(new ThemeKey("inheritedMethod", METHOD)),
			METHOD_DECLARATION			= register(new ThemeKey("methodDeclaration", METHOD)),
			FIELD						= register(new ThemeKey("field", FOREGROUND)),
			STATIC_FIELD				= register(new ThemeKey("staticField", FIELD)),
			CONSTANT					= register(new ThemeKey("constant", STATIC_FIELD)),
			INHERITED_FIELD				= register(new ThemeKey("inheritedField", FIELD)),
			LOCAL_VARIABLE				= register(new ThemeKey("localVariable", FOREGROUND)),
			LOCAL_VARIABLE_DECLARATION	= register(new ThemeKey("localVariableDeclaration", LOCAL_VARIABLE)),
			ARGUMENT					= register(new ThemeKey("argument", LOCAL_VARIABLE)),
			ANNOTATION					= register(new ThemeKey("annotation", CLASS)),
			ANNOTATION_KEY				= register(new ThemeKey("annotationKey", FIELD)),
			TEMPLATE_PARAMETER			= register(new ThemeKey("templateParameter", CLASS)),
			TEMPLATE_ARGUMENT			= register(new ThemeKey("templateArgument", CLASS)),
			MACRO						= register(new ThemeKey("macro", METHOD)),
			MACRO_DECLARATION			= register(new ThemeKey("macroDeclaration", MACRO)),
			NAMESPACE					= register(new ThemeKey("namespace", FOREGROUND)),
			DIRECTIVE					= register(new ThemeKey("directive", KEYWORD)),
			DEPRECATED					= register(new ThemeKey("deprecated", FOREGROUND)),
			DOC							= register(new ThemeKey("doc", COMMENT)),
			DOC_TAG						= register(new ThemeKey("docTag", COMMENT)),
			DOC_LINK					= register(new ThemeKey("docLink", COMMENT)),
			DOC_XML_TAG					= register(new ThemeKey("docXmlTag", COMMENT)),
			XML_DIRECTIVE				= register(new ThemeKey("xmlDirective", DIRECTIVE)),
			XML_TAG						= register(new ThemeKey("xmlTag", FOREGROUND)),
			XML_ATTRIBUTE				= register(new ThemeKey("xmlAttribute", FOREGROUND)),
			KEY							= register(new ThemeKey("key", FIELD)),
			ERROR						= register(new ThemeKey("error", FOREGROUND)),
			OCCURRENCE					= register(new ThemeKey("occurrence", CURRENT_LINE)),
			WRITE_OCCURRENCE			= register(new ThemeKey("writeOccurrence", OCCURRENCE)),
			TEXT_OCCURRENCE				= register(new ThemeKey("textOccurrence", OCCURRENCE)),
			ADDED_LINE					= register(new ThemeKey("addedLine", BACKGROUND)),
			MODIFIED_LINE				= register(new ThemeKey("modifiedLine", BACKGROUND)),
			REMOVED_LINE				= register(new ThemeKey("removedLine", BACKGROUND)),
			MATCHING_BRACKET			= register(new ThemeKey("matchingBracket", FOREGROUND));

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
