package pt.ist.socialsoftware.blendedworkflow.presentation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

//import org.apache.log4j.Logger;

import jvstm.Transaction;
import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AchieveGoal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Role;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.User;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AchieveGoal.GoalState;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModel;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.LogRecord;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Task;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Task.TaskState;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskModel;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem.WorkItemState;
import pt.ist.socialsoftware.blendedworkflow.shared.Bootstrap;

import com.vaadin.Application;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.LoginForm.LoginEvent;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.Table;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window.Notification;

@SuppressWarnings("serial")
public class BWPresentation extends Application {

	private String username = "";
	private User user = null;
//	private Logger log = Logger.getLogger("BWPresentation");
	private Boolean bwManagerAcess = false;
	private Boolean worklistManagerAcess = false;

	// Main Window
	private final Window mainWindow = new Window("Blended Workflow");
	private final Window loginWindow = new Window("Login");
	private final LoginForm login = new LoginForm();
	private CssLayout toolbar = new CssLayout();
	private final CssLayout left = new CssLayout();
	private CssLayout right = new CssLayout();
	private TabSheet bwTabSheet;
	private Tab bwSpecTab, bwInstanceTab, bwDataTab, taskViewTab, goalViewTab;
	private Button bwManagerBtn = new Button("BW Manager");
	private Button wManagerBtn = new Button("Worklist Manager");

	// BWSpecTab
	private ListSelect loadedList = new ListSelect();
	private Table bwSpecInfoTable = new Table("Basic Information:");
	private TreeTable bwSpecJobsInfoTable = new TreeTable("Tasks/Goals:");

	// BWInstanceTab
	private ListSelect launchedList = new ListSelect();
	private Table bwInstanceInfoTable = new Table("Basic Information:");
	private Table bwInstanceJobsInfoTable = new Table("Jobs Executed:");

	// DataModelTab
	private ListSelect dataList = new ListSelect();
	private TreeTable entitydetailsTreetable = new TreeTable("Data Model:");

	// TaskViewTab
	private ListSelect taskList = new ListSelect();
	private Table taskInfoTable = new Table("Task Information:");
	private Property.ValueChangeListener taskListListener;
	private ListSelect goalList = new ListSelect();
	private Table goalInfoTable = new Table("Goal Information:");
	private Property.ValueChangeListener goalListListener;
//	private NativeSelect taskListFilter = new NativeSelect();

	// GoalViewTab
	private ListSelect goalBWInstanceList = new ListSelect();
	private TreeTable goalTable = new TreeTable("Goals");
//	private NativeSelect goalListFilter = new NativeSelect();
	
	private Application bwPresentation;

	/**
	 * Initialize the Application.
	 */
	@Override
	public void init() {
		bwPresentation = this;
		// Init and populate database
		if (!Bootstrap.isInitialized()) {
			Bootstrap.init();
			registerBWPresentation();
			Bootstrap.populate();
		}
		registerBWPresentation();
		initLoginWindow();
	}
	
	/**
	 * Register the BWPresentation with the BWManager and WorklistManager.
	 */
	private void registerBWPresentation() {
		Transaction.begin();
		BlendedWorkflow.getInstance().getBwManager().setBwPresentation(BWPresentation.this);
		BlendedWorkflow.getInstance().getWorkListManager().setBwPresentation(BWPresentation.this);
		Transaction.commit();
	}

	/******************************
	 * Windows.
	 ******************************/
	private void initLoginWindow() {
		setTheme("reindeermods");
		setMainWindow(mainWindow);

		Label title = new Label("Blended Workflow");
		title.addStyleName("h1");

		left.setSizeUndefined();
		left.addStyleName("left");
		left.addComponent(title);
		toolbar.setWidth("100%");
		toolbar.addStyleName("toolbar-invert");
		toolbar.addComponent(left);

		generateLoginForm();
		mainWindow.addComponent(toolbar);
	}

