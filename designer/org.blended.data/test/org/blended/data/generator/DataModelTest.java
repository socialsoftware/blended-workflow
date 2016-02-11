package org.blended.data.generator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.blended.common.common.Attribute;
import org.blended.common.common.AttributeGroup;
import org.blended.common.common.CommonFactory;
import org.blended.common.common.Entity;
import org.blended.common.common.EntityAchieveCondition;
import org.blended.common.common.EntityAchieveConditionExist;
import org.blended.common.common.Specification;
import org.blended.condition.condition.ConditionFactory;
import org.blended.condition.condition.ConditionModel;
import org.blended.data.data.DataFactory;
import org.blended.data.data.DataModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataModelTest {
	private static final Logger logger = LoggerFactory.getLogger(DataModelTest.class);
	private static DataModel dataModel;
	private static ConditionModel conditionModel;
	private static CommonFactory factory;
	private static DataFactory dataFactory;
	private static ConditionFactory conditionFactory;
	private static DataGeneratorConditionModel dataGen;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		factory = CommonFactory.eINSTANCE;
		dataFactory = DataFactory.eINSTANCE;
		conditionFactory = ConditionFactory.eINSTANCE;

		dataModel = dataFactory.createDataModel();
		Specification spec = factory.createSpecification();
		spec.setName("Doctor appoinment");
		dataModel.setSpecification(spec);
		setEntities();

		dataGen = new DataGeneratorConditionModel(null, null);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		conditionModel = conditionFactory.createConditionModel();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getSpecificationName() {
		assertThat(dataModel.getSpecification().getName(), is("Doctor appointment"));
	}

	@Test
	public void fromDMtoCMEntityAchieveCondition() {
		Optional<Entity> ent = dataModel.getEntities().stream().filter(e -> e.getName().equals("Episode")).findFirst();
		assertTrue(ent.isPresent());
		dataGen.entityAchieveCondition(ent.get(), conditionModel);
		Optional<EntityAchieveCondition> entAC = conditionModel.getEntityAchieveConditions().stream()
				.filter(e -> e.eClass().getName().equals("EntityAchieveCondition")).map(e -> (EntityAchieveCondition) e)
				.filter(e -> e.getName().equals("Episode")).findFirst();
		assertTrue(entAC.isPresent());
	}

	@Test
	public void fromDMtoCMEntityAchieveConditionExist() {
		Optional<Entity> ent = dataModel.getEntities().stream().filter(e -> e.getName().equals("Patient")).findFirst();
		assertTrue(ent.isPresent());
		dataGen.entityAchieveCondition(ent.get(), conditionModel);
		Optional<EntityAchieveConditionExist> entAC = conditionModel.getEntityAchieveConditions().stream()
				.filter(e -> e.eClass().getName().equals("EntityAchieveConditionExist"))
				.map(e -> (EntityAchieveConditionExist) e).filter(e -> e.getName().equals("Patient")).findFirst();
		assertTrue(entAC.isPresent());
	}

	@Test
	public void setSpecificationName() {
		Specification spec = dataModel.getSpecification();
		spec.setName("testingSpecification");
		dataModel.setSpecification(spec);
		assertThat(dataModel.getSpecification().getName(), is("testingSpecification"));
		spec.setName("Doctor appointment");
		dataModel.setSpecification(spec);
		assertThat(dataModel.getSpecification().getName(), is("Doctor appointment"));
	}

	private static void setEntities() {
		Entity ePatient = factory.createEntity();
		ePatient.setName("Patient");
		ePatient.setExists(true);
		Attribute att = factory.createAttribute();
		att.setName("name");
		att.setType("String");
		ePatient.getAttributes().add(att);
		att = factory.createAttribute();
		att.setName("age");
		att.setType("Number");
		ePatient.getAttributes().add(att);
		att = factory.createAttribute();
		att.setName("heartProblems");
		att.setType("Boolean");
		ePatient.getAttributes().add(att);
		dataModel.getEntities().add(ePatient);

		Entity eEpisode = factory.createEntity();
		eEpisode.setName("Episode");
		eEpisode.getDependsOn().add("Patient");
		att = factory.createAttribute();
		att.setName("reserveDate");
		att.setType("String");
		eEpisode.getAttributes().add(att);
		att = factory.createAttribute();
		att.setName("checkIn");
		att.setType("Boolean");
		eEpisode.getAttributes().add(att);
		att = factory.createAttribute();
		att.setName("CheckOut");
		att.setType("Boolean");
		eEpisode.getAttributes().add(att);
		dataModel.getEntities().add(eEpisode);

		Entity ePrescription = factory.createEntity();
		ePrescription.setName("Prescription");
		att = factory.createAttribute();
		att.setName("description");
		att.setType("String");
		att.getDependsOn().add("episode.report.description");
		ePrescription.getAttributes().add(att);
		dataModel.getEntities().add(ePrescription);

		Entity eData = factory.createEntity();
		eData.setName("Data");
		att = factory.createAttribute();
		att.setName("height");
		att.setType("Number");
		eData.getAttributes().add(att);
		att = factory.createAttribute();
		att.setName("weight");
		att.setType("Number");
		eData.getAttributes().add(att);
		att = factory.createAttribute();
		att.setName("bloodPressure");
		att.setType("Number");
		eData.getAttributes().add(att);
		att = factory.createAttribute();
		att.setName("physicalCondition");
		att.setType("String");
		eData.getAttributes().add(att);
		dataModel.getEntities().add(eData);

		Entity eReport = factory.createEntity();
		eReport.setName("Report");
		att = factory.createAttribute();
		att.setName("description");
		att.setType("String");
		eReport.getAttributes().add(att);
		dataModel.getEntities().add(eReport);

		Entity eMedication = factory.createEntity();
		eMedication.setName("Medication");
		AttributeGroup group = factory.createAttributeGroup();
		group.setName("group1");
		group.getDependsOn().add("Prescription.description");
		eMedication.getAttributes().add(group);
		att = factory.createAttribute();
		att.setName("name");
		att.setType("String");
		group.getAttributes().add(att);
		att = factory.createAttribute();
		att.setName("quantity");
		att.setType("Number");
		group.getAttributes().add(att);
		att = factory.createAttribute();
		att.setName("heartImpact");
		att.setType("Number");
		group.getAttributes().add(att);
		dataModel.getEntities().add(eMedication);
	}

}
