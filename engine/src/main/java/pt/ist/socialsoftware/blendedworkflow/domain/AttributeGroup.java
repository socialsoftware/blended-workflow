package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.dto.AttributeGroupDTO;

public class AttributeGroup extends AttributeGroup_Base {
	private static Logger log = LoggerFactory.getLogger(AttributeGroup.class);

	public AttributeGroup(DataModel dataModel, Entity entity, String name, boolean isMandatory) {
		setDataModel(dataModel);
		setEntity(entity);
		setName(name);
		setIsMandatory(isMandatory);
	}

	@Override
	public ProductType getProductType() {
		return ProductType.ATTRIBUTE_GROUP;
	}

	public Optional<AttributeBasic> getAttribute(String name) {
		return getAttributeSet().stream().filter(att -> att.getName().equals(name)).findFirst();
	}

	@Override
	public void delete() {
		setDataModel(null);
		setEntity(null);
		if (getDefAttributeCondition() != null)
			getDefAttributeCondition().delete();
		getAttributeSet().stream().forEach(att -> att.delete());

		super.delete();
	}

	@Override
	public Product getNext(List<String> pathLeft, String path) {
		// log.debug("getNext {}:{}", path, pathLeft);

		if (pathLeft.isEmpty())
			return this;

		AttributeBasic att = getAttribute(pathLeft.get(0))
				.orElseThrow(() -> new BWException(BWErrorType.INVALID_PATH, path + ":" + pathLeft));

		pathLeft.remove(0);
		return att.getNext(pathLeft, path);
	}

	@Override
	public AttributeGroupDTO getDTO() {
		AttributeGroupDTO group = new AttributeGroupDTO();
		group.setSpecId(getDataModel().getSpecification().getSpecId());
		group.setExtId(getExternalId());
		group.setProductType(getProductType().name());
		group.setEntityExtId(getEntity().getExternalId());
		group.setEntityName(getEntity().getName());
		group.setName(getName());
		group.setMandatory(getIsMandatory());

		return group;
	}

	@Override
	public DefProductCondition getDefCondition() {
		return DefAttributeCondition.getDefAttribute(this);
	}

	@Override
	public Set<AttributeBasic> getAttributeBasicSet() {
		return getAttributeSet();
	}

}
