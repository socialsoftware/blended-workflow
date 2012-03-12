package pt.ist.socialsoftware.blendedworkflow.presentation;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.terminal.UserError;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class ControlPanel extends Window {

	private String procName = null;
	private TextField processName = new TextField("Process Name");
	private VerticalLayout loadSpecs = new VerticalLayout();
	private VerticalLayout runProc = null;
	private VerticalLayout runningProcs = null;
	private Tree loadedCases = null; 
	private Button launchBtn = null;

	//spec receivers
	//	private CaseReceiver caseReceiver = new CaseReceiver(this);

	public ControlPanel() {

		this.setCaption("Admin Panel");

		// create layout
		HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth(null);
		this.setWidth("450px");

		// create accordion
		Accordion acc = new Accordion();
		acc.setWidth("400px");
		acc.setHeight(null);

		acc.addListener(new SelectedTabChangeListener() {

			@Override
			public void selectedTabChange(SelectedTabChangeEvent event) {
				TabSheet tabSheet = event.getTabSheet();
				Tab tab = tabSheet.getTab(tabSheet.getSelectedTab());
				if(tab != null) {
					String tabCaption = tab.getCaption();
					if(tabCaption.equals("Load Specifications")) {
						// load the "load specs" panel
						fillLoadSpecs();
					} else if(tabCaption.equals("Launch Case")) {
						// load the "lauch case" panel
						fillRunningProcesses();
					} else if(tabCaption.equals("Running processes")) {
						//TODO load the "running processes" panel
					}
				}

			}
		});

		acc.addTab(loadSpecs, "Load Specifications", null);

		// Launch cases:
		this.runProc = new VerticalLayout();
		acc.addTab(runProc, "Launch Case", null);

		//TODO running processes:
		this.runningProcs = new VerticalLayout();
		acc.addTab(runningProcs, "Running processes", null);

		layout.addComponent(acc);
		addComponent(layout);
	}

	protected void fillLoadSpecs() {
		loadSpecs.removeAllComponents();

		// load specifications:
		loadSpecs.setSpacing(true);
		loadSpecs.setMargin(true);

		// process name
		processName.setRequired(true);
		processName.setComponentError(null);
		processName.setWidth("12em");
		processName.setNullRepresentation("");

		Button procNameBtn = new Button("Submit");
		// add listener
		procNameBtn.addListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				if(!processName.isValid()) {
					processName.setComponentError(new UserError("Please enter a valid name"));
					return;
				}
				procName = (String) processName.getValue();
				addUploads();
			}
		});

		//add to the layout
		loadSpecs.addComponent(processName);
		loadSpecs.addComponent(procNameBtn);
	}

	protected void addUploads() {
		loadSpecs.removeAllComponents();

		// add title
		Label title = new Label("<b>Upload the specifications for the <i>" + procName + "</i> case:</b>");
		title.setContentMode(Label.CONTENT_XHTML);

		loadSpecs.addComponent(title);

		//		this.caseReceiver.initCase(procName);

		//  load activity specification (upload)
		//		Upload uploadActivity = new Upload("Upload the activity specification here:", this.caseReceiver.getActivitySpecReceiver());
		//		uploadActivity.setButtonCaption("Submit");
		//		uploadActivity.addListener((Upload.SucceededListener) this.caseReceiver.getActivitySpecReceiver());
		//		uploadActivity.addListener((Upload.FailedListener) this.caseReceiver.getActivitySpecReceiver());

		//  load goal specification (upload)
		//		Upload uploadBW = new Upload("Upload the Blended Workflow specification here:", this.caseReceiver.getBWSpecReceiver());
		//		uploadBW.setButtonCaption("Submit");
		//		uploadBW.addListener((Upload.SucceededListener) this.caseReceiver.getBWSpecReceiver());
		//		uploadBW.addListener((Upload.FailedListener) this.caseReceiver.getBWSpecReceiver());

		//		loadSpecs.addComponent(uploadActivity);
		//		loadSpecs.addComponent(uploadBW);

		Button uploadBtn = new Button("Upload");
		uploadBtn.addListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				//				String errors = caseReceiver.uploadSpec();
				//				
				//				if(errors != null) {
				//					getWindow().showNotification(errors, Notification.TYPE_ERROR_MESSAGE);
				//					return;
				//				}
				//					getWindow().showNotification("Both specifications uploaded successfuly");
			}
		});
		loadSpecs.addComponent(uploadBtn);
		loadSpecs.setComponentAlignment(uploadBtn, Alignment.TOP_RIGHT);
	}

	protected void fillRunningProcesses() {
		runProc.removeAllComponents();

		this.loadedCases = new Tree("Loaded cases:");
		this.runProc.addComponent(loadedCases);
		loadedCases.setImmediate(true);

		// get the loaded cases from the worklist manager
		//		ArrayList<BWCase> cases = WorklistManager.get().getLoadedCases();
		//		for (BWCase bwCase : cases) {
		//			String caseName = bwCase.getCaseName();
		//			String yawlCaseName = bwCase.getYAWLSpecName();
		//			String bwCaseName = bwCase.getBWSpecName();
		//			loadedCases.addItem(caseName);
		//			loadedCases.setChildrenAllowed(caseName, false);
		//			loadedCases.addItem(yawlCaseName);
		//			loadedCases.addItem(bwCaseName);
		//			loadedCases.setParent(caseName, yawlCaseName);
		//			loadedCases.setParent(caseName, bwCaseName);
		//			loadedCases.setChildrenAllowed(bwCaseName, false);
		//			loadedCases.setChildrenAllowed(yawlCaseName, false);
		//		}

		loadedCases.addListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				if(event.getProperty().getValue() != null) {
					if(loadedCases.getParent(event.getProperty().getValue()) == null) {
						// it's a root item, enable the button
						launchBtn.setEnabled(true);
					} else {
						launchBtn.setEnabled(false);
					}
				}
			}
		});

		this.launchBtn = new Button("Launch case");
		launchBtn.setEnabled(false);

		runProc.addComponent(launchBtn);
		runProc.setComponentAlignment(launchBtn, Alignment.TOP_RIGHT);
		runProc.setMargin(true);

		// add listener
		this.launchBtn.addListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				// request Worklist Manager to launch a case
				//				if(WorklistManager.get().launchCase((String)loadedCases.getValue())) {
				//					getApplication().getMainWindow().showNotification("Case launched successfully");
				//					ControlPanel.this.close();
				//				} else {
				//					getApplication().getMainWindow().showNotification("Could not launch the case", Notification.TYPE_ERROR_MESSAGE);
				//				}
			}
		});
	}

}

