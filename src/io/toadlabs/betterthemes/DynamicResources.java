//package io.toadlabs.betterthemes;
//
//import java.io.*;
//import java.net.*;
//
//import io.toadlabs.betterthemes.theme.*;
//import io.toadlabs.betterthemes.utils.*;
//
//public class DynamicResources extends ByteArrayURLStreamHandlerService {
//
//	@Override
//	protected String getString(URL url) throws IOException {
//		switch(url.getPath()) {
//			case "/ui-theme.css":
//				return generateUiTheme();
//		}
//
//		return null;
//	}
//
//	private String generateUiTheme() throws IOException {
////		return "";
//		ThemeData theme = ThemeManager.get().getActive();
//		try(InputStream templateIn = BetterThemes.get().getBundle().getResource("css/template.css").openStream()) {
//			String template = Utils.toString(templateIn);
//			String bg = Utils.cssColour(theme.get(ThemeKey.UI_BACKGROUND));
//			String fg = Utils.cssColour(theme.get(ThemeKey.UI_FOREGROUND));
//			return template.replace("$bg", bg).replace("$fg", fg).replace("$link", "blue")
//					.replace("$inactive_tab_outline_color", bg).replace("$active_nofocus_tab_selected_fg", fg)
//					.replace("$active_nofocus_tab_bg_start", bg).replace("$active_nofocus_tab_selected_fg", fg)
//					.replace("$inactive_tab_fg", "#BBBBBB").replace("$active_tab_outline", bg)
//					.replace("$active_tab_outer_keyline", bg).replace("$active_unselected_tabs_bg_start", bg)
//					.replace("$active_unselected_tabs_bg_end", bg);
//		}
////		result.append("Shell,\n"
////				+ "Composite, ScrolledComposite, ExpandableComposite, Canvas, TabFolder, CLabel, Label,\n"
////				+ "CoolBar, Sash, Group, RefactoringLocationControl, ChangeParametersControl, Link, FilteredTree,\n"
////				+ "ProxyEntriesComposite, NonProxyHostsComposite, DelayedFilterCheckboxTree,\n"
////				+ "Splitter, ScrolledPageContent, ViewForm, LaunchConfigurationFilteredTree,\n"
////				+ "ContainerSelectionGroup, BrowseCatalogItem, EncodingSettings,\n"
////				+ "ProgressMonitorPart, DocCommentOwnerComposite, NewServerComposite,\n"
////				+ "NewManualServerComposite, ServerTypeComposite, FigureCanvas,\n"
////				+ "DependenciesComposite, ListEditorComposite, WrappedPageBook,\n"
////				+ "CompareStructureViewerSwitchingPane, CompareContentViewerSwitchingPane,\n"
////				+ "QualifiedNameComponent, RefactoringStatusViewer,\n"
////				+ "MessageLine,\n"
////				+ "Button /* SWT-BUG: checkbox inner label font color is not accessible */,\n"
////				+ "Composite > *,\n"
////				+ "Composite > * > *,\n"
////				+ "Group > StyledText { background-color: #000000; color: white; }");
////		result.append("TabFolder > *,\n"
////				+ "CTabFolder > *,\n"
////				+ "TabFolder > Composite > *, /* Composite > CommitSearchPage$... */\n"
////				+ "CTabFolder > Composite > *, /* Composite > CommitSearchPage$... */\n"
////				+ "TabFolder > Composite > * > * { color: white; }");
////		result.append("ToolBar {\n"
////				+ "	background-color:inherit;	\n"
////				+ "}");
////		result.append("Combo,\n"
////				+ "List,\n"
////				+ "Text,\n"
////				+ "Spinner,\n"
////				+ "CCombo { background-color: black; color: white }");
////		return result.toString();
//	}
//
//}
