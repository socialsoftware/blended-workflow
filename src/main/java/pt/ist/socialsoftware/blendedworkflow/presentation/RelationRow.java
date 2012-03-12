package pt.ist.socialsoftware.blendedworkflow.presentation;

//import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
//import com.vaadin.ui.Label;
//import com.vaadin.ui.NativeSelect;
//import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class RelationRow extends HorizontalLayout {
	
//	private NewEntityForm parent = null;
//	
//	private NativeSelect otherEntity = new NativeSelect();
//	private NativeSelect cardinality = new NativeSelect();
//	
//	private CheckBox isOtherKey = new CheckBox("The other Entity is a key Entity to this one");
//	private CheckBox isThisKey = new CheckBox("This Entity is key to the other one");
//
//	public RelationRow(NewEntityForm parent) {
//		this.parent = parent;
//		
//		VerticalLayout vPanel = new VerticalLayout();
//		vPanel.setSpacing(true);
//		
//		//other entity
//		HorizontalLayout otherPanel = new HorizontalLayout();
//		otherPanel.addComponent(new Label("Other Entity"));
//		
//		//fill the other entity
//		this.otherEntity.addItem("Select...");
//		ArrayList<String> entities = parent.getEntitiesAsList();
//		for (String entity : entities) {
//			this.otherEntity.addItem(entity);
//		}
//		this.otherEntity.setValue("Select...");
//		otherPanel.addComponent(this.otherEntity);
//		
//		//Cardinality
//		HorizontalLayout cardPanel = new HorizontalLayout();
//		cardPanel.addComponent(new Label("Cardinality"));
//		this.cardinality.addItem("Select...");
//		this.cardinality.addItem("1 to 0..1");
//		this.cardinality.addItem("1 to *");
//		this.cardinality.addItem("1 to 1");
//		this.cardinality.addItem("0..1 to 1");
//		this.cardinality.addItem("0..1 to 0..1");
//		this.cardinality.addItem("0..1 to *");
//		this.cardinality.addItem("* to 1");
//		this.cardinality.addItem("* to 0..1");
//		this.cardinality.addItem("* to *");
//		
//		this.cardinality.setValue("Select...");
//		cardPanel.addComponent(this.cardinality);
//		
//		//add to the panel
//		vPanel.addComponent(otherPanel);
//		vPanel.addComponent(cardPanel);
//		vPanel.addComponent(this.isThisKey);
//		vPanel.addComponent(this.isOtherKey);
//		
//		addComponent(vPanel);
//	}
//		
//	public String getOtherEntity() throws FormNotFilledException {
//		String value = (String) this.otherEntity.getValue();
//		if(value.equals("Select...")) {
//			throw new FormNotFilledException();
//		}
//		return value;
//	}
//	
//	public String getCardinality() throws FormNotFilledException {
//		String value = (String) this.cardinality.getValue();
//		if(value.equals("Select...")) {
//			throw new FormNotFilledException();
//		}
//		return value;
//	}
//	
//	public boolean isOtherKey() {
//		return (Boolean) this.isOtherKey.getValue();
//	}
//	
//	public boolean isThisKey() {
//		return (Boolean) this.isThisKey.getValue();
//	}
//	
//	public AddRelationMessage getRelation() throws FormNotFilledException {
//		AddRelationMessage relationMsg = new AddRelationMessage();
//		
//		// note: this = one; other = two.
//		String thisEntityName = parent.getEntityName();
//		relationMsg.isOneKeyEntity(this.isThisKey());
//		relationMsg.isTwoKeyEntity(this.isOtherKey());
//		
//		relationMsg.setEntityOne(thisEntityName);
//		relationMsg.setEntityTwo(this.getOtherEntity());
//		relationMsg.setCardinalityOne(parseThisCardinality(getCardinality()));
//		relationMsg.setCardinalityTwo(parseOtherCardinality(getCardinality()));
//		
//		return relationMsg;
//	}
//	
//	private String parseThisCardinality(String card) {
//		if(card.equals("1 to 0..1")) {
//			return "ONE";
//		} else if(card.equals("1 to *")) {
//			return "ONE";
//		} else if(card.equals("1 to 1")) {
//			return "ONE";
//		} else if(card.equals("0..1 to 1")) {
//			return "ZERO_OR_ONE";
//		} else if(card.equals("0..1 to 0..1")) {
//			return "ZERO_OR_ONE";
//		} else if(card.equals("0..1 to *")) {
//			return "ZERO_OR_ONE";
//		} else if(card.equals("* to 1")) {
//			return "MANY";
//		} else if(card.equals("* to 0..1")) {
//			return "MANY";
//		} else if(card.equals("* to *")) {
//			return "MANY";
//		}
//		return null;
//	}
//	
//	private String parseOtherCardinality(String card) {
//		if(card.equals("1 to 0..1")) {
//			return "ZERO_OR_ONE";
//		} else if(card.equals("1 to *")) {
//			return "MANY";
//		} else if(card.equals("1 to 1")) {
//			return "ONE";
//		} else if(card.equals("0..1 to 1")) {
//			return "ONE";
//		} else if(card.equals("0..1 to 0..1")) {
//			return "ZERO_OR_ONE";
//		} else if(card.equals("0..1 to *")) {
//			return "MANY";
//		} else if(card.equals("* to 1")) {
//			return "ONE";
//		} else if(card.equals("* to 0..1")) {
//			return "ZERO_OR_ONE";
//		} else if(card.equals("* to *")) {
//			return "MANY";
//		}
//		return null;
//	}
//	
}

