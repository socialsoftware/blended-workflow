package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.List;

public class DependencyTreeAndEntityInstancesDto {
	private List<List<AttributeInstanceDto>> dependencyTree;
	private List<EntityInstanceDto> entityInstances;
	
	public DependencyTreeAndEntityInstancesDto() {
		
	}
	
	public List<List<AttributeInstanceDto>> getDependencyTree() {
		return dependencyTree;
	}
	
	public void setDependencyTree(List<List<AttributeInstanceDto>> dependencyTree) {
		this.dependencyTree = dependencyTree;
	}
	
	public List<EntityInstanceDto> getEntityInstances() {
		return entityInstances;
	}
	
	public void setEntityInstances(List<EntityInstanceDto> entityInstances) {
		this.entityInstances = entityInstances;
	}
}
