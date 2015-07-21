package pt.ist.socialsoftware.blendedworkflow.presentation;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.Application;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.event.Action;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.LoginForm.LoginEvent;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.Table;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.Notification;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.domain.AchieveGoal;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWGoalModel;
import pt.ist.socialsoftware.blendedworkflow.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation;
import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.domain.GoalWorkItem.GoalState;
import pt.ist.socialsoftware.blendedworkflow.domain.LogRecord;
import pt.ist.socialsoftware.blendedworkflow.domain.MaintainGoal;
import pt.ist.socialsoftware.blendedworkflow.domain.MaintainGoal.MaintainGoalState;
import pt.ist.socialsoftware.blendedworkflow.domain.Role;
import pt.ist.socialsoftware.blendedworkflow.domain.Task;
import pt.ist.socialsoftware.blendedworkflow.domain.TaskModel;
import pt.ist.socialsoftware.blendedworkflow.domain.TaskModelInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.TaskWorkItem;
import pt.ist.socialsoftware.blendedworkflow.domain.TaskWorkItem.ActivityState;
import pt.ist.socialsoftware.blendedworkflow.domain.User;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.shared.Bootstrap;

@SuppressWarnings("serial")
public class BWPresentation extends Application {

    public static Class<?> tmp_class;
    private static Constructor<?> tmp_const;

    private String username = "";
    private User user = null;
    private final Logger log = LoggerFactory.getLogger("BWPresentation");
    private Boolean bwManagerAcess = false;
    private Boolean worklistManagerAcess = false;

    // Main Window
    private final Window mainWindow = new Window("Blended Workflow");
    private final Window loginWindow = new Window("Login");
    private final LoginForm login = new LoginForm();
    private final CssLayout toolbar = new CssLayout();
    private final CssLayout left = new CssLayout();
    private final CssLayout right = new CssLayout();
    private TabSheet bwTabSheet;
    private Tab bwSpecTab, bwInstanceTab, bwDataTab, taskViewTab, goalViewTab;
    private final Button bwManagerBtn = new Button("BW Manager");
    private final Button wManagerBtn = new Button("Worklist Manager");

    // BWSpecTab
    private final ListSelect loadedList = new ListSelect("BW Specifications:");
    private final Table bwSpecInfoTable = new Table(
            "BW Specification Information:");
    private final TreeTable bwSpecJobsInfoTable = new TreeTable(
            "Activities/Goals:");

    // BWInstanceTab
    private final ListSelect launchedList = new ListSelect("BW Instances:");
    private final Table bwInstanceInfoTable = new Table(
            "BW Instance Information:");
    private final Table bwInstanceJobsInfoTable = new Table("Jobs Executed:");

    // DataModelTab
    private final ListSelect dataList = new ListSelect("BW Instances:");
    private final TreeTable entitydetailsTreetable = new TreeTable(
            "Data Model:");

    // TaskViewTab
    private final ListSelect taskList = new ListSelect("Activities:");
    private Table taskInfoTable = new Table("Activity Information:");
    private Property.ValueChangeListener taskListListener;
    private final ListSelect goalList = new ListSelect("Goals:");
    private final Table goalInfoTable = new Table("Goal Information:");
    private Property.ValueChangeListener goalListListener;
    // private NativeSelect taskListFilter = new NativeSelect();

    // GoalViewTab
    private final ListSelect goalBWInstanceList = new ListSelect(
            "BW Instances:");
    private final TreeTable goalTable = new TreeTable("Achieve Goals:");
    // private NativeSelect goalListFilter = new NativeSelect();
    private final Table goalWorkItemTree = new Table("GoalWorkItems:");
    private final Table maintainGoalsTree = new Table("Maintain Goals:");
    private static final Action ACTIVATE_GOAL_ACTION = new Action(
            "Activate Goal");
    private static final Action REDO_GOAL_ACTION = new Action(
            "Redo GoalWorkItem");
    private static final Action NEW_GOAL_ACTION = new Action("Create New Goal");
    private static final Action ENABLE_CONDITION_ACTION = new Action(
            "Enable Condition");
    private static final Action DISABLE_CONDITION_ACTION = new Action(
            "Disable Condition");

    private Application bwPresentation;

    /**
     * Initialize the Application.
     */
    @Override
    public void init() {
        bwPresentation = this;
        // Init and populate database
        Bootstrap.init();
        registerBWPresentation();
        registerBWPresentation();
        initLoginWindow();
    }

