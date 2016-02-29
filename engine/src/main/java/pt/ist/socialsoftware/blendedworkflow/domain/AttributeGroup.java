package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.dto.AttributeDTO;

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
	public AttributeDTO getDTO() {
		AttributeDTO attDTO = new AttributeDTO();
		attDTO.setSpecId(getDataModel().getSpecification().getSpecId());
		attDTO.setExtId(getExternalId());
		attDTO.setProductType(ProductType.ATTRIBUTE_GROUP.name());
		attDTO.setEntityExtId(getEntity().getExternalId());
		attDTO.setEntityName(getEntity().getName());
		attDTO.setName(getName());
		attDTO.setMandatory(getIsMandatory());

		return attDTO;
	}

	@Override
	public DefProductCondition getDefCondition() {
		return DefAttributeCondition.getDefAttribute(this);
	}

	@Override
	public Set<AttributeBasic> getAttributeBasicSet() {
		return getAttributeSet();
	}

	@Override
	public boolean canBeDefinedBefore(Product product) {
		if (this == product) {
			return false;
		} else if (product.getProductType().equals(ProductType.ENTITY)) {
			return this.getEntity() != product;
		} else if (product.getProductType().equals(ProductType.ATTRIBUTE_BASIC)) {
			return !this.getAttributeSet().contains(product);
		} else {
			return true;
		}
	}

	@Override
	public boolean isCreatedTogether(Product product) {
		if (this == product) {
			return true;
		}

		if (product.getProductType().equals(ProductType.ATTRIBUTE_BASIC)) {
			return this.getAttributeSet().contains(product);
		}

		return false;
	}

}
