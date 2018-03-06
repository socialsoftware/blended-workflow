package pt.ist.socialsoftware.blendedworkflow.core.controller;

import java.util.Set;
import java.util.stream.Collectors;

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

import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Dependence;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Product;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Rule;
import pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.AttributeDTO;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.DependenceDTO;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.EntityDTO;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.ProductDTO;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.RelationDTO;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.RuleDTO;

@RestController
@RequestMapping(value = "/specs/{specId}/datamodel")
public class DataModelController {
	private static Logger log = LoggerFactory.getLogger(DataModelController.class);

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public ResponseEntity<String> cleanDataModel(@PathVariable("specId") String specId) {
		log.debug("cleanDataModel specId:{}", specId);

		DesignInterface adi = DesignInterface.getInstance();

		adi.cleanDataModel(specId);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public ResponseEntity<Boolean> checkDataModel(@PathVariable("specId") String specId) {
		log.debug("checkDataModel specId:{}", specId);

		DesignInterface adi = DesignInterface.getInstance();

		adi.checkDataModel(specId);

		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@RequestMapping(value = "/products/{path}/", method = RequestMethod.GET)
	public ResponseEntity<ProductDTO> getProduct(@PathVariable("specId") String specId,
			@PathVariable("path") String path) {
		log.debug("getProduct path:{}", path);

		DesignInterface adi = DesignInterface.getInstance();

		Product product = adi.getProduct(specId, path);

		return new ResponseEntity<>(product.getDTO(), HttpStatus.OK);
	}

	@RequestMapping(value = "/entities/{entityName}", method = RequestMethod.GET)
	public ResponseEntity<EntityDTO> getEntityByName(@PathVariable("specId") String specId,
			@PathVariable("entityName") String entityName) {
		log.debug("getProduct entityName:{}", entityName);

		DesignInterface adi = DesignInterface.getInstance();

		Entity entity = adi.getEntityByName(specId, entityName);

		return new ResponseEntity<>(entity.getDTO(), HttpStatus.OK);
	}

	@RequestMapping(value = "/entities", method = RequestMethod.GET)
	public ResponseEntity<EntityDTO[]> getEntities(@PathVariable("specId") String specId) {
		log.debug("getEntities specId:{}", specId);

		DesignInterface adi = DesignInterface.getInstance();

		EntityDTO[] entities = adi.getEntities(specId).stream().map(e -> e.getDTO())
				.toArray(size -> new EntityDTO[size]);

		return new ResponseEntity<>(entities, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/entities", method = RequestMethod.POST)
	public ResponseEntity<EntityDTO> createEntity(@PathVariable("specId") String specId,
			@RequestBody EntityDTO entDTO) {
		log.debug("createEntity specId:{}, name:{}, exists:{}, mandatory:{}", specId, entDTO.getName(),
				entDTO.getExists(), entDTO.isMandatory());

		DesignInterface adi = DesignInterface.getInstance();

		Entity entity = adi.createEntity(entDTO);

		return new ResponseEntity<>(entity.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/attributes", method = RequestMethod.GET)
	public ResponseEntity<AttributeDTO[]> getAttributtes(@PathVariable("specId") String specId) {
		log.debug("getAttributtes specId:{}", specId);

		DesignInterface adi = DesignInterface.getInstance();

		AttributeDTO[] attributes = adi.getAttributes(specId).stream().map(a -> a.getDTO())
				.toArray(size -> new AttributeDTO[size]);

		return new ResponseEntity<>(attributes, HttpStatus.OK);
	}

	@RequestMapping(value = "/attributes/{extId}", method = RequestMethod.GET)
	public ResponseEntity<AttributeDTO> getAttributeByExtId(@PathVariable("extId") String extId) {
		log.debug("getAttributeByExtId specId:{} extId:{}", extId);

		DesignInterface adi = DesignInterface.getInstance();

		Attribute attribute = adi.getAttributeByExtId(extId);

		return new ResponseEntity<>(attribute.getDTO(), HttpStatus.OK);
	}

	@RequestMapping(value = "/attributes", method = RequestMethod.POST)
	public ResponseEntity<AttributeDTO> createAttribute(@PathVariable("specId") String specId,
			@RequestBody AttributeDTO attDTO) {
		log.debug("createAttribute entityExtId:{},  name:{}, type:{}", attDTO.getEntityExtId(), attDTO.getName(),
				attDTO.getType());

		DesignInterface adi = DesignInterface.getInstance();

		Attribute attribute = adi.createAttribute(attDTO);

		return new ResponseEntity<>(attribute.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/relations", method = RequestMethod.POST)
	public ResponseEntity<RelationDTO> createRelation(@PathVariable("specId") String specId,
			@RequestBody RelationDTO relDTO) {
		log.debug("createRelation {}, {}, {}", relDTO.getName(), relDTO.getEntOneName(), relDTO.getEntTwoName());

		DesignInterface adi = DesignInterface.getInstance();

		RelationBW relation = adi.createRelation(relDTO);

		return new ResponseEntity<>(relation.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/relations", method = RequestMethod.GET)
	public ResponseEntity<RelationDTO[]> getRelations(@PathVariable("specId") String specId) {
		log.debug("getRelations specId:{}", specId);

		DesignInterface adi = DesignInterface.getInstance();

		RelationDTO[] attributes = adi.getRelations(specId).stream().map(a -> a.getDTO())
				.toArray(size -> new RelationDTO[size]);

		return new ResponseEntity<>(attributes, HttpStatus.OK);
	}

	@RequestMapping(value = "/rules", method = RequestMethod.POST)
	public ResponseEntity<RuleDTO> createRule(@PathVariable("specId") String specId, @RequestBody RuleDTO ruleDTO) {
		log.debug("createRule specId:{}, entityName:{}, name:{}, expression:{}", ruleDTO.getSpecId(),
				ruleDTO.getEntityName(), ruleDTO.getName(), ruleDTO.getExpression().toString());

		DesignInterface adi = DesignInterface.getInstance();

		Rule rule = adi.createRule(ruleDTO);

		return new ResponseEntity<>(rule.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/rules", method = RequestMethod.GET)
	public ResponseEntity<RuleDTO[]> getRules(@PathVariable("specId") String specId) {
		log.debug("getRules specId:{}", specId);

		DesignInterface adi = DesignInterface.getInstance();

		RuleDTO[] attributes = adi.getRules(specId).stream().map(a -> a.getDTO()).toArray(size -> new RuleDTO[size]);

		return new ResponseEntity<>(attributes, HttpStatus.OK);
	}

	@RequestMapping(value = "/dependencies", method = RequestMethod.POST)
	public ResponseEntity<DependenceDTO> createDependence(@PathVariable("specId") String specId,
			@RequestBody DependenceDTO dependenceDTO) {
		log.debug("createDependence product:{}, path:{}", dependenceDTO.getProduct(), dependenceDTO.getPath());

		DesignInterface adi = DesignInterface.getInstance();

		Dependence dependence = adi.createDependence(dependenceDTO);

		return new ResponseEntity<>(dependence.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/dependencies", method = RequestMethod.GET)
	public ResponseEntity<DependenceDTO[]> getDependencies(@PathVariable("specId") String specId) {
		log.debug("getDependencies specId:{}", specId);

		DesignInterface adi = DesignInterface.getInstance();

		Set<Dependence> dependencies = adi.getDependencies(specId);

		Set<DependenceDTO> dependenciesDTO;
		dependenciesDTO = dependencies.stream().map(dep -> dep.getDTO()).collect(Collectors.toSet());

		log.debug("getDependencies dependenciesDTO.size:{}", dependenciesDTO.size());

		DependenceDTO[] depsArray = dependenciesDTO.toArray(new DependenceDTO[dependenciesDTO.size()]);

		return new ResponseEntity<>(depsArray, HttpStatus.OK);
	}

	@RequestMapping(value = "/dependencies/{depExtId}/check", method = RequestMethod.GET)
	public ResponseEntity<Boolean> checkDependence(@PathVariable("specId") String specId,
			@PathVariable("depExtId") String depExtId) {
		log.debug("checkDependence productExtId:{}", depExtId);

		DesignInterface adi = DesignInterface.getInstance();

		boolean result = adi.checkDependence(depExtId);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/dependencies/{depExtId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteDependence(@PathVariable("specId") String specId,
			@PathVariable("depExtId") String depExtId) {
		log.debug("deleteDependence productExtId:{}", depExtId);

		DesignInterface adi = DesignInterface.getInstance();

		adi.deleteDependence(depExtId);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
