package pt.ist.socialsoftware.blendedworkflow.core.xml;

import java.util.Set;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ActivityModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefProductCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.GoalModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ProductGoal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;

public class SpecXmlExport {
	public final String export(Specification spec) {
		Element element = createHeader(spec);

		exportCore(spec, element);

		exportModules(spec, element);

		return formatResult(element);
	}

	protected void exportModules(Specification spec, Element element) {
		// to be overridden by other modules
	}

	private String formatResult(Element element) {
		XMLOutputter xml = new XMLOutputter();
		xml.setFormat(Format.getPrettyFormat());
		// System.out.println(xml.outputString(element));

		return xml.outputString(element);
	}

	private void exportCore(Specification spec, Element element) {
		exportDataModel(element, spec);
		exportActivityModel(element, spec);
		exportGoalModel(element, spec);
	}

	private Element createHeader(Specification spec) {
		Document jdomDoc = new Document();
		Element rootElement = new Element("specification");
		rootElement.setAttribute("name", spec.getName());
		rootElement.setAttribute("specId", spec.getSpecId());

		jdomDoc.setRootElement(rootElement);
		return rootElement;
	}

	private void exportDataModel(Element element, Specification spec) {
		Element dataModelElement = new Element("data-model");

		exportEntities(dataModelElement, spec);

		exportAssociations(dataModelElement, spec);

		element.addContent(dataModelElement);
	}

	private void exportEntities(Element element, Specification spec) {
		Element entityListElement = new Element("entities");
		DataModel dataModel = spec.getDataModel();

		for (Entity entity : dataModel.getEntitySet()) {
			Element entityElement = new Element("entity");
			entityElement.setAttribute("name", entity.getName());
			entityElement.setAttribute("exists", entity.getExists() ? "true" : "false");
			entityElement.setAttribute("mandatory", entity.getMandatory() ? "true" : "false");

			exportAttributes(entityElement, entity);

			entityListElement.addContent(entityElement);
		}
		element.addContent(entityListElement);
	}

	private void exportAttributes(Element element, Entity entity) {
		for (Attribute attribute : entity.getAttributeSet()) {
			Element attributeElement = new Element("attribute");
			attributeElement.setAttribute("name", attribute.getName());
			attributeElement.setAttribute("type", attribute.getType().toString());
			attributeElement.setAttribute("mandatory", attribute.getIsMandatory() ? "true" : "false");

			element.addContent(attributeElement);
		}
	}

	private void exportAssociations(Element element, Specification spec) {
		Element associationListElement = new Element("associations");
		DataModel dataModel = spec.getDataModel();

		for (RelationBW relation : dataModel.getRelationBWSet()) {
			Element associationElement = new Element("association");
			associationElement.setAttribute("name", relation.getName());

			Element entityOne = new Element("member");
			entityOne.setAttribute("entity", relation.getEntityOne().getName());
			entityOne.setAttribute("role", relation.getRolenameOne());
			entityOne.setAttribute("cardinality", relation.getCardinalityOne().getExp());
			associationElement.addContent(entityOne);

			Element entityTwo = new Element("member");
			entityTwo.setAttribute("entity", relation.getEntityTwo().getName());
			entityTwo.setAttribute("role", relation.getRolenameTwo());
			entityTwo.setAttribute("cardinality", relation.getCardinalityTwo().getExp());
			associationElement.addContent(entityTwo);

			associationListElement.addContent(associationElement);
		}
		element.addContent(associationListElement);
	}

	private void exportActivityModel(Element element, Specification spec) {
		Element activityModelElement = new Element("activity-model");

		ActivityModel activityModel = spec.getActivityModel();

		for (Activity activity : activityModel.getActivitySet()) {
			Element activityElement = new Element("activity");
			activityElement.setAttribute("name", activity.getName());

			exportPreConditions(activityElement, activity.getPreConditionSet());
			exportSeqConditions(activityElement, activity.getSequenceConditionSet());
			exportPostConditions(activityElement, activity.getPostConditionSet());
			exportMulConditions(activityElement, activity.getMultiplicityInvariantSet());

			activityModelElement.addContent(activityElement);
		}

		element.addContent(activityModelElement);
	}

	private void exportPreConditions(Element element, Set<DefPathCondition> preConditionSet) {
		Element pathConditionsElement = new Element("preConditions");

		for (DefPathCondition defPathCondition : preConditionSet) {
			Element pathCondition = new Element("condition");
			pathCondition.setAttribute("path", defPathCondition.getPath().getValue());
			pathConditionsElement.addContent(pathCondition);
		}

		element.addContent(pathConditionsElement);
	}

	private void exportSeqConditions(Element element, Set<DefPathCondition> sequenceConditionSet) {
		Element seqConditionsElement = new Element("seqConditions");

		for (DefPathCondition defPathCondition : sequenceConditionSet) {
			Element pathCondition = new Element("condition");
			pathCondition.setAttribute("path", defPathCondition.getPath().getValue());
			seqConditionsElement.addContent(pathCondition);
		}

		element.addContent(seqConditionsElement);
	}

	private void exportPostConditions(Element element, Set<DefProductCondition> postConditionSet) {
		Element postConditionsElement = new Element("postConditions");

		for (DefProductCondition defProductCondition : postConditionSet) {
			Element pathCondition = new Element("condition");
			pathCondition.setAttribute("path", defProductCondition.getPath().getValue());
			postConditionsElement.addContent(pathCondition);
		}

		element.addContent(postConditionsElement);
	}

	private void exportMulConditions(Element element, Set<MulCondition> multiplicityInvariantSet) {
		Element mulConditionsElement = new Element("mulConditions");

		for (MulCondition mulCondition : multiplicityInvariantSet) {
			Element mulConditionElement = new Element("condition");
			mulConditionElement.setAttribute("name", mulCondition.getRelationBW().getName());
			mulConditionElement.setAttribute("rolename", mulCondition.getRolename());
			mulConditionElement.setAttribute("cardinality", mulCondition.getCardinality().getExp());
			mulConditionsElement.addContent(mulConditionElement);
		}

		element.addContent(mulConditionsElement);
	}

	private void exportGoalModel(Element element, Specification spec) {
		Element goalModelElement = new Element("goal-model");

		GoalModel goalModel = spec.getGoalModel();

		for (Goal goal : goalModel.getGoalSet()) {
			Element goalElement = new Element("goal");
			goalElement.setAttribute("type", goal.getClass().getName());
			goalElement.setAttribute("name", goal.getName());

			if (goal instanceof ProductGoal) {
				exportPreConditions(goalElement, goal.getActivationConditionSet());
				exportPostConditions(goalElement, goal.getSuccessConditionSet());
			} else {
				exportPreConditions(goalElement, goal.getActivationConditionSet());
				exportMulConditions(goalElement, goal.getEntityInvariantConditionSet());
			}

			goalModelElement.addContent(goalElement);
		}

		element.addContent(goalModelElement);
	}

}
