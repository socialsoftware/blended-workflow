package pt.ist.socialsoftware.blendedworkflow.presentation;

import jvstm.Atomic;
import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CheckInWorkItemService;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Window.Notification;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class GoalForm extends VerticalLayout {

	private long goalWorkItemOID;
	private GoalWorkItem goalWorkItem;
	
	public GoalForm(final long workItemOID) {
		
		setMargin(true);
		setSpacing(true);
		
		this.goalWorkItemOID = workItemOID;
		this.goalWorkItem = AbstractDomainObject.fromOID(goalWorkItemOID);
		
		getOutputData();
		
		HorizontalLayout footer = new HorizontalLayout();
		footer.setMargin(true);
		footer.setSpacing(true);
		
		Button submitButton = new Button("Submit");
		submitButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					for(int i = 0; i < getComponentCount(); i++) {
						AbstractField field;
						if(getComponent(i) instanceof CheckBox) {
							field = (CheckBox) getComponent(i);
						} else {
							field = (TextField) getComponent(i);
						}
						String value = field.getValue().toString();
							
						goalWorkItem.getConstrainViolationWorkItemArguments().get(i).setValue(value);				
					}
				} catch (java.lang.NullPointerException jle) {
					// FIXME: ??
				}
				
				try {
					new CheckInWorkItemService(goalWorkItemOID).execute();
				} catch (BlendedWorkflowException bwe) {
					getApplication().getMainWindow().showNotification(bwe.getError().toString(), Notification.TYPE_ERROR_MESSAGE);
				}
				
				getApplication().getMainWindow().showNotification("Goal accomplished", Notification.TYPE_TRAY_NOTIFICATION);
				getApplication().getMainWindow().removeWindow(GoalForm.this.getWindow());
			}
		});
		footer.addComponent(submitButton);
		
		Button cancelButton = new Button("Cancel");
		cancelButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getApplication().getMainWindow().removeWindow(GoalForm.this.getWindow());
			}
		});
		footer.addComponent(cancelButton);
		
		addComponent(footer);
		setComponentAlignment(footer, Alignment.BOTTOM_RIGHT);
	}

	@Atomic
	private void getOutputData() {
		for (WorkItemArgument workItemArgument : this.goalWorkItem.getConstrainViolationWorkItemArguments()) {
			Attribute attribute = workItemArgument.getAttributeInstance().getAttribute();
			addTextBox(attribute.getName());
		}
	}
	
	protected void addCheckBox(String attributeName, String value, boolean readOnly) {
		CheckBox checkBox = new CheckBox(attributeName);
		addComponent(checkBox);
	}
	
	protected void addTextBox(String attributeName) {
		TextField tf = new TextField(attributeName);
		addComponent(tf);
	}
}

