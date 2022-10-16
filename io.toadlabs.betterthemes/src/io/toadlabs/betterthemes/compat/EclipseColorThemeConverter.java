package io.toadlabs.betterthemes.compat;

import java.io.*;

import com.google.gson.*;
import io.toadlabs.betterthemes.theme.*;
import io.toadlabs.betterthemes.utils.*;
import javax.xml.parsers.*;
import org.eclipse.swt.graphics.*;
import org.w3c.dom.*;
import org.xml.sax.*;

/**
 * Used to convert Eclipse Color Theme XML files to BetterThemes JSON files.
 */
public class EclipseColorThemeConverter {

	public static void convert(InputStream in, OutputStream out) throws SAXException, IOException, ParserConfigurationException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(in);
		Element root = document.getDocumentElement();

		JsonObject result = new JsonObject();
		result.addProperty("name", root.getAttribute("name"));
		result.addProperty("type", "dark");

		for(int i = 0; i < root.getChildNodes().getLength(); i++) {
			Node node = root.getChildNodes().item(i);
			if(node.hasAttributes()) {
				String colour = node.getAttributes().getNamedItem("color").getNodeValue();

				boolean bold, italic, strikethrough, underline;
				bold = italic = strikethrough = underline = false;

				Node boldNode = node.getAttributes().getNamedItem("bold"),
						italicNode = node.getAttributes().getNamedItem("italic"),
						strikethroughNode = node.getAttributes().getNamedItem("strikethrough"),
						underlineNode = node.getAttributes().getNamedItem("underline");

				if(boldNode != null && boldNode.getNodeValue().equals("true")) {
					bold = true;
				}
				if(italicNode != null && italicNode.getNodeValue().equals("true")) {
					italic = true;
				}
				if(strikethroughNode != null && strikethroughNode.getNodeValue().equals("true")) {
					strikethrough = true;
				}
				if(underlineNode != null && underlineNode.getNodeValue().equals("true")) {
					underline = true;
				}

				if(node.getNodeName().equals("background")) {
					RGB rgb = Utils.cssColour(colour);
					// from https://alienryderflex.com/hsp.html
					result.addProperty("type", Math.sqrt(0.299 * (rgb.red * rgb.red) + 0.587 * (rgb.green * rgb.green)
							+ 0.114 * (rgb.blue * rgb.blue)) > 127.5 ? "light" : "dark");
				}

				convert(node.getNodeName(), colour, bold, italic, strikethrough, underline, result);
			}
		}

