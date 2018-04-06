package pt.ist.socialsoftware.blendedworkflow.core.xml;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class SpecXmlImport {

	private static final BWErrorType CONVERSION_ERROR = null;

	public void importSpecification(InputStream inputStream) {
		SAXBuilder builder = new SAXBuilder();
		builder.setIgnoringElementContentWhitespace(true);

		Document doc;
		try {
			doc = builder.build(inputStream);
		} catch (FileNotFoundException e) {
			throw new BWException(BWErrorType.FILE_ERROR, "Ficheiro não encontrado");
		} catch (JDOMException e) {
			throw new BWException(BWErrorType.FILE_ERROR, "Ficheiro com problemas de codificação TEI");
		} catch (IOException e) {
			throw new BWException(BWErrorType.FILE_ERROR, "Problemas com o ficheiro, tipo ou formato");
		}

		if (doc == null) {
			BWException ex = new BWException(BWErrorType.FILE_ERROR, "Ficheiro inexistente ou sem formato TEI");
			throw ex;
		}

		processImport(doc);
	}

	public void importSpecification(String specXml) {
		SAXBuilder builder = new SAXBuilder();
		builder.setIgnoringElementContentWhitespace(true);

		InputStream stream = new ByteArrayInputStream(specXml.getBytes());

		importSpecification(stream);
	}

	@Atomic(mode = TxMode.WRITE)
	public void processImport(Document doc) {
		XPathFactory xpfac = XPathFactory.instance();
		XPathExpression<Element> xp = xpfac.compile("//specification", Filters.element());
		for (Element specification : xp.evaluate(doc)) {
			String name = specification.getAttributeValue("name");
			String specId = specification.getAttributeValue("specId");

			Specification spec = new Specification(specId, name);

			importDataModel(doc, spec.getDataModel());
			spec.getConditionModel().generateConditions();
			// importActivityModel(doc, blendedWorkflow);
			// importGoalModel(doc, blendedWorkflow);
		}
	}

	private void importDataModel(Document doc, DataModel dataModel) {
		importEntities(doc, dataModel);
		importAssociations(doc, dataModel);
	}

	private void importEntities(Document doc, DataModel dataModel) {
		XPathFactory xpfac = XPathFactory.instance();
		XPathExpression<Element> xp = xpfac.compile("//specification/data-model/entities/entity", Filters.element());
		for (Element entityElement : xp.evaluate(doc)) {
			String entityName = entityElement.getAttributeValue("name");
			boolean entityMandatory = convertStringToBool(entityElement.getAttributeValue("mandatory"));
			boolean exists = convertStringToBool(entityElement.getAttributeValue("exists"));

			Entity entity = new Entity(dataModel, entityName, exists, entityMandatory);

			for (Element attributeElement : entityElement.getChildren("attribute")) {
				String attributeName = attributeElement.getAttributeValue("name");
				Attribute.AttributeType type = convertStringToAttributeType(attributeElement.getAttributeValue("type"));
				boolean attributeMandatory = convertStringToBool(entityElement.getAttributeValue("mandatory"));

				new Attribute(dataModel, entity, attributeName, type, attributeMandatory);
			}
		}
	}

	private void importAssociations(Document doc, DataModel dataModel) {
		XPathFactory xpfac = XPathFactory.instance();
		XPathExpression<Element> xp = xpfac.compile("//specification/data-model/associations/association",
				Filters.element());
		for (Element associationElement : xp.evaluate(doc)) {
			String name = associationElement.getAttributeValue("name");
			String[] entity = new String[2];
			String[] rolename = new String[2];
			String[] cardinality = new String[2];
			int i = 0;
			for (Element memberElement : associationElement.getChildren("member")) {
				entity[i] = memberElement.getAttributeValue("entity");
				rolename[i] = memberElement.getAttributeValue("role");
				cardinality[i] = memberElement.getAttributeValue("cardinality");
				i++;
			}
			new RelationBW(dataModel, name, dataModel.getEntity(entity[0]).get(), rolename[0], cardinality[0],
					dataModel.getEntity(entity[1]).get(), rolename[1], cardinality[1]);
		}
	}

	protected static boolean convertStringToBool(String value) {
		return value.equals("true") ? true : false;
	}

	protected static Attribute.AttributeType convertStringToAttributeType(String value) {
		switch (value) {
		case "Boolean":
			return Attribute.AttributeType.BOOLEAN;
		case "Number":
			return Attribute.AttributeType.NUMBER;
		case "String":
			return Attribute.AttributeType.STRING;
		case "Date":
			return Attribute.AttributeType.DATE;
		default:
			throw new BWException(CONVERSION_ERROR, value);
		}

	}
}
