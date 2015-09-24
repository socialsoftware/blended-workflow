package pt.ist.socialsoftware.blendedworkflow.presentation;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.Notification;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

@SuppressWarnings("serial")
public class NewAttributeForm extends VerticalLayout {

    private final TextField nameTf = new TextField("Attribute name:");
    private final NativeSelect typeNS = new NativeSelect("Type:");
    private final CheckBox isKeyCB = new CheckBox("Key Attribute:");

    public NewAttributeForm(final AllDataModelTree parent,
            final String bwInstanceOID, final String entityName) {
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
            @Atomic(mode = TxMode.WRITE)
            @Override
            public void buttonClick(ClickEvent event) {
                try {
                    String name = (String) nameTf.getValue();
                    String type = (String) typeNS.getValue();
                    Boolean isKeyAttribute = (Boolean) isKeyCB.getValue();
                    addAttribute(bwInstanceOID, name, entityName, type,
                            isKeyAttribute);
                    getApplication().getMainWindow().showNotification(
                            "New Attribute  " + name + " created",
                            Notification.TYPE_TRAY_NOTIFICATION);
                    parent.refreshTree(bwInstanceOID);
                    getApplication().getMainWindow()
                            .removeWindow(NewAttributeForm.this.getWindow());
                } catch (java.lang.NullPointerException jle) {
                    getApplication().getMainWindow()
                            .showNotification("Please fill all fields");
                } catch (BWException bwe) {
                    getApplication().getMainWindow().showNotification(
                            bwe.getError().toString(),
                            Notification.TYPE_ERROR_MESSAGE);
                }
            }
        });

        Button cancel = new Button("Cancel");
        cancel.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                getApplication().getMainWindow()
                        .removeWindow(NewAttributeForm.this.getWindow());
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

    public void addAttribute(String BwInstanceOID, String name,
            String entityName, String typeString, Boolean isKeyAttribute)
                    throws BWException {
        BWInstance bwInstance = FenixFramework.getDomainObject(BwInstanceOID);
        DataModelInstance dataModel = bwInstance.getDataModelInstance();
        Entity entity = dataModel.getEntity(entityName).get();

        AttributeType type;
        if (typeString.equals("String")) {
            type = AttributeType.STRING;
        } else if (typeString.equals("Number")) {
            type = AttributeType.NUMBER;
        } else {
            type = AttributeType.BOOLEAN;
        }

        new AttributeBasic(dataModel, entity, null, name, type, true,
                isKeyAttribute, false); // FIXME:
        // isSystem
    }

}
