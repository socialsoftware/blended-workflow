package pt.ist.socialsoftware.blendedworkflow.presentation;

import jvstm.Transaction;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window.Notification;

@SuppressWarnings("serial")
public class LoadForm extends VerticalLayout {

	private SpecificationReceiver bwSpecReceiver;

	public LoadForm(BWPresentation bwPresentation) {
		bwSpecReceiver = new SpecificationReceiver(bwPresentation);

		// Upload buttons
		setMargin(true);
		setWidth("320px");
		setHeight("120px");

		// Load goal specification (upload)
		Upload uploadBW = new Upload("Upload the Blended Workflow specification here:", this.bwSpecReceiver);
		uploadBW.setButtonCaption("Submit");
		uploadBW.addListener((Upload.SucceededListener) this.bwSpecReceiver);
		uploadBW.addListener((Upload.FailedListener) this.bwSpecReceiver);

		HorizontalLayout submitPanel = new HorizontalLayout();
		submitPanel.setSpacing(true);
		Button bwSpecificationLoadBtn = new Button("Load");
		bwSpecificationLoadBtn.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					String bwSpec = bwSpecReceiver.getSpecInString();
					if (!bwSpec.equals(null)) {	
						Transaction.begin();
						BlendedWorkflow.getInstance().getBwManager().loadBWSpecification(bwSpec);
						Transaction.commit();

						getApplication().getMainWindow().removeWindow(LoadForm.this.getWindow());
					}
					else {
						getApplication().getMainWindow().showNotification("Blended Workflow Specification missing");
					}
				}
				catch (java.lang.NullPointerException jle) {
					getApplication().getMainWindow().showNotification("Please upload a Blended Workflow Specifications", Notification.TYPE_ERROR_MESSAGE);
				}
			}
		});
		submitPanel.addComponent(bwSpecificationLoadBtn);

		Button cancel = new Button("Cancel");
		cancel.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getApplication().getMainWindow().removeWindow(LoadForm.this.getWindow());
			}
		});
		submitPanel.addComponent(cancel);

		addComponent(uploadBW);
		addComponent(submitPanel);
		setComponentAlignment(submitPanel, Alignment.BOTTOM_CENTER);
	}

}
