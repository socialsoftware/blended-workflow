package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DependenceDTO;

public class Dependence extends Dependence_Base {
	private static Logger log = LoggerFactory.getLogger(Dependence.class);

	@Override
	public void setPath(String value) {
		checkPathPrefix(value);
		super.setPath(value);
	}

	public Dependence(DataModel dataModel, Product product, String value) {
		setDataModel(dataModel);
		setProduct(product);
		setPath(value);
	}

	private void checkPathPrefix(String value) {
		if (!value.split("\\.")[0].equals(getProduct().getEntity().getName())) {
			throw new BWException(BWErrorType.INVALID_PATH,
					value + " requires to have the Entity name as prefix: " + getProduct().getEntity().getName());
		}
	}

	public boolean check() {
		log.debug("check {}", getPath());

		checkPathPrefix(getPath());

		List<String> pathLeft = Arrays.stream(getPath().split("\\.")).collect(Collectors.toList());
		if (pathLeft.size() == 1) {
			throw new BWException(BWErrorType.INVALID_PATH, getPath());
		}

		pathLeft.remove(0);

		Product product = getProduct().getEntity().getNext(pathLeft, getPath());

		return (product != null);
	}

	public void delete() {
		setDataModel(null);
		setProduct(null);

		deleteDomainObject();
	}

	public DependenceDTO getDTO() {
		DependenceDTO depDTO = new DependenceDTO();
		depDTO.setSpecId(getDataModel().getSpecification().getSpecId());
		depDTO.setExtId(getExternalId());
		depDTO.setProductExtId(getProduct().getExternalId());
		depDTO.setPath(getPath());

		return depDTO;
	}

	public Product getTarget() {
		return getDataModel().getTargetOfPath(getPath());
	}

}
