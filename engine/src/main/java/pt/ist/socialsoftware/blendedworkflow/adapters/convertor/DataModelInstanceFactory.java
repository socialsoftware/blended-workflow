package pt.ist.socialsoftware.blendedworkflow.adapters.convertor;

import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;

import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.OldAttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.OldEntityInstance;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.shared.StringUtils;

public class DataModelInstanceFactory {

	public void parseXMLDataModel(DataModel dataModel, String specificationXML) throws BWException {
		Document doc = StringUtils.stringToDoc(specificationXML);

		Element root = doc.getRootElement();
		Namespace bwNamespace = root.getNamespace();

		Element dataModelXML = root.getChild("DataModelInstance", bwNamespace);

		List<?> entities = dataModelXML.getChildren("EntityInstance", bwNamespace);
		for (Object ent : entities) {
			Element entityXML = (Element) ent;
			OldEntityInstance entityInstance = parseEntityInstance(dataModel, entityXML);
			parseAttributeInstance(dataModel, entityInstance, entityXML);
		}
	}

	private OldEntityInstance parseEntityInstance(DataModel dataModel, Element entityXML) throws BWException {
		Namespace dmNamespace = entityXML.getNamespace();

		String entityName = entityXML.getChildText("Type", dmNamespace);
		Entity entity = dataModel.getEntity(entityName).get();
		OldEntityInstance entityInstance = new OldEntityInstance(entity);
		return entityInstance;
	}

	private void parseAttributeInstance(DataModel dataModel, OldEntityInstance entityInstance, Element entityXML)
			throws BWException {
		Namespace dmNamespace = entityXML.getNamespace();

		for (Attribute attribute : entityInstance.getEntity().getAttributeSet()) {
			String attributeName = attribute.getName().replaceAll(" ", "_");
			String value = entityXML.getChildText(attributeName, dmNamespace);
			OldAttributeInstance attributeInstance = new OldAttributeInstance(attribute, entityInstance);
			attributeInstance.setValue(value);
		}
	}

}