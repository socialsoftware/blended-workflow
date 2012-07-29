package pt.ist.socialsoftware.blendedworkflow.presentation.MedicalAppointment;

import java.text.DateFormat;

import jvstm.Transaction;
import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.User;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class DoctorAppointmentForm extends VerticalLayout {
	DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT);

	private long taskWorkItemOID;
	HorizontalLayout footer = new HorizontalLayout();

	private Label patientDataLabel = new Label("Patient Data");
	private TextField bloodPressureTF = new TextField("Blood Pressure");
	private Label medicalReportLabel = new Label("Medical Report");
	private TextField descriptionTF = new TextField("Description");
	private Label mpL = new Label("Medical Prescription (Optional)");
	private TextField medicalPrescriptionTF = new TextField("Description");
	private Table prescriptionMedicationTable = new Table("Prescription Medication:");
	private Boolean first = true;

	public DoctorAppointmentForm(final long workItemOID) {
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
		prescriptionMedicationTable.addContainerProperty("Number", String.class, "");
		prescriptionMedicationTable.addContainerProperty("Name", String.class, "");
		prescriptionMedicationTable.addContainerProperty("Quantity", String.class, "");
		prescriptionMedicationTable.addContainerProperty("Heart Impact", String.class, "");

		addComponent(mpL);
		addComponent(medicalPrescriptionTF);
		addComponent(prescriptionMedicationTable);

		footer.setMargin(true);
		footer.setSpacing(true);

		Button addPMBtn = new Button("Add Prescription Medication");
		addPMBtn.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if (first) {
					createMedicalPrescription();
				}
				
				Transaction.begin();
				TaskWorkItem taskWorkItem = AbstractDomainObject.fromOID(taskWorkItemOID);
				long bwInstanceOID = taskWorkItem.getBwInstance().getOID();
				showDataModelTreeWindow(bwInstanceOID);
				Transaction.commit();
			}

		});
		footer.addComponent(addPMBtn);

		Button submitButton = new Button("Submit");
		submitButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				processData(workItemOID);
				getApplication().getMainWindow().removeWindow(DoctorAppointmentForm.this.getWindow());
			}
		});
		footer.addComponent(submitButton);

		Button cancelButton = new Button("Cancel");
		cancelButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getApplication().getMainWindow().removeWindow(DoctorAppointmentForm.this.getWindow());
			}
		});
		footer.addComponent(cancelButton);

		addComponent(footer);
		setComponentAlignment(footer, Alignment.MIDDLE_CENTER);
	}
	
	private void getInputData() {
		Transaction.begin();
		TaskWorkItem taskWorkItem = AbstractDomainObject.fromOID(taskWorkItemOID);
		String value = taskWorkItem.getInputWorkItemArguments().get(0).getValue();
		bloodPressureTF.setValue(value);
		bloodPressureTF.setEnabled(false);
		Transaction.commit();
	}

	private void createMedicalPrescription() {
		Transaction.begin();
		TaskWorkItem taskWorkItem = AbstractDomainObject.fromOID(taskWorkItemOID);
		BWInstance bwInstance = taskWorkItem.getBwInstance();
		long bwInstanceOID = taskWorkItem.getBwInstance().getOID();
		long episodeOID = taskWorkItem.getBwInstance().getDataModelInstance().getEntity("Episode").getEntityInstance("Episode.1").getOID();
		DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();

		Entity medicalPrescription = dataModelInstance.getEntity("Medical Prescription");
		Attribute description = medicalPrescription.getAttribute("Description");

		EntityInstance medicalPrescription1 = new EntityInstance(medicalPrescription);
		long medicalPrescription1OID = medicalPrescription1.getOID();
		AttributeInstance medicalPrescription1Description = new AttributeInstance(description, medicalPrescription1);
		String value = medicalPrescriptionTF.getValue().toString();
		medicalPrescription1Description.setValue(value);


		BlendedWorkflow.getInstance().getBwManager().addRelationInstance(bwInstanceOID, episodeOID, medicalPrescription1OID);
		Transaction.commit();
		first = false;
	}
	
	private void processData(final long workItemOID) {
		String medicalReport = descriptionTF.getValue().toString();

		// Set WorkItemArguments
		Transaction.begin();
		TaskWorkItem taskWorkItem = AbstractDomainObject.fromOID(workItemOID);
		taskWorkItem.getOutputWorkItemArguments().get(0).setValue(medicalReport);
		taskWorkItem.getOutputWorkItemArguments().get(0).setState(DataState.DEFINED);
		Transaction.commit();

		Transaction.begin();
		User activeUser = BlendedWorkflow.getInstance().getOrganizationalManager().getActiveUser();
		taskWorkItem.setUser(activeUser);
		Transaction.commit();
		Transaction.begin();
		BlendedWorkflow.getInstance().getWorkListManager().checkInWorkItem(taskWorkItemOID);
		Transaction.commit();
	}

	public void prescriptionMedication(String number, String name, String quantity, String heartImpact) {
		int index = prescriptionMedicationTable.getItemIds().size();
		prescriptionMedicationTable.addItem(new Object[] {number, name, quantity, heartImpact}, new Integer(index+1));
	}

	// generate dataModelWindow
	protected void showDataModelTreeWindow(long bwInstanceOID) {
		Window dataModel = new Window("Add Prescription Medication");
		dataModel.setContent(new PrescriptionMedicationForm(this, bwInstanceOID));
		dataModel.center();
		dataModel.setClosable(false);
		dataModel.setResizable(false);
		getApplication().getMainWindow().addWindow(dataModel);
	}
}

