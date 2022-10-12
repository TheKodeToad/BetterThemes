package io.toadlabs.betterthemes.preferences;

import java.io.*;

import io.toadlabs.betterthemes.*;
import io.toadlabs.betterthemes.theme.*;
import org.eclipse.core.runtime.*;
import org.eclipse.jface.preference.*;
import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.program.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.*;
import org.eclipse.ui.statushandlers.*;

public final class ThemesPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

	private IWorkbench workbench;
	private List selection;
	private Button removeButton;
	private ThemeData[] listThemes;

	public ThemesPreferencePage() {
		setPreferenceStore(BetterThemes.get().getPreferenceStore());
		setDescription("Select a theme.");
	}

	@Override
	public void init(IWorkbench workbench) {
		this.workbench = workbench;
	}

	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		GridLayout layout = new GridLayout(2, false);
		container.setLayout(layout);

//		GridData gridData = new GridData(GridData.fill);
//		gridData.heightHint = 10;

		selection = new List(container, SWT.BORDER);
//		selection.setLayoutData(gridData);

		Composite buttons = new Composite(container, SWT.NONE);
		buttons.setLayout(new GridLayout(1, true));
		GridData gridData = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false);
//		gridData.widthHint = 100;

		buttons.setLayoutData(gridData);
//		buttons.setSize(200, buttons.getSize().y);


		GridData fill = new GridData(GridData.FILL_HORIZONTAL);

		Button addButton = new Button(buttons, SWT.NONE);
		addButton.setText("&Import");
		addButton.setLayoutData(fill);
		addButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent ignored) {
				FileDialog dialog = new FileDialog(getShell());
				dialog.setText("Select a theme");
				dialog.setFilterExtensions	(new String[] { "*.json;*.jsonc", "*.*" });
				dialog.setFilterNames		(new String[] { "Theme files (JSON)", "All files" });
				String selectedFile = dialog.open();
				if(selectedFile == null) {
					return;
				}

				String themeName;

				try {
					themeName = ThemeManager.get().add(selectedFile);
					if(themeName == null) {
						StatusManager.getManager().handle(Status.error("Could not install from file - conflicts with built-in theme."));
						return;
					}
				}
				catch(IOException | ThemeParseException error) {
					StatusManager.getManager().handle(Status.error("Invalid theme.", error), StatusManager.SHOW);
					return;
				}
				loadThemes();
				selection.setSelection(new String[] { themeName });
				removeButton.setEnabled(true);
			}

		});

		removeButton = new Button(buttons, SWT.NONE);
		removeButton.setText("&Remove");
		removeButton.setLayoutData(fill);
		removeButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent ignored) {
				listThemes[selection.getSelectionIndex()].getFile().ifPresent(File::delete);
				ThemeManager.get().getActive();
				loadThemes();
			}

		});

		Button openFolderButton = new Button(buttons, SWT.NONE);
		openFolderButton.setText("&Open Folder");
		openFolderButton.setLayoutData(fill);
		openFolderButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent ignored) {
				Program.launch(ThemeManager.get().getFolder().getAbsolutePath());
			}

		});

		selection.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent ignored) {
				updateRemoveButton();
			}

		});
		loadThemes();

		return container;
	}

	private void updateRemoveButton() {
		removeButton.setEnabled(selection.getSelectionIndex() != -1 && listThemes[selection.getSelectionIndex()].getFile().isPresent());
	}

	private void loadThemes() {
		ThemeManager themes = ThemeManager.get();
		themes.tryLoad();

		listThemes = themes.getEntriesArray();

		selection.setLayoutData(new GridData(GridData.FILL_BOTH));
		selection.removeAll();

		for(ThemeData theme : listThemes) {
			selection.add(theme.getName());
		}

		selection.setSelection(new String[] { themes.getActiveName() });
		updateRemoveButton();
	}

	@Override
	protected void performDefaults() {
		super.performDefaults();
		ThemeManager.get().getEntries().forEach((theme) -> theme.getFile().ifPresent(File::delete));

		getPreferenceStore().setToDefault(Preferences.THEME);
		loadThemes();
	}

	@Override
	public boolean performOk() {
		if(selection.getSelectionIndex() >= 0) {
			ThemeManager.get().setActive(listThemes[selection.getSelectionIndex()].getName());
		}

		ThemeManager.get().apply(workbench);

//		ThemeEngine engine = ((ThemeEngine) Utils.getThemeEngine(workbench));
//		if(engine.getActiveTheme().getId().equals(BetterThemes.THEME_ID)) {
//			engine.setTheme(engine.getActiveTheme(), true, true);
//		}

		return true;
	}

}