package pt.ist.socialsoftware.blendedworkflow.presentation;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ConstrainsForm extends VerticalLayout{

	private final Label label = new Label("Please Select the Data Constraint:");
	private final Label label2 = new Label("Please Select the Relation Constraint:");
	private final CheckBox isNotCB = new CheckBox("not?");
	private final TextField valueTf = new TextField("Value");
	private final NativeSelect typeNS = new NativeSelect("Operator");
	private final CheckBox forAllCB = new CheckBox("ForAll?");
	private final CheckBox existsOneCB = new CheckBox("ExistsOne?");
	
	private String selectedType;
	private String relationName;


	public ConstrainsForm(final DataModelTree parent, final String entity, final String attribute, final String relation, final String type) {
		selectedType = type;
		relationName = relation;
		
		label.addStyleName("h2");
		label2.addStyleName("h2");
		setMargin(true);

		setWidth("300px");
		setHeight("200px");

		typeNS.setNullSelectionAllowed(false);
		typeNS.addItem("");
		typeNS.addItem("Exists(\u2203)");
		typeNS.addItem(">");
		typeNS.addItem(">=");
		typeNS.addItem("<");
		typeNS.addItem("<=");
		typeNS.addItem("=");
		typeNS.addItem("!=");
		typeNS.setValue("");
		
//		forAllCB.setValue(true);

		HorizontalLayout consPanel = new HorizontalLayout();
		consPanel.setSpacing(true);
		
		HorizontalLayout consPanel2 = new HorizontalLayout();
		consPanel2.setSpacing(true);

		HorizontalLayout submitPanel = new HorizontalLayout();
		submitPanel.setSpacing(true);

		Button bwInstanceCreateBtn = new Button("Submit");
		bwInstanceCreateBtn.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					String condition = createCondition(entity, attribute);

					parent.finalize(condition);
					getApplication().getMainWindow().removeWindow(parent.getWindow());
					getApplication().getMainWindow().removeWindow(ConstrainsForm.this.getWindow());
				}
				catch (java.lang.NullPointerException jle) {
					getApplication().getMainWindow().showNotification("Constrains null");
				}
			}

		});

		Button cancel = new Button("Cancel");
		cancel.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getApplication().getMainWindow().removeWindow(ConstrainsForm.this.getWindow());
			}
		});

		consPanel.addComponent(isNotCB);
		consPanel.addComponent(typeNS);
		consPanel.addComponent(valueTf);
		addComponent(label);
		addComponent(consPanel);

		if (type.equals("RelationEntity") || type.equals("RelationAttribute")) {
			consPanel2.addComponent(forAllCB);
			consPanel2.addComponent(existsOneCB);
			addComponent(label2);
			addComponent(consPanel2);
		}

		submitPanel.addComponent(bwInstanceCreateBtn);
		submitPanel.addComponent(cancel);
		addComponent(submitPanel);
		setComponentAlignment(submitPanel, Alignment.MIDDLE_CENTER);
	}
	
	private String createCondition(String entity, String attribute) {
		//createCondition
		String condition = "";

		String isNot ="";
		String data ="";
		String dataType ="";
		String constrain ="";
		String relation ="";
		
		//data
		if (selectedType.equals("Entity") || selectedType.equals("RelationEntity")) {
			data = entity;
			dataType = "Entity";
		} else {
			data = entity + "." + attribute;
			dataType = "Attribute";
		}
		
		//Is not
		Boolean isNotBool = (Boolean) isNotCB.getValue();
		if (isNotBool == true) {
			isNot = ".not()";
		}
		
		//Constrains
		String opType = (String) typeNS.getValue();
		String value = (String) valueTf.getValue();
		if (opType.equals("") || value.equals("") || opType.equals("Exists(\u2203)")) {
			constrain = "";
		}
		else {
			constrain = opType + "." + value;
		}
		
		// Condition Type
		if (constrain.equals("")) {
			condition += "exists" + dataType + "(" + data + ")" + isNot;
		}
		else {
			condition += "compare" + dataType + "To("+ data + "," + constrain + ")" + isNot;
		}
		
		// relation
		if (selectedType.equals("RelationEntity") || selectedType.equals("RelationAttribute")) {
			Boolean isForAllBool = (Boolean) forAllCB.getValue();
			if (isForAllBool == true) {
				relation = "forAll[";
			} else {
				relation = "existsOne(";
			}
			relation += entity + "." + relationName + ",";
			condition = relation + condition + "]";

		}
		return condition;
	}
	
}

