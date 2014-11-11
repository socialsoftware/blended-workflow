package pt.ist.socialsoftware.blendedworkflow.adapters.convertor;

import java.util.HashMap;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Task;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskModel;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class YAWLSpecificationFactory {

	private BWSpecification bwSpecification;
	private String specificationURI;
	private int lovalVariablesIndex = 0;
	private int taskIndex = 3;
	private int inputOutputParamIndex = 0;
	private final HashMap<String, String> taskYAWLID = new HashMap<String, String>();

	public String parseYAWLSpecificationFactory(BWSpecification bwSpecification)
			throws BlendedWorkflowException {

		this.bwSpecification = bwSpecification;
		this.specificationURI = bwSpecification.getName().replaceAll(" ", "");

		String yawlSpecification = "";

		yawlSpecification += header();
		yawlSpecification += metaData();
		yawlSpecification += schema();
		yawlSpecification += localVariables();
		yawlSpecification += processControlElements();
		yawlSpecification += inputOutputParam();
		yawlSpecification += footer();

		// JDOMUtil.documentToFile(JDOMUtil.stringToDocument(yawlSpecification),
		// "C:/Users/User/Desktop/yawl.xml");
		// System.out.println(yawlSpecification);
		return yawlSpecification;
	}

	private String header() {
		String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<specificationSet xmlns=\"http://www.yawlfoundation.org/yawlschema\" "
				+ "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
				+ "version=\"2.2\" xsi:schemaLocation=\"http://www.yawlfoundation.org/yawlschema "
				+ "http://www.yawlfoundation.org/yawlschema/YAWL_Schema2.2.xsd\">";
		return header;
	}

	private String metaData() {
		String metaData = "<specification uri=\"" + this.specificationURI
				+ "\">" + "<metaData>" + "<title>"
				+ this.bwSpecification.getName() + "</title>" + "<creator>"
				+ this.bwSpecification.getAuthor() + "</creator>"
				+ "<description>" + this.bwSpecification.getDescription()
				+ "</description>" + "<version>"
				+ this.bwSpecification.getVersion() + "</version>"
				+ "<persistent>false</persistent>" + "<identifier>"
				+ this.bwSpecification.getUID() + "</identifier>"
				+ "</metaData>";
		return metaData;
	}

	private String schema() {
		String schema = "<schema xmlns=\"http://www.w3.org/2001/XMLSchema\" />";
		return schema;
	}

	private String localVariables() {
		String localVariables = "<decomposition id=\"" + specificationURI
				+ "\" isRootNet=\"true\" xsi:type=\"NetFactsType\">";

		DataModel dataModel = bwSpecification.getDataModel();
		for (Attribute attribute : dataModel.getAttributesSet()) {
			String entityName = attribute.getEntity().getName()
					.replaceAll(" ", "");
			String attributeName = attribute.getName().replaceAll(" ", "");
			String type = attribute.getYAWLAttributeType();
			String value = attribute.getYAWLAttributeValue();

			String localVariable = "<localVariable>" + "<index>"
					+ lovalVariablesIndex + "</index>" + "<name>" + entityName
					+ "_" + attributeName + "_State" + "</name>" + "<type>"
					+ "string" + "</type>"
					+ "<namespace>http://www.w3.org/2001/XMLSchema</namespace>"
					+ "<initialValue>" + DataState.UNDEFINED
					+ "</initialValue>" + "</localVariable>";

			lovalVariablesIndex++;
			localVariable += "<localVariable>" + "<index>"
					+ lovalVariablesIndex + "</index>" + "<name>" + entityName
					+ "_" + attributeName + "</name>" + "<type>" + type
					+ "</type>"
					+ "<namespace>http://www.w3.org/2001/XMLSchema</namespace>"
					+ "<initialValue>" + value + "</initialValue>"
					+ "</localVariable>";
			lovalVariablesIndex++;
			localVariables += localVariable;
		}
		return localVariables;
	}

	private String processControlElements() {
		Set<Attribute> attributes;
		String processControlElements = "";
		String element = "";
		String startingMappings = "";
		String completedMappings = "";
		Boolean firstTask = true;

		// Tasks
		TaskModel taskModel = bwSpecification.getTaskModel();

		// Create YAWL Task IDs
		for (Task task : taskModel.getTasksSet()) {
			String taskName = task.getName();
			String yawlID = task.getName().replaceAll(" ", "_") + "_"
					+ taskIndex;
			this.taskYAWLID.put(taskName, yawlID);
			taskIndex++;
		}

		for (Task task : taskModel.getTasksSet()) {
			String taskName = task.getName().replaceAll(" ", "_");
			String taskID = this.taskYAWLID.get(task.getName());
			String joinCode = task.getJoinCode();
			String splitCode = task.getSplitCode();
			element = "";
			startingMappings = "";
			completedMappings = "";
			if (firstTask) {
				element += "<processControlElements>"
						+ "<inputCondition id=\"InputCondition_1\">"
						+ "<flowsInto>" + "<nextElementRef id=\"" + taskID
						+ "\" />" + "</flowsInto>" + "</inputCondition>";
				processControlElements += element;
				element = "";
				firstTask = false;
			}

			element += "<task id=\"" + taskID + "\">" + "<name>" + taskName
					+ "</name>";

			if (task.getNextTasksSet().size() > 0) {
				for (Task nextTask : task.getNextTasksSet()) {
					String nextTaskID = this.taskYAWLID.get(nextTask.getName());
					element += "<flowsInto>" + "<nextElementRef id=\""
							+ nextTaskID + "\" />" + "</flowsInto>";
				}
			} else {
				element += "<flowsInto>" + "<nextElementRef id=\""
						+ "OutputCondition_2" + "\" />" + "</flowsInto>";
			}

			element += "<join code=\"" + joinCode + "\" />" + "<split code=\""
					+ splitCode + "\" />";

			// Starting Mapping
			attributes = task.getPreConstraint().getAttributes();
			if (attributes.size() > 0) {
				startingMappings += "<startingMappings>";
				for (Attribute attribute : attributes) {
					String entityName = attribute.getEntity().getName()
							.replaceAll(" ", "");
					String attributeName = attribute.getName().replaceAll(" ",
							"");

					startingMappings += "<mapping>"
							+ "<expression query=\"&lt;"
							+ entityName
							+ "_"
							+ attributeName
							+ "_State"
							+ "&gt;{/"
							+ this.specificationURI
							+ "/"
							+ entityName
							+ "_"
							+ attributeName
							+ "_State"
							+ "/text()}&lt;/"
							+ entityName
							+ "_"
							+ attributeName
							+ "_State"
							+ "&gt;\" /> "
							+ "<mapsTo>"
							+ entityName
							+ "_"
							+ attributeName
							+ "_State" + "</mapsTo>" + "</mapping>";

					startingMappings += "<mapping>"
							+ "<expression query=\"&lt;" + entityName + "_"
							+ attributeName + "&gt;{/" + this.specificationURI
							+ "/" + entityName + "_" + attributeName
							+ "/text()}&lt;/" + entityName + "_"
							+ attributeName + "&gt;\" /> " + "<mapsTo>"
							+ entityName + "_" + attributeName + "</mapsTo>"
							+ "</mapping>";

				}
				startingMappings += "</startingMappings>";
				element += startingMappings;
			}

			// Complete Mapping
			attributes = task.getPostConstraint().getAttributes();
			if (attributes.size() > 0) {
				completedMappings += "<completedMappings>";
				for (Attribute attribute : attributes) {
					String entityName = attribute.getEntity().getName()
							.replaceAll(" ", "");
					String attributeName = attribute.getName().replaceAll(" ",
							"");

					completedMappings += "<mapping>"
							+ "<expression query=\"&lt;"
							+ entityName
							+ "_"
							+ attributeName
							+ "_State"
							+ "&gt;{/"
							+ taskName
							+ "/"
							+ entityName
							+ "_"
							+ attributeName
							+ "_State"
							+ "/text()}&lt;/"
							+ entityName
							+ "_"
							+ attributeName
							+ "_State"
							+ "&gt;\" /> "
							+ "<mapsTo>"
							+ entityName
							+ "_"
							+ attributeName
							+ "_State"
							+ "</mapsTo>"
							+ "</mapping>";

					completedMappings += "<mapping>"
							+ "<expression query=\"&lt;" + entityName + "_"
							+ attributeName + "&gt;{/" + taskName + "/"
							+ entityName + "_" + attributeName
							+ "/text()}&lt;/" + entityName + "_"
							+ attributeName + "&gt;\" /> " + "<mapsTo>"
							+ entityName + "_" + attributeName + "</mapsTo>"
							+ "</mapping>";
				}
				completedMappings += "</completedMappings>";
				element += completedMappings;
			}

			// footer
			element += "<resourcing>" + "<offer initiator=\"user\" />"
					+ "<allocate initiator=\"user\" />"
					+ "<start initiator=\"user\" />" + "</resourcing>"
					+ "<decomposesTo id=\"" + taskName + "\" />" + "</task>";
			processControlElements += element;
		}
		element = "<outputCondition id=\"OutputCondition_2\" /></processControlElements></decomposition>";
		processControlElements += element;
		return processControlElements;
	}

	private String inputOutputParam() {
		Set<Attribute> attributes;
		String inputOutputParameters = "";
		String inputParam = "";
		String outputParam = "";

		// Tasks
		TaskModel taskModel = bwSpecification.getTaskModel();
		for (Task task : taskModel.getTasksSet()) {
			inputOutputParamIndex = 0;
			inputParam = "";
			outputParam = "";

			String taskName = task.getName().replaceAll(" ", "_");
			inputOutputParameters += "<decomposition id=\"" + taskName
					+ "\" xsi:type=\"WebServiceGatewayFactsType\">";

			// Input Parameters
			attributes = task.getPreConstraint().getAttributes();
			for (Attribute attribute : attributes) {
				String entityName = attribute.getEntity().getName()
						.replaceAll(" ", "");
				String attributeName = attribute.getName().replaceAll(" ", "");
				String type = attribute.getYAWLAttributeType();

				inputParam += "<inputParam>"
						+ "<index>"
						+ inputOutputParamIndex
						+ "</index>"
						+ "<name>"
						+ entityName
						+ "_"
						+ attributeName
						+ "_State"
						+ "</name>"
						+ "<type>"
						+ "string"
						+ "</type>"
						+ "<namespace>http://www.w3.org/2001/XMLSchema</namespace>"
						+ "</inputParam>";

				inputOutputParamIndex++;
				inputParam += "<inputParam>"
						+ "<index>"
						+ inputOutputParamIndex
						+ "</index>"
						+ "<name>"
						+ entityName
						+ "_"
						+ attributeName
						+ "</name>"
						+ "<type>"
						+ type
						+ "</type>"
						+ "<namespace>http://www.w3.org/2001/XMLSchema</namespace>"
						+ "</inputParam>";
				inputOutputParamIndex++;
			}
			inputOutputParameters += inputParam;

			// Output Parameters
			attributes = task.getPostConstraint().getAttributes();
			for (Attribute attribute : attributes) {
				String entityName = attribute.getEntity().getName()
						.replaceAll(" ", "");
				String attributeName = attribute.getName().replaceAll(" ", "");
				String type = attribute.getYAWLAttributeType();
				outputParam += "<outputParam>"
						+ "<index>"
						+ inputOutputParamIndex
						+ "</index>"
						+ "<name>"
						+ entityName
						+ "_"
						+ attributeName
						+ "_State"
						+ "</name>"
						+ "<type>"
						+ "string"
						+ "</type>"
						+ "<namespace>http://www.w3.org/2001/XMLSchema</namespace>"
						+ "</outputParam>";

				inputOutputParamIndex++;
				outputParam += "<outputParam>"
						+ "<index>"
						+ inputOutputParamIndex
						+ "</index>"
						+ "<name>"
						+ entityName
						+ "_"
						+ attributeName
						+ "</name>"
						+ "<type>"
						+ type
						+ "</type>"
						+ "<namespace>http://www.w3.org/2001/XMLSchema</namespace>"
						+ "</outputParam>";
				inputOutputParamIndex++;
			}
			inputOutputParameters += outputParam;
			inputOutputParameters += "<externalInteraction>manual</externalInteraction></decomposition>";
		}
		return inputOutputParameters;
	}

	private String footer() {
		String footer = "</specification></specificationSet>";
		return footer;
	}

}