	private void initMainWindow(String name) {
		this.username = name + "   ";
		
		getMainWindow().showNotification("Welcome " + this.username);
		Label welcome = new Label("Welcome " + this.username + "   ");
		welcome.addStyleName("h3");

		Label splitter = new Label("|");
		Label splitter2 = new Label("|");

		bwManagerBtn.addStyleName("borderless");
		bwManagerBtn.setEnabled(false);
		bwManagerBtn.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				bwManagerBtn.setEnabled(false);
				bwSpecTab.setEnabled(true);
				bwInstanceTab.setEnabled(true);
				bwDataTab.setEnabled(true);
				
				if (worklistManagerAcess) {
					wManagerBtn.setEnabled(true);
					taskViewTab.setEnabled(false);
					goalViewTab.setEnabled(false);
				}
			}
		});

		wManagerBtn.addStyleName("borderless");
		wManagerBtn.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				wManagerBtn.setEnabled(false);
				taskViewTab.setEnabled(true);
				goalViewTab.setEnabled(true);

				if (bwManagerAcess) {
					bwManagerBtn.setEnabled(true);
					bwSpecTab.setEnabled(false);
					bwInstanceTab.setEnabled(false);
					bwDataTab.setEnabled(false);
				}
			}
		});

		Button logout = new Button("Logout");
		logout.addStyleName("borderless"); 
		logout.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				bwPresentation.close();
				initLoginWindow();
			}
		});

		right.setSizeUndefined();
		right.addStyleName("right");
		right.addComponent(welcome);
		
		this.bwTabSheet = new TabSheet();
		this.bwTabSheet.setHeight("100%");
		this.bwTabSheet.setWidth("100%");
		
		// Role Manager
		Transaction.begin();
		user = BlendedWorkflow.getInstance().getOrganizationalManager().getActiveUser();
		Role userRole = user.getRole();
		if (userRole.getBwManagerAcess()) {
			right.addComponent(bwManagerBtn);
			right.addComponent(splitter);
			VerticalLayout bwManagerVL = initBWSpecTab();
			VerticalLayout bwInstancesVL = initBWInstancesTab();
			VerticalLayout bwInstancesDataVL = initBWInstanceDataTab();
			bwSpecTab = this.bwTabSheet.addTab(bwManagerVL, "BW Specifications");
			bwInstanceTab = this.bwTabSheet.addTab(bwInstancesVL, "BW Instances");
			bwDataTab = this.bwTabSheet.addTab(bwInstancesDataVL, "Data Model");
			BlendedWorkflow.getInstance().getBwManager().updateBWPresentation();
			bwManagerAcess = true;
//			bwSpecTab.setEnabled(true);
//			bwInstanceTab.setEnabled(true);
//			bwDataTab.setEnabled(true);
		}
		if (userRole.getOrganizationalManagerAcess()) {
			//TODO: OrganizationalTab
		}
		if (userRole.getWorklistManagerAcess()) {
			right.addComponent(wManagerBtn);
			right.addComponent(splitter2);
			VerticalLayout bwTaskViewVL = initTaskViewTab();
			VerticalLayout bwGoalViewVL = initGoalViewTab();
			
			taskViewTab = this.bwTabSheet.addTab(bwTaskViewVL, "Worklist");
			goalViewTab = this.bwTabSheet.addTab(bwGoalViewVL, "Goal Manager");
			if (bwManagerAcess) {
				taskViewTab.setEnabled(false);
				goalViewTab.setEnabled(false);
			} else {
				taskViewTab.setEnabled(true);
				goalViewTab.setEnabled(true);
			}

			BlendedWorkflow.getInstance().getWorkListManager().updateBWPresentation();
			worklistManagerAcess = true;
		}
		Transaction.commit();
		
		right.addComponent(logout);
		toolbar.addComponent(right);

		mainWindow.addComponent(this.bwTabSheet);
	}

	/******************************
	 * Main Window Tabs.
	 ******************************/
	private VerticalLayout initBWSpecTab() {
		VerticalLayout bwSpecVL = new VerticalLayout();
		HorizontalLayout infoHL = new HorizontalLayout();
		VerticalLayout infoVL = new VerticalLayout();
		HorizontalLayout bwSpecBtnLayout = new HorizontalLayout();

		loadedList.setWidth("300px");
		loadedList.setHeight("450px");
		loadedList.setImmediate(true);

		loadedList.addListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				if (loadedList.getValue() == null) {
					bwSpecInfoTable.removeAllItems();
					bwSpecJobsInfoTable.removeAllItems();
				} else {
				long bwSpecificationOID = (Long) loadedList.getValue();
				updateBWSpecificationInfo(bwSpecificationOID); }
			}
		});

		bwSpecInfoTable.addContainerProperty("Name", String.class,  null);
		bwSpecInfoTable.addContainerProperty("Value",  String.class,  null);

		bwSpecJobsInfoTable.addContainerProperty("Name", String.class, "");
		bwSpecJobsInfoTable.addContainerProperty("Description", String.class, "");
		bwSpecJobsInfoTable.addContainerProperty("Input Data", String.class, "");
		bwSpecJobsInfoTable.addContainerProperty("Output Data", String.class, "");

		Button bwSpecificationLoadBtn = new Button("Load");
		bwSpecificationLoadBtn.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				generateLoadForm();
			}
		});

		Button specificationUnLoadBtn = new Button("UnLoad");
		specificationUnLoadBtn.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO: UnLoad
				getMainWindow().showNotification("Not Yet Implemented");
			}
		});

		Button bwInstanceCreateBtn = new Button("Launch");
		bwInstanceCreateBtn.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					long bwSpecificationOID = (Long) loadedList.getValue();
					generateLaunchForm(bwSpecificationOID);
				}
				catch (java.lang.NullPointerException jle) {
					getMainWindow().showNotification("Please select one specification to launch");
				}
			}
		});

		bwSpecInfoTable.setWidth("500px");
		bwSpecInfoTable.setHeight("150px");
		bwSpecJobsInfoTable.setWidth("800px");
		bwSpecJobsInfoTable.setHeight("240px");

		bwSpecBtnLayout.setSpacing(true);
		bwSpecBtnLayout.setMargin(true);
		bwSpecBtnLayout.addComponent(bwSpecificationLoadBtn);
		bwSpecBtnLayout.addComponent(specificationUnLoadBtn);
		bwSpecBtnLayout.addComponent(bwInstanceCreateBtn);
		infoVL.setMargin(true);
		infoVL.setSpacing(true);
		infoVL.addComponent(bwSpecInfoTable);
		infoVL.addComponent(bwSpecJobsInfoTable);
		infoVL.setComponentAlignment(bwSpecJobsInfoTable, Alignment.BOTTOM_LEFT);
		infoHL.addComponent(loadedList);
		infoHL.addComponent(infoVL);
		infoHL.setComponentAlignment(loadedList, Alignment.TOP_LEFT);
		bwSpecVL.addComponent(infoHL);
		bwSpecVL.addComponent(new Label("<hr />",Label.CONTENT_XHTML));
		bwSpecVL.addComponent(bwSpecBtnLayout);
		bwSpecVL.setComponentAlignment(bwSpecBtnLayout, Alignment.TOP_LEFT);

		return bwSpecVL;
	}

	private VerticalLayout initBWInstancesTab() {
		// Layouts
		VerticalLayout bwInstancesVL = new VerticalLayout();
		HorizontalLayout infoHL = new HorizontalLayout();
		VerticalLayout infoVL = new VerticalLayout();
		HorizontalLayout bwInstancesBtnLayout = new HorizontalLayout();

		// Components
		launchedList.setWidth("300px");
		launchedList.setHeight("450px");

		launchedList.setImmediate(true);
		launchedList.addListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				if (launchedList.getValue() == null) {
					bwInstanceInfoTable.removeAllItems();
					bwInstanceJobsInfoTable.removeAllItems();
				} else {
				long bwInstanceOID = (Long) launchedList.getValue();
				updateBWInstanceInfo(bwInstanceOID); }
			}
		});

		bwInstanceInfoTable.setWidth("300px");
		bwInstanceInfoTable.setHeight("130px");
		bwInstanceInfoTable.addContainerProperty("Name", String.class,  null);
		bwInstanceInfoTable.addContainerProperty("Value",  String.class,  null);

		bwInstanceJobsInfoTable.setWidth("800px");
		bwInstanceJobsInfoTable.setHeight("260px");
		bwInstanceJobsInfoTable.addContainerProperty("Date", String.class, "");
		bwInstanceJobsInfoTable.addContainerProperty("Action", String.class, "");
		bwInstanceJobsInfoTable.addContainerProperty("Value", String.class, "");
		bwInstanceJobsInfoTable.addContainerProperty("Author", String.class, "");

		Button bwInstanceCancelBtn = new Button("Cancel");
		bwInstanceCancelBtn.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				//TODO: Cancel
				getMainWindow().showNotification("Not Yet Implemented");
			}
		});



		// Layouts - configurations
		bwInstancesBtnLayout.setSpacing(true);
		bwInstancesBtnLayout.setMargin(true);
		bwInstancesBtnLayout.addComponent(bwInstanceCancelBtn);
		bwInstancesBtnLayout.setComponentAlignment(bwInstanceCancelBtn, Alignment.TOP_RIGHT);

		infoVL.setMargin(true);
		infoVL.setSpacing(true);
		infoVL.addComponent(bwInstanceInfoTable);
		infoVL.addComponent(bwInstanceJobsInfoTable);
		infoVL.setComponentAlignment(bwInstanceJobsInfoTable, Alignment.BOTTOM_LEFT);

		infoHL.addComponent(launchedList);
		infoHL.addComponent(infoVL);
		infoHL.setComponentAlignment(launchedList, Alignment.TOP_LEFT);

		bwInstancesVL.addComponent(infoHL);
		bwInstancesVL.addComponent(new Label("<hr />",Label.CONTENT_XHTML));
		bwInstancesVL.addComponent(bwInstancesBtnLayout);
		bwInstancesVL.setComponentAlignment(bwInstancesBtnLayout, Alignment.TOP_LEFT);

		return bwInstancesVL;
	}

	private VerticalLayout initBWInstanceDataTab() {
		// Layouts
		VerticalLayout bwInstancesVL = new VerticalLayout();
		HorizontalLayout infoHL = new HorizontalLayout();
		VerticalLayout infoVL = new VerticalLayout();
		HorizontalLayout bwInstancesBtnLayout = new HorizontalLayout();

		// Components
		dataList.setWidth("300px");
		dataList.setHeight("450px");

		dataList.setImmediate(true);

		dataList.addListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				if (dataList.getValue() == null) {
					entitydetailsTreetable.removeAllItems();
				} else {
				long bwSpecificationOID = (Long) dataList.getValue();
				updateBWInstanceDataInfo(bwSpecificationOID);
				}
			}
		});

		entitydetailsTreetable.setWidth("500px");
		entitydetailsTreetable.setHeight("400px");
		entitydetailsTreetable.addContainerProperty("Entity/Attribute", String.class, "");
		entitydetailsTreetable.addContainerProperty("Value", String.class, "");
		entitydetailsTreetable.addContainerProperty("State", String.class, "");

		// Layouts - configurations
		bwInstancesBtnLayout.setSpacing(true);
		bwInstancesBtnLayout.setMargin(true);

		infoVL.setMargin(true);
		infoVL.setSpacing(true);
		infoVL.addComponent(entitydetailsTreetable);
		infoVL.setComponentAlignment(entitydetailsTreetable, Alignment.TOP_LEFT);

		infoHL.addComponent(dataList);
		infoHL.addComponent(infoVL);
		infoHL.setComponentAlignment(dataList, Alignment.TOP_LEFT);

		bwInstancesVL.addComponent(infoHL);
		bwInstancesVL.addComponent(new Label("<hr />",Label.CONTENT_XHTML));
		bwInstancesVL.addComponent(bwInstancesBtnLayout);
		bwInstancesVL.setComponentAlignment(bwInstancesBtnLayout, Alignment.TOP_LEFT);

		return bwInstancesVL;
	}

	private VerticalLayout initTaskViewTab() {
		// Layouts
		VerticalLayout bwInstancesVL = new VerticalLayout();
		HorizontalLayout infoHL = new HorizontalLayout();
		VerticalLayout infoVL = new VerticalLayout();
		HorizontalLayout bwInstancesBtnLayout = new HorizontalLayout();

		// Components
//		taskListFilter.addListener(new Property.ValueChangeListener() {
//			public void valueChange(ValueChangeEvent event) {
//				long workItemOID = (Long) taskListFilter.getValue();
//				updateTaskList(workItemOID);
//			}
//		});

		taskList.setWidth("300px");
		taskList.setHeight("450px");

		taskList.setImmediate(true);
		initTaskListListener();

		taskInfoTable = new Table("Task Information:");

		taskInfoTable.addContainerProperty("Name", String.class,  null);
		taskInfoTable.addContainerProperty("Value",  String.class,  null);
		
		// goal
		goalList.setImmediate(true);
		initGoalListListener();

		goalInfoTable.addContainerProperty("Name", String.class,  null);
		goalInfoTable.addContainerProperty("Value",  String.class,  null);

		Button taskExecuteBtn = new Button("Execute Task");
		taskExecuteBtn.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					long workItemOID = (Long) taskList.getValue();
					
					TaskWorkItem taskWorkItem = AbstractDomainObject.fromOID(workItemOID);
					Transaction.begin();
					Boolean isPreTask = false;
					if (taskWorkItem.getState().equals(WorkItemState.PRE_TASK)) {
						isPreTask = true;
					}
					Transaction.commit();
					if (isPreTask) {
						generatePreTaskForm(workItemOID);
					} else {
						generateTaskForm(workItemOID);
					}
					
					
				} catch (java.lang.NullPointerException jle) {
					getMainWindow().showNotification("Please select a workItem to execute");
				}
			}
		});

		Button taskSkipBtn = new Button("Skip Task");
		taskSkipBtn.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					long workItemOID = (Long) taskList.getValue();
					Transaction.begin();
					TaskWorkItem taskWorkItem = AbstractDomainObject.fromOID(workItemOID);
					User activeUser = BlendedWorkflow.getInstance().getOrganizationalManager().getActiveUser();
					taskWorkItem.setUser(activeUser);
					Transaction.commit();
					
					Transaction.begin();
					BlendedWorkflow.getInstance().getWorkListManager().skipWorkItem(workItemOID);
					Transaction.commit();
					removeTaskWorkItem(workItemOID);
