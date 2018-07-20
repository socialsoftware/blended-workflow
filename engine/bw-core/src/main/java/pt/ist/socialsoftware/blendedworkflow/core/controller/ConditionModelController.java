package pt.ist.socialsoftware.blendedworkflow.core.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pt.ist.socialsoftware.blendedworkflow.core.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Dependence;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Rule;
import pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.DefAttributeConditionDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.DefEntityConditionDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.DependenceDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.MulConditionDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.RuleDto;
import pt.ist.socialsoftware.blendedworkflow.core.utils.ModulesFactory;

@RestController
@RequestMapping(value = "/specs/{specId}/conditionmodel")
public class ConditionModelController {
	private static Logger log = LoggerFactory.getLogger(ConditionModelController.class);

	@Inject
	private ModulesFactory factory;

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public ResponseEntity<String> cleanConditionModel(@PathVariable("specId") String specId) {
		log.debug("cleanConditionModel specId:{}", specId);

		DesignInterface adi = this.factory.createDesignInterface();

		adi.cleanConditionModel(specId);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<Boolean> generateConditionModel(@PathVariable("specId") String specId) {
		log.debug("generateConditionModel specId:{}", specId);

		DesignInterface adi = this.factory.createDesignInterface();

		boolean result = adi.generateConditionModel(specId);

		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/entityachieveconditions", method = RequestMethod.GET)
	public ResponseEntity<DefEntityConditionDto[]> getEntityAchieveConditionSet(@PathVariable("specId") String specId) {
		log.debug("getEntityAchieveConditionSet specId:{}", specId);

		DesignInterface adi = this.factory.createDesignInterface();

		DefEntityConditionDto[] defConditions = adi.getEntityAchieveConditionSet(specId).stream()
				.map(def -> def.getDTO()).toArray(size -> new DefEntityConditionDto[size]);

		return new ResponseEntity<>(defConditions, HttpStatus.OK);
	}

	@RequestMapping(value = "/entityachieveconditions", method = RequestMethod.POST)
	public ResponseEntity<DefEntityConditionDto> createEntityAchieveCondition(@PathVariable("specId") String specId,
			@RequestBody DefEntityConditionDto eacDTO) {
		log.debug("createEntityAchieveCondition entityName:{}, exists:{}", eacDTO.getEntityName(), eacDTO.isExists());

		DesignInterface adi = this.factory.createDesignInterface();

		DefEntityCondition defCondition = adi.createEntityAchieveCondition(eacDTO);

		return new ResponseEntity<>(defCondition.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/attributeachieveconditions", method = RequestMethod.GET)
	public ResponseEntity<DefAttributeConditionDto[]> getAttributeAchieveConditionSet(
			@PathVariable("specId") String specId) {
		log.debug("getAttributeAchieveConditionSet specId:{}", specId);

		DesignInterface adi = this.factory.createDesignInterface();

		DefAttributeConditionDto[] defConditions = adi.getAttributeAchieveConditionSet(specId).stream()
				.map(def -> def.getDto()).toArray(size -> new DefAttributeConditionDto[size]);

		return new ResponseEntity<>(defConditions, HttpStatus.OK);
	}

	@RequestMapping(value = "/attributeachieveconditions", method = RequestMethod.POST)
	public ResponseEntity<DefAttributeConditionDto> createAttributeAchieveCondition(
			@PathVariable("specId") String specId, @RequestBody DefAttributeConditionDto aacDTO) {
		log.debug("createAttributeAchieveCondition path:{}, mandatory:{}", aacDTO.getPath(), aacDTO.isMandatory());

		DesignInterface adi = this.factory.createDesignInterface();

		DefAttributeCondition defCondition = adi.createAttributeAchieveCondition(aacDTO);

		return new ResponseEntity<>(defCondition.getDto(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/entitydependenceconditions", method = RequestMethod.GET)
	public ResponseEntity<DependenceDto[]> getEntityDependenceConditionSet(@PathVariable("specId") String specId) {
		log.debug("getEntityDependenceConditionSet specId:{}", specId);

		DesignInterface adi = this.factory.createDesignInterface();

		DependenceDto[] dependences = adi.getEntityDependenceConditionSet(specId).stream().map(dep -> dep.getDTO())
				.toArray(size -> new DependenceDto[size]);

		return new ResponseEntity<>(dependences, HttpStatus.OK);
	}

	@RequestMapping(value = "/entitydependenceconditions", method = RequestMethod.POST)
	public ResponseEntity<DependenceDto> createEntityDependenceCondition(@PathVariable("specId") String specId,
			@RequestBody DependenceDto dependenceDTO) {
		log.debug("createEntityDependenceCondition product:{}, path:{}", dependenceDTO.getProduct(),
				dependenceDTO.getPath());

		DesignInterface adi = this.factory.createDesignInterface();

		Dependence dependence = adi.createEntityDependenceCondition(dependenceDTO);

		return new ResponseEntity<>(dependence.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/attributedependenceconditions", method = RequestMethod.GET)
	public ResponseEntity<DependenceDto[]> getAttributeDependenceConditionSet(@PathVariable("specId") String specId) {
		log.debug("getAttributeDependenceConditionSet specId:{}", specId);

		DesignInterface adi = this.factory.createDesignInterface();

		DependenceDto[] dependences = adi.getAttributeDependenceConditionSet(specId).stream().map(dep -> dep.getDTO())
				.toArray(size -> new DependenceDto[size]);

		return new ResponseEntity<>(dependences, HttpStatus.OK);
	}

	@RequestMapping(value = "/attributedependenceconditions", method = RequestMethod.POST)
	public ResponseEntity<DependenceDto> createAttributeDependenceCondition(@PathVariable("specId") String specId,
			@RequestBody DependenceDto dependenceDTO) {
		log.debug("createAttributeDependenceCondition product:{}, path:{}", dependenceDTO.getProduct(),
				dependenceDTO.getPath());

		DesignInterface adi = this.factory.createDesignInterface();

		Dependence dependence = adi.createAttributeDependenceCondition(dependenceDTO);

		return new ResponseEntity<>(dependence.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/entityinvariantconditions", method = RequestMethod.GET)
	public ResponseEntity<MulConditionDto[]> getEntityInvariantConditionSet(@PathVariable("specId") String specId) {
		log.debug("getEntityInvariantConditionSet specId:{}", specId);

		DesignInterface adi = this.factory.createDesignInterface();

		MulConditionDto[] mulConditionsDTO = adi.getEntityInvariantConditionSet(specId).stream()
				.map(mul -> mul.getDTO()).toArray(size -> new MulConditionDto[size]);

		return new ResponseEntity<>(mulConditionsDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/entityinvariantconditions", method = RequestMethod.POST)
	public ResponseEntity<MulConditionDto> createEntityInvariantCondition(@PathVariable("specId") String specId,
			@RequestBody MulConditionDto mulConditionDTO) {
		log.debug("createEntityInvariantCondition specId:{}, rolePath:{}, cardinality:{}", specId,
				mulConditionDTO.getRolePath(), mulConditionDTO.getCardinality());

		DesignInterface adi = this.factory.createDesignInterface();

		MulCondition mulCondition = adi.createEntityInvariantCondition(specId, mulConditionDTO);

		return new ResponseEntity<>(mulCondition.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/attributeinvariantconditions", method = RequestMethod.GET)
	public ResponseEntity<RuleDto[]> getAttributeInvariantConditionSet(@PathVariable("specId") String specId) {
		log.debug("getAttributeInvariantConditionSet specId:{}", specId);

		DesignInterface adi = this.factory.createDesignInterface();

		RuleDto[] rulesDTO = adi.getAttributeInvariantConditionSet(specId).stream().map(mul -> mul.getDTO())
				.toArray(size -> new RuleDto[size]);

		return new ResponseEntity<>(rulesDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/attributeinvariantconditions", method = RequestMethod.POST)
	public ResponseEntity<RuleDto> createAttributeInvariantCondition(@PathVariable("specId") String specId,
			@RequestBody RuleDto ruleDTO) {
		log.debug("createAttributeInvariantCondition specId:{}, name:{}", ruleDTO.getSpecId(), ruleDTO.getName());

		DesignInterface adi = this.factory.createDesignInterface();

		Rule rule = adi.createAttributeInvariant(ruleDTO);

		return new ResponseEntity<>(rule.getDTO(), HttpStatus.CREATED);
	}

}