		try(Writer writer = new OutputStreamWriter(out)) {
			writer.write("// Converted from Eclipse Color Theme file\n");
			StringBuilder info = new StringBuilder();
			if(root.getAttribute("author") != null) {
				info.append("Author: ");
				info.append(root.getAttribute("author"));
			}
			if(root.getAttribute("website") != null && !root.getAttribute("website").isBlank()) {
				if(info.length() != 0) {
					info.append(", ");
				}

				info.append("Website: ");
				info.append(root.getAttribute("website"));
			}

			if(info.length() != 0) {
				writer.write("// Original info\n");
				writer.write("// ");
				writer.append(info);
				writer.write('\n');
			}

			writer.write(gson.toJson(result));
		}
	}

	private static void convert(String property, String colour, boolean bold, boolean italic, boolean strikethrough, boolean underline, JsonObject result) {
		switch(property) {
			case "foreground":
				property = ThemeKey.FOREGROUND.getId();
				break;
			case "background":
				property = ThemeKey.BACKGROUND.getId();
				break;
			case "selectionForeground":
				property = ThemeKey.SELECTION_FOREGROUND.getId();
				break;
			case "selectionBackground":
				property = ThemeKey.SELECTION_BACKGROUND.getId();
				break;
			case "currentLine":
				property = ThemeKey.CURRENT_LINE.getId();
				break;
			case "lineNumber":
				property = ThemeKey.LINE_NUMBER.getId();
				break;
			case "occurrenceIndication":
				property = ThemeKey.OCCURRENCE.getId();
				break;
			case "writeOccurrenceIndication":
				property = ThemeKey.WRITE_OCCURRENCE.getId();
				break;
			case "deletionIndication":
				property = ThemeKey.REMOVED_LINE.getId();
				break;
			case "findScope":
				property = ThemeKey.FIND_SCOPE.getId();
				break;
			case "searchResultIndication":
				property = ThemeKey.SEARCH_RESULT.getId();
				break;
			case "filteredSearchResultIndication":
				property = ThemeKey.FILTERED_SEARCH_RESULT.getId();
				break;
			case "debugCurrentInstructionPointer":
				property = ThemeKey.CURRENT_INSTRUCTION_POINTER.getId();
				break;
			case "debugSecondaryInstructionPointer":
				property = ThemeKey.DEBUG_CALL_STACK.getId();
				break;
			case "singleLineComment":
				property = ThemeKey.COMMENT.getId();
				break;
			case "multiLineComment":
				property = ThemeKey.MULTILINE_COMMENT.getId();
				break;
			case "commentTaskTag":
				property = ThemeKey.TASK_TAG.getId();
				break;
			case "number":
				property = ThemeKey.NUMBER.getId();
				break;
			case "string":
				property = ThemeKey.STRING.getId();
				break;
			case "bracket":
				property = ThemeKey.BRACKET.getId();
				break;
			case "operator":
				property = ThemeKey.OPERATOR.getId();
				break;
			case "keyword":
				property = ThemeKey.KEYWORD.getId();
				break;
			case "class":
				property = ThemeKey.CLASS.getId();
				break;
			case "abstractClass": // non-standard
				property = ThemeKey.ABSTRACT_CLASS.getId();
				break;
			case "interface":
				property = ThemeKey.INTERFACE.getId();
				break;
			case "enum":
				property = ThemeKey.ENUM.getId();
				break;
			case "method":
				property = ThemeKey.METHOD.getId();
				break;
			case "methodDeclaration":
				property = ThemeKey.METHOD_DECLARATION.getId();
				break;
			case "annotation":
				property = ThemeKey.ANNOTATION.getId();
				break;
			case "localVariable":
				property = ThemeKey.LOCAL_VARIABLE.getId();
				break;
			case "localVariableDeclaration":
				property = ThemeKey.LOCAL_VARIABLE_DECLARATION.getId();
				break;
			case "inheritedMethod":
				property = ThemeKey.INHERITED_METHOD.getId();
				break;
			case "abstractMethod":
				property = ThemeKey.ABSTRACT_METHOD.getId();
				break;
			case "staticMethod":
				property = ThemeKey.STATIC_METHOD.getId();
				break;
			case "javadoc":
				property = ThemeKey.DOC.getId();
				break;
			case "javadocTag":
				property = ThemeKey.DOC_XML_TAG.getId();
				break;
			case "javadocKeyword":
				property = ThemeKey.DOC_TAG.getId();
				break;
			case "javadocLink":
				property = ThemeKey.DOC_LINK.getId();
				break;
			case "field":
				property = ThemeKey.FIELD.getId();
				break;
			case "staticField":
				property = ThemeKey.STATIC_FIELD.getId();
				break;
			case "staticFinalField":
			case "constant":
				property = ThemeKey.CONSTANT.getId();
				break;
			case "parameterVariable":
				property = ThemeKey.ARGUMENT.getId();
				break;
			case "typeArgument":
				property = ThemeKey.TEMPLATE_ARGUMENT.getId();
				break;
			case "typeParameter":
				property = ThemeKey.TEMPLATE_PARAMETER.getId();
				break;
			case "deprecated":
				property = ThemeKey.DEPRECATED.getId();
				break;
		}

		if(!bold && !italic && !strikethrough && !underline) {
			result.addProperty(property, colour);
		}
		else {
			JsonObject style = new JsonObject();
			style.addProperty("color", colour);
			if(bold) {
				style.addProperty("bold", true);
			}
			if(italic) {
				style.addProperty("italic", true);
			}
			if(strikethrough) {
				style.addProperty("strikethrough", true);
			}
			if(underline) {
				style.addProperty("underline", true);
			}
			result.add(property, style);
		}
	}

}
