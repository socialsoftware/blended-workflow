package pt.ist.socialsoftware.blendedworkflow.presentation;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.Notification;

@SuppressWarnings("serial")
public class NewEntityForm extends VerticalLayout {

	public NewEntityForm(final AllDataModelTree parent,
			final String bwInstanceOID) {
		setMargin(true);

		setWidth("300px");
		setHeight("120px");

		final TextField nameTf = new TextField("Entity name:");
		addComponent(nameTf);

		HorizontalLayout submitPanel = new HorizontalLayout();
		submitPanel.setSpacing(true);

		Button bwInstanceCreateBtn = new Button("Submit");
		bwInstanceCreateBtn.addListener(new ClickListener() {
			@Atomic(mode = TxMode.WRITE)
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					String name = (String) nameTf.getValue();
					addEntity(bwInstanceOID, name);
					getApplication().getMainWindow().showNotification(
							"New Entity " + name + " created",
							Notification.TYPE_TRAY_NOTIFICATION);
					parent.refreshTree(bwInstanceOID);
					getApplication().getMainWindow().removeWindow(
							NewEntityForm.this.getWindow());
				} catch (java.lang.NullPointerException jle) {
					getApplication().getMainWindow().showNotification(
							"Please fill all fields");
				} catch (BWException bwe) {
					getApplication().getMainWindow().showNotification(
							bwe.getError().toString(),
							Notification.TYPE_ERROR_MESSAGE);
				}
			}
		});
		submitPanel.addComponent(bwInstanceCreateBtn);

		Button cancel = new Button("Cancel");
		cancel.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getApplication().getMainWindow().removeWindow(
						NewEntityForm.this.getWindow());
			}
		});
		submitPanel.addComponent(cancel);

		addComponent(submitPanel);
		setComponentAlignment(submitPanel, Alignment.MIDDLE_CENTER);
	}

	public void addEntity(String BwInstanceOID, String name)
			throws BWException {
		BWInstance bwInstance = FenixFramework.getDomainObject(BwInstanceOID);
		DataModelInstance dataModel = bwInstance.getDataModelInstance();
		new Entity(dataModel, name);
	}

}
