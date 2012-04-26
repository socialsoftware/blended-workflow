package pt.ist.socialsoftware.blendedworkflow.presentation;

import jvstm.Transaction;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CreateBWInstanceService;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window.Notification;

@SuppressWarnings("serial")
public class LaunchForm extends VerticalLayout {
	
	public LaunchForm(final long bwSpecificationOID) {
		setMargin(true);
		
		setWidth("200px");
		setHeight("130px");
		
		final TextField nameTf = new TextField("Name");
		addComponent(nameTf);
		
		HorizontalLayout submitPanel = new HorizontalLayout();
		submitPanel.setSpacing(true);
		
		Button bwInstanceCreateBtn = new Button("Launch");
		bwInstanceCreateBtn.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					String name = (String) nameTf.getValue();
					String activeUserID = "";
					
					Transaction.begin();
					activeUserID = BlendedWorkflow.getInstance().getOrganizationalManager().getActiveUser().getID();
					Transaction.commit();
					
					new CreateBWInstanceService(bwSpecificationOID, name, activeUserID).execute();
					getApplication().getMainWindow().showNotification("Blended Workflow Instance created", Notification.TYPE_TRAY_NOTIFICATION);
					getApplication().getMainWindow().removeWindow(LaunchForm.this.getWindow());
				}
				catch (java.lang.NullPointerException jle) {
					getApplication().getMainWindow().showNotification("Please fill all fields");
				}
				catch (BlendedWorkflowException bwe) {
					getApplication().getMainWindow().showNotification(bwe.getError().toString(), Notification.TYPE_ERROR_MESSAGE);
				}
			}
		});
		submitPanel.addComponent(bwInstanceCreateBtn);
		
		Button cancel = new Button("Cancel");
		cancel.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getApplication().getMainWindow().removeWindow(LaunchForm.this.getWindow());
			}
		});
		
		submitPanel.addComponent(cancel);
		addComponent(submitPanel);
		setComponentAlignment(submitPanel, Alignment.BOTTOM_CENTER);

	}

}
