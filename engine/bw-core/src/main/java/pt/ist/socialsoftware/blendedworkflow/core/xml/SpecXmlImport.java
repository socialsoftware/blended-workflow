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
import pt.ist.socialsoftware.blendedworkflow.core.domain.AssociationGoal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.GoalModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ProductGoal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.ActivityDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.AttributeDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.EntityDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.GoalDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.RelationDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.SpecDto;

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

		importSpecification(doc);
	}

	public final void importSpecification(String specXml) {
		SAXBuilder builder = new SAXBuilder();
		builder.setIgnoringElementContentWhitespace(true);

		InputStream stream = new ByteArrayInputStream(specXml.getBytes());

		importSpecification(stream);
	}

	protected void importModules(Element specElement, Specification spec) {
		// to be overridden by other modules
	}

	@Atomic(mode = TxMode.WRITE)
	private void importSpecification(Document doc) {
		XPathFactory xpfac = XPathFactory.instance();
		XPathExpression<Element> xp = xpfac.compile("//specification", Filters.element());
		for (Element specElement : xp.evaluate(doc)) {
			String name = specElement.getAttributeValue("name");
			String specId = specElement.getAttributeValue("specId");

			Specification spec = DesignInterface.getInstance().createSpecification(new SpecDto(specId, name));

			importCore(specElement, spec);
			importModules(specElement, spec);
		}
	}

	private void importCore(Element specElement, Specification spec) {
		importDataModel(specElement, spec.getDataModel());
		spec.getConditionModel().generateConditions();
		importActivityModel(specElement, spec.getActivityModel());
		importGoalModel(specElement, spec.getGoalModel());
	}

	private void importDataModel(Element specElement, DataModel dataModel) {
		importEntities(specElement, dataModel);
		importAssociations(specElement, dataModel);
	}

	private void importEntities(Element specElement, DataModel dataModel) {
		for (Element entityElement : specElement.getChild("data-model").getChild("entities").getChildren("entity")) {
			String entityName = entityElement.getAttributeValue("name");
			boolean entityMandatory = convertStringToBool(entityElement.getAttributeValue("mandatory"));
			boolean exists = convertStringToBool(entityElement.getAttributeValue("exists"));

			Entity entity = DesignInterface.getInstance().createEntity(
					new EntityDto(dataModel.getSpecification().getSpecId(), entityName, exists, entityMandatory));

			for (Element attributeElement : entityElement.getChildren("attribute")) {
				String attributeName = attributeElement.getAttributeValue("name");
				Attribute.AttributeType type = convertStringToAttributeType(attributeElement.getAttributeValue("type"));
				boolean attributeMandatory = convertStringToBool(entityElement.getAttributeValue("mandatory"));

				DesignInterface.getInstance().createAttribute(new AttributeDto(dataModel.getSpecification().getSpecId(),
						null, entity.getExternalId(), entityName, attributeName, type.toString(), attributeMandatory));
			}
		}
	}

	private void importAssociations(Element specElement, DataModel dataModel) {
		for (Element associationElement : specElement.getChild("data-model").getChild("associations")
				.getChildren("association")) {
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
			DesignInterface.getInstance()
					.createRelation(new RelationDto(dataModel.getSpecification().getSpecId(), name,
							dataModel.getEntity(entity[0]).get().getExternalId(), rolename[0], cardinality[0],
							dataModel.getEntity(entity[1]).get().getExternalId(), rolename[1], cardinality[1]));
		}
	}

	private void importActivityModel(Element specElement, ActivityModel activityModel) {
		for (Element activityElement : specElement.getChild("activity-model").getChildren("activity")) {
			String name = activityElement.getAttributeValue("name");
			Activity activity = DesignInterface.getInstance()
					.createActivity(new ActivityDto(activityModel.getSpecification().getSpecId(), name));
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
					activity.getActivityModel().getSpecification().getConditionModel().getDefProductByPath(path));
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

	private void importGoalModel(Element specElement, GoalModel goalModel) {
		for (Element goalElement : specElement.getChild("goal-model").getChildren("goal")) {
			String type = goalElement.getAttributeValue("type");
			String name = goalElement.getAttributeValue("name");
			if (type.equals(ProductGoal.class.getName())) {
				ProductGoal goal = DesignInterface.getInstance()
						.createProductGoal(new GoalDto(goalModel.getSpecification().getSpecId(), null, type, name));

				importPreConditions(goalElement, goal);
				importPostConditions(goalElement, goal);
			} else {
				AssociationGoal goal = DesignInterface.getInstance()
						.createAssociationGoal(new GoalDto(goalModel.getSpecification().getSpecId(), null, type, name));
				importPreConditions(goalElement, goal);
				importMulConditions(goalElement, goal);
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
					goal.getGoalModel().getSpecification().getConditionModel().getDefProductByPath(path));
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
