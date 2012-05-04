package pt.ist.socialsoftware.blendedworkflow.presentation;

import org.apache.log4j.Logger;

import jvstm.Transaction;
import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.Notification;

@SuppressWarnings("serial")
public class NewAttributeForm extends VerticalLayout{

	private final TextField nameTf = new TextField("Attribute name:");
	private final NativeSelect typeNS = new NativeSelect("Type:");
	private final CheckBox isKeyCB = new CheckBox("Key Attribute:");
	
	private Logger log = Logger.getLogger("NewAttributeForm");

	public NewAttributeForm(final DataModelTree parent, final long bwInstanceOID, final String entityName) {
		setMargin(true);

		setWidth("300px");
		setHeight("200px");

		typeNS.addItem("String");
		typeNS.addItem("Number");
		typeNS.addItem("Boolean");
		typeNS.setValue("String");
		typeNS.setNullSelectionAllowed(false);

		HorizontalLayout submitPanel = new HorizontalLayout();
		submitPanel.setSpacing(true);

		Button bwInstanceCreateBtn = new Button("Submit");
		bwInstanceCreateBtn.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					String name = (String) nameTf.getValue();
					String type = (String) typeNS.getValue();
					Boolean isKeyAttribute = (Boolean) isKeyCB.getValue();
					addAttribute(bwInstanceOID, name, entityName, type, isKeyAttribute);
					getApplication().getMainWindow().showNotification("New Attribute created", Notification.TYPE_TRAY_NOTIFICATION);
					parent.refreshTree(bwInstanceOID);
					getApplication().getMainWindow().removeWindow(NewAttributeForm.this.getWindow());
				} catch (java.lang.NullPointerException jle) {
					getApplication().getMainWindow().showNotification("Please fill all fields");
				} catch (BlendedWorkflowException bwe) {
					getApplication().getMainWindow().showNotification(bwe.getError().toString(), Notification.TYPE_ERROR_MESSAGE);
				}
			}
		});

		Button cancel = new Button("Cancel");
		cancel.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getApplication().getMainWindow().removeWindow(NewAttributeForm.this.getWindow());
			}
		});

		addComponent(nameTf);
		addComponent(typeNS);
		addComponent(isKeyCB);
		submitPanel.addComponent(bwInstanceCreateBtn);
		submitPanel.addComponent(cancel);
		addComponent(submitPanel);
		setComponentAlignment(submitPanel, Alignment.MIDDLE_CENTER);
	}

	public void addAttribute(long BwInstanceOID, String name, String entityName, String typeString, Boolean isKeyAttribute) throws BlendedWorkflowException {
		Transaction.begin();

		BWInstance bwInstance = AbstractDomainObject.fromOID(BwInstanceOID);
		DataModelInstance dataModel = bwInstance.getDataModelInstance();
		Entity entity = dataModel.getEntity(entityName);

		// FIXME:
		log.info("NAF: type: " + typeString);
		AttributeType type;
		if (typeString.equals("String")) {
			type = AttributeType.STRING;
		} else if (typeString.equals("Number")) {
			type = AttributeType.NUMBER;
		} else {
			type = AttributeType.BOOLEAN;
		}

		new Attribute(dataModel, name, entity, type, isKeyAttribute);

		Transaction.commit();
	}

}
