package pt.ist.socialsoftware.blendedworkflow.presentation;

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
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;

import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class TaskForm extends VerticalLayout {
	DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT);

	private long taskWorkItemOID;
	VerticalLayout preData = new VerticalLayout();
	VerticalLayout data = new VerticalLayout();
	VerticalLayout entitiesInstances = new VerticalLayout();
	HorizontalLayout footer = new HorizontalLayout();
	VerticalLayout prescrition = new VerticalLayout();
	private Table prescriptionMedicationTable = new Table("Prescription Medication:");
	private Label mpL = new Label("Medical Prescription (Optional)");
	private TextField medicalPrescriptionTF = new TextField("Description");
	private Boolean first = true;
	 
	public TaskForm(final long workItemOID) {
		setMargin(true);
		setSpacing(true);

		this.taskWorkItemOID = workItemOID;

		addComponent(preData);
		addComponent(data);
		addComponent(entitiesInstances);
		addComponent(prescrition);
		getInputData();
		getOutputData();
		getRelationEntities();

		
		footer.setMargin(true);
		footer.setSpacing(true);

		Button submitButton = new Button("Submit");
		submitButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				
				//New
				Transaction.begin();
				TaskWorkItem taskWorkItem = AbstractDomainObject.fromOID(workItemOID);
				String taskName = taskWorkItem.getTask().getName();
				Transaction.commit();
				
				// Task=Booking choose Patient and create RelationInstance
				if (taskName.equals("Booking")) {
					Transaction.begin();
					long bwInstanceOID = taskWorkItem.getBwInstance().getOID();
					long episodeOID = taskWorkItem.getBwInstance().getDataModelInstance().getEntity("Episode").getEntityInstance("Episode.1").getOID();
					Transaction.commit();

					for (int i = 0; i < entitiesInstances.getComponentCount(); i++) {
						NativeSelect selec = (NativeSelect) entitiesInstances.getComponent(i);
						long patientOID = (Long) selec.getValue();
						Transaction.begin();
						BlendedWorkflow.getInstance().getBwManager().addRelationInstance(bwInstanceOID, episodeOID, patientOID);
						Transaction.commit();
					}
				}
				//New
				
				int workItemAttributeIndex = 0;
				for (int y = 0; y < data.getComponentCount(); y++) {
					AbstractField field;
					String value;
					if (!data.getComponent(y).getClass().equals(Label.class)) {
						if(data.getComponent(y).getClass().equals(CheckBox.class)) {
							field = (CheckBox) data.getComponent(y);
							value = field.getValue().toString();
						} else if(data.getComponent(y).getClass().equals(TextField.class)){
							field = (TextField) data.getComponent(y);
							value = field.getValue().toString();
						} else {
							field = (PopupDateField) data.getComponent(y);
							value = dateFormatter.format(field.getValue());
						}

						setWorkItemArgumentValue(workItemAttributeIndex, value);
						workItemAttributeIndex++;
					}
				}

				Transaction.begin();
				User activeUser = BlendedWorkflow.getInstance().getOrganizationalManager().getActiveUser();
				taskWorkItem.setUser(activeUser);
				Transaction.commit();
				Transaction.begin();
				BlendedWorkflow.getInstance().getWorkListManager().checkInWorkItem(taskWorkItemOID);
				Transaction.commit();

				getApplication().getMainWindow().removeWindow(TaskForm.this.getWindow());
			}
		});
		footer.addComponent(submitButton);

		Button cancelButton = new Button("Cancel");
		cancelButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getApplication().getMainWindow().removeWindow(TaskForm.this.getWindow());
			}
		});
		footer.addComponent(cancelButton);

		addComponent(footer);
		setComponentAlignment(footer, Alignment.MIDDLE_CENTER);
	}

	private void setWorkItemArgumentValue(int index, String value) {
		Transaction.begin();
		TaskWorkItem taskWorkItem = AbstractDomainObject.fromOID(taskWorkItemOID);
		taskWorkItem.getOutputWorkItemArguments().get(index).setValue(value);
		taskWorkItem.getOutputWorkItemArguments().get(index).setState(DataState.DEFINED);
		Transaction.commit();
	}

	private void getInputData() {
		Transaction.begin();
		TaskWorkItem taskWorkItem = AbstractDomainObject.fromOID(taskWorkItemOID);

		Entity previousEntity = null;
		Boolean first = true;
		Boolean posAttribute = false;
		for (WorkItemArgument preWorkItemArgument : taskWorkItem.getInputWorkItemArguments()) {
			AttributeInstance preAttributeInstance = preWorkItemArgument.getAttributeInstance();
			Attribute attribute = preAttributeInstance.getAttribute();
			Entity entity = attribute.getEntity();
			String value = preWorkItemArgument.getValue();

			for (WorkItemArgument posWorkItemArgument: taskWorkItem.getOutputWorkItemArguments()) {
				AttributeInstance posAttributeInstance = posWorkItemArgument.getAttributeInstance();
				if (preAttributeInstance.equals(posAttributeInstance)) {
					posAttribute = true;
				}
			}

			if (!posAttribute) {

				if (first) {
					previousEntity = entity;
					addLabel(entity.getName(), true);
					first = false;
				}
				if (!entity.equals(previousEntity)) {
					addLabel(entity.getName(), true);
				}

				if (attribute.getType().equals(AttributeType.BOOLEAN)) {
					addCheckBox(attribute.getName(), true, value);
				} else {
					addTextBox(attribute.getName(), true, value);
				}
			}
			posAttribute = false;
			previousEntity = entity;
		}
		Transaction.commit();
	}

	private void getOutputData() {
		Transaction.begin();

		TaskWorkItem taskWorkItem = AbstractDomainObject.fromOID(taskWorkItemOID);

		Entity previousEntity = null;
		Boolean first = true;
		for (WorkItemArgument workItemArgument : taskWorkItem.getOutputWorkItemArguments()) {
			Attribute attribute = workItemArgument.getAttributeInstance().getAttribute();
			Entity entity = attribute.getEntity();

			if (first) {
				previousEntity = entity;
				addLabel(entity.getName(), false);
				first = false;
			}
			if (!entity.equals(previousEntity)) {
				addLabel(entity.getName(), false);
			}

			if (attribute.getName().contains("Date")) {
				//TODO:
				PopupDateField datetime;
				datetime = new PopupDateField(attribute.getName());
		        datetime.setValue(new java.util.Date());
		        datetime.setResolution(PopupDateField.RESOLUTION_DAY);
		        datetime.setImmediate(true);
		        data.addComponent(datetime);
		        //TODO:
			}
			else if (attribute.getType().equals(AttributeType.BOOLEAN)) {
				addCheckBox(attribute.getName(), false, null);
			} else {
				addTextBox(attribute.getName(), false, null);
			}
			previousEntity = entity;
		}
		Transaction.commit();

	}
	
	private void getRelationEntities() {
		Transaction.begin();
		TaskWorkItem taskWorkItem = AbstractDomainObject.fromOID(taskWorkItemOID);
		
		// checkIN
		if (taskWorkItem.getTask().getName().equals("Booking")) {
			BWInstance bwInstance = taskWorkItem.getBwInstance();
			DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
			Entity patient = dataModelInstance.getEntity("Patient");
			addNativeSelect(patient);
		}
		
		// doctor appoitment
		if (taskWorkItem.getTask().getName().equals("Doctor Appointment")) {

			mpL.addStyleName("h2");

			prescriptionMedicationTable.setHeight("100px");
			prescriptionMedicationTable.addContainerProperty("Number", String.class, "");
			prescriptionMedicationTable.addContainerProperty("Name", String.class, "");
			prescriptionMedicationTable.addContainerProperty("Quantity", String.class, "");
			prescriptionMedicationTable.addContainerProperty("Heart Impact", String.class, "");
			
			prescrition.addComponent(mpL);
			prescrition.addComponent(medicalPrescriptionTF);
			prescrition.addComponent(prescriptionMedicationTable);
			
			Button addPMBtn = new Button("Add Prescription Medication");
			addPMBtn.addListener(new ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					// CRIA MEDICAL prescription apenas na primeira vez
					if (first) {
					Transaction.begin();
					TaskWorkItem taskWorkItem = AbstractDomainObject.fromOID(taskWorkItemOID);
					BWInstance bwInstance = taskWorkItem.getBwInstance();
					long bwInstanceOID = taskWorkItem.getBwInstance().getOID();
					long episodeOID = taskWorkItem.getBwInstance().getDataModelInstance().getEntity("Episode").getEntityInstance("Episode.1").getOID();
					DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
					
					Entity medicalPrescription = dataModelInstance.getEntity("Medical Prescription");
					Attribute description = medicalPrescription.getAttribute("Description");
					
					EntityInstance medicalPrescription1 = new EntityInstance(dataModelInstance, medicalPrescription);
					long medicalPrescription1OID = medicalPrescription1.getOID();
					AttributeInstance medicalPrescription1Description = new AttributeInstance(description, medicalPrescription1);
					String value = medicalPrescriptionTF.getValue().toString();
					medicalPrescription1Description.setValue(value);
					
					
					BlendedWorkflow.getInstance().getBwManager().addRelationInstance(bwInstanceOID, episodeOID, medicalPrescription1OID);
					Transaction.commit();
					first = false;
					}
					// open add pmedication window
					Transaction.begin();
					TaskWorkItem taskWorkItem = AbstractDomainObject.fromOID(taskWorkItemOID);
					long bwInstanceOID = taskWorkItem.getBwInstance().getOID();
					showDataModelTreeWindow(bwInstanceOID);
					Transaction.commit();
				}
			});
			footer.addComponent(addPMBtn);
		}
		
		Transaction.commit();
	}
	
	protected void addCheckBox(String attributeName, Boolean isPreData, String value) {
		CheckBox checkBox = new CheckBox(attributeName);

		if (value != null) {
			if (value.equals(true))
				checkBox.setValue(true);
			else
				checkBox.setValue(false);
		}
		checkBox.setReadOnly(isPreData);
		if (isPreData) {
			preData.addComponent(checkBox);
		} else {
			data.addComponent(checkBox);
		}
	}

	protected void addTextBox(String attributeName, Boolean isPreData, String value) {
		TextField tf = new TextField(attributeName);
		if (value != null) {
			tf.setValue(value);
		}
		tf.setEnabled(!isPreData);
		if (isPreData) {
			preData.addComponent(tf);
		} else {
			data.addComponent(tf);
		}
	}

	protected void addLabel(String entityName, Boolean isPreData) {
		Label l= new Label(entityName);
		l.addStyleName("h2");

		if (isPreData) {
			preData.addComponent(l);
		} else {
			data.addComponent(l);
		}
	}
	
	protected void addNativeSelect(Entity entity) {
		NativeSelect ns = new NativeSelect(entity.getName());
		ns.setNullSelectionAllowed(false);
		ns.setImmediate(true);
		entitiesInstances.addComponent(ns);
		ns.addStyleName("h2");
		for (EntityInstance entityInstance : entity.getEntityInstances()) {
			ns.addItem(entityInstance.getOID());
			ns.setItemCaption(entityInstance.getOID(), entityInstance.getAttributeInstance("Name.1").getValue());
		}
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