    /**
     * Register the BWPresentation with the BWManager and WorklistManager.
     */
    @Atomic(mode = TxMode.WRITE)
    private void registerBWPresentation() {
        BlendedWorkflow.getInstance().getBwManager()
                .setBwPresentation(BWPresentation.this);
        BlendedWorkflow.getInstance().getWorkListManager()
                .setBwPresentation(BWPresentation.this);
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

    @Atomic(mode = TxMode.WRITE)
    private void initMainWindow(String name) {
        // Refresh HACK!
        final ProgressIndicator progressindicator = new ProgressIndicator(
                new Float(0.0));
        // Invible Solution - do not work
        // #1Solution
        // progressindicator.setStyleName("invisible");
        // toolbar.addComponent(progressindicator);
        // #2Solution
        // HorizontalLayout progressindicatorHL = new HorizontalLayout();
        // progressindicatorHL.setStyleName("invisible");
        // progressindicatorHL.addComponent(progressindicator);
        // toolbar.addComponent(progressindicatorHL);
        // #3Solution
        progressindicator.setWidth("1px");
        progressindicator.setHeight("1px");
        progressindicator.setPollingInterval(2000);
        // Refresh HACK!

        this.username = name + "   ";
        DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT);
        getMainWindow().showNotification("Welcome " + this.username);
        Label welcome = new Label(dateFormatter.format(new java.util.Date())
                + "  Welcome " + this.username + "   ");
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
        right.addComponent(progressindicator);
        right.addComponent(welcome);

        this.bwTabSheet = new TabSheet();
        this.bwTabSheet.setHeight("100%");
        this.bwTabSheet.setWidth("100%");

        // Role Manager
        user = BlendedWorkflow.getInstance().getOrganizationalManager()
                .getActiveUser();
        Role userRole = user.getRole();
        if (userRole.getBwManagerAcess()) {
            right.addComponent(bwManagerBtn);
            right.addComponent(splitter);
            VerticalLayout bwManagerVL = initBWSpecTab();
            VerticalLayout bwInstancesVL = initBWInstancesTab();
            VerticalLayout bwInstancesDataVL = initBWInstanceDataTab();
            bwSpecTab = this.bwTabSheet.addTab(bwManagerVL,
                    "BW Specifications");
            bwInstanceTab = this.bwTabSheet.addTab(bwInstancesVL,
                    "BW Instances");
            bwDataTab = this.bwTabSheet.addTab(bwInstancesDataVL, "Data Model");
            BlendedWorkflow.getInstance().getBwManager().updateBWPresentation();
            bwManagerAcess = true;
            // bwSpecTab.setEnabled(true);
            // bwInstanceTab.setEnabled(true);
            // bwDataTab.setEnabled(true);
        }
        if (userRole.getOrganizationalManagerAcess()) {
            // TODO: OrganizationalTab
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

            BlendedWorkflow.getInstance().getWorkListManager()
                    .updateBWPresentation();
            worklistManagerAcess = true;
        }

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
            @Override
            public void valueChange(ValueChangeEvent event) {
                if (loadedList.getValue() == null) {
                    bwSpecInfoTable.removeAllItems();
                    bwSpecJobsInfoTable.removeAllItems();
                } else {
                    String bwSpecificationOID = (String) loadedList.getValue();
                    updateBWSpecificationInfo(bwSpecificationOID);
                }
            }
        });

        bwSpecInfoTable.addContainerProperty("Name", String.class, null);
        bwSpecInfoTable.addContainerProperty("Value", String.class, null);

        bwSpecJobsInfoTable.addContainerProperty("Name", String.class, "");
        bwSpecJobsInfoTable.addContainerProperty("Description", String.class,
                "");
        bwSpecJobsInfoTable.addContainerProperty("Input Data", String.class,
                "");
        bwSpecJobsInfoTable.addContainerProperty("Output Data", String.class,
                "");

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
                // TODO:FutureImplementation: UnLoad
                getMainWindow().showNotification("Not Yet Implemented");
            }
        });

        Button bwInstanceCreateBtn = new Button("Launch");
        bwInstanceCreateBtn.addListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                try {
                    String bwSpecificationOID = (String) loadedList.getValue();
                    generateLaunchForm(bwSpecificationOID);
                } catch (java.lang.NullPointerException jle) {
                    getMainWindow().showNotification(
                            "Please select one specification to launch");
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
        infoVL.setComponentAlignment(bwSpecJobsInfoTable,
                Alignment.BOTTOM_LEFT);
        infoHL.addComponent(loadedList);
        infoHL.addComponent(infoVL);
        infoHL.setComponentAlignment(loadedList, Alignment.TOP_LEFT);
        bwSpecVL.addComponent(infoHL);
        bwSpecVL.addComponent(new Label("<hr />", Label.CONTENT_XHTML));
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
            @Override
            public void valueChange(ValueChangeEvent event) {
                if (launchedList.getValue() == null) {
                    bwInstanceInfoTable.removeAllItems();
                    bwInstanceJobsInfoTable.removeAllItems();
                } else {
                    String bwInstanceOID = (String) launchedList.getValue();
                    updateBWInstanceInfo(bwInstanceOID);
                }
            }
        });

        bwInstanceInfoTable.setWidth("300px");
        bwInstanceInfoTable.setHeight("130px");
        bwInstanceInfoTable.addContainerProperty("Name", String.class, null);
        bwInstanceInfoTable.addContainerProperty("Value", String.class, null);

        bwInstanceJobsInfoTable.setWidth("800px");
        bwInstanceJobsInfoTable.setHeight("260px");
        bwInstanceJobsInfoTable.addContainerProperty("Date", String.class, "");
        bwInstanceJobsInfoTable.addContainerProperty("Action", String.class,
                "");
        bwInstanceJobsInfoTable.addContainerProperty("Value", String.class, "");
        bwInstanceJobsInfoTable.addContainerProperty("Author", String.class,
                "");

        Button bwInstanceCancelBtn = new Button("Cancel");
        bwInstanceCancelBtn.addListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                // TODO:FutureImplementation: Cancel
                getMainWindow().showNotification("Not Yet Implemented");
            }
        });

        // Layouts - configurations
        bwInstancesBtnLayout.setSpacing(true);
        bwInstancesBtnLayout.setMargin(true);
        bwInstancesBtnLayout.addComponent(bwInstanceCancelBtn);
        bwInstancesBtnLayout.setComponentAlignment(bwInstanceCancelBtn,
                Alignment.TOP_RIGHT);

        infoVL.setMargin(true);
        infoVL.setSpacing(true);
        infoVL.addComponent(bwInstanceInfoTable);
        infoVL.addComponent(bwInstanceJobsInfoTable);
        infoVL.setComponentAlignment(bwInstanceJobsInfoTable,
                Alignment.BOTTOM_LEFT);

        infoHL.addComponent(launchedList);
        infoHL.addComponent(infoVL);
        infoHL.setComponentAlignment(launchedList, Alignment.TOP_LEFT);

        bwInstancesVL.addComponent(infoHL);
        bwInstancesVL.addComponent(new Label("<hr />", Label.CONTENT_XHTML));
        bwInstancesVL.addComponent(bwInstancesBtnLayout);
        bwInstancesVL.setComponentAlignment(bwInstancesBtnLayout,
                Alignment.TOP_LEFT);

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
            @Override
            public void valueChange(ValueChangeEvent event) {
                if (dataList.getValue() == null) {
                    entitydetailsTreetable.removeAllItems();
                } else {
                    String bwSpecificationOID = (String) dataList.getValue();
                    updateBWInstanceDataInfo(bwSpecificationOID);
                }
            }
        });

        entitydetailsTreetable.setWidth("500px");
        entitydetailsTreetable.setHeight("400px");
        entitydetailsTreetable.addContainerProperty("Entity/Attribute",
                String.class, "");
        entitydetailsTreetable.addContainerProperty("Value", String.class, "");
        entitydetailsTreetable.addContainerProperty("State", String.class, "");

        // Layouts - configurations
        bwInstancesBtnLayout.setSpacing(true);
        bwInstancesBtnLayout.setMargin(true);

        infoVL.setMargin(true);
        infoVL.setSpacing(true);
        infoVL.addComponent(entitydetailsTreetable);
        infoVL.setComponentAlignment(entitydetailsTreetable,
                Alignment.TOP_LEFT);

        infoHL.addComponent(dataList);
        infoHL.addComponent(infoVL);
        infoHL.setComponentAlignment(dataList, Alignment.TOP_LEFT);

        bwInstancesVL.addComponent(infoHL);
        bwInstancesVL.addComponent(new Label("<hr />", Label.CONTENT_XHTML));
        bwInstancesVL.addComponent(bwInstancesBtnLayout);
        bwInstancesVL.setComponentAlignment(bwInstancesBtnLayout,
                Alignment.TOP_LEFT);

        return bwInstancesVL;
    }

    @Atomic(mode = TxMode.WRITE)
    private VerticalLayout initTaskViewTab() {
        // Layouts
        VerticalLayout bwInstancesVL = new VerticalLayout();
        HorizontalLayout infoHL = new HorizontalLayout();
        VerticalLayout infoVL = new VerticalLayout();
        HorizontalLayout bwInstancesBtnLayout = new HorizontalLayout();

        // Components
        // taskListFilter.addListener(new Property.ValueChangeListener() {
        // public void valueChange(ValueChangeEvent event) {
        // String workItemOID = (String) taskListFilter.getValue();
        // updateTaskList(workItemOID);
        // }
        // });

        taskList.setWidth("300px");
        taskList.setHeight("450px");

        taskList.setImmediate(true);
        initTaskListListener();

        taskInfoTable = new Table("Activity Information:");

        taskInfoTable.addContainerProperty("Name", String.class, null);
        taskInfoTable.addContainerProperty("Value", String.class, null);

        // goal
        goalList.setImmediate(true);
        initGoalListListener();

        goalInfoTable.addContainerProperty("Name", String.class, null);
        goalInfoTable.addContainerProperty("Value", String.class, null);

        Button taskExecuteBtn = new Button("Execute Activity");
        taskExecuteBtn.addListener(new ClickListener() {
            @Atomic(mode = TxMode.WRITE)
            @Override
            public void buttonClick(ClickEvent event) {
                try {
                    String workItemOID = (String) taskList.getValue();

                    TaskWorkItem taskWorkItem = FenixFramework
                            .getDomainObject(workItemOID);
                    Boolean isPreTask = false;
                    if (taskWorkItem.getState()
                            .equals(ActivityState.PRE_ACTIVITY)) {
                        isPreTask = true;
                    }

                    String specificationName = taskWorkItem.getBwInstance()
                            .getSpecification().getName().replaceAll(" ", "");
                    String taskName = taskWorkItem.getTask().getName()
                            .replaceAll(" ", "");
                    String className = specificationName + "." + taskName
                            + "Form";

                    if (isPreTask) {
                        generatePreTaskForm(workItemOID);
                    } else {
                        generateTaskForm(workItemOID, className);
                    }

                } catch (java.lang.NullPointerException jle) {
                    getMainWindow().showNotification(
                            "Please select a workItem to execute");
                }
            }
        });

        Button taskSkipBtn = new Button("Skip Activity");
        taskSkipBtn.addListener(new ClickListener() {
            @Atomic(mode = TxMode.WRITE)
            @Override
            public void buttonClick(ClickEvent event) {
                try {
                    String workItemOID = (String) taskList.getValue();
                    TaskWorkItem taskWorkItem = FenixFramework
                            .getDomainObject(workItemOID);
                    User activeUser = BlendedWorkflow.getInstance()
                            .getOrganizationalManager().getActiveUser();
                    taskWorkItem.setUser(activeUser);

                    BlendedWorkflow.getInstance().getWorkListManager()
                            .skipWorkItem(workItemOID);
                } catch (java.lang.NullPointerException jle) {
                    getMainWindow().showNotification(
                            "Please select a workItem to skip");
                }
            }
        });

        // goal
        Button goalExecuteBtn = new Button("Achieve Goal");
        goalExecuteBtn.addListener(new ClickListener() {
            @Atomic(mode = TxMode.WRITE)
            @Override
            public void buttonClick(ClickEvent event) {
                try {
                    String workItemOID = (String) goalList.getValue();

                    GoalWorkItem goalWorkItem = FenixFramework
                            .getDomainObject(workItemOID);
                    Boolean isPreGoal = false;
                    if (goalWorkItem.getState().equals(GoalState.PRE_GOAL)) {
                        isPreGoal = true;
                    }
                    if (isPreGoal) {
                        generatePreGoalForm(workItemOID);
                    } else {
                        generateGoalForm(workItemOID);
                    }
                } catch (java.lang.NullPointerException jle) {
                    getMainWindow().showNotification(
                            "Please select a workItem to execute");
                }
            }
        });

        Button goalSkipBtn = new Button("Skip Goal");
        goalSkipBtn.addListener(new ClickListener() {
            @Atomic(mode = TxMode.WRITE)
            @Override
            public void buttonClick(ClickEvent event) {
                try {
                    String workItemOID = (String) goalList.getValue();

                    GoalWorkItem goalWorkItem = FenixFramework
                            .getDomainObject(workItemOID);
                    User activeUser = BlendedWorkflow.getInstance()
                            .getOrganizationalManager().getActiveUser();
                    goalWorkItem.setUser(activeUser);

                    Boolean isPreGoal = false;
                    if (goalWorkItem.getState().equals(GoalState.PRE_GOAL)) {
                        isPreGoal = true;
                    }

                    if (isPreGoal) {
                        getMainWindow().showNotification(
                                "A GoalWorkItem in a Pre-Goal State cannot be Skipped.");
                    } else {
                        BlendedWorkflow.getInstance().getWorkListManager()
                                .skipWorkItem(workItemOID);
                    }

                    // BlendedWorkflow.getInstance().getWorkListManager().skipWorkItem(workItemOID);

                } catch (java.lang.NullPointerException jle) {
                    getMainWindow().showNotification(
                            "Please select a workItem to skip");
                }
            }
        });

        // Layouts - configurations
        // taskInfoTable.setWidth("500px");
        // taskInfoTable.setHeight("135px");

        // NEW
        taskInfoTable.setWidth("692px");
        taskInfoTable.setHeight("135px");

        // goal
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

        // bwInstancesBtnLayout.addComponent(taskListFilter);

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
        bwInstancesVL.addComponent(new Label("<hr />", Label.CONTENT_XHTML));
        bwInstancesVL.addComponent(bwInstancesBtnLayout);
        bwInstancesVL.setComponentAlignment(bwInstancesBtnLayout,
                Alignment.TOP_LEFT);

        return bwInstancesVL;
    }

    @Atomic(mode = TxMode.WRITE)
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
            @Override
            public void valueChange(ValueChangeEvent event) {
                if (goalBWInstanceList.getValue() == null) {
                    goalTable.removeAllItems();
                    maintainGoalsTree.removeAllItems();
                } else {
                    updateGoalTreeInfo();
                }
            }
        });

        goalTable.setWidth("450px");
        goalTable.setHeight("190px");
        goalTable.addContainerProperty("OID", String.class, "");
        goalTable.addContainerProperty("Goal", String.class, "");
        goalTable.setVisibleColumns(new Object[] { "Goal" });

        goalTable.addActionHandler(new Action.Handler() {
            @Override
            public void handleAction(Action action, Object sender,
                    Object target) {
                String bwInstanceOID = (String) goalBWInstanceList.getValue();
                if (action == ACTIVATE_GOAL_ACTION) {
                    String goalOID = (String) goalTable
                            .getContainerProperty(target, "OID").getValue();
                    generateActivateGoalWindow(bwInstanceOID, goalOID);
                    // updateGoalTreeInfo();
                } else {
                    String goalOID = (String) goalTable
                            .getContainerProperty(target, "OID").getValue();
                    generateNewGoalWindow(bwInstanceOID, goalOID);
                    // updateGoalTreeInfo();
                }
            }

            @Override
            public Action[] getActions(Object target, Object sender) {
                if (target == null) {
                    return new Action[] {};
                } else {
                    return new Action[] { ACTIVATE_GOAL_ACTION,
                            NEW_GOAL_ACTION };
                }
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

        // Maintain
        VerticalLayout novo = new VerticalLayout();
        novo.setMargin(true);
        novo.setSpacing(true);

        // WorkItems
        goalWorkItemTree.addContainerProperty("OID", String.class, null);
        goalWorkItemTree.addContainerProperty("GoalWorkItems", String.class,
                null);
        goalWorkItemTree.addContainerProperty("State", String.class, null);

        goalWorkItemTree.setWidth("400px");
        goalWorkItemTree.setHeight("410px");
        novo.addComponent(goalWorkItemTree);
        novo.setComponentAlignment(goalWorkItemTree, Alignment.TOP_LEFT);
        goalWorkItemTree
                .setVisibleColumns(new Object[] { "GoalWorkItems", "State" });
        goalWorkItemTree.addActionHandler(new Action.Handler() {
            @Atomic(mode = TxMode.WRITE)
            @Override
            public void handleAction(Action action, Object sender,
                    Object target) {
                if (action == REDO_GOAL_ACTION) {
                    String workItemlOID = (String) goalWorkItemTree
                            .getContainerProperty(target, "OID").getValue();
                    String activeUserID = BlendedWorkflow.getInstance()
                            .getOrganizationalManager().getActiveUser().getID();
                    BlendedWorkflow.getInstance().getWorkListManager()
                            .redoGoal(workItemlOID, activeUserID);
                    updateGoalTreeInfo();
                } else if (action == DISABLE_CONDITION_ACTION) {
                    // remove condition
                    String workItemlOID = (String) goalWorkItemTree
                            .getContainerProperty(target, "OID").getValue();
                    updateGoalTreeInfo();
                    generateManageGoalWorkItemsConditionsWindows(workItemlOID);
                }
            }

            @Override
            public Action[] getActions(Object target, Object sender) {
                if (target == null) {
                    return new Action[] {};
                } else {
                    String workItemState = (String) goalWorkItemTree
                            .getContainerProperty(target, "State").getValue();
                    if (workItemState.equals("PRE_FALSE")
                            || workItemState.equals("ENABLED")) {
                        return new Action[] { DISABLE_CONDITION_ACTION };
                    } else {
                        return new Action[] { REDO_GOAL_ACTION };
                    }
                }
            }
        });

        maintainGoalsTree.addContainerProperty("OID", String.class, null);
        maintainGoalsTree.addContainerProperty("Goal", String.class, null);
        maintainGoalsTree.addContainerProperty("State", String.class, null);
        maintainGoalsTree.setImmediate(true);
        maintainGoalsTree.setWidth("450px");
        maintainGoalsTree.setHeight("190px");
        maintainGoalsTree.setVisibleColumns(new Object[] { "Goal", "State" });
        infoVL.addComponent(maintainGoalsTree);
        infoVL.setComponentAlignment(maintainGoalsTree, Alignment.TOP_LEFT);

        maintainGoalsTree.addActionHandler(new Action.Handler() {
            @Atomic(mode = TxMode.WRITE)
            @Override
            public void handleAction(Action action, Object sender,
                    Object target) {
                if (action == DISABLE_CONDITION_ACTION) {
                    // remove condition
                    String goalOID = (String) maintainGoalsTree
                            .getContainerProperty(target, "OID").getValue();

                    BlendedWorkflow.getInstance().getWorkListManager()
                            .manageGoalCondition(goalOID,
                                    MaintainGoalState.DEACTIVATED);
                    updateGoalTreeInfo();
                } else {
                    // remove condition
                    String goalOID = (String) maintainGoalsTree
                            .getContainerProperty(target, "OID").getValue();
                    BlendedWorkflow.getInstance().getWorkListManager()
                            .manageGoalCondition(goalOID,
                                    MaintainGoalState.ENABLED);
                    updateGoalTreeInfo();
                }
            }

            @Override
            public Action[] getActions(Object target, Object sender) {
                if (target == null) {
                    return new Action[] {};
                } else {
                    String state = maintainGoalsTree
                            .getContainerProperty(target, "State").toString();
                    if (state.equals("ENABLED")) {
                        return new Action[] { DISABLE_CONDITION_ACTION };
                    } else {
                        return new Action[] { ENABLE_CONDITION_ACTION };
                    }
                }
            }
        });

        infoHL.addComponent(novo);

        goalManagerVL.addComponent(infoHL);
        goalManagerVL.addComponent(new Label("<hr />", Label.CONTENT_XHTML));
        goalManagerVL.addComponent(goalManagerBtnLayout);
        goalManagerVL.setComponentAlignment(goalManagerBtnLayout,
                Alignment.TOP_LEFT);

        return goalManagerVL;
    }

    /******************************
     * Listeners
     ******************************/
    private void initTaskListListener() {
        taskList.addListener(
                taskListListener = new Property.ValueChangeListener() {
                    @Override
                    public void valueChange(ValueChangeEvent event) {
                        if (taskList.getValue() == null) {
                            taskInfoTable.removeAllItems();
                        } else {
                            String workItemOID = (String) taskList.getValue();
                            updateTaskView(workItemOID);
                        }
                    }
                });
    }

    private void initGoalListListener() {
        goalList.addListener(
                goalListListener = new Property.ValueChangeListener() {
                    @Override
                    public void valueChange(ValueChangeEvent event) {
                        if (goalList.getValue() == null) {
                            goalInfoTable.removeAllItems();
                        } else {
                            String workItemOID = (String) goalList.getValue();
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

    public void generateLaunchForm(String bwSpecificationOID) {
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
            @Override
            @Atomic(mode = TxMode.WRITE)
            public void onLogin(LoginEvent event) {
                String userID = event.getLoginParameter("username");
                String userPassword = event.getLoginParameter("password");

                if (BlendedWorkflow.getInstance().getOrganizationalManager()
                        .loginUser(userID, userPassword)) {
                    getMainWindow().removeWindow(loginWindow);
                    initMainWindow(event.getLoginParameter("username"));
                } else {
                    getMainWindow().showNotification(
                            "Username or Password incorrect.",
                            Notification.TYPE_WARNING_MESSAGE);
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

    public void generatePreTaskForm(String workItemOID) {
        Window taskWindow = new Window("Pre-Activity Form");
        taskWindow.setContent(new PreTaskForm(workItemOID));
        taskWindow.setWidth("30%");
        taskWindow.center();
        getMainWindow().addWindow(taskWindow);
    }

    public void generateTaskForm(String workItemOID, String className) {
        log.info("className:" + className);
        String packageName = "pt.ist.socialsoftware.blendedworkflow.presentation.";

        // Get Form class
        try {
            tmp_class = Class.forName(packageName + className);
        } catch (Exception e) {
            try {
                log.info("nexiste");
                tmp_class = Class.forName(packageName + "TaskForm");
            } catch (Exception classExeception) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PrintStream stream = new PrintStream(baos);
                e.printStackTrace(stream);
                stream.flush();
                log.info(new String(baos.toByteArray()));
            }
        }

        // New Instance
        try {
            tmp_const = tmp_class.getDeclaredConstructor(String.class);
            Window taskWindow = new Window(className);
            taskWindow.setContent(
                    (ComponentContainer) tmp_const.newInstance(workItemOID));
            taskWindow.setWidth("30%");
            taskWindow.center();
            getMainWindow().addWindow(taskWindow);
        } catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream stream = new PrintStream(baos);
            e.printStackTrace(stream);
            stream.flush();
            log.info(new String(baos.toByteArray()));
        }
    }

    public void generatePreGoalForm(String workItemOID) {
        Window goalWindow = new Window("Pre Goal Form");
        goalWindow.setContent(new PreGoalForm(workItemOID));
        goalWindow.setWidth("30%");
        goalWindow.center();
        getMainWindow().addWindow(goalWindow);
    }

    public void generateGoalForm(String workItemOID) {
        Window goalWindow = new Window("Goal Form");
        goalWindow.setContent(new GoalForm(workItemOID));
        goalWindow.setWidth("30%");
        goalWindow.center();
        getMainWindow().addWindow(goalWindow);
    }

    public void generateActivateGoalWindow(String bwInstanceOID,
            String goalOID) {
        Window newGoalWindow = new Window("Activate Goal");
        newGoalWindow.setContent(new ActivateGoalForm(bwInstanceOID, goalOID));
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

    public void generateNewGoalWindow(String bwInstanceOID, String goalOID) {
        Window newGoalWindow = new Window("New Goal Form");
        newGoalWindow.setContent(new NewGoalForm(bwInstanceOID, goalOID));
        newGoalWindow.center();
        newGoalWindow.setClosable(false);
        newGoalWindow.setDraggable(false);
        newGoalWindow.setResizable(false);
        getMainWindow().addWindow(newGoalWindow);
    }

    public void generateAddSubGoalsContextWindow(EntityInstance entityContext,
            HashMap<BWEntity, BWRelation> neededEntityInstances) {
        Window newGoalWindow = new Window("SubGoals Context");
        newGoalWindow.setContent(new NewAddSubGoalsContextForm(entityContext,
                neededEntityInstances));
        newGoalWindow.center();
        newGoalWindow.setClosable(false);
        newGoalWindow.setDraggable(false);
        newGoalWindow.setResizable(false);
        getMainWindow().addWindow(newGoalWindow);
    }

    public void generateNewDisableMaintainGoalsWindow(String bwInstanceOID) {
        Window newGoalWindow = new Window("Enable/Disable MaintainGoals");
        newGoalWindow.setContent(
                new ManageMaintainGoalsConditionsForm(bwInstanceOID));
        newGoalWindow.center();
        newGoalWindow.setClosable(false);
        newGoalWindow.setDraggable(false);
        newGoalWindow.setResizable(false);
        getMainWindow().addWindow(newGoalWindow);
    }

    public void generateManageGoalWorkItemsConditionsWindows(
            String workItemOID) {
        Window newGoalWindow = new Window("Enable/Disable WorkItem Conditions");
        newGoalWindow
                .setContent(new ManageGoalWorkItemsConditionsForm(workItemOID));
        newGoalWindow.center();
        newGoalWindow.setClosable(false);
        newGoalWindow.setDraggable(false);
        newGoalWindow.setResizable(false);
        getMainWindow().addWindow(newGoalWindow);
    }

    /********************************************
     * BWPresentation Data changes methods
     ********************************************/
    public void addBWSpecification(String OID, String name) {
        this.loadedList.addItem(OID);
        this.loadedList.setItemCaption(OID, name);
        // getMainWindow().showNotification("Blended Workflow Specification " +
        // name +" loaded", Notification.TYPE_TRAY_NOTIFICATION);
    }

    public void addBWInstance(String OID, String name) {
        this.launchedList.addItem(OID);
        this.launchedList.setItemCaption(OID, name);
        this.dataList.addItem(OID);
        this.dataList.setItemCaption(OID, name);
        this.goalBWInstanceList.addItem(OID);
        this.goalBWInstanceList.setItemCaption(OID, name);
        // this.taskListFilter.addItem(OID);
        // this.taskListFilter.setItemCaption(OID, name);
        // this.goalListFilter.addItem(OID);
        // this.goalListFilter.setItemCaption(OID, name);
        getMainWindow().showNotification(
                "Blended Workflow Instance " + name + " created",
                Notification.TYPE_TRAY_NOTIFICATION);
    }

    public void addGoalWorkItem(String OID, String ID) {
        if (this.goalList.getItem(OID) != null) {
            getMainWindow().showNotification(ID + " Failed to Complete!",
                    Notification.TYPE_ERROR_MESSAGE); // "[GOAL] " +
        } else {
            this.goalList.addItem(OID);
            this.goalList.setItemCaption(OID, ID); // "[GOAL] " +
            this.goalList.attach();
        }
    }

    public void addTaskWorkItem(String OID, String ID) {
        if (this.taskList.getItem(OID) != null) {// "[TASK] " +
            // getMainWindow().showNotification(ID + " Failed to Complete!",
            // Notification.TYPE_ERROR_MESSAGE);
        } else {
            this.taskList.addItem(OID);
            this.taskList.setItemCaption(OID, ID);// "[TASK] " +
            this.taskList.attach();
        }
    }

    public void addUser(String oid, String name) {
        // TODO:FutureImplementation: addUser
    }

    public void addRole(String oid, String name) {
        // TODO:FutureImplementation: addRole
    }

    public void removeGoalWorkItem(String OID, String ID, String state) {
        this.goalList.removeListener(goalListListener);
        this.goalList.removeItem(OID);
        this.goalInfoTable.removeAllItems();
        getMainWindow().showNotification(ID + " is " + state,
                Notification.TYPE_TRAY_NOTIFICATION);
        initGoalListListener();
    }

    public void removeTaskWorkItem(String OID, String ID, String state) {
        this.taskList.removeListener(taskListListener);
        this.taskList.removeItem(OID);
        this.taskInfoTable.removeAllItems();
        getMainWindow().showNotification(ID + " is " + state,
                Notification.TYPE_TRAY_NOTIFICATION);
        initTaskListListener();
    }

    @Atomic(mode = TxMode.WRITE)
    public void updateTaskList(String bwInstanceOID) {
        this.taskList.removeListener(taskListListener);
        this.taskList.removeAllItems();

        BWInstance bwInstance = FenixFramework.getDomainObject(bwInstanceOID);
        for (WorkItem workItem : bwInstance.getWorkItemsSet()) {
            if (workItem instanceof TaskWorkItem) {
                TaskWorkItem taskWorkItem = (TaskWorkItem) workItem;
                if ((taskWorkItem.getState().equals(ActivityState.ENABLED)
                        || taskWorkItem.getState()
                                .equals(ActivityState.PRE_ACTIVITY))) {
                    addTaskWorkItem(workItem.getExternalId(), workItem.getID());
                }
            }
        }
        initTaskListListener();
    }

    @Atomic(mode = TxMode.WRITE)
    public void updateGoalList(String bwInstanceOID) {
        this.goalList.removeListener(goalListListener);
        this.goalList.removeAllItems();
        initGoalListListener();
        BWInstance bwInstance = FenixFramework.getDomainObject(bwInstanceOID);
        for (WorkItem workItem : bwInstance.getWorkItemsSet()) {
            if (workItem instanceof GoalWorkItem) {
                GoalWorkItem goalWorkItem = (GoalWorkItem) workItem;
                if ((goalWorkItem.getState().equals(GoalState.ENABLED)
                        || goalWorkItem.getState()
                                .equals(GoalState.PRE_GOAL))) {
                    addGoalWorkItem(workItem.getExternalId(), workItem.getID());
                }
            }
        }
        initGoalListListener();
    }

    @Atomic(mode = TxMode.WRITE)
    public void updateBWSpecificationInfo(String OID) {
        bwSpecInfoTable.removeAllItems();
        bwSpecJobsInfoTable.removeAllItems();
        BWSpecification bwSpecification = FenixFramework.getDomainObject(OID);

        // BW Info
        bwSpecInfoTable.addItem(
                new Object[] { "Name", bwSpecification.getName() },
                new Integer(1));
        bwSpecInfoTable.addItem(
                new Object[] { "Author", bwSpecification.getAuthor() },
                new Integer(2));
        bwSpecInfoTable
                .addItem(
                        new Object[] { "Creation Date",
                                bwSpecification.getCreationDate() },
                        new Integer(3));
        bwSpecInfoTable
                .addItem(
                        new Object[] { "Created Instances",
                                bwSpecification.getInstanceCounter() },
                        new Integer(4));
        bwSpecInfoTable
                .addItem(
                        new Object[] { "Description",
                                bwSpecification.getDescription() },
                        new Integer(5));

        // BW Tasks/Goals
        Object tasks = bwSpecJobsInfoTable
                .addItem(new Object[] { "Activities", "", "", "" }, null);
        Object goals = bwSpecJobsInfoTable
                .addItem(new Object[] { "Goals", "", "", "" }, null);

        TaskModel taskModel = bwSpecification.getTaskModel();
        for (Task task : taskModel.getTasksSet()) {
            Object task1 = bwSpecJobsInfoTable
                    .addItem(new Object[] { task.getName(),
                            task.getDescription(), task.getConstraintData(true),
                            task.getConstraintData(false) }, null);
            bwSpecJobsInfoTable.setParent(task1, tasks);
            bwSpecJobsInfoTable.setChildrenAllowed(task1, false);
        }

        BWGoalModel goalModel = bwSpecification.getGoalModel();
        for (AchieveGoal goal : goalModel.getAchieveGoalsSet()) {
            Object goal1 = bwSpecJobsInfoTable.addItem(new Object[] {
                    goal.getName(), goal.getDescription(),
                    goal.getPreConstraintData(), goal.getConstraintData() },
                    null);
            bwSpecJobsInfoTable.setParent(goal1, goals);
            bwSpecJobsInfoTable.setChildrenAllowed(goal1, false);
        }

    }

    @Atomic(mode = TxMode.WRITE)
    public void updateBWInstanceInfo(String OID) {
        bwInstanceInfoTable.removeAllItems();
        bwInstanceJobsInfoTable.removeAllItems();

        BWInstance bwInstance = FenixFramework.getDomainObject(OID);
        int executedTasksCount = 0;
        // int achievedGoalsCount = 0;

        TaskModelInstance taskModelInstance = bwInstance.getTaskModelInstance();
        // GoalModelInstance goalModelInstance =
        // bwInstance.getGoalModelInstance();

        for (Task task : taskModelInstance.getTasksSet()) {
            if (task.getTaskWorkItem() != null) {
                if (task.getTaskWorkItem().getState()
                        .equals(ActivityState.COMPLETED)
                        || task.getTaskWorkItem().getState()
                                .equals(ActivityState.SKIPPED)) {
                    executedTasksCount++;
                }
            }
        }

        // Info
        bwInstanceInfoTable.addItem(
                new Object[] { "Name", bwInstance.getName() }, new Integer(1));
        bwInstanceInfoTable.addItem(
                new Object[] { "Launched by", bwInstance.getUser().getID() },
                new Integer(2));
        bwInstanceInfoTable.addItem(
                new Object[] { "Creation Date", bwInstance.getCreationDate() },
                new Integer(3));
        bwInstanceInfoTable
                .addItem(
                        new Object[] { "Executed Activities",
                                executedTasksCount + " of " + taskModelInstance
                                        .getTasksSet().size() },
                new Integer(4));

        // JobInfo
        int jobIndex = 1;
        for (LogRecord logRecord : bwInstance.getLog().getLogRecordsSet()) {
            bwInstanceJobsInfoTable.addItem(
                    new Object[] { logRecord.getDate(), logRecord.getAction(),
                            logRecord.getValue(), logRecord.getAuthor() },
                    new Integer(jobIndex));
            jobIndex++;
        }
    }

    @Atomic(mode = TxMode.WRITE)
    public void updateBWInstanceDataInfo(String OID) {
        entitydetailsTreetable.removeAllItems();

        BWInstance bwInstance = FenixFramework.getDomainObject(OID);
        DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();

        for (BWEntity entity : dataModelInstance.getEntitiesSet()) {
            Object entityItem = entitydetailsTreetable
                    .addItem(new Object[] { entity.getName(), "", "" }, null);
            for (EntityInstance entityInstance : entity
                    .getEntityInstancesSet()) {
                Object entityInstanceItem = entitydetailsTreetable
                        .addItem(new Object[] { entityInstance.getID(), "",
                                entityInstance.getState() }, null);
                entitydetailsTreetable.setParent(entityInstanceItem,
                        entityItem);
                for (AttributeInstance attributeInstance : entityInstance
                        .getAttributeInstancesSet()) {
                    String value = " ";
                    if (attributeInstance.getValue() != "$UNDEFINED$"
                            && attributeInstance.getValue() != "$SKIPPED$")
                        value = attributeInstance.getValue();
                    Object attributeInstanceItem = entitydetailsTreetable
                            .addItem(
                                    new Object[] {
                                            attributeInstance.getAttribute()
                                                    .getName(),
                                            value,
                                            attributeInstance.getState() },
                                    null);
                    entitydetailsTreetable.setParent(attributeInstanceItem,
                            entityInstanceItem);
                    entitydetailsTreetable
                            .setChildrenAllowed(attributeInstanceItem, false);
                }
            }
        }
    }

    @Atomic(mode = TxMode.WRITE)
    public void updateGoalTreeInfo() {
        String bwInstanceOID = (String) goalBWInstanceList.getValue();
        goalTable.setVisibleColumns(new Object[] { "OID", "Goal" });
        goalTable.removeAllItems();

        BWInstance bwInstance = FenixFramework.getDomainObject(bwInstanceOID);
        GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();

        // Add Goals
        HashMap<AchieveGoal, Object> addedGoals = new HashMap<AchieveGoal, Object>();
        for (AchieveGoal goal : goalModelInstance.getAchieveGoalsSet()) {
            Object goalItem = goalTable.addItem(
                    new Object[] { goal.getExternalId(), goal.getName() },
                    null);
            addedGoals.put(goal, goalItem);
        }

        // Goal Relations
        for (AchieveGoal goal : goalModelInstance.getAchieveGoalsSet()) {
            if (goal.getParentGoal() != null) {
                Object goalObject = addedGoals.get(goal);
                Object parentGoalObject = addedGoals.get(goal.getParentGoal());
                goalTable.setParent(goalObject, parentGoalObject);
                if (goal.getSubGoalsSet().size() == 0) {
                    goalTable.setChildrenAllowed(goalObject, false);
                }
            }
        }

        goalTable.setVisibleColumns(new Object[] { "Goal" });

        // maintain
        getMaintainGoalTree();
        // workitem
        getGoalWorkItemsTree();
    }

    @Atomic(mode = TxMode.WRITE)
    public void updateTaskView(String OID) {
        taskInfoTable.removeAllItems();
        TaskWorkItem taskWorkItem = FenixFramework.getDomainObject(OID);
        Task task = taskWorkItem.getTask();

        String inputData = "";
        int inputIndex = 0;
        ArrayList<BWEntity> inputEntities = new ArrayList<BWEntity>();
        for (AttributeInstance attributeInstance : taskWorkItem
                .getInputAttributeInstancesSet()) {
            BWEntity entity = attributeInstance.getAttribute().getEntity();
            if (!inputEntities.contains(entity)) {
                inputEntities.add(entity);
            }
        }
        for (BWEntity entity : inputEntities) {
            if (inputIndex == 0) {
                inputData += entity.getName();
            } else {
                inputData += ", " + entity.getName();
            }
            inputIndex++;
        }

        String outputData = "";
        int outputIndex = 0;
        ArrayList<BWEntity> outputEntities = new ArrayList<BWEntity>();
        for (AttributeInstance attributeInstance : taskWorkItem
                .getOutputAttributeInstancesSet()) {
            BWEntity entity = attributeInstance.getAttribute().getEntity();
            if (!outputEntities.contains(entity)) {
                outputEntities.add(entity);
            }
        }
        for (BWEntity entity : outputEntities) {
            if (outputIndex == 0) {
                outputData += entity.getName();
            } else {
                outputData += ", " + entity.getName();
            }
            outputIndex++;
        }

        // Populate Data
        taskInfoTable.addItem(new Object[] { "Name", task.getName() },
                new Integer(1));
        taskInfoTable.addItem(
                new Object[] { "Description", task.getDescription() },
                new Integer(2));
        taskInfoTable.addItem(new Object[] { "Input Data", inputData },
                new Integer(3));
        taskInfoTable.addItem(new Object[] { "Output Data", outputData },
                new Integer(4));
        taskInfoTable.addItem(
                new Object[] { "Previous Activity", task.getPrevious() },
                new Integer(5));

        // taskInfoTable.setWidth("100%");
    }

    @Atomic(mode = TxMode.WRITE)
    public void updateGoalView(String OID) {
        goalInfoTable.removeAllItems();
        GoalWorkItem goalWorkItem = FenixFramework.getDomainObject(OID);
        AchieveGoal goal = goalWorkItem.getAchieveGoal();

        String inputData = "None";
        String subGoals = "None";
        int inputIndex = 0;
        int subGoalIndex = 0;
        ArrayList<BWEntity> inputEntities = new ArrayList<BWEntity>();

        for (AchieveGoal subGoal : goal.getSubGoalsSet()) {

            // TODO: GoalInputData
            // for (AttributeInstance attributeInstance :
            // subGoal.getGoalWorkItem().getContraintViolationAttributeInstances())
            // {
            // Entity entity = attributeInstance.getAttribute().getEntity();
            // if (!inputEntities.contains(entity)) {
            // inputEntities.add(entity);
            // }
            // }
            if (subGoalIndex == 0) {
                subGoals = subGoal.getName();
            } else {
                subGoals += ", " + subGoal.getName();
            }
            subGoalIndex++;
        }

        for (BWEntity entity : inputEntities) {
            if (inputIndex == 0) {
                inputData = entity.getName();
            } else {
                inputData += ", " + entity.getName();
            }
            inputIndex++;
        }

        String outputData = "";
        int outputIndex = 0;
        ArrayList<BWEntity> outputEntities = new ArrayList<BWEntity>();
        for (AttributeInstance attributeInstance : goalWorkItem
                .getOutputAttributeInstancesSet()) {
            BWEntity entity = attributeInstance.getAttribute().getEntity();
            if (!outputEntities.contains(entity)) {
                outputEntities.add(entity);
            }
        }
        for (BWEntity entity : outputEntities) {
            if (outputIndex == 0) {
                outputData += entity.getName();
            } else {
                outputData += ", " + entity.getName();
            }
            outputIndex++;
        }

        String parentGoal = "None";
        if (goal.getParentGoal() != null) {
            parentGoal = goal.getParentGoal().getName();
        }

        goalInfoTable.addItem(new Object[] { "Name", goal.getName() },
                new Integer(1));
        goalInfoTable.addItem(
                new Object[] { "Description", goal.getDescription() },
                new Integer(2));
        goalInfoTable.addItem(new Object[] { "Parent Goal", parentGoal },
                new Integer(3));
        goalInfoTable.addItem(new Object[] { "Sub Goals", subGoals },
                new Integer(4));
        goalInfoTable.addItem(new Object[] { "Input Data", inputData },
                new Integer(5));
        goalInfoTable.addItem(new Object[] { "Output Data", outputData },
                new Integer(6));

        // goalInfoTable.setWidth("100%");
    }

    @Atomic(mode = TxMode.WRITE)
    public void getMaintainGoalTree() {
        maintainGoalsTree.removeAllItems();
        String bwInstanceOID = (String) goalBWInstanceList.getValue();
        maintainGoalsTree
                .setVisibleColumns(new Object[] { "OID", "Goal", "State" });

        BWInstance bwInstance = FenixFramework.getDomainObject(bwInstanceOID);
        GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();

        for (MaintainGoal achieveGoal : goalModelInstance
                .getMaintainGoalsSet()) {
            String goalOID = achieveGoal.getExternalId();
            String goalName = achieveGoal.getName();
            String state = achieveGoal.getState().toString();
            maintainGoalsTree.addItem(new Object[] { goalOID, goalName, state },
                    null);
        }
        maintainGoalsTree.setVisibleColumns(new Object[] { "Goal", "State" });
    }

    @Atomic(mode = TxMode.WRITE)
    public void getGoalWorkItemsTree() {
        goalWorkItemTree.removeAllItems();
        String bwInstanceOID = (String) goalBWInstanceList.getValue();
        goalWorkItemTree.setVisibleColumns(
                new Object[] { "OID", "GoalWorkItems", "State" });

        BWInstance bwInstance = FenixFramework.getDomainObject(bwInstanceOID);

        for (WorkItem workItem : bwInstance.getWorkItemsSet()) {
            if (workItem instanceof GoalWorkItem) {
                GoalWorkItem goalWorkItem = (GoalWorkItem) workItem;
                if ((goalWorkItem.getState().equals(GoalState.ACHIEVED)
                        || goalWorkItem.getState().equals(GoalState.SKIPPED)
                        || goalWorkItem.getState().equals(GoalState.PRE_GOAL)
                        || goalWorkItem.getState().equals(GoalState.ENABLED))) {
                    String workItemOID = goalWorkItem.getExternalId();
                    String workItemID = goalWorkItem.getID();
                    String workItemState = goalWorkItem.getState().toString();
                    goalWorkItemTree.addItem(new Object[] { workItemOID,
                            workItemID, workItemState }, null);
                }
            }
        }
        goalWorkItemTree
                .setVisibleColumns(new Object[] { "GoalWorkItems", "State" });
    }

}