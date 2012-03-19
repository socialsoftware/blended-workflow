package pt.ist.socialsoftware.blendedworkflow.presentation;

import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CreateBWInstanceService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.LoadBWSpecificationService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.SkipWorkItemService;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.shared.Bootstrap;

import com.vaadin.Application;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window.Notification;

@SuppressWarnings("serial")
public class BWPresentation extends Application {

	private CssLayout toolbar = new CssLayout();
	private TabSheet bwTabSheet;
	private GridLayout bwManagerGrid = new GridLayout(2,4);
	private ListSelect loadedList = new ListSelect();
	private ListSelect launchedList = new ListSelect();
	private GridLayout workListGrid = new GridLayout(2,3);
	private ListSelect taskList = new ListSelect();
	private ListSelect goalList = new ListSelect();

	private SpecificationReceiver activitySpecReceiver = new SpecificationReceiver(this);
	private SpecificationReceiver bwSpecReceiver = new SpecificationReceiver(this);

	@Override
	public void init() {
		setTheme("reindeermods");
		final Window mainWindow = new Window("Blended Workflow");
		setMainWindow(mainWindow);

		// init and populate database
		Bootstrap.init(BWPresentation.this);		
		Bootstrap.populate();

		// Toolbar
		toolbar.setWidth("100%");
		toolbar.addStyleName("toolbar-invert");

		CssLayout toolbarLeft = new CssLayout();
		toolbarLeft.setSizeUndefined();
		toolbarLeft.addStyleName("toolbarLeft");
		toolbar.addComponent(toolbarLeft);

		Label title = new Label("Blended Workflow");
		title.addStyleName("h1");
		toolbarLeft.addComponent(title);

		// BWManager Tab
		VerticalLayout bwManagerVL = initBWMangerTab();

		// WorklistManager Tab
		VerticalLayout workListManagerVL = initWorkListManagerTab();

		// Add tabs to Tabsheet
		this.bwTabSheet = new TabSheet();
		this.bwTabSheet.setHeight("100%");
		this.bwTabSheet.setWidth("100%");
		this.bwTabSheet.addTab(bwManagerVL, "BW Manager");
		this.bwTabSheet.addTab(workListManagerVL, "WorkList Manager");

		// Add components to the main window
		mainWindow.addComponent(toolbar);
		mainWindow.addComponent(this.bwTabSheet);
	}

