package pt.ist.socialsoftware.blendedworkflow.adapters.convertor;

import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;

import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.Relation;
import pt.ist.socialsoftware.blendedworkflow.domain.Relation.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.shared.StringUtils;

public class DataModelFactory {

    public void parseXMLDataModel(DataModel dataModel, String specificationXML)
            throws BWException {
        Document doc = StringUtils.stringToDoc(specificationXML);

        Element root = doc.getRootElement();
        Namespace bwNamespace = root.getNamespace();

        Element dataModelXML = root.getChild("DataModel", bwNamespace);

        List<?> entities = dataModelXML.getChildren("Entity", bwNamespace);
        for (Object ent : entities) {
            Element entityXML = (Element) ent;
            Entity entity = parseEntity(dataModel, entityXML);

            List<?> attributes = entityXML.getChildren("Attribute",
                    bwNamespace);
            for (Object att : attributes) {
                Element attributeXML = (Element) att;
                parseAttribute(dataModel, entity, attributeXML);
            }
        }

        List<?> relations = dataModelXML.getChildren("Relation", bwNamespace);
        for (Object rel : relations) {
            Element relationXML = (Element) rel;
            parseRelation(dataModel, relationXML);
        }
    }

    private Entity parseEntity(DataModel dataModel, Element entityXML)
            throws BWException {
        Namespace dmNamespace = entityXML.getNamespace();

        String entityName = entityXML.getChildText("Name", dmNamespace);
        Entity entity = new Entity(dataModel, entityName);
        return entity;
    }

    private void parseAttribute(DataModel dataModel, Entity entity,
            Element attributeXML) throws BWException {
        Namespace dmNamespace = attributeXML.getNamespace();

        String attName = attributeXML.getChildText("Name", dmNamespace);
        String attType = attributeXML.getChildText("Type", dmNamespace);
        boolean isKey = Boolean
                .parseBoolean(attributeXML.getChildText("isKey", dmNamespace));
        boolean isSystem = Boolean.parseBoolean(
                attributeXML.getChildText("isSystem", dmNamespace));
        new Attribute(dataModel, attName, entity, parseAttributeType(attType),
                isKey, isSystem);
    }

    private void parseRelation(DataModel dataModel, Element relationInXML)
            throws BWException {
        Namespace dmNamespace = relationInXML.getNamespace();

        String relationName = relationInXML.getChildText("Name", dmNamespace);
        Element entityOneXML = relationInXML.getChild("EntityOne", dmNamespace);
        Element entityTwoXML = relationInXML.getChild("EntityTwo", dmNamespace);

        Entity entityOne = dataModel
                .getEntity(entityOneXML.getChildText("EntityName", dmNamespace))
                .get();
        Cardinality cardinalityOne = parseCardinality(
                entityOneXML.getChildText("EntityCardinality", dmNamespace));
        Boolean isOneKeyEntity = Boolean.parseBoolean(
                entityOneXML.getChildText("isEntityKey", dmNamespace));

        Entity entityTwo = dataModel
                .getEntity(entityTwoXML.getChildText("EntityName", dmNamespace))
                .get();
        Cardinality cardinalityTwo = parseCardinality(
                entityTwoXML.getChildText("EntityCardinality", dmNamespace));
        Boolean isTwoKeyEntity = parseIsKeyEntity(
                entityTwoXML.getChildText("isEntityKey", dmNamespace));

        new Relation(dataModel, relationName, entityOne, "", cardinalityOne,
                isOneKeyEntity, entityTwo, "", cardinalityTwo, isTwoKeyEntity);
    }

    private Cardinality parseCardinality(String cardinality) {
        if (cardinality.equals(Cardinality.ONE.toString())) {
            return Cardinality.ONE;
        } else if (cardinality.equals(Cardinality.MANY.toString())) {
            return Cardinality.MANY;
        } else if (cardinality.equals(Cardinality.ZERO_OR_ONE.toString())) {
            return Cardinality.ZERO_OR_ONE;
        }
        return null;
    }

    private AttributeType parseAttributeType(String type) {
        if (type.equalsIgnoreCase(AttributeType.BOOLEAN.toString())) {
            return AttributeType.BOOLEAN;
        } else if (type.equalsIgnoreCase(AttributeType.STRING.toString())) {
            return AttributeType.STRING;
        } else if (type.equalsIgnoreCase(AttributeType.NUMBER.toString())) {
            return AttributeType.NUMBER;
        }
        return null;
    }

    private Boolean parseIsKeyEntity(String iskeyEntity) {
        return Boolean.parseBoolean(iskeyEntity);
    }

}