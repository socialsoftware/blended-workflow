package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Dependence;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ProductInstance;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.EntityInstanceDto.Depth;

public class DependenceInstanceDto {
	private DependenceDto dependence;
	private List<EntityInstanceDto> entityInstances = new ArrayList<>();

	public DependenceInstanceDto(Dependence dependence, ProductInstance productInstance) {
		this.dependence = new DependenceDto(dependence);
		setEntityInstances(productInstance.getDependentProductInstances(dependence).stream()
				.map(pi -> new EntityInstanceDto(pi.getEntityInstance(), Depth.SHALLOW))
				.sorted(Comparator.comparing(EntityInstanceDto::getId)).collect(Collectors.toList()));

	}

	public DependenceDto getDependence() {
		return this.dependence;
	}

	public void setDependence(DependenceDto dependence) {
		this.dependence = dependence;
	}

	public List<EntityInstanceDto> getEntityInstances() {
		return this.entityInstances;
	}

	public void setEntityInstances(List<EntityInstanceDto> entityInstances) {
		this.entityInstances = entityInstances;
	}

}
