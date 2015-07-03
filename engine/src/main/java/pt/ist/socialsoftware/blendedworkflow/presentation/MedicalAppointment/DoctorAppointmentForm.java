package pt.ist.socialsoftware.blendedworkflow.presentation.MedicalAppointment;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.TaskWorkItem;
import pt.ist.socialsoftware.blendedworkflow.domain.User;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel.DataState;
import pt.ist.fenixframework.FenixFramework;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class DoctorAppointmentForm extends VerticalLayout {
	DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT);

	private final String taskWorkItemOID;
	HorizontalLayout footer = new HorizontalLayout();

	private final Label patientDataLabel = new Label("Patient Data");
	private final TextField bloodPressureTF = new TextField("Blood Pressure");
	private final Label medicalReportLabel = new Label("Medical Report");
	private final TextField descriptionTF = new TextField("Description");
	private final Label mpL = new Label("Medical Prescription (Optional)");
	private final TextField medicalPrescriptionTF = new TextField("Description");
	private final Table prescriptionMedicationTable = new Table(
			"Prescription Medication:");
	private Boolean first = true;

	public DoctorAppointmentForm(final String workItemOID) {
		setMargin(true);
		setSpacing(true);

		this.taskWorkItemOID = workItemOID;

		patientDataLabel.addStyleName("h2");
		addComponent(patientDataLabel);
		addComponent(bloodPressureTF);
		getInputData();

		medicalReportLabel.addStyleName("h2");
		addComponent(medicalReportLabel);
		addComponent(descriptionTF);

		mpL.addStyleName("h2");
		prescriptionMedicationTable.setHeight("100px");
		prescriptionMedicationTable.addContainerProperty("Number",
				String.class, "");
		prescriptionMedicationTable.addContainerProperty("Name", String.class,
				"");
		prescriptionMedicationTable.addContainerProperty("Quantity",
				String.class, "");
		prescriptionMedicationTable.addContainerProperty("Heart Impact",
				String.class, "");

		addComponent(mpL);
		addComponent(medicalPrescriptionTF);
		addComponent(prescriptionMedicationTable);

		footer.setMargin(true);
		footer.setSpacing(true);

		Button addPMBtn = new Button("Add Prescription Medication");
		addPMBtn.addListener(new ClickListener() {
			@Atomic(mode = TxMode.WRITE)
			@Override
			public void buttonClick(ClickEvent event) {
				if (first) {
					createMedicalPrescription();
				}

				TaskWorkItem taskWorkItem = FenixFramework
						.getDomainObject(taskWorkItemOID);
				String bwInstanceOID = taskWorkItem.getBwInstance()
						.getExternalId();
				showDataModelTreeWindow(bwInstanceOID);
			}

		});
		footer.addComponent(addPMBtn);

		Button submitButton = new Button("Submit");
		submitButton.addListener(new ClickListener() {
			@Atomic(mode = TxMode.WRITE)
			@Override
			public void buttonClick(ClickEvent event) {
				processData(workItemOID);
				getApplication().getMainWindow().removeWindow(
						DoctorAppointmentForm.this.getWindow());
			}
		});
		footer.addComponent(submitButton);

		Button cancelButton = new Button("Cancel");
		cancelButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getApplication().getMainWindow().removeWindow(
						DoctorAppointmentForm.this.getWindow());
			}
		});
		footer.addComponent(cancelButton);

		addComponent(footer);
		setComponentAlignment(footer, Alignment.MIDDLE_CENTER);
	}

	@Atomic(mode = TxMode.WRITE)
	private void getInputData() {
		TaskWorkItem taskWorkItem = FenixFramework
				.getDomainObject(taskWorkItemOID);
		List<WorkItemArgument> arguments = new ArrayList<WorkItemArgument>(
				taskWorkItem.getInputWorkItemArgumentsSet());
		String value = arguments.get(0).getValue();
		bloodPressureTF.setValue(value);
		bloodPressureTF.setEnabled(false);
	}

	private void createMedicalPrescription() {
		TaskWorkItem taskWorkItem = FenixFramework
				.getDomainObject(taskWorkItemOID);
		BWInstance bwInstance = taskWorkItem.getBwInstance();
		String bwInstanceOID = taskWorkItem.getBwInstance().getExternalId();
		String episodeOID = taskWorkItem.getBwInstance().getDataModelInstance()
				.getEntity("Episode").getEntityInstance("Episode.1")
				.getExternalId();
		DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();

		Entity medicalPrescription = dataModelInstance
				.getEntity("Medical Prescription");
		Attribute description = medicalPrescription.getAttribute("Description");

		EntityInstance medicalPrescription1 = new EntityInstance(
				medicalPrescription);
		String medicalPrescription1OID = medicalPrescription1.getExternalId();
		AttributeInstance medicalPrescription1Description = new AttributeInstance(
				description, medicalPrescription1);
		String value = medicalPrescriptionTF.getValue().toString();
		medicalPrescription1Description.setValue(value);

		BlendedWorkflow
				.getInstance()
				.getBwManager()
				.addRelationInstance(bwInstanceOID, episodeOID,
						medicalPrescription1OID);
		first = false;
	}

	private void processData(final String workItemOID) {
		String medicalReport = descriptionTF.getValue().toString();

		// Set WorkItemArguments
		TaskWorkItem taskWorkItem = FenixFramework.getDomainObject(workItemOID);
		List<WorkItemArgument> arguments = new ArrayList<WorkItemArgument>(
				taskWorkItem.getOutputWorkItemArgumentsSet());
		arguments.get(0).setValue(medicalReport);
		arguments.get(0).setState(DataState.DEFINED);

		User activeUser = BlendedWorkflow.getInstance()
				.getOrganizationalManager().getActiveUser();
		taskWorkItem.setUser(activeUser);
		BlendedWorkflow.getInstance().getWorkListManager()
				.checkInWorkItem(taskWorkItemOID);
	}

	public void prescriptionMedication(String number, String name,
			String quantity, String heartImpact) {
		int index = prescriptionMedicationTable.getItemIds().size();
		prescriptionMedicationTable.addItem(new Object[] { number, name,
				quantity, heartImpact }, new Integer(index + 1));
	}

	// generate dataModelWindow
	protected void showDataModelTreeWindow(String bwInstanceOID) {
		Window dataModel = new Window("Add Prescription Medication");
		dataModel
				.setContent(new PrescriptionMedicationForm(this, bwInstanceOID));
		dataModel.center();
		dataModel.setClosable(false);
		dataModel.setResizable(false);
		getApplication().getMainWindow().addWindow(dataModel);
	}
}
