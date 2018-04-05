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
import pt.ist.socialsoftware.blendedworkflow.core.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class SpecXmlImport {

	public void importUsers(InputStream inputStream) {
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

		importUsers(stream);
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
		// importAssociations(doc,blendedWorkflow);
	}

	private void importEntities(Document doc, DataModel dataModel) {
		XPathFactory xpfac = XPathFactory.instance();
		XPathExpression<Element> xp = xpfac.compile("//specification/data-model/entities", Filters.element());
		for (Element entityElement : xp.evaluate(doc)) {
			String name = entityElement.getAttributeValue("name");
			boolean mandatory = convertStringToBool(entityElement.getAttributeValue("mandatory"));
			boolean exists = convertStringToBool(entityElement.getAttributeValue("exists"));

			Entity entity = new Entity(dataModel, name, exists, mandatory);
		}
	}

	protected static boolean convertStringToBool(String value) {
		return value.equals("true") ? true : false;
	}

}
