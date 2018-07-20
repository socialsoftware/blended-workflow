package pt.ist.socialsoftware.blendedworkflow.core.service.design;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.RelationDto;
import pt.ist.socialsoftware.blendedworkflow.core.utils.ModulesFactory;

public class CreateRelationServiceTest extends TeardownRollbackTest {
	private static final String SPEC_ID = "Spec ID";
	private static final String RELATION_NAME = "Relation name";
	private static final String ENTITY_ONE_NAME = "Entity Name One";
	private static final String ENTITY_TWO_NAME = "Entity Name Two";
	private static final String ROLENAME_ONE = "Rolename Name One";
	private static final String ROLENAME_TWO = "Rolename Name Two";
	private static final String ONE = "1";
	private static final String MANY = "*";

	private final ModulesFactory factory = new ModulesFactory();

	String dataModelExtId;
	Entity entityOne;
	Entity entityTwo;

	@Override
	public void populate4Test() throws BWException {
		Specification spec = new Specification(SPEC_ID, "name");
		this.dataModelExtId = spec.getDataModel().getExternalId();
		this.entityOne = new Entity(spec.getDataModel(), ENTITY_ONE_NAME, false);
		this.entityTwo = new Entity(spec.getDataModel(), ENTITY_TWO_NAME, false);
	}

	@Test
	public void success() throws BWException {
		this.factory.createDesignInterface().createRelation(new RelationDto(SPEC_ID, RELATION_NAME,
				this.entityOne.getExternalId(), ROLENAME_ONE, ONE, this.entityTwo.getExternalId(), ROLENAME_TWO, MANY));

		RelationBW relation = this.entityOne.getRelationOneSet().stream()
				.filter(rel -> rel.getRolenameOne().equals(ROLENAME_ONE)).findFirst().orElse(null);

		assertNotNull(relation);
		assertEquals(ENTITY_ONE_NAME, relation.getEntityOne().getName());
		assertEquals(ROLENAME_ONE, relation.getRolenameOne());
		assertEquals(Cardinality.ONE, relation.getCardinalityOne().getExp());
		assertEquals(ENTITY_TWO_NAME, relation.getEntityTwo().getName());
		assertEquals(ROLENAME_TWO, relation.getRolenameTwo());
		assertEquals(Cardinality.ZERO_MANY, relation.getCardinalityTwo().getExp());
	}

}