//					getMainWindow().showNotification("WorkItem Skipped", Notification.TYPE_TRAY_NOTIFICATION);
				} catch (java.lang.NullPointerException jle) {
					getMainWindow().showNotification("Please select a workItem to skip");
				}
			}
		});
		
		// goal
		Button goalExecuteBtn = new Button("Achieve Goal");
		goalExecuteBtn.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					long workItemOID = (Long) goalList.getValue();
					generateGoalForm(workItemOID);
				} catch (java.lang.NullPointerException jle) {
					getMainWindow().showNotification("Please select a workItem to achieve");
				}
			}
		});

		Button goalSkipBtn = new Button("Skip Goal");
		goalSkipBtn.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					long workItemOID = (Long) goalList.getValue();
				
					Transaction.begin();
					GoalWorkItem goalWorkItem = AbstractDomainObject.fromOID(workItemOID);
					User activeUser = BlendedWorkflow.getInstance().getOrganizationalManager().getActiveUser();
					goalWorkItem.setUser(activeUser);
					Transaction.commit();
					
					Transaction.begin();
					BlendedWorkflow.getInstance().getWorkListManager().skipWorkItem(workItemOID);
					Transaction.commit();
					
					removeGoalWorkItem(workItemOID);
					getMainWindow().showNotification("WorkItem skipped", Notification.TYPE_TRAY_NOTIFICATION);
				} catch (java.lang.NullPointerException jle) {
					getMainWindow().showNotification("Please select a workItem to skip");
				}
			}
		});

		
		// Layouts - configurations
//		taskInfoTable.setWidth("500px");
//		taskInfoTable.setHeight("135px");
		
		//NEW
		taskInfoTable.setWidth("692px");
		taskInfoTable.setHeight("135px");
		
		//goal
		goalList.setWidth("300px");
		goalList.setHeight("450px");
		goalInfoTable.setWidth("692px");
		goalInfoTable.setHeight("155px");
		
		bwInstancesBtnLayout.setSpacing(true);
		bwInstancesBtnLayout.setMargin(true);
		bwInstancesBtnLayout.addComponent(taskExecuteBtn);
		bwInstancesBtnLayout.addComponent(taskSkipBtn);

		Label labelspace = new Label("");
		labelspace.setWidth("850px");
		bwInstancesBtnLayout.addComponent(labelspace);

		bwInstancesBtnLayout.addComponent(goalExecuteBtn);
		bwInstancesBtnLayout.addComponent(goalSkipBtn);
		
//		bwInstancesBtnLayout.addComponent(taskListFilter);

		infoVL.setMargin(true);
		infoVL.setSpacing(true);
		infoVL.addComponent(taskInfoTable);
		infoVL.addComponent(goalInfoTable);

		infoHL.addComponent(taskList);
		infoHL.addComponent(infoVL);
		infoHL.addComponent(goalList);
		infoHL.setComponentAlignment(taskList, Alignment.TOP_LEFT);
		infoHL.setComponentAlignment(goalList, Alignment.TOP_RIGHT);
		
		bwInstancesVL.addComponent(infoHL);
		bwInstancesVL.addComponent(new Label("<hr />",Label.CONTENT_XHTML));
		bwInstancesVL.addComponent(bwInstancesBtnLayout);
		bwInstancesVL.setComponentAlignment(bwInstancesBtnLayout, Alignment.TOP_LEFT);

		return bwInstancesVL;
	}

	private VerticalLayout initGoalViewTab() {
		// Layouts
		VerticalLayout goalManagerVL = new VerticalLayout();
		HorizontalLayout infoHL = new HorizontalLayout();
		VerticalLayout infoVL = new VerticalLayout();
		HorizontalLayout goalManagerBtnLayout = new HorizontalLayout();

		// Components
		goalBWInstanceList.setWidth("300px");
		goalBWInstanceList.setHeight("450px");
		goalBWInstanceList.setImmediate(true);

		goalBWInstanceList.addListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				if (goalBWInstanceList.getValue() == null) {
					goalTable.removeAllItems();
				} else {
					long bwInstanceOID = (Long) goalBWInstanceList.getValue();
					updateGoalTreeInfo(bwInstanceOID);
				}
			}
		});

		goalTable.setWidth("500px");
		goalTable.setHeight("400px");
		goalTable.addContainerProperty("Goal", String.class, "");
		goalTable.addContainerProperty("WorkItems Created", String.class, "");
		
		// Goal buttons
		Button goalActivateBtn = new Button("Activate Goal");
		goalActivateBtn.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				generateActivateGoalWindow();
			}
		});

		Button goalReDoBtn = new Button("ReDo Goal");
		goalReDoBtn.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				generateReDoGoalWindow();
			}
		});

		Button goalCreateBtn = new Button("Create New Goal");
		goalCreateBtn.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				generateNewGoalWindow();
			}
		});


		// Layouts - configurations
		goalManagerBtnLayout.setSpacing(true);
		goalManagerBtnLayout.setMargin(true);

		infoVL.setMargin(true);
		infoVL.setSpacing(true);
		infoVL.addComponent(goalTable);
		infoVL.setComponentAlignment(goalTable, Alignment.TOP_LEFT);

		infoHL.addComponent(goalBWInstanceList);
		infoHL.addComponent(infoVL);
		infoHL.setComponentAlignment(goalBWInstanceList, Alignment.TOP_LEFT);
		
		goalManagerBtnLayout.addComponent(goalActivateBtn);
		goalManagerBtnLayout.addComponent(goalReDoBtn);
		goalManagerBtnLayout.addComponent(goalCreateBtn);

		goalManagerVL.addComponent(infoHL);
		goalManagerVL.addComponent(new Label("<hr />",Label.CONTENT_XHTML));
		goalManagerVL.addComponent(goalManagerBtnLayout);
		goalManagerVL.setComponentAlignment(goalManagerBtnLayout, Alignment.TOP_LEFT);

		return goalManagerVL;
	}

	/******************************
	 * Listeners
	 ******************************/
	private void initTaskListListener() {
		taskList.addListener(taskListListener = new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				if (taskList.getValue() == null) {
					taskInfoTable.removeAllItems();
				} else {
				long workItemOID = (Long) taskList.getValue();
				updateTaskView(workItemOID); }
			}
		});
	}

	private void initGoalListListener() {
		goalList.addListener(goalListListener = new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				if (goalList.getValue() == null) {
					goalInfoTable.removeAllItems();
				} else {
					long workItemOID = (Long) goalList.getValue();
					updateGoalView(workItemOID);
				}
			}
		});
	}

	/******************************
	 * Forms generators.
	 ******************************/
	public void generateLoadForm() {
		Window loadWindow = new Window("Load a BWSpecification");
		loadWindow.setContent(new LoadForm(BWPresentation.this));
		loadWindow.center();
		loadWindow.setClosable(false);
		loadWindow.setDraggable(false);
		loadWindow.setResizable(false);
		getMainWindow().addWindow(loadWindow);	
	}
	
	public void generateLaunchForm(long bwSpecificationOID) {
		Window launchWindow = new Window("Launch a BWInstance");
		launchWindow.setContent(new LaunchForm(bwSpecificationOID));
		launchWindow.center();
		launchWindow.setClosable(false);
		launchWindow.setDraggable(false);
		launchWindow.setResizable(false);
		getMainWindow().addWindow(launchWindow);		
	}

	public void generateLoginForm() {
		VerticalLayout vlogin = new VerticalLayout();
		login.setWidth("300px");
		login.setHeight("300px");
		login.addListener(new LoginForm.LoginListener() {
			public void onLogin(LoginEvent event) {
				Transaction.begin();
				String userID = event.getLoginParameter("username");
				String userPassword = event.getLoginParameter("password");
				
				if (BlendedWorkflow.getInstance().getOrganizationalManager().loginUser(userID, userPassword)) {
					Transaction.commit();
					getMainWindow().removeWindow(loginWindow);
					initMainWindow(event.getLoginParameter("username"));
				} else {
					getMainWindow().showNotification("Username or Password incorrect.", Notification.TYPE_WARNING_MESSAGE);
				}
				
				
			}
		});
		vlogin.addComponent(login);
		vlogin.setMargin(true);
		vlogin.setHeight("140px");
		vlogin.setWidth("160px");
		vlogin.setComponentAlignment(login, Alignment.MIDDLE_CENTER);
		loginWindow.setContent(vlogin);
		loginWindow.setClosable(false);
		loginWindow.setDraggable(false);
		loginWindow.setResizable(false);
		loginWindow.center();
		getMainWindow().addWindow(loginWindow);		
	}

	public void generatePreTaskForm(long workItemOID) {
		Window taskWindow = new Window("Pre Task Form");
		taskWindow.setContent(new PreTaskForm(workItemOID));
		taskWindow.setWidth("30%");
		taskWindow.center();
		getMainWindow().addWindow(taskWindow);		
	}
	
	public void generateTaskForm(long workItemOID) {
		Window taskWindow = new Window("Task Form");
		taskWindow.setContent(new TaskForm(workItemOID));
		taskWindow.setWidth("30%");
		taskWindow.center();
		getMainWindow().addWindow(taskWindow);		
	}

	public void generateGoalForm(long workItemOID) {
		Window goalWindow = new Window("Goal Form");
		goalWindow.setContent(new GoalForm(workItemOID));
		goalWindow.setWidth("30%");
		goalWindow.center();
		getMainWindow().addWindow(goalWindow);		
	}
	
	public void generateActivateGoalWindow() {
		Window newGoalWindow = new Window("Activate Goal");
		newGoalWindow.setContent(new ActivateGoalForm());
		newGoalWindow.center();
		newGoalWindow.setClosable(false);
		newGoalWindow.setDraggable(false);
		newGoalWindow.setResizable(false);
		getMainWindow().addWindow(newGoalWindow);
	}
	
	public void generateReDoGoalWindow() {
		Window newGoalWindow = new Window("Redo Goal");
		newGoalWindow.setContent(new RedoGoalForm());
		newGoalWindow.center();
		newGoalWindow.setClosable(false);
		newGoalWindow.setDraggable(false);
		newGoalWindow.setResizable(false);
		getMainWindow().addWindow(newGoalWindow);
	}
	
	public void generateNewGoalWindow() {
		Window newGoalWindow = new Window("New Goal Form");
		newGoalWindow.setContent(new NewGoalForm());
		newGoalWindow.center();
		newGoalWindow.setClosable(false);
		newGoalWindow.setDraggable(false);
		newGoalWindow.setResizable(false);
		getMainWindow().addWindow(newGoalWindow);
	}

	/********************************************
	 * BWPresentation Data changes methods
	 ********************************************/
	public void addBWSpecification(long OID, String name) {
		this.loadedList.addItem(OID);
		this.loadedList.setItemCaption(OID, name);
	}

	public void addBWInstance(long OID, String name) {
		this.launchedList.addItem(OID);
		this.launchedList.setItemCaption(OID, name);
		this.dataList.addItem(OID);
		this.dataList.setItemCaption(OID, name);
		this.goalBWInstanceList.addItem(OID);
		this.goalBWInstanceList.setItemCaption(OID, name);
//		this.taskListFilter.addItem(OID);
//		this.taskListFilter.setItemCaption(OID, name);
//		this.goalListFilter.addItem(OID);
//		this.goalListFilter.setItemCaption(OID, name);
	}

	public void addGoalWorkItem(long OID, String ID) {
		this.goalList.addItem(OID);
		this.goalList.setItemCaption(OID, "[GOAL] " + ID);
		this.goalList.attach();
	}

	public void addTaskWorkItem(long OID, String ID) {
		this.taskList.addItem(OID);
		this.taskList.setItemCaption(OID, "[TASK] " + ID);
		this.taskList.attach();
	}

	public void addUser(long oid, String name) {
		// TODO Auto-generated method stub
	}

	public void addRole(long oid, String name) {
		// TODO Auto-generated method stub
	}

	public void removeGoalWorkItem(long OID) {
		this.goalList.removeListener(goalListListener);
		this.goalList.removeItem(OID);
		this.goalInfoTable.removeAllItems();
		initGoalListListener();
	}

	public void removeTaskWorkItem(long OID) {
		this.taskList.removeListener(taskListListener);
		this.taskList.removeItem(OID);
		this.taskInfoTable.removeAllItems();
		initTaskListListener();
	}

	public void updateTaskList(long bwInstanceOID) {
		this.taskList.removeListener(taskListListener);
		this.taskList.removeAllItems();

		BWInstance bwInstance = AbstractDomainObject.fromOID(bwInstanceOID);
		Transaction.begin();
		for (WorkItem workItem : bwInstance.getWorkItems()) {
			if (workItem.getClass().equals(TaskWorkItem.class) && (workItem.getState().equals(WorkItemState.ENABLED) ||
					workItem.getState().equals(WorkItemState.PRE_TASK))) {
				addTaskWorkItem(workItem.getOID(), workItem.getID());
			}
		}
		Transaction.commit();
		initTaskListListener();
	}

	public void updateGoalList(long bwInstanceOID) {
		this.goalList.removeListener(goalListListener);
		this.goalList.removeAllItems();
		initGoalListListener();
		BWInstance bwInstance = AbstractDomainObject.fromOID(bwInstanceOID);
		Transaction.begin();
		for (WorkItem workItem : bwInstance.getWorkItems()) {
			if (workItem.getClass().equals(GoalWorkItem.class) && workItem.getState().equals(WorkItemState.ENABLED)) {
				addGoalWorkItem(workItem.getOID(), workItem.getID());
			}
		}
		Transaction.commit();
		initGoalListListener();
	}

	public void updateBWSpecificationInfo(long OID) {
		Transaction.begin();
		bwSpecInfoTable.removeAllItems();
		bwSpecJobsInfoTable.removeAllItems();
		BWSpecification bwSpecification = AbstractDomainObject.fromOID(OID);

		// BW Info
		bwSpecInfoTable.addItem(new Object[] {"Name",bwSpecification.getName()}, new Integer(1));
		bwSpecInfoTable.addItem(new Object[] {"Author", bwSpecification.getAuthor()}, new Integer(2));
		bwSpecInfoTable.addItem(new Object[] {"Creation Date", bwSpecification.getCreationDate()}, new Integer(3));
		bwSpecInfoTable.addItem(new Object[] {"Created Instances", bwSpecification.getBwInstanceCounter()}, new Integer(4));
		bwSpecInfoTable.addItem(new Object[] {"Description", bwSpecification.getDescription()}, new Integer(5));

		// BW Tasks/Goals
		Object tasks = bwSpecJobsInfoTable.addItem(new Object[] {"Tasks","", "", ""}, null);
		Object goals = bwSpecJobsInfoTable.addItem(new Object[] {"Goals", "", "", ""}, null);
		


		TaskModel taskModel = bwSpecification.getTaskModel();
		for (Task task : taskModel.getTasks()) {    	
			Object task1 = bwSpecJobsInfoTable.addItem(new Object[] {task.getName(), task.getDescription(), task.getConstraintData(true), task.getConstraintData(false)}, null);
			bwSpecJobsInfoTable.setParent(task1, tasks);
			bwSpecJobsInfoTable.setChildrenAllowed(task1, false);
		}

		GoalModel goalModel = bwSpecification.getGoalModel();
		for (AchieveGoal goal : goalModel.getAchieveGoals()) {
			Object goal1 = bwSpecJobsInfoTable.addItem(new Object[] {goal.getName(), goal.getDescription(), goal.getSubGoalsData(), goal.getConstraintData()}, null);
			bwSpecJobsInfoTable.setParent(goal1, goals);
			bwSpecJobsInfoTable.setChildrenAllowed(goal1, false);
		}
		
		Transaction.commit();

	}

	public void updateBWInstanceInfo(long OID) {
		Transaction.begin();
		bwInstanceInfoTable.removeAllItems();
		bwInstanceJobsInfoTable.removeAllItems();
		
		BWInstance bwInstance = AbstractDomainObject.fromOID(OID);
		int executedTasksCount = 0;
		int achievedGoalsCount = 0;

		TaskModelInstance taskModelInstance = bwInstance.getTaskModelInstance();
		GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();

		for (Task task : taskModelInstance.getTasks()) {
			if (task.getState().equals(TaskState.ACHIEVED) || task.getState().equals(TaskState.SKIPPED)) {
				executedTasksCount++;
			}
		}

		for (AchieveGoal goal : goalModelInstance.getAchieveGoals()) {
			if (goal.getState().equals(GoalState.ACHIEVED) || goal.getState().equals(GoalState.SKIPPED)) {
				achievedGoalsCount++;
			}
		}

		// Info
		bwInstanceInfoTable.addItem(new Object[] {"Name", bwInstance.getName()}, new Integer(1));
		bwInstanceInfoTable.addItem(new Object[] {"Launched by", bwInstance.getUser().getID()}, new Integer(2));
		bwInstanceInfoTable.addItem(new Object[] {"Creation Date", bwInstance.getCreationDate()}, new Integer(3));
		bwInstanceInfoTable.addItem(new Object[] {"Executed Tasks", executedTasksCount + " of " + taskModelInstance.getTasksCount()}, new Integer(4));
		bwInstanceInfoTable.addItem(new Object[] {"Achieved Goals", achievedGoalsCount + " of " + goalModelInstance.getAchieveGoalsCount()}, new Integer(5));

		// JobInfo
		int jobIndex = 1;
		for (LogRecord logRecord : bwInstance.getLog().getLogRecords()) {
			bwInstanceJobsInfoTable.addItem(new Object[] {logRecord.getDate(), logRecord.getAction(), logRecord.getValue(), logRecord.getAuthor()}, new Integer(jobIndex));
			jobIndex++;
		}
		
		Transaction.commit();
	}

	public void updateBWInstanceDataInfo(long OID) {
		Transaction.begin();
		entitydetailsTreetable.removeAllItems();

		BWInstance bwInstance = AbstractDomainObject.fromOID(OID);
		DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();

		for (Entity entity : dataModelInstance.getEntities()) {
			Object entityItem = entitydetailsTreetable.addItem(new Object[] {entity.getName(),"",""}, null);
			for (EntityInstance entityInstance : entity.getEntityInstances()) {
				Object entityInstanceItem = entitydetailsTreetable.addItem(new Object[] {entityInstance.getID(),"",entityInstance.getState()}, null);
				entitydetailsTreetable.setParent(entityInstanceItem, entityItem);
				for (AttributeInstance attributeInstance : entityInstance.getAttributeInstances()) {
					String value =" ";
					if (attributeInstance.getValue() != "$UNDEFINED$" && attributeInstance.getValue() != "$SKIPPED$")
						value = attributeInstance.getValue();
					Object attributeInstanceItem = entitydetailsTreetable.addItem(new Object[] {attributeInstance.getAttribute().getName(), value, attributeInstance.getState()}, null);
					entitydetailsTreetable.setParent(attributeInstanceItem, entityInstanceItem);
					entitydetailsTreetable.setChildrenAllowed(attributeInstanceItem, false);
				}
			}
		}
		Transaction.commit();
	}
	
	public void updateGoalTreeInfo(long OID) {
		Transaction.begin();
		goalTable.removeAllItems();

		BWInstance bwInstance = AbstractDomainObject.fromOID(OID);
		GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();
		
		// Add Goals
		HashMap<AchieveGoal, Object> addedGoals = new HashMap<AchieveGoal, Object>();
		for (AchieveGoal goal : goalModelInstance.getAchieveGoals()) {
			int timesExecuted = 0;
			if (goal.getGoalWorkItem() != null) {
				timesExecuted = 1;
			}
			
			Object goalItem = goalTable.addItem(new Object[] {goal.getName(), timesExecuted}, null);
			addedGoals.put(goal, goalItem);
		}
		
		// Goal Relations
		for (AchieveGoal goal : goalModelInstance.getAchieveGoals()) {
			if (goal.getParentGoal() != null) {
				Object goalObject = addedGoals.get(goal);
				Object parentGoalObject = addedGoals.get(goal.getParentGoal());
				goalTable.setParent(goalObject, parentGoalObject);
				if (goal.getSubGoalsCount() == 0) {
					goalTable.setChildrenAllowed(goalObject, false);
				}
			}
		}
		
		Iterator<Entry<AchieveGoal, Object>> itr = addedGoals.entrySet().iterator();
		while(itr.hasNext()) {
			goalTable.setCollapsed(itr.next(), false);
		}

		Transaction.commit();
	}

	public void updateTaskView(long OID) {
		Transaction.begin();
		taskInfoTable.removeAllItems();
		TaskWorkItem taskWorkItem = AbstractDomainObject.fromOID(OID);
		Task task = taskWorkItem.getTask();

		String inputData = "";
		int inputIndex = 0;
		ArrayList<Entity> inputEntities = new ArrayList<Entity>();
		for (AttributeInstance attributeInstance : taskWorkItem.getPreConstraintAttributeInstances()) {
			Entity entity = attributeInstance.getAttribute().getEntity();
			if (!inputEntities.contains(entity)) {
				inputEntities.add(entity);
			}
		}
		for (Entity entity : inputEntities) {
			if (inputIndex == 0) {
				inputData += entity.getName();
			}
			else {
				inputData += ", " + entity.getName();
			}
			inputIndex++;
		}

		String outputData = "";
		int outputIndex = 0;
		ArrayList<Entity> outputEntities = new ArrayList<Entity>();
		for (AttributeInstance attributeInstance : taskWorkItem.getContraintViolationAttributeInstances()) {
			Entity entity = attributeInstance.getAttribute().getEntity();
			if (!outputEntities.contains(entity)) {
				outputEntities.add(entity);
			}
		}
		for (Entity entity : outputEntities) {
			if (outputIndex == 0) {
				outputData += entity.getName();
			}
			else {
				outputData += ", " + entity.getName();
			}
			outputIndex++;
		}

		// Populate Data
		taskInfoTable.addItem(new Object[] {"Name",task.getName()}, new Integer(1));
		taskInfoTable.addItem(new Object[] {"Description", task.getDescription()}, new Integer(2));
		taskInfoTable.addItem(new Object[] {"Input Data", inputData}, new Integer(3));
		taskInfoTable.addItem(new Object[] {"Output Data", outputData}, new Integer(4));
		taskInfoTable.addItem(new Object[] {"Previous Task",task.getPrevious()}, new Integer(5));
		
//		taskInfoTable.setWidth("100%");
		Transaction.commit();

	}

	public void updateGoalView(long OID) {
		Transaction.begin();
		goalInfoTable.removeAllItems();
		GoalWorkItem goalWorkItem = AbstractDomainObject.fromOID(OID);
		AchieveGoal goal = goalWorkItem.getAchieveGoal();
		
		String inputData = "None";
		String subGoals ="None";
		int inputIndex = 0;
		int subGoalIndex = 0;
		ArrayList<Entity> inputEntities = new ArrayList<Entity>();

		for (AchieveGoal subGoal : goal.getSubGoals()) {
			for (AttributeInstance attributeInstance : subGoal.getGoalWorkItem().getContraintViolationAttributeInstances()) {
				Entity entity = attributeInstance.getAttribute().getEntity();
				if (!inputEntities.contains(entity)) {
					inputEntities.add(entity);
				}
			}
			if (subGoalIndex == 0) {
				subGoals = subGoal.getName();
			}
			else {
				subGoals += ", " + subGoal.getName();
			}
			subGoalIndex++;
		}

		for (Entity entity : inputEntities) {
			if (inputIndex == 0) {
				inputData = entity.getName();
			}
			else {
				inputData += ", " + entity.getName();
			}
			inputIndex++;
		}

		String outputData = "";
		int outputIndex = 0;
		ArrayList<Entity> outputEntities = new ArrayList<Entity>();
		for (AttributeInstance attributeInstance : goalWorkItem.getContraintViolationAttributeInstances()) {
			Entity entity = attributeInstance.getAttribute().getEntity();
			if (!outputEntities.contains(entity)) {
				outputEntities.add(entity);
			}
		}
		for (Entity entity : outputEntities) {
			if (outputIndex == 0) {
				outputData += entity.getName();
			}
			else {
				outputData += ", " + entity.getName();
			}
			outputIndex++;
		}
		
		String parentGoal = "None";
		if (goal.getParentGoal() != null) {
			parentGoal = goal.getParentGoal().getName();
		}

		goalInfoTable.addItem(new Object[] {"Name", goal.getName()}, new Integer(1));
		goalInfoTable.addItem(new Object[] {"Description", goal.getDescription()}, new Integer(2));
		goalInfoTable.addItem(new Object[] {"Parent Goal", parentGoal}, new Integer(3));
		goalInfoTable.addItem(new Object[] {"Sub Goals", subGoals}, new Integer(4));
		goalInfoTable.addItem(new Object[] {"Input Data", inputData}, new Integer(5));
		goalInfoTable.addItem(new Object[] {"Output Data", outputData}, new Integer(6));

//		goalInfoTable.setWidth("100%");
		Transaction.commit();
	}

}