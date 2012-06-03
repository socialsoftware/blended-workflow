package pt.ist.socialsoftware.blendedworkflow.presentation;

import jvstm.Transaction;
import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Relation;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Relation.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.Notification;

@SuppressWarnings("serial")
public class NewRelationForm extends VerticalLayout{

	private Table relations = new Table("Existing Relations:");
	private NativeSelect otherEntity = new NativeSelect("Other Entity:");
	private NativeSelect cardinality = new NativeSelect("Relation cardinality:");
	private CheckBox isOtherKey = new CheckBox("The other Entity is a key Entity to this one?");
	private CheckBox isThisKey = new CheckBox("This Entity is key of the other Entity?");

	public NewRelationForm(AllDataModelTree parent, final long bwInstanceOID, final String entityName) {
		setMargin(true);

		setWidth("530px");
		setHeight("480px");
		
		isOtherKey.setCaption("The other Entity is a key of " + entityName +"?"); 
		isThisKey.setCaption(entityName + " is key of the other Entity?");
		
		final TextField nameTf = new TextField("Relation name:");

		otherEntity.setNullSelectionAllowed(false);

		cardinality.setNullSelectionAllowed(false);
		cardinality.addItem("1 to 0..1");
		cardinality.addItem("1 to *");
		cardinality.addItem("1 to 1");
		cardinality.addItem("0..1 to 1");
		cardinality.addItem("0..1 to 0..1");
		cardinality.addItem("0..1 to *");
		cardinality.addItem("* to 1");
		cardinality.addItem("* to 0..1");
		cardinality.addItem("* to *");

		relations.setWidth("500px");
		relations.setHeight("130px");
		relations.addContainerProperty("Name",  String.class,  null);
		relations.addContainerProperty("Entity One",  String.class,  null);
		relations.addContainerProperty("Entity Two",  String.class,  null);
		relations.addContainerProperty("Cardinality",  String.class,  null);
		relations.addContainerProperty("is One Key?",  String.class,  null);
		relations.addContainerProperty("is Two Key?",  String.class,  null);

		getRelations(bwInstanceOID, entityName);
		getEntities(bwInstanceOID, entityName);

		HorizontalLayout submitPanel = new HorizontalLayout();
		submitPanel.setSpacing(true);

		Button bwInstanceCreateBtn = new Button("Submit");
		bwInstanceCreateBtn.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					String name = (String) nameTf.getValue();
					String secondEntityName = (String) otherEntity.getValue();
					String card = (String) cardinality.getValue();
					Boolean isOne = (Boolean) isThisKey.getValue();
					Boolean isTwo = (Boolean) isOtherKey.getValue();

					addRelation(bwInstanceOID, name, entityName, secondEntityName, card, isOne, isTwo);

					getApplication().getMainWindow().showNotification("New Relation " + name + " created", Notification.TYPE_TRAY_NOTIFICATION);
					getApplication().getMainWindow().removeWindow(NewRelationForm.this.getWindow());
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
				getApplication().getMainWindow().removeWindow(NewRelationForm.this.getWindow());
			}
		});

		addComponent(relations);
		addComponent(nameTf);
		addComponent(otherEntity);
		addComponent(this.cardinality);
		addComponent(isThisKey);
		addComponent(isOtherKey);
		submitPanel.addComponent(bwInstanceCreateBtn);
		submitPanel.addComponent(cancel);
		addComponent(submitPanel);
		setComponentAlignment(submitPanel, Alignment.MIDDLE_CENTER);
	}

	private void getRelations(long bwInstanceOID, String entityName) {
		Transaction.begin();
		BWInstance bwInstance = AbstractDomainObject.fromOID(bwInstanceOID);
		DataModelInstance dataModel = bwInstance.getDataModelInstance();

		int relationIndex = 1;
		for (Relation relation : dataModel.getRelations()) {
			Entity entityOne = relation.getEntityOne();
			Entity entityTwo = relation.getEntityTwo();
			if (entityOne.getName().equals(entityName) || entityTwo.getName().equals(entityName) ) {

				String name = relation.getName();
				String card = parseCardinalityToString(relation.getCardinalityOne()) + " to " + parseCardinalityToString(relation.getCardinalityTwo());
				Boolean isOne = relation.getIsOneKeyEntity();
				Boolean isTwo = relation.getIsTwoKeyEntity();

				this.relations.addItem(new Object[] {name, entityOne.getName(),entityTwo.getName(), card, isOne, isTwo}, new Integer(relationIndex));
				relationIndex++;
			}
		}
		Transaction.commit();	
	}

	private void getEntities(long bwInstanceOID, String entityName) {
		Transaction.begin();
		BWInstance bwInstance = AbstractDomainObject.fromOID(bwInstanceOID);
		DataModelInstance dataModel = bwInstance.getDataModelInstance();

		for (Entity entity : dataModel.getEntities()) {
			if (!entity.getName().equals(entityName)) {
				this.otherEntity.addItem(entity.getName());
			}
		}
		Transaction.commit();
	}

	public void addRelation(long bwInstanceOID, String name, String entityOneName, 
			String entityTwoName, String cardinality, Boolean isOneKeyEntity, Boolean isTwoKeyEntity) throws BlendedWorkflowException {

		Transaction.begin();
		BWInstance bwInstance = AbstractDomainObject.fromOID(bwInstanceOID);
		DataModelInstance dataModel = bwInstance.getDataModelInstance();

		Entity entityOne = dataModel.getEntity(entityOneName);
		Entity entityTwo = dataModel.getEntity(entityTwoName);

		Cardinality cardinalityOne = parseThisCardinality(cardinality);
		Cardinality cardinalityTwo = parseOtherCardinality(cardinality);

		new Relation(dataModel, name, entityOne, entityTwo, cardinalityOne, cardinalityTwo, isOneKeyEntity, isTwoKeyEntity);

		Transaction.commit();
	}

	private String parseCardinalityToString(Cardinality card) {
		if (card.equals(Cardinality.ONE)) {
			return "1";
		} else 	if (card.equals(Cardinality.ZERO_OR_ONE)) {
			return "0..1";
		} else {
			return "*";
		}
	}

	private Cardinality parseThisCardinality(String card) {
		if(card.equals("1 to 0..1")) {
			return Cardinality.ONE;
		} else if(card.equals("1 to *")) {
			return Cardinality.ONE;
		} else if(card.equals("1 to 1")) {
			return Cardinality.ONE;
		} else if(card.equals("0..1 to 1")) {
			return Cardinality.ZERO_OR_ONE;
		} else if(card.equals("0..1 to 0..1")) {
			return Cardinality.ZERO_OR_ONE;
		} else if(card.equals("0..1 to *")) {
			return Cardinality.ZERO_OR_ONE;
		} else if(card.equals("* to 1")) {
			return Cardinality.MANY;
		} else if(card.equals("* to 0..1")) {
			return Cardinality.MANY;
		} else if(card.equals("* to *")) {
			return Cardinality.MANY;
		}
		return null;
	}

	private Cardinality parseOtherCardinality(String card) {
		if(card.equals("1 to 0..1")) {
			return Cardinality.ZERO_OR_ONE;
		} else if(card.equals("1 to *")) {
			return Cardinality.MANY;
		} else if(card.equals("1 to 1")) {
			return Cardinality.ONE;
		} else if(card.equals("0..1 to 1")) {
			return Cardinality.ONE;
		} else if(card.equals("0..1 to 0..1")) {
			return Cardinality.ZERO_OR_ONE;
		} else if(card.equals("0..1 to *")) {
			return Cardinality.MANY;
		} else if(card.equals("* to 1")) {
			return Cardinality.ONE;
		} else if(card.equals("* to 0..1")) {
			return Cardinality.ZERO_OR_ONE;
		} else if(card.equals("* to *")) {
			return Cardinality.MANY;
		}
		return null;
	}

}

