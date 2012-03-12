package pt.ist.socialsoftware.blendedworkflow.presentation;

//import com.vaadin.ui.Alignment;
//import com.vaadin.ui.Button;
//import com.vaadin.ui.Button.ClickEvent;
//import com.vaadin.ui.HorizontalLayout;
//import com.vaadin.ui.TextField;
//import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
//import com.vaadin.ui.Window;
//import com.vaadin.ui.Window.Notification;

@SuppressWarnings("serial")
public class DataModelTree extends VerticalLayout {

//	private Tree dataModel = new Tree("Data Model");
//	private TextField value = null;
//	
//	private NewGoalForm parent = null;
//	
//	private String goalCaseInstance;
//	
//	private Window newEntityWindow = null;
//	private Window newAttributeWindow = null;
//	
//	public DataModelTree(NewGoalForm parent, String goalCaseInstanceID, boolean specifyValue) {
//		
//		setMargin(true);
//		setSpacing(true);
//		setWidth(null);
//		
//		this.parent = parent;
//		this.goalCaseInstance = goalCaseInstanceID;
//		
//		String dataModelInXML = WorklistManager.get().getDataModel(goalCaseInstanceID);
//		parseDataModelXML(dataModelInXML);
//		
//		HorizontalLayout treePanel = new HorizontalLayout();
//		treePanel.addComponent(dataModel);
//		if(specifyValue) {
//			value = new TextField("Data Value");
//			treePanel.addComponent(value);
//		}
//		
//		treePanel.setSpacing(true);
//		addComponent(treePanel);
//		
//		HorizontalLayout submitPanel = new HorizontalLayout();
//		Button ok = new Button("Select");
//		ok.addListener(new Button.ClickListener() {
//			
//			@Override
//			public void buttonClick(ClickEvent event) {
//				if(dataModel.getValue().toString().equals("")) {
//					getApplication().getMainWindow().showNotification("You must select a data element", Notification.TYPE_WARNING_MESSAGE);
//					return;
//				}
//				processDataChoice();
//				getApplication().getMainWindow().removeWindow(DataModelTree.this.getWindow());
//			}
//		});
//		
//		Button cancel = new Button("Cancel");
//		cancel.addListener(new Button.ClickListener() {
//			
//			@Override
//			public void buttonClick(ClickEvent event) {
//				getApplication().getMainWindow().removeWindow(DataModelTree.this.getWindow());
//			}
//		});
//		
//		Button addEntity = new Button("Add new entity...");
//		// add listener
//		addEntity.addListener(new Button.ClickListener() {
//			
//			@Override
//			public void buttonClick(ClickEvent event) {
//				openNewEntityForm();
//			}
//		});
//		
//		Button addAttribute = new Button("Add new attribute...");
//		addAttribute.addListener(new Button.ClickListener() {
//			
//			@Override
//			public void buttonClick(ClickEvent event) {
//				if(!isSelectedEntity()) {
//					getApplication().getMainWindow().showNotification("Please select an entity");
//				}
//				openNewAttributeForm((String) dataModel.getValue());
//			}
//		});
//		
//		submitPanel.addComponent(addEntity);
//		submitPanel.addComponent(addAttribute);
//		submitPanel.addComponent(cancel);
//		submitPanel.addComponent(ok);
//		//submitPanel.setMargin(true);
//		submitPanel.setSpacing(true);
//		
//		addComponent(submitPanel);
//		setComponentAlignment(submitPanel, Alignment.BOTTOM_RIGHT);
//	}
//	
//	protected boolean isSelectedEntity() {
//		String attribute = (String) this.dataModel.getValue();
//		if(attribute == null || "".equals(attribute)) {
//			return false;
//		}
//		
//		String entity = (String) this.dataModel.getParent(attribute);
//		if(entity == null) { //an entity was selected
//			return true;
//		}
//		return false;
//	}
//	
//	public String getGoalCaseInstanceID() {
//		return parent.getInstanceSpecification();
//	}
//	
//	public void refreshTree() {
//		dataModel.removeAllItems();
//		String dataModelInXML = WorklistManager.get().getDataModel(goalCaseInstance);
//		parseDataModelXML(dataModelInXML);
//	}
//	
//	protected void openNewEntityForm() {
//		this.newEntityWindow = new NewEntityForm(this);
//		getApplication().getMainWindow().addWindow(this.newEntityWindow);
//	}
//	
//	protected void openNewAttributeForm(String entityName) {
//		this.newAttributeWindow = new NewAttributeForm(entityName, this);
//		getApplication().getMainWindow().addWindow(this.newAttributeWindow);
//	}
//	
//	
//	protected void processDataChoice() {
//		String dataName = null;
//		String dataValue = null;
//		String attribute = (String) this.dataModel.getValue();
//		if(attribute.equals("")) {
//			return;
//		}
//		
//		String entity = (String) this.dataModel.getParent(attribute);
//		if(entity == null) { //an entity was selected
//			dataName = attribute;
//		} else {
//			dataName = entity + "." + attribute;
//		}
//		if(value != null) {
//			dataValue = (String) value.getValue();
//		}
//		parent.setData(dataName, dataValue);
//	}
//	
//	protected void parseDataModelXML(String dataModel) {
//		Document doc = StringUtils.stringToDoc(dataModel);
//		if(doc == null) return;
//		
//		Element root = doc.getRootElement(); // DataModel
//		
//		for (Object ent : root.getChildren("Entity")) {
//			Element entity = (Element) ent;
//			String entityName = entity.getAttributeValue("Name");
//			this.dataModel.addItem(entityName);
//			if(entity.getChildren("Attribute").size()>0) {
//				for (Object att : entity.getChildren("Attribute")) {
//					Element attribute = (Element) att;
//					String attributeName = attribute.getAttributeValue("Name");
//					if(attributeName.split("\\.").length > 1) attributeName = attributeName.split("\\.")[1];
//					this.dataModel.addItem(attributeName);
//					this.dataModel.setChildrenAllowed(attributeName, false);  //FIXME i think by this, the attribute name must be unique...
//					this.dataModel.setParent(attributeName, entityName);
//				}
//				this.dataModel.expandItemsRecursively(entityName);
//			} else {
//				this.dataModel.setChildrenAllowed(entityName, false);
//			}
//		}
//	}
}
