package pt.ist.socialsoftware.blendedworkflow.presentation.MedicalAppointment;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.EntityInstance;

@SuppressWarnings("serial")
public class PrescriptionMedicationForm extends VerticalLayout {

    HorizontalLayout footer = new HorizontalLayout();
    private final Label pmL = new Label("Prescription Medication");
    private final TextField numberTF = new TextField("Number");
    private final TextField nameTF = new TextField("Name");
    private final TextField quantityTF = new TextField("Quantity");
    private final CheckBox heartImpactCB = new CheckBox("Heart Impact");

    public PrescriptionMedicationForm(final DoctorAppointmentForm parent,
            final String bwInstanceOID) {
        setMargin(true);
        setSpacing(true);

        pmL.addStyleName("h2");

        addComponent(pmL);
        addComponent(numberTF);
        addComponent(nameTF);
        addComponent(quantityTF);
        addComponent(heartImpactCB);

        footer.setMargin(true);
        footer.setSpacing(true);

        Button submitButton = new Button("Submit");
        submitButton.addListener(new ClickListener() {
            @Atomic(mode = TxMode.WRITE)
            @Override
            public void buttonClick(ClickEvent event) {
                // User Values
                String number = numberTF.getValue().toString();
                String name = nameTF.getValue().toString();
                String quantity = quantityTF.getValue().toString();
                String heartImpact = heartImpactCB.getValue().toString();

                // Models
                BWInstance bwInstance = FenixFramework
                        .getDomainObject(bwInstanceOID);
                DataModelInstance dataModelInstance = bwInstance
                        .getDataModelInstance();

                // PrescriptionMedication
                Entity prescriptionMedication = dataModelInstance
                        .getEntity("Prescription Medication").get();
                Attribute numberAtt = prescriptionMedication
                        .getAttribute("Number").orElse(null);
                Attribute nameAtt = prescriptionMedication
                        .getAttribute("Name").orElse(null);
                Attribute quantityAtt = prescriptionMedication
                        .getAttribute("Quantity").orElse(null);
                Attribute heartImpactAtt = prescriptionMedication
                        .getAttribute("Heart Impact").orElse(null);

                EntityInstance prescriptionMedication1 = new EntityInstance(
                        prescriptionMedication);
                AttributeInstance prescriptionMedication1Number = new AttributeInstance(
                        numberAtt, prescriptionMedication1);
                prescriptionMedication1Number.setValue(number);
                AttributeInstance prescriptionMedication1Name = new AttributeInstance(
                        nameAtt, prescriptionMedication1);
                prescriptionMedication1Name.setValue(name);
                AttributeInstance prescriptionMedication1Quantity = new AttributeInstance(
                        quantityAtt, prescriptionMedication1);
                prescriptionMedication1Quantity.setValue(quantity);
                AttributeInstance prescriptionMedication1HeartImpact = new AttributeInstance(
                        heartImpactAtt, prescriptionMedication1);
                prescriptionMedication1HeartImpact.setValue(heartImpact);

                // Relation to Medical Prescription
                Entity medicalPrescription = dataModelInstance
                        .getEntity("Medical Prescription").get();
                String medicalPrescription1OID = medicalPrescription
                        .getEntityInstance("Medical Prescription.1")
                        .getExternalId();
                String prescriptionMedication1OID = prescriptionMedication1
                        .getExternalId();
                BlendedWorkflow.getInstance().getBwManager()
                        .addRelationInstance(bwInstanceOID,
                                medicalPrescription1OID,
                                prescriptionMedication1OID);

                // Add to parentWindowsTable and close
                parent.prescriptionMedication(number, name, quantity,
                        heartImpact);
                getApplication().getMainWindow()
                        .showNotification("Medical Prescription " + name
                                + " Successfully added!");

                getApplication().getMainWindow().removeWindow(
                        PrescriptionMedicationForm.this.getWindow());
            }
        });
        footer.addComponent(submitButton);

        Button cancelButton = new Button("Cancel");
        cancelButton.addListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                getApplication().getMainWindow().removeWindow(
                        PrescriptionMedicationForm.this.getWindow());
            }
        });
        footer.addComponent(cancelButton);

        addComponent(footer);
        setComponentAlignment(footer, Alignment.MIDDLE_CENTER);
    }

}
