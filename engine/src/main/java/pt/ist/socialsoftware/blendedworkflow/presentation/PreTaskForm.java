package pt.ist.socialsoftware.blendedworkflow.presentation;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.Notification;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.BWDataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.TaskWorkItem;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkItemArgument;

@SuppressWarnings("serial")
public class PreTaskForm extends VerticalLayout {

    public static Class<?> tmp_class;
    private static Constructor<?> tmp_const;
    private final String taskWorkItemOID;
    VerticalLayout data = new VerticalLayout();
    private final Logger log = LoggerFactory.getLogger("PreTask");

    public PreTaskForm(final String workItemOID) {
        setMargin(true);
        setSpacing(true);

        this.taskWorkItemOID = workItemOID;

        addComponent(data);
        getOutputData();

        HorizontalLayout footer = new HorizontalLayout();
        footer.setMargin(true);
        footer.setSpacing(true);

        Button submitButton = new Button("Submit");
        submitButton.addListener(new ClickListener() {
            @Atomic(mode = TxMode.WRITE)
            @Override
            public void buttonClick(ClickEvent event) {
                int workItemAttributeIndex = 0;
                for (int y = 0; y < data.getComponentCount(); y++) {
                    AbstractField field;

                    if (!data.getComponent(y).getClass().equals(Label.class)) {
                        if (data.getComponent(y).getClass()
                                .equals(CheckBox.class)) {
                            field = (CheckBox) data.getComponent(y);
                        } else {
                            field = (TextField) data.getComponent(y);
                        }
                        String value = field.getValue().toString();
                        setWorkItemArgumentValue(workItemAttributeIndex, value);
                        workItemAttributeIndex++;
                    }
                }

                generateTaskForm(workItemOID);
                getApplication().getMainWindow().showNotification(
                        "Pre-Activity accomplished",
                        Notification.TYPE_TRAY_NOTIFICATION);
                getApplication().getMainWindow()
                        .removeWindow(PreTaskForm.this.getWindow());

            }
        });
        footer.addComponent(submitButton);

        Button cancelButton = new Button("Cancel");
        cancelButton.addListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                getApplication().getMainWindow()
                        .removeWindow(PreTaskForm.this.getWindow());
            }
        });
        footer.addComponent(cancelButton);

        addComponent(footer);
        setComponentAlignment(footer, Alignment.MIDDLE_CENTER);
    }

    private void setWorkItemArgumentValue(int index, String value) {
        TaskWorkItem taskWorkItem = FenixFramework
                .getDomainObject(taskWorkItemOID);
        List<WorkItemArgument> arguments = new ArrayList<WorkItemArgument>(
                taskWorkItem.getInputWorkItemArgumentsSet());
        arguments.get(index).setValue(value);
        arguments.get(index).setState(DataState.DEFINED);
    }

    @Atomic(mode = TxMode.WRITE)
    private void getOutputData() {
        TaskWorkItem taskWorkItem = FenixFramework
                .getDomainObject(taskWorkItemOID);

        BWEntity previousEntity = null;
        Boolean first = true;
        for (WorkItemArgument workItemArgument : taskWorkItem
                .getInputWorkItemArgumentsSet()) {
            BWAttribute attribute = workItemArgument.getAttributeInstance()
                    .getAttribute();
            BWEntity entity = attribute.getEntity();

            if (first) {
                previousEntity = entity;
                addLabel(entity.getName());
                first = false;
            }
            if (!entity.equals(previousEntity)) {
                addLabel(entity.getName());
            }

            if (attribute.getType().equals(AttributeType.BOOLEAN)) {
                addCheckBox(attribute.getName());
            } else {
                addTextBox(attribute.getName());
            }
            previousEntity = entity;
        }
    }

    protected void addCheckBox(String attributeName) {
        CheckBox checkBox = new CheckBox(attributeName);
        data.addComponent(checkBox);
    }

    protected void addTextBox(String attributeName) {
        TextField tf = new TextField(attributeName);
        data.addComponent(tf);
    }

    protected void addLabel(String entityName) {
        Label l = new Label(entityName);
        l.addStyleName("h2");
        data.addComponent(l);
    }

    public void generateTaskForm(String workItemOID) {
        TaskWorkItem taskWorkItem = FenixFramework.getDomainObject(workItemOID);
        String specificationName = taskWorkItem.getBwInstance()
                .getSpecification().getName().replaceAll(" ", "");
        String taskName = taskWorkItem.getTask().getName().replaceAll(" ", "");
        String className = specificationName + "." + taskName + "Form";

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
            tmp_const = tmp_class.getDeclaredConstructor(long.class);
            Window taskWindow = new Window(className);
            taskWindow.setContent(
                    (ComponentContainer) tmp_const.newInstance(workItemOID));
            taskWindow.setWidth("30%");
            taskWindow.center();
            getApplication().getMainWindow().addWindow(taskWindow);
        } catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream stream = new PrintStream(baos);
            e.printStackTrace(stream);
            stream.flush();
            log.info(new String(baos.toByteArray()));
        }
    }
}
