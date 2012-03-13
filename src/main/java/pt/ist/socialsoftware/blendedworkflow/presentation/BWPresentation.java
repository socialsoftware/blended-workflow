package pt.ist.socialsoftware.blendedworkflow.presentation;

import pt.ist.socialsoftware.blendedworkflow.worklistmanager.WorkListManager;

import com.vaadin.Application;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.Notification;

@SuppressWarnings("serial")
public class BWPresentation extends Application {
	
	private GridLayout grid = new GridLayout(2,3);
	private CssLayout toolbar = new CssLayout(); // bar on the top
	private CssLayout right = new CssLayout(); //app menu (on right side)
	private ListSelect taskList = new ListSelect(); // the task list
	private ListSelect goalList = new ListSelect(); // the goal list
	
	@Override
	public void init() {
		setTheme("reindeermods");
		Window mainWindow = new Window("Blended Workflow");
		toolbar.setWidth("100%");
		toolbar.addStyleName("toolbar-invert");
		
        CssLayout left = new CssLayout();
        left.setSizeUndefined();
        left.addStyleName("left");
        toolbar.addComponent(left);
        
        //Add app name
        Label title = new Label("Blended Workflow");
        title.addStyleName("h1");
        left.addComponent(title);
        
        //add app menu (on right side)
        right.setSizeUndefined();
        right.addStyleName("right");
        toolbar.addComponent(right);
        
        Button adminPanel = new Button("Admin Panel");
        adminPanel.addStyleName("borderless");
        
        // add handle to button
        adminPanel.addListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				WorkListManager.getInstance().init(BWPresentation.this);
				ControlPanel ctrlPanel = new ControlPanel();
				ctrlPanel.center();
				getMainWindow().addWindow(ctrlPanel);
			}
		});
        right.addComponent(adminPanel);
        
        Label splitter = new Label("|");
        right.addComponent(splitter);
        
        Button logout = new Button("Logout");
        logout.addStyleName("borderless");
        
        //TODO add handle to button
        logout.addListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				getMainWindow().showNotification("This feature is still to implement", Notification.TYPE_WARNING_MESSAGE);
			}
		});
        right.addComponent(logout);
        
		
		grid.setSpacing(true);
		grid.setWidth("960px");
		
		Label taskLabel = new Label("Tasks");
		taskLabel.setWidth(null);
		Label goalLabel = new Label("Goals");
		goalLabel.setWidth(null);
		grid.addComponent(taskLabel, 0, 0);
		grid.setComponentAlignment(taskLabel, Alignment.TOP_LEFT);
		
		grid.addComponent(goalLabel, 1, 0);
		grid.setComponentAlignment(goalLabel, Alignment.TOP_RIGHT);
		
		//configure task list
		taskList.setWidth("460px");
		taskList.removeAllItems();
		grid.addComponent(taskList, 0, 1);

		//configure goal list
		goalList.setWidth("460px");
		goalList.removeAllItems();
		grid.addComponent(goalList, 1, 1);
		grid.setComponentAlignment(goalList, Alignment.TOP_RIGHT);

		//add task buttons
		HorizontalLayout taskBtnLayout = new HorizontalLayout();
		taskBtnLayout.setSpacing(true);
		Button taskExecuteBtn = new Button("Execute");
		taskExecuteBtn.addListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
//				String taskName = (String) taskList.getValue();
//				if(!taskName.equals("")) {
//					WorklistManager.get().init(BlendedworkflowserviceApplication.this);
//					WorklistManager.get().executeTask(taskName);
//				} else {
//					getMainWindow().showNotification("Please select a task to execute", Notification.TYPE_WARNING_MESSAGE);
//				}
			}
		});
		Button taskSkipBtn = new Button("Skip");
		taskSkipBtn.addListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
//				String taskName = (String) taskList.getValue();
//				if(!taskName.equals("")) {
//					WorklistManager.get().init(BlendedworkflowserviceApplication.this);
//					WorklistManager.get().registerSkippedTask(taskName);
//				} else {
//					getMainWindow().showNotification("Please select a task to skip", Notification.TYPE_WARNING_MESSAGE);
//				}
			}

		});
		
		taskBtnLayout.addComponent(taskExecuteBtn);
		taskBtnLayout.addComponent(taskSkipBtn);
		
		this.grid.addComponent(taskBtnLayout, 0, 2);
		this.grid.setComponentAlignment(taskBtnLayout, Alignment.TOP_CENTER);
		
		//add goal buttons
		HorizontalLayout goalBtnLayout = new HorizontalLayout();
		goalBtnLayout.setSpacing(true);
		Button goalExecuteBtn = new Button("Execute");
		Button goalSkipBtn = new Button("Skip");
		Button goalCreateBtn = new Button("Create");
		goalBtnLayout.addComponent(goalExecuteBtn);
		goalBtnLayout.addComponent(goalSkipBtn);
		goalBtnLayout.addComponent(goalCreateBtn);
		
		//TODO add the listeners
		goalExecuteBtn.addListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
