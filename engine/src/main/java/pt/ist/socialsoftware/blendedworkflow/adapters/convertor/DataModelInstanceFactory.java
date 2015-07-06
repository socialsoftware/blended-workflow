package pt.ist.socialsoftware.blendedworkflow.adapters.convertor;

import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;

import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.shared.StringUtils;

public class DataModelInstanceFactory {

    public void parseXMLDataModel(DataModel dataModel, String specificationXML)
            throws BWException {
        Document doc = StringUtils.stringToDoc(specificationXML);

        Element root = doc.getRootElement();
        Namespace bwNamespace = root.getNamespace();

        Element dataModelXML = root.getChild("DataModelInstance", bwNamespace);

        List<?> entities = dataModelXML.getChildren("EntityInstance",
                bwNamespace);
        for (Object ent : entities) {
            Element entityXML = (Element) ent;
            EntityInstance entityInstance = parseEntityInstance(dataModel,
                    entityXML);
            parseAttributeInstance(dataModel, entityInstance, entityXML);
        }
    }

    private EntityInstance parseEntityInstance(DataModel dataModel,
            Element entityXML) throws BWException {
        Namespace dmNamespace = entityXML.getNamespace();

        String entityName = entityXML.getChildText("Type", dmNamespace);
        Entity entity = dataModel.getEntity(entityName).get();
        EntityInstance entityInstance = new EntityInstance(entity);
        return entityInstance;
    }

    private void parseAttributeInstance(DataModel dataModel,
            EntityInstance entityInstance, Element entityXML)
                    throws BWException {
        Namespace dmNamespace = entityXML.getNamespace();

        for (Attribute attribute : entityInstance.getEntity()
                .getAttributesSet()) {
            String attributeName = attribute.getName().replaceAll(" ", "_");
            String value = entityXML.getChildText(attributeName, dmNamespace);
            AttributeInstance attributeInstance = new AttributeInstance(
                    attribute, entityInstance);
            attributeInstance.setValue(value);
        }
    }

}