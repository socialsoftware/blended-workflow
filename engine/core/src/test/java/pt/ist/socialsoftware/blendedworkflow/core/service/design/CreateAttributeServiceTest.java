package pt.ist.socialsoftware.blendedworkflow.core.service.design;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Product.ProductType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.AttributeDTO;

public class CreateAttributeServiceTest extends TeardownRollbackTest {
	private static Logger log = LoggerFactory.getLogger(CreateAttributeServiceTest.class);

	private static final String SPEC_ID = "Spec ID";
	private static final String ENTITY_NAME = "Entity Name";
	private static final String ATTRIBUTE_NAME = "Attribute Name";
	private static final String NON_EXIST = "No Name";
	private static final String DUP_NAME = "Exists Name";
	private static final String EMPTY_NAME = "";

	Entity ent;

	@Override
	public void populate4Test() throws BWException {
		Specification spec = new Specification(SPEC_ID, "name", "author", "description", "version", "UID");
		this.ent = new Entity(spec.getDataModel(), ENTITY_NAME, false);
		new Attribute(spec.getDataModel(), this.ent, DUP_NAME, AttributeType.NUMBER, false, false, false);
	}

	@Test
	public void success() throws BWException {
		log.debug("success ent.getExternalId():{}", this.ent.getExternalId());

		DesignInterface.getInstance().createAttribute(new AttributeDTO(SPEC_ID, ProductType.ATTRIBUTE.name(),
				this.ent.getExternalId(), null, ATTRIBUTE_NAME, AttributeType.NUMBER.toString(), true));

		Specification spec = getBlendedWorkflow().getSpecById(SPEC_ID).get();
		Entity entity = spec.getDataModel().getEntity(ENTITY_NAME).get();
		Attribute att = entity.getAttribute(ATTRIBUTE_NAME).orElse(null);
		assertNotNull(att);
		assertEquals(ATTRIBUTE_NAME, att.getName());
		assertEquals(AttributeType.NUMBER, att.getType());
	}

	@Test
	public void nonExistsEntityExtId() throws BWException {
		try {
			DesignInterface.getInstance().createAttribute(new AttributeDTO(SPEC_ID, ProductType.ATTRIBUTE.name(),
					NON_EXIST, null, ATTRIBUTE_NAME, AttributeType.BOOLEAN.toString(), false));
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.NOT_FOUND, bwe.getError());
			assertEquals(NON_EXIST, bwe.getMessage());
		}

	}

	@Test
	public void emptyEntityExtId() throws BWException {
		try {
			DesignInterface.getInstance().createAttribute(new AttributeDTO(SPEC_ID, ProductType.ATTRIBUTE.name(),
					EMPTY_NAME, EMPTY_NAME, ATTRIBUTE_NAME, AttributeType.STRING.toString(), true));
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.NOT_FOUND, bwe.getError());
			assertEquals(EMPTY_NAME, bwe.getMessage());
		}

	}

	@Test
	public void nullEntityExtId() throws BWException {
		try {
			DesignInterface.getInstance().createAttribute(new AttributeDTO(SPEC_ID, ProductType.ATTRIBUTE.name(), null,
					null, ATTRIBUTE_NAME, AttributeType.BOOLEAN.toString(), false));
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.NOT_FOUND, bwe.getError());
			assertEquals(null, bwe.getMessage());
		}

	}

}
