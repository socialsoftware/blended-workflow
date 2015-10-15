package org.blended.common.repository;

import java.util.HashSet;
import java.util.Set;

import org.blended.common.repository.resttemplate.RepositoryException;
import org.blended.common.repository.resttemplate.dto.AttributeDTO;
import org.blended.common.repository.resttemplate.dto.DefAttributeConditionDTO;
import org.blended.common.repository.resttemplate.dto.DefDependenceConditionDTO;
import org.blended.common.repository.resttemplate.dto.DefEntityConditionDTO;
import org.blended.common.repository.resttemplate.dto.DefProductConditionSetDTO;
import org.blended.common.repository.resttemplate.dto.DependenceDTO;
import org.blended.common.repository.resttemplate.dto.EntityDTO;
import org.blended.common.repository.resttemplate.dto.ExpressionDTO;
import org.blended.common.repository.resttemplate.dto.ExpressionDTO.Type;
import org.blended.common.repository.resttemplate.dto.RelationDTO;
import org.blended.common.repository.resttemplate.dto.RuleDTO;
import org.blended.common.repository.resttemplate.dto.SpecDTO;
import org.blended.common.repository.resttemplate.req.AddActivityReq;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateActivityModelTest {
    private static final String TEST_SPEC_ID = "TestSpecId";
    private static final String RULE_NAME = "ruleName";
    private static final String ROLENAME_TWO = "entityTwo";
    private static final String ROLENAME_ONE = "entityOne";
    private static final String ATT_THREE = "att3";
    private static final String ATT_TWO = "att2";
    private static final String ATT_ONE = "att1";
    private static final String ENTITY_TWO = "EntityTwo";
    private static final String ENTITY_ONE = "EntityOne";

    private static Logger log = LoggerFactory
            .getLogger(CreateActivityModelTest.class);

    private CommonInterface ci;

    @Before
    public void setUp() {
        log.debug("setUp");

        ci = CommonInterface.getInstance();

        try {
            ci.getSpecBySpecId(TEST_SPEC_ID);
            ci.deleteSpecification(TEST_SPEC_ID);
        } catch (RepositoryException re) {
            log.debug("getSpec: {}", re.getMessage());
        }
        ci.createSpec(new SpecDTO(TEST_SPEC_ID, "spec model"));

        EntityDTO entityDTO = ci
                .createEntity(new EntityDTO(TEST_SPEC_ID, ENTITY_ONE, false));

        AttributeDTO attOneDTO = ci
                .createAttribute(new AttributeDTO(TEST_SPEC_ID,
                        entityDTO.getExtId(), null, ATT_ONE, "Number", false));

        ci.createAttribute(new AttributeDTO(TEST_SPEC_ID, entityDTO.getExtId(),
                null, ATT_TWO, "Number", false));

        entityDTO = ci
                .createEntity(new EntityDTO(TEST_SPEC_ID, ENTITY_TWO, false));

        ci.createAttribute(new AttributeDTO(TEST_SPEC_ID, entityDTO.getExtId(),
                null, ATT_THREE, "String", false));

        ci.createRelation(
                new RelationDTO(TEST_SPEC_ID, "RelationName", ENTITY_ONE,
                        ROLENAME_ONE, "0..1", ENTITY_TWO, ROLENAME_TWO, "*"));

        ci.createDependence(
                new DependenceDTO(TEST_SPEC_ID, attOneDTO.getExtId(),
                        ENTITY_ONE + "." + ROLENAME_TWO + "." + ATT_THREE));

        ci.createRule(new RuleDTO(TEST_SPEC_ID, RULE_NAME,
                new ExpressionDTO(TEST_SPEC_ID, ExpressionDTO.Type.EQUAL,
                        new ExpressionDTO(TEST_SPEC_ID, Type.ATT_VALUE,
                                ENTITY_ONE + "." + ATT_ONE),
                        new ExpressionDTO(TEST_SPEC_ID, Type.ATT_VALUE,
                                ENTITY_ONE + "." + ATT_TWO))));

        ci.generateConditionModel(TEST_SPEC_ID);
    }

    @After
    public void tearDown() {
        log.debug("tearDown");
        try {
            ci.deleteSpecification(TEST_SPEC_ID);
        } catch (RepositoryException re) {
            log.debug("createSpec: {}", re.getMessage());
        }
    }

    @Test
    public void createActivityModelOne() {
        Set<DefEntityConditionDTO> defEnts = new HashSet<DefEntityConditionDTO>();
        Set<DefAttributeConditionDTO> defAtts = new HashSet<DefAttributeConditionDTO>();
        Set<DefDependenceConditionDTO> defDeps = new HashSet<DefDependenceConditionDTO>();
        defAtts.add(new DefAttributeConditionDTO(TEST_SPEC_ID,
                ENTITY_ONE + "." + ATT_ONE));
        AddActivityReq request = new AddActivityReq("ActivityOne",
                "Description",
                new DefProductConditionSetDTO(defEnts, defAtts, defDeps));
        ci.addActivity(TEST_SPEC_ID, request);

        defEnts.clear();
        defAtts.clear();
        defEnts.add(new DefEntityConditionDTO(TEST_SPEC_ID, ENTITY_ONE));
        request = new AddActivityReq("ActivityTwo", "Description",
                new DefProductConditionSetDTO(defEnts, defAtts, defDeps));
        ci.addActivity(TEST_SPEC_ID, request);

        defEnts.clear();
        defAtts.clear();
        defAtts.add(new DefAttributeConditionDTO(TEST_SPEC_ID,
                ENTITY_ONE + "." + ATT_TWO));
        request = new AddActivityReq("ActivityThree", "Description",
                new DefProductConditionSetDTO(defEnts, defAtts, defDeps));
        ci.addActivity(TEST_SPEC_ID, request);

        defEnts.clear();
        defAtts.clear();
        defAtts.add(new DefAttributeConditionDTO(TEST_SPEC_ID,
                ENTITY_TWO + "." + ATT_THREE));
        request = new AddActivityReq("ActivityFour", "Description",
                new DefProductConditionSetDTO(defEnts, defAtts, defDeps));
        ci.addActivity(TEST_SPEC_ID, request);

        defEnts.clear();
        defAtts.clear();
        defEnts.add(new DefEntityConditionDTO(TEST_SPEC_ID, ENTITY_TWO));
        request = new AddActivityReq("ActivityFive", "Description",
                new DefProductConditionSetDTO(defEnts, defAtts, defDeps));
        ci.addActivity(TEST_SPEC_ID, request);

        ci.checkActivityModelConsistency(TEST_SPEC_ID);
    }

    @Test
    public void createActivityModelTwo() {
        Set<DefEntityConditionDTO> defEnts = new HashSet<DefEntityConditionDTO>();
        Set<DefAttributeConditionDTO> defAtts = new HashSet<DefAttributeConditionDTO>();
        Set<DefDependenceConditionDTO> defDeps = new HashSet<DefDependenceConditionDTO>();
        defEnts.add(new DefEntityConditionDTO(TEST_SPEC_ID, ENTITY_ONE));
        defAtts.add(new DefAttributeConditionDTO(TEST_SPEC_ID,
                ENTITY_ONE + "." + ATT_ONE));
        AddActivityReq request = new AddActivityReq("ActivityOne",
                "Description",
                new DefProductConditionSetDTO(defEnts, defAtts, defDeps));
        ci.addActivity(TEST_SPEC_ID, request);

        defEnts.clear();
        defAtts.clear();
        defEnts.add(new DefEntityConditionDTO(TEST_SPEC_ID, ENTITY_TWO));
        request = new AddActivityReq("ActivityTwo", "Description",
                new DefProductConditionSetDTO(defEnts, defAtts, defDeps));
        ci.addActivity(TEST_SPEC_ID, request);

        defEnts.clear();
        defAtts.clear();
        defAtts.add(new DefAttributeConditionDTO(TEST_SPEC_ID,
                ENTITY_ONE + "." + ATT_TWO));
        defAtts.add(new DefAttributeConditionDTO(TEST_SPEC_ID,
                ENTITY_TWO + "." + ATT_THREE));
        request = new AddActivityReq("ActivityThree", "Description",
                new DefProductConditionSetDTO(defEnts, defAtts, defDeps));
        ci.addActivity(TEST_SPEC_ID, request);

        ci.checkActivityModelConsistency(TEST_SPEC_ID);
    }

    @Test
    public void createActivityModelThree() {
        Set<DefEntityConditionDTO> defEnts = new HashSet<DefEntityConditionDTO>();
        Set<DefAttributeConditionDTO> defAtts = new HashSet<DefAttributeConditionDTO>();
        Set<DefDependenceConditionDTO> defDeps = new HashSet<DefDependenceConditionDTO>();
        defEnts.add(new DefEntityConditionDTO(TEST_SPEC_ID, ENTITY_ONE));
        defAtts.add(new DefAttributeConditionDTO(TEST_SPEC_ID,
                ENTITY_ONE + "." + ATT_ONE));
        AddActivityReq request = new AddActivityReq("ActivityOne",
                "Description",
                new DefProductConditionSetDTO(defEnts, defAtts, defDeps));
        ci.addActivity(TEST_SPEC_ID, request);

        defEnts.clear();
        defAtts.clear();
        defAtts.add(new DefAttributeConditionDTO(TEST_SPEC_ID,
                ENTITY_ONE + "." + ATT_TWO));
        defAtts.add(new DefAttributeConditionDTO(TEST_SPEC_ID,
                ENTITY_TWO + "." + ATT_THREE));
        defEnts.add(new DefEntityConditionDTO(TEST_SPEC_ID, ENTITY_TWO));
        request = new AddActivityReq("ActivityTwo", "Description",
                new DefProductConditionSetDTO(defEnts, defAtts, defDeps));
        ci.addActivity(TEST_SPEC_ID, request);

        ci.checkActivityModelConsistency(TEST_SPEC_ID);
    }

    @Test
    public void createActivityModelFour() {
        Set<DefEntityConditionDTO> defEnts = new HashSet<DefEntityConditionDTO>();
        Set<DefAttributeConditionDTO> defAtts = new HashSet<DefAttributeConditionDTO>();
        Set<DefDependenceConditionDTO> defDeps = new HashSet<DefDependenceConditionDTO>();
        defEnts.add(new DefEntityConditionDTO(TEST_SPEC_ID, ENTITY_ONE));
        defEnts.add(new DefEntityConditionDTO(TEST_SPEC_ID, ENTITY_TWO));
        defAtts.add(new DefAttributeConditionDTO(TEST_SPEC_ID,
                ENTITY_ONE + "." + ATT_ONE));
        defAtts.add(new DefAttributeConditionDTO(TEST_SPEC_ID,
                ENTITY_ONE + "." + ATT_TWO));
        defAtts.add(new DefAttributeConditionDTO(TEST_SPEC_ID,
                ENTITY_TWO + "." + ATT_THREE));
        AddActivityReq request = new AddActivityReq("ActivityTwo",
                "Description",
                new DefProductConditionSetDTO(defEnts, defAtts, defDeps));
        ci.addActivity(TEST_SPEC_ID, request);

        ci.checkActivityModelConsistency(TEST_SPEC_ID);
    }

}