	private VerticalLayout initBWMangerTab() {
		VerticalLayout bwManagerVL = new VerticalLayout();
		bwManagerVL.setMargin(true);

		bwManagerGrid.setSpacing(true);
		bwManagerGrid.setWidth("100%");

		Label loadedLabel = new Label("BW Specifications");
		loadedLabel.setWidth(null);
		Label launchedLabel = new Label("BW Instances");
		launchedLabel.setWidth(null);

		bwManagerGrid.addComponent(loadedLabel, 0, 0);
		bwManagerGrid.setComponentAlignment(loadedLabel, Alignment.TOP_LEFT);
		bwManagerGrid.addComponent(launchedLabel, 1, 0);
		bwManagerGrid.setComponentAlignment(launchedLabel, Alignment.TOP_LEFT);

		loadedList.setWidth("630px");
		loadedList.setNullSelectionAllowed(false);
		bwManagerGrid.addComponent(loadedList, 0, 1);

		launchedList.setWidth("630px");
		launchedList.setNullSelectionAllowed(false);
		bwManagerGrid.addComponent(launchedList, 1, 1);
		bwManagerGrid.setComponentAlignment(launchedList, Alignment.TOP_RIGHT);

		bwManagerVL.addComponent(bwManagerGrid);
		bwManagerVL.setComponentAlignment(bwManagerGrid, Alignment.TOP_CENTER);
		bwManagerVL.setMargin(true);

		// Upload buttons
		HorizontalLayout bwSpecificationUploadBtnLayout = new HorizontalLayout();
		bwSpecificationUploadBtnLayout.setSpacing(true);

		//  Load activity specification (upload)
		Upload uploadActivity = new Upload("Upload the activity specification here:", this.activitySpecReceiver);
		uploadActivity.setButtonCaption("Submit");
		uploadActivity.addListener((Upload.SucceededListener) this.activitySpecReceiver);
		uploadActivity.addListener((Upload.FailedListener) this.activitySpecReceiver);

		//  Load goal specification (upload)
		Upload uploadBW = new Upload("Upload the Blended Workflow specification here:", this.bwSpecReceiver);
		uploadBW.setButtonCaption("Submit");
		uploadBW.addListener((Upload.SucceededListener) this.bwSpecReceiver);
		uploadBW.addListener((Upload.FailedListener) this.bwSpecReceiver);

		bwSpecificationUploadBtnLayout.addComponent(uploadActivity);
		bwSpecificationUploadBtnLayout.addComponent(uploadBW);

		this.bwManagerGrid.addComponent(bwSpecificationUploadBtnLayout, 0, 2);
		this.bwManagerGrid.setComponentAlignment(bwSpecificationUploadBtnLayout, Alignment.TOP_CENTER);

		// Specification
		HorizontalLayout bwSpecificationBtnLayout = new HorizontalLayout();
		bwSpecificationBtnLayout.setSpacing(true);

		Button bwSpecificationLoadBtn = new Button("Load BW Specification");
		bwSpecificationLoadBtn.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					String bwSpec = bwSpecReceiver.getSpecInString();
					String activitySpec = activitySpecReceiver.getSpecInString();
					if (!bwSpec.equals(null) && !activitySpec.equals(null)) {
						new LoadBWSpecificationService(bwSpec, activitySpec).execute();
						getMainWindow().showNotification("Both specifications uploaded successfuly");
					}
					else {
						getMainWindow().showNotification("Activity or Blended Workflow Specification missing");
					}
				}
				catch (BlendedWorkflowException bwe) {
					getMainWindow().showNotification(bwe.getError().toString(), Notification.TYPE_ERROR_MESSAGE);
				}
			}
		});
		bwSpecificationBtnLayout.addComponent(bwSpecificationLoadBtn);

		Button specificationUnLoadBtn = new Button("UnLoad");
		specificationUnLoadBtn.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO: BWPresentation UnLoad
				getMainWindow().showNotification("Not Yet Implemented");
			}
		});
		bwSpecificationBtnLayout.addComponent(specificationUnLoadBtn);

		this.bwManagerGrid.addComponent(bwSpecificationBtnLayout, 0, 3);
		this.bwManagerGrid.setComponentAlignment(bwSpecificationBtnLayout, Alignment.TOP_CENTER);

		// Instances buttons
		HorizontalLayout bwInstanceBtnLayout = new HorizontalLayout();
		bwInstanceBtnLayout.setSpacing(true);

		Button bwInstanceCreateBtn = new Button("Launch");
		bwInstanceCreateBtn.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					long bwSpecificationOID = (Long) loadedList.getValue();
					new CreateBWInstanceService(bwSpecificationOID).execute();
					getMainWindow().showNotification("Blended Workflow Instance created successfuly");
				}
				catch (java.lang.NullPointerException jle) {
					getMainWindow().showNotification("Please select one specification to launch");
				}
				catch (BlendedWorkflowException bwe) {
					getMainWindow().showNotification(bwe.getError().toString(), Notification.TYPE_ERROR_MESSAGE);
				}
			}
		});
		bwInstanceBtnLayout.addComponent(bwInstanceCreateBtn);

		Button bwInstanceCancelBtn = new Button("Cancel");
		bwInstanceCancelBtn.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				//TODO: BWPresentation Cancel
				getMainWindow().showNotification("Not Yet Implemented");
			}
		});
		bwInstanceBtnLayout.addComponent(bwInstanceCancelBtn);

		this.bwManagerGrid.addComponent(bwInstanceBtnLayout, 1, 2);
		this.bwManagerGrid.setComponentAlignment(bwInstanceBtnLayout, Alignment.TOP_CENTER);

		return bwManagerVL;
	}

	private VerticalLayout initWorkListManagerTab() {
		VerticalLayout workListManagerVL = new VerticalLayout();
		workListManagerVL.setMargin(true);

		workListGrid.setSpacing(true);
		workListGrid.setWidth("100%");

		Label taskLabel = new Label("Tasks");
		taskLabel.setWidth(null);
		Label goalLabel = new Label("Goals");
		goalLabel.setWidth(null);
		workListGrid.addComponent(taskLabel, 0, 0);
		workListGrid.setComponentAlignment(taskLabel, Alignment.TOP_LEFT);

		workListGrid.addComponent(goalLabel, 1, 0);
		workListGrid.setComponentAlignment(goalLabel, Alignment.TOP_LEFT);

		// Configure task list
		taskList.setWidth("630px");
		taskList.setNullSelectionAllowed(false);
		workListGrid.addComponent(taskList, 0, 1);

		// Configure goal list
		goalList.setWidth("630px");
		goalList.setNullSelectionAllowed(false);
		workListGrid.addComponent(goalList, 1, 1);
		workListGrid.setComponentAlignment(goalList, Alignment.TOP_RIGHT);

		workListManagerVL.addComponent(workListGrid);
		workListManagerVL.setComponentAlignment(workListGrid, Alignment.TOP_CENTER);
		workListManagerVL.setMargin(true);

		// Task buttons
		HorizontalLayout taskBtnLayout = new HorizontalLayout();
		taskBtnLayout.setSpacing(true);

		Button taskExecuteBtn = new Button("Execute");
		taskExecuteBtn.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO: Execute Task
				getMainWindow().showNotification("Not Yet Implemented");
			}
		});
		taskBtnLayout.addComponent(taskExecuteBtn);

		Button taskSkipBtn = new Button("Skip");
		taskSkipBtn.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO: Skip Task
				getMainWindow().showNotification("Not Yet Implemented");
			}
		});
		taskBtnLayout.addComponent(taskSkipBtn);

		this.workListGrid.addComponent(taskBtnLayout, 0, 2);
		this.workListGrid.setComponentAlignment(taskBtnLayout, Alignment.TOP_CENTER);

		// Goal buttons
		HorizontalLayout goalBtnLayout = new HorizontalLayout();
		goalBtnLayout.setSpacing(true);

		Button goalExecuteBtn = new Button("Achieve");
		goalExecuteBtn.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					long workItemOID = (Long) goalList.getValue();
					generateGoalForm(workItemOID);
				}
				catch (java.lang.NullPointerException jle) {
					getMainWindow().showNotification("Please select a workItem to skip");
				}
			}
		});
		goalBtnLayout.addComponent(goalExecuteBtn);

		Button goalSkipBtn = new Button("Skip");
		goalSkipBtn.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					long workItemOID = (Long) goalList.getValue();
					new SkipWorkItemService(workItemOID).execute();
					removeGoalWorkItem(workItemOID);
				} catch (java.lang.NullPointerException jle) {
					getMainWindow().showNotification("Please select a workItem to skip");
				} catch (BlendedWorkflowException bwe) {
					getMainWindow().showNotification(bwe.getError().toString(), Notification.TYPE_ERROR_MESSAGE);
				}
			}
		});
		goalBtnLayout.addComponent(goalSkipBtn);

		Button goalCreateBtn = new Button("Create");
		goalCreateBtn.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				generateNewGoalWindow();
			}
		});
		goalBtnLayout.addComponent(goalCreateBtn);

		this.workListGrid.addComponent(goalBtnLayout, 1, 2);
		this.workListGrid.setComponentAlignment(goalBtnLayout, Alignment.TOP_CENTER);

		return workListManagerVL;
	}
	
	public void generateGoalForm(long workItemOID) {
		Window goalWindow = new Window("");
		goalWindow.setContent(new GoalForm(workItemOID));
		goalWindow.setWidth("30%");
		goalWindow.center();
		getMainWindow().addWindow(goalWindow);		
	}

	public void generateNewGoalWindow() {
		Window newGoalWindow = new Window("New Goal");
		newGoalWindow.setContent(new NewGoalForm());
		newGoalWindow.setWidth("40%");
		newGoalWindow.setHeight("60%");
		newGoalWindow.center();
		getMainWindow().addWindow(newGoalWindow);
	}

	public void addBWSpecification(long OID, String name) {
		this.loadedList.addItem(OID);
		this.loadedList.setItemCaption(OID, name);
	}

	public void addBWInstance(long OID, String ID) {
		this.launchedList.addItem(OID);
		this.launchedList.setItemCaption(OID, ID);
	}

	public void addGoalWorkItem(long OID, String ID) {
		this.goalList.addItem(OID);
		this.goalList.setItemCaption(OID, ID);
	}

	public void addTaskWorkItem(long OID, String ID) {
		this.taskList.addItem(OID);
		this.taskList.setItemCaption(OID, ID);
	}

	public void removeGoalWorkItem(long OID) {
		this.goalList.removeItem(OID);
	}

	public void removeTaskWorkItem(long OID) {
		this.taskList.removeItem(OID);
	}
}

