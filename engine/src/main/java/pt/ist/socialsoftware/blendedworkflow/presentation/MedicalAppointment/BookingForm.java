package pt.ist.socialsoftware.blendedworkflow.presentation.MedicalAppointment;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.TaskWorkItem;
import pt.ist.socialsoftware.blendedworkflow.domain.User;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkItemArgument;

@SuppressWarnings("serial")
public class BookingForm extends VerticalLayout {
    DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT);

    private final String taskWorkItemOID;
    HorizontalLayout footer = new HorizontalLayout();

    Label episodeLabel;
    TextField numberTF;
    PopupDateField reserveDatePDF;
    NativeSelect patientNS;

    public BookingForm(final String workItemOID) {
        setMargin(true);
        setSpacing(true);

        this.taskWorkItemOID = workItemOID;

        episodeLabel = new Label("Episode");
        episodeLabel.addStyleName("h2");
        addComponent(episodeLabel);

        numberTF = new TextField("Number");
        addComponent(numberTF);

        reserveDatePDF = new PopupDateField("Reserve Date");
        reserveDatePDF.setValue(new java.util.Date());
        reserveDatePDF.setResolution(DateField.RESOLUTION_DAY);
        reserveDatePDF.setImmediate(true);
        addComponent(reserveDatePDF);

        patientNS = new NativeSelect("Patient");
        patientNS.setNullSelectionAllowed(false);
        patientNS.setImmediate(true);
        addComponent(patientNS);
        patientNS.addStyleName("h2");
        getPatients();

        footer.setMargin(true);
        footer.setSpacing(true);

        Button submitButton = new Button("Submit");
        submitButton.addListener(new ClickListener() {
            @Atomic(mode = TxMode.WRITE)
            @Override
            public void buttonClick(ClickEvent event) {

                processData(workItemOID);
                getApplication().getMainWindow()
                        .removeWindow(BookingForm.this.getWindow());
            }
        });
        footer.addComponent(submitButton);

        Button cancelButton = new Button("Cancel");
        cancelButton.addListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                getApplication().getMainWindow()
                        .removeWindow(BookingForm.this.getWindow());
            }
        });
        footer.addComponent(cancelButton);

        addComponent(footer);
        setComponentAlignment(footer, Alignment.MIDDLE_CENTER);
    }

    private void processData(final String workItemOID) {
        String episodeNumber = numberTF.getValue().toString();
        String episodeReserveDate = dateFormatter
                .format(reserveDatePDF.getValue());
        String patientOID = (String) patientNS.getValue();

        // Create RelationInstance
        TaskWorkItem taskWorkItem = FenixFramework.getDomainObject(workItemOID);
        String bwInstanceOID = taskWorkItem.getBwInstance().getExternalId();
        String episodeOID = taskWorkItem.getBwInstance().getDataModelInstance()
                .getEntity("Episode").get().getEntityInstance("Episode.1")
                .getExternalId();
        BlendedWorkflow.getInstance().getBwManager()
                .addRelationInstance(bwInstanceOID, episodeOID, patientOID);

        // Set WorkItemArguments
        List<WorkItemArgument> arguments = new ArrayList<WorkItemArgument>(
                taskWorkItem.getOutputWorkItemArgumentsSet());
        arguments.get(0).setValue(episodeNumber);
        arguments.get(0).setState(DataState.DEFINED);
        arguments.get(1).setValue(episodeReserveDate);
        arguments.get(1).setState(DataState.DEFINED);

        User activeUser = BlendedWorkflow.getInstance()
                .getOrganizationalManager().getActiveUser();
        taskWorkItem.setUser(activeUser);
        BlendedWorkflow.getInstance().getWorkListManager()
                .checkInWorkItem(taskWorkItemOID);
    }

    @Atomic(mode = TxMode.WRITE)
    protected void getPatients() {
        TaskWorkItem taskWorkItem = FenixFramework
                .getDomainObject(taskWorkItemOID);
        BWInstance bwInstance = taskWorkItem.getBwInstance();
        DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
        Entity patient = dataModelInstance.getEntity("Patient").get();
        for (EntityInstance entityInstance : patient.getEntityInstancesSet()) {
            patientNS.addItem(entityInstance.getExternalId());
            patientNS.setItemCaption(entityInstance.getExternalId(),
                    entityInstance.getAttributeInstance("Name.1").getValue());
        }
    }
}
