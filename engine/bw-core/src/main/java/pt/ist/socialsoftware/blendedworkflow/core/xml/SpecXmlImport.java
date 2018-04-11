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
import pt.ist.socialsoftware.blendedworkflow.core.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ActivityModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.GoalModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class SpecXmlImport {

	private static final BWErrorType CONVERSION_ERROR = null;

	public final void importSpecification(InputStream inputStream) {
		SAXBuilder builder = new SAXBuilder();
		builder.setIgnoringElementContentWhitespace(true);

		Document doc;
		try {
			doc = builder.build(inputStream);
		} catch (FileNotFoundException e) {
			throw new BWException(BWErrorType.FILE_ERROR, "Ficheiro não encontrado");
		} catch (JDOMException e) {
			throw new BWException(BWErrorType.FILE_ERROR, "Ficheiro com problemas de codificação XML");
		} catch (IOException e) {
			throw new BWException(BWErrorType.FILE_ERROR, "Problemas com o ficheiro, tipo ou formato");
		}

		if (doc == null) {
			BWException ex = new BWException(BWErrorType.FILE_ERROR, "Ficheiro inexistente ou sem formato XML");
			throw ex;
		}

		processImport(doc);
	}

	public final void importSpecification(String specXml) {
		SAXBuilder builder = new SAXBuilder();
		builder.setIgnoringElementContentWhitespace(true);

		InputStream stream = new ByteArrayInputStream(specXml.getBytes());

		importSpecification(stream);
	}

	@Atomic(mode = TxMode.WRITE)
	public final void processImport(Document doc) {
		importCore(doc);
		importModules(doc);
	}

	protected void importModules(Document doc) {
		// to be overridden by other modules
	}

	private void importCore(Document doc) {
		XPathFactory xpfac = XPathFactory.instance();
		XPathExpression<Element> xp = xpfac.compile("//specification", Filters.element());
		for (Element specification : xp.evaluate(doc)) {
			String name = specification.getAttributeValue("name");
			String specId = specification.getAttributeValue("specId");

			Specification spec = new Specification(specId, name);

			importDataModel(doc, spec.getDataModel());
			spec.getConditionModel().generateConditions();
			importActivityModel(doc, spec.getActivityModel());
			importGoalModel(doc, spec.getGoalModel());
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

	private void importActivityModel(Document doc, ActivityModel activityModel) {
		XPathFactory xpfac = XPathFactory.instance();
		XPathExpression<Element> xp = xpfac.compile("//specification/activity-model/activity", Filters.element());
		for (Element activityElement : xp.evaluate(doc)) {
			String name = activityElement.getAttributeValue("name");
			Activity activity = new Activity(activityModel, name, "");
			importPreConditions(activityElement, activity);
			importSeqConditions(activityElement, activity);
			importPostConditions(activityElement, activity);
			importMulConditions(activityElement, activity);
		}
	}

	private void importPreConditions(Element element, Activity activity) {
		Element subElement = element.getChild("preConditions");
		for (Element condition : subElement.getChildren("condition")) {
			String path = condition.getAttributeValue("path");
			activity.addPreCondition(
					DefPathCondition.getDefPathCondition(activity.getActivityModel().getSpecification(), path));
		}
	}

	private void importSeqConditions(Element element, Activity activity) {
		Element subElement = element.getChild("seqConditions");
		for (Element condition : subElement.getChildren("condition")) {
			String path = condition.getAttributeValue("path");
			activity.addSequenceCondition(
					DefPathCondition.getDefPathCondition(activity.getActivityModel().getSpecification(), path));
		}
	}

	private void importPostConditions(Element element, Activity activity) {
		Element subElement = element.getChild("postConditions");
		for (Element condition : subElement.getChildren("condition")) {
			String path = condition.getAttributeValue("path");
			activity.addPostCondition(
					DefPathCondition.getDefPathCondition(activity.getActivityModel().getSpecification(), path));
		}
	}

	private void importMulConditions(Element element, Activity activity) {
		Element subElement = element.getChild("mulConditions");
		for (Element condition : subElement.getChildren("condition")) {
			String name = condition.getAttributeValue("name");
			String rolename = condition.getAttributeValue("rolename");
			RelationBW relation = activity.getActivityModel().getSpecification().getDataModel().getRelation(name);
			activity.addMultiplicityInvariant(MulCondition.getMulCondition(relation, rolename));
		}
	}

	private void importGoalModel(Document doc, GoalModel goalModel) {
		XPathFactory xpfac = XPathFactory.instance();
		XPathExpression<Element> xp = xpfac.compile("//specification/goal-model/goal", Filters.element());
		for (Element goalElement : xp.evaluate(doc)) {
			String name = goalElement.getAttributeValue("name");
			Goal goal = new Goal(goalModel, name);
			importPreConditions(goalElement, goal);
			importPostConditions(goalElement, goal);
			importMulConditions(goalElement, goal);
		}

		for (Element goalElement : xp.evaluate(doc)) {
			String name = goalElement.getAttributeValue("name");
			String parent = goalElement.getAttributeValue("parent");
			if (parent != null && !parent.isEmpty()) {
				Goal goal = goalModel.getGoal(name);
				goal.setParentGoal(goalModel.getGoal(parent));
			}
		}
	}

	private void importPreConditions(Element element, Goal goal) {
		Element subElement = element.getChild("preConditions");
		for (Element condition : subElement.getChildren("condition")) {
			String path = condition.getAttributeValue("path");
			goal.addActivationCondition(
					DefPathCondition.getDefPathCondition(goal.getGoalModel().getSpecification(), path));
		}
	}

	private void importPostConditions(Element element, Goal goal) {
		Element subElement = element.getChild("postConditions");
		for (Element condition : subElement.getChildren("condition")) {
			String path = condition.getAttributeValue("path");
			goal.addSuccessCondition(
					DefPathCondition.getDefPathCondition(goal.getGoalModel().getSpecification(), path));
		}
	}

	private void importMulConditions(Element element, Goal goal) {
		Element subElement = element.getChild("mulConditions");
		for (Element condition : subElement.getChildren("condition")) {
			String name = condition.getAttributeValue("name");
			String rolename = condition.getAttributeValue("rolename");
			RelationBW relation = goal.getGoalModel().getSpecification().getDataModel().getRelation(name);
			goal.addEntityInvariantCondition(MulCondition.getMulCondition(relation, rolename));
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
