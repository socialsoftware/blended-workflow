package pt.ist.socialsoftware.blendedworkflow.presentation;

import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ConstrainsForm extends VerticalLayout{
	
	private final CheckBox isNot = new CheckBox("False?");
	private final TextField valueTf = new TextField("Value");
	private final NativeSelect typeNS = new NativeSelect("Operator");

	public ConstrainsForm(final DataModelTree parent,final String data, final String dataType) {
		setMargin(true);

		setWidth("300px");
		setHeight("200px");

		typeNS.setNullSelectionAllowed(false);
		typeNS.addItem("");
		typeNS.addItem(">");
		typeNS.addItem(">=");
		typeNS.addItem("<");
		typeNS.addItem("<=");
		typeNS.addItem("=");
		typeNS.addItem("!=");
		typeNS.setValue("");
		
		HorizontalLayout consPanel = new HorizontalLayout();
		consPanel.setSpacing(true);
		
		HorizontalLayout submitPanel = new HorizontalLayout();
		submitPanel.setSpacing(true);

		Button bwInstanceCreateBtn = new Button("Submit");
		bwInstanceCreateBtn.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					Boolean isKeyAttribute = (Boolean) isNot.getValue();
					String type = (String) typeNS.getValue();
					String value = (String) valueTf.getValue();
					
					String constrain = type + "." + value;
					if (type.equals("")) {
						constrain = "";
					}
					else {
						constrain = type + "." + value;
					}

					parent.finalize(data, dataType, isKeyAttribute, constrain);
					getApplication().getMainWindow().showNotification("Data Selected sucessfully");
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
		
		addComponent(isNot);
		consPanel.addComponent(typeNS);
		consPanel.addComponent(valueTf);
		addComponent(consPanel);
		submitPanel.addComponent(bwInstanceCreateBtn);
		submitPanel.addComponent(cancel);
		addComponent(submitPanel);
	}
}

