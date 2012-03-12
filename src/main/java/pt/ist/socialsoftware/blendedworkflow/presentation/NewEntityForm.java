package pt.ist.socialsoftware.blendedworkflow.presentation;

//import com.vaadin.ui.Alignment;
//import com.vaadin.ui.Button;
//import com.vaadin.ui.HorizontalLayout;
//import com.vaadin.ui.Label;
//import com.vaadin.ui.TextField;
//import com.vaadin.ui.VerticalLayout;
//import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class NewEntityForm extends Window {
//	
//	VerticalLayout content = new VerticalLayout();
//	
//	DataModelTree parent = null;
//	
//	private TextField entityName = new TextField("Entity name");
//	private AttributesPanel attributes;
//	private RelationRow relation;
//
//	public NewEntityForm(DataModelTree parent) {
//		this.parent = parent;
//		content.setCaption("New Entity");
//		content.setMargin(true);
//		content.setSpacing(true);
//		
//		this.setWidth(null);
//		
//		// add entity text field
//		content.addComponent(this.entityName);
//		
//		//add attributes panel
//		content.addComponent(new Label("Attributes"));
//		this.attributes = new AttributesPanel(this);
//		content.addComponent(this.attributes);
//		
//		// add relation
//		content.addComponent(new Label("Relation"));
//		this.relation = new RelationRow(this);
//		content.addComponent(this.relation);
//		
//		//add the buttons panel
//		HorizontalLayout submitPanel = new HorizontalLayout();
//		Button ok = new Button("OK");
//		ok.addListener(new Button.ClickListener() {
//			
//			@Override
//			public void buttonClick(ClickEvent event) {
//				try {
//					processDialogData();
//				} catch (FormNotFilledException e) {
//					getApplication().getMainWindow().showNotification("Please fill all the fields");
//				}
//				getParentWindow().refreshTree();
//				
//				NewEntityForm.this.close();
//			}
//		});
//		
//		submitPanel.addComponent(ok);
//		
//		content.addComponent(submitPanel);
//		content.setComponentAlignment(submitPanel, Alignment.BOTTOM_RIGHT);
//		
//		this.setContent(content);
//		this.center();
//	}
//	
//	private DataModelTree getParentWindow() {
//		return this.parent;
//	}
//	
//	public String getEntityName() throws FormNotFilledException {
//		String value = (String) this.entityName.getValue(); 
//		if(value.equals("")) {
//			throw new FormNotFilledException();
//		}
//		return value;
//	}
//	
//	private void processDialogData() throws FormNotFilledException {
//		String entityName = getEntityName();
//		String goalCaseInstanceID = parent.getGoalCaseInstanceID();
//		String dataModelURI = WorklistManager.get().getDataModelURI(goalCaseInstanceID);
//		String dataModelInstanceID = WorklistManager.get().getDataModelInstanceID(goalCaseInstanceID);
//		
//		ArrayList<AddDataMessage> atts = this.attributes.getAttributes();
//		AddRelationMessage rel = this.relation.getRelation();
//		WorklistManager.get().addData(dataModelURI, dataModelInstanceID, entityName, atts, rel);
//	}
//	
//	public ArrayList<String> getEntitiesAsList() {
//		String goalCaseInstanceID = parent.getGoalCaseInstanceID();
//		String xmlStructure = WorklistManager.get().getDataModel(goalCaseInstanceID);
//		
//		ArrayList<String> entities = new ArrayList<String>();
//		
//		Document doc = StringUtils.stringToDoc(xmlStructure);
//		Element root = doc.getRootElement();
//		
//		for (Object child : root.getChildren("Entity")) {
//			Element entity = (Element) child;
//			entities.add(entity.getAttributeValue("Name"));
//		}
//		
//		return entities;
//	}
//	
}
