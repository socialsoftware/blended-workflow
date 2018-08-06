package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;

public class EntityInstanceToDefineDto extends EntityInstanceDto {
	private List<EntityInstanceDto> entityInstancesContext = new ArrayList<>();

	public EntityInstanceToDefineDto(Entity entity) {
		setExternalId(null);
		setId(null);
		setEntity(entity.getDto());
	}

	public EntityInstanceToDefineDto(Entity entity, Set<EntityInstance> entityInstancesContext) {
		setExternalId(null);
		setId(null);
		setEntity(entity.getDto());
		setEntityInstancesContext(entityInstancesContext.stream().sorted(Comparator.comparing(EntityInstance::getId))
				.map(ei -> new EntityInstanceDto(ei, Depth.SHALLOW)).collect(Collectors.toList()));
	}

	public void fillUndefAttributeInstances(Entity entity) {
		Set<AttributeInstanceUndefDto> undefinedAttributeInstances = new HashSet<>();
		for (Attribute attribute : entity.getAttributeSet()) {
			if (!getAttributeInstances().stream()
					.anyMatch(ai -> ai.getAttribute().getName().equals(attribute.getName()))) {
				undefinedAttributeInstances.add(new AttributeInstanceUndefDto(attribute));
			}
		}

		setAttributeInstances(Stream.concat(getAttributeInstances().stream(), undefinedAttributeInstances.stream())
				.sorted((a1, a2) -> a1.getAttribute().getName().compareTo(a2.getAttribute().getName()))
				.collect(Collectors.toList()));
	}

	public void fillUndefLinks(Entity entity) {
		Set<LinkUndefDto> undefinedLinks = new HashSet<>();
		for (MulCondition mulCondition : entity.getMulConditions().stream()
				.sorted((m1, m2) -> m1.getRolename().compareTo(m2.getRolename())).collect(Collectors.toList())) {
			if (!getLinks().stream()
					.anyMatch(l -> l.getMulCondition().getRolename().equals(mulCondition.getRolename()))) {
				undefinedLinks.add(new LinkUndefDto(mulCondition));
			}
		}

		setLinks(Stream.concat(getLinks().stream(), undefinedLinks.stream())
				.sorted((l1, l2) -> l1.getMulCondition().getRolename().compareTo(l2.getMulCondition().getRolename()))
				.collect(Collectors.toList()));
	}

	public List<EntityInstanceDto> getEntityInstancesContext() {
		return this.entityInstancesContext;
	}

	public void setEntityInstancesContext(List<EntityInstanceDto> entityInstancesContext) {
		this.entityInstancesContext = entityInstancesContext;
	}

	protected void addAttributeInstance(AttributeInstanceToDefineDto attributeInstanceToDefineDto) {
		getAttributeInstances().add(attributeInstanceToDefineDto);

	}

}