//				// get the selected goal
//				String goalItem = (String) goalList.getValue();
//				if(!goalItem.equals("")) {
//					WorklistManager.get().init(BlendedworkflowserviceApplication.this);
//					String goalName = null;
//					if(goalItem.contains("(Optional)")) {
//						goalName = goalItem.replace(" (Optional)", "");
//					} else {
//						goalName = goalItem.replace(" (Mandatory)", "");
//					}
//					WorklistManager.get().executeGoal(goalName);
//				} else {
//					getMainWindow().showNotification("Please select a goal", Notification.TYPE_WARNING_MESSAGE);
//				}
			}
		});
		
		goalSkipBtn.addListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
//				String goalName = (String) goalList.getValue();
//				if(!goalName.equals("")) {
//					WorklistManager.get().init(BlendedworkflowserviceApplication.this);
//					WorklistManager.get().skipGoal(goalName);
//				} else {
//					getMainWindow().showNotification("Please select a goal", Notification.TYPE_WARNING_MESSAGE);
//				}
			}
		});
		
		goalCreateBtn.addListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
//				WorklistManager.get().init(BlendedworkflowserviceApplication.this);
//				String selectedGoal = (String) goalList.getValue();
//				if(null == selectedGoal || selectedGoal.equals("")) {
//					getMainWindow().showNotification("Please select a goal");
//					return;
//				}
//				String[] selectedGoalSplit = selectedGoal.split("[:]");
//				generateNewGoalWindow(selectedGoalSplit[0]);
			}
		});
		
		this.grid.addComponent(goalBtnLayout, 1, 2);
		this.grid.setComponentAlignment(goalBtnLayout, Alignment.TOP_CENTER);
		
		//put grid on horizontal layout
        HorizontalLayout hLayout = new HorizontalLayout();
        hLayout.setWidth("100%");
        hLayout.addComponent(grid);
        hLayout.setComponentAlignment(grid, Alignment.TOP_CENTER);
        hLayout.setMargin(true);
		
        //add to main window
		mainWindow.addComponent(toolbar);
		mainWindow.addComponent(hLayout);
		
		setMainWindow(mainWindow);
	}
//	
//	public void addTask(String taskName) {
//		this.taskList.addItem(taskName);
//	}
//	
//	public void addGoal(String goalName, boolean isMandatory) {
//		String goalItem = (isMandatory) ? goalName + " (Mandatory)" : goalName + " (Optional)";
//		if(this.goalList.containsId(goalItem)) {
//			return;
//		}
//			this.goalList.addItem(goalItem);
//	}
//	
//	public void removeTask(String taskName) {
//		this.taskList.removeItem(taskName);
//	}
//	
//	public void removeGoal(String goalName) {
//		this.goalList.removeItem(goalName);
//	}
//	
//	public void executeTask(TaskInfo taskInformation, boolean isActivity) {
//		Window taskWindow = new Window(taskInformation.getTaskType() + ": " + taskInformation.getTaskIDForDisplay());
//		// add the form
//		taskWindow.setContent(new TaskForm(taskInformation, isActivity));
//		// show the window
//		taskWindow.center();
//		getMainWindow().addWindow(taskWindow);		
//	}
//	
//	public void generateNewGoalWindow(String goalInstanceID) {
//		Window newGoalWindow = new Window("New Goal");
//		String goalCase = CaseManager.get().getGoalCaseInstanceFromBWCase(goalInstanceID);
//		newGoalWindow.setContent(new NewGoalForm(this, goalCase));
//		
//		newGoalWindow.center();
//		getMainWindow().addWindow(newGoalWindow);
//	}
//	
//	public String getSelectedGoalID() {
//		return this.goalList.getValue().toString();
//	}
	
	
	/************************
	 * TEST 
	 ************************/
	
//	final Window main = new Window("FF Test");
//	setMainWindow(main);
//
//	final TextField results = new TextField("ADD:");
//	results.setWidth("100%");
//	main.addComponent(results);
//
//	final TextField resultsc = new TextField("Count:", "...");
//	resultsc.setWidth("100%");
//	main.addComponent(resultsc);
//
//	Bootstrap.init();
//
//	Button adminPanel = new Button("Add BW Spec");
//	adminPanel.addListener(new ClickListener() {
//
//		@Override
//		public void buttonClick(ClickEvent event) {
//			Transaction.begin();
//			try {
//				BlendedWorkflow.getInstance().addBwSpecifications(new BWSpecification(results.getValue().toString()));
//				getMainWindow().showNotification("AYE YEAH!");
//			} catch (BlendedWorkflowException e) {
//				getMainWindow().showNotification("OH NO... Já existe");
//			}
//			Transaction.commit();
//		}
//	});
//	main.addComponent(adminPanel);
//
//	Button x = new Button("Count of BW Spec");
//	x.addListener(new ClickListener() {
//
//		@Override
//		public void buttonClick(ClickEvent event) {
//			Transaction.begin();
//			resultsc.setValue(BlendedWorkflow.getInstance().getBwSpecificationsCount());
//			getMainWindow().showNotification("AYE YEAH!");
//			Transaction.commit();
//		}
//	});
//	main.addComponent(x);
	
}


