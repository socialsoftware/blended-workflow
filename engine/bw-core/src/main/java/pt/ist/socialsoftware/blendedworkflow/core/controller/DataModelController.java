package pt.ist.socialsoftware.blendedworkflow.core.controller;

import java.util.Set;
import java.util.stream.Collectors;

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

import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Dependence;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Product;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Rule;
import pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.AttributeDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.DataModelDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.DependenceDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.EntityDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.ProductDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.RelationDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.RuleDto;
import pt.ist.socialsoftware.blendedworkflow.core.utils.ModulesFactory;

@RestController
@RequestMapping(value = "/specs/{specId}/datamodel")
public class DataModelController {
	private static Logger log = LoggerFactory.getLogger(DataModelController.class);

	@Inject
	private ModulesFactory factory;

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public ResponseEntity<String> cleanDataModel(@PathVariable("specId") String specId) {
		log.debug("cleanDataModel specId:{}", specId);

		DesignInterface adi = this.factory.createDesignInterface();

		adi.cleanDataModel(specId);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public ResponseEntity<Boolean> checkDataModel(@PathVariable("specId") String specId) {
		log.debug("checkDataModel specId:{}", specId);

		DesignInterface adi = this.factory.createDesignInterface();

		adi.checkDataModel(specId);

		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<DataModelDto> getDataModel(@PathVariable("specId") String specId) {
		log.debug("getDataModel specId:{}", specId);

		DesignInterface adi = this.factory.createDesignInterface();

		DataModelDto dataModelDto = adi.getDataModel(specId);

		return new ResponseEntity<>(dataModelDto, HttpStatus.OK);
	}

	@RequestMapping(value = "/products/{path}/", method = RequestMethod.GET)
	public ResponseEntity<ProductDto> getProduct(@PathVariable("specId") String specId,
												 @PathVariable("path") String path) {
		log.debug("getProduct path:{}", path);

		DesignInterface adi = this.factory.createDesignInterface();

		Product product = adi.getProduct(specId, path);

		return new ResponseEntity<>(product.getDTO(), HttpStatus.OK);
	}

	@RequestMapping(value = "/entities/{entityName}", method = RequestMethod.GET)
	public ResponseEntity<EntityDto> getEntityByName(@PathVariable("specId") String specId,
													 @PathVariable("entityName") String entityName) {
		log.debug("getProduct entityName:{}", entityName);

		DesignInterface adi = this.factory.createDesignInterface();

		Entity entity = adi.getEntityByName(specId, entityName);

		return new ResponseEntity<>(entity.getDTO(), HttpStatus.OK);
	}

	@RequestMapping(value = "/entities", method = RequestMethod.GET)
	public ResponseEntity<EntityDto[]> getEntities(@PathVariable("specId") String specId) {
		log.debug("getEntities specId:{}", specId);

		DesignInterface adi = this.factory.createDesignInterface();

		EntityDto[] entities = adi.getEntities(specId).stream().map(e -> e.getDTO())
				.toArray(size -> new EntityDto[size]);

		return new ResponseEntity<>(entities, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/entities", method = RequestMethod.POST)
	public ResponseEntity<EntityDto> createEntity(@PathVariable("specId") String specId,
												  @RequestBody EntityDto entDTO) {
		log.debug("createEntity specId:{}, name:{}, exists:{}, mandatory:{}", specId, entDTO.getName(),
				entDTO.getExists(), entDTO.isMandatory());

		DesignInterface adi = this.factory.createDesignInterface();

		Entity entity = adi.createEntity(entDTO);

		return new ResponseEntity<>(entity.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/attributes", method = RequestMethod.GET)
	public ResponseEntity<AttributeDto[]> getAttributtes(@PathVariable("specId") String specId) {
		log.debug("getAttributtes specId:{}", specId);

		DesignInterface adi = this.factory.createDesignInterface();

		AttributeDto[] attributes = adi.getAttributes(specId).stream().map(a -> a.getDTO())
				.toArray(size -> new AttributeDto[size]);

		return new ResponseEntity<>(attributes, HttpStatus.OK);
	}

	@RequestMapping(value = "/attributes/{extId}", method = RequestMethod.GET)
	public ResponseEntity<AttributeDto> getAttributeByExtId(@PathVariable("extId") String extId) {
		log.debug("getAttributeByExtId specId:{} extId:{}", extId);

		DesignInterface adi = this.factory.createDesignInterface();

		Attribute attribute = adi.getAttributeByExtId(extId);

		return new ResponseEntity<>(attribute.getDTO(), HttpStatus.OK);
	}

	@RequestMapping(value = "/attributes", method = RequestMethod.POST)
	public ResponseEntity<AttributeDto> createAttribute(@PathVariable("specId") String specId,
														@RequestBody AttributeDto attDTO) {
		log.debug("createAttribute entityExtId:{},  name:{}, type:{}", attDTO.getEntityExtId(), attDTO.getName(),
				attDTO.getType());

		DesignInterface adi = this.factory.createDesignInterface();

		Attribute attribute = adi.createAttribute(attDTO);

		return new ResponseEntity<>(attribute.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/relations", method = RequestMethod.POST)
	public ResponseEntity<RelationDto> createRelation(@PathVariable("specId") String specId,
													  @RequestBody RelationDto relDTO) {
		log.debug("createRelation {}, {}, {}", relDTO.getName(), relDTO.getEntOneName(), relDTO.getEntTwoName());

		DesignInterface adi = this.factory.createDesignInterface();

		RelationBW relation = adi.createRelation(relDTO);

		return new ResponseEntity<>(relation.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/relations", method = RequestMethod.GET)
	public ResponseEntity<RelationDto[]> getRelations(@PathVariable("specId") String specId) {
		log.debug("getRelations specId:{}", specId);

		DesignInterface adi = this.factory.createDesignInterface();

		RelationDto[] attributes = adi.getRelations(specId).stream().map(a -> a.getDTO())
				.toArray(size -> new RelationDto[size]);

		return new ResponseEntity<>(attributes, HttpStatus.OK);
	}

	@RequestMapping(value = "/rules", method = RequestMethod.POST)
	public ResponseEntity<RuleDto> createRule(@PathVariable("specId") String specId, @RequestBody RuleDto ruleDTO) {
		log.debug("createRule specId:{}, entityName:{}, name:{}, expression:{}", ruleDTO.getSpecId(),
				ruleDTO.getEntityName(), ruleDTO.getName(), ruleDTO.getExpression().toString());

		DesignInterface adi = this.factory.createDesignInterface();

		Rule rule = adi.createRule(ruleDTO);

		return new ResponseEntity<>(rule.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/rules", method = RequestMethod.GET)
	public ResponseEntity<RuleDto[]> getRules(@PathVariable("specId") String specId) {
		log.debug("getRules specId:{}", specId);

		DesignInterface adi = this.factory.createDesignInterface();

		RuleDto[] attributes = adi.getRules(specId).stream().map(a -> a.getDTO()).toArray(size -> new RuleDto[size]);

		return new ResponseEntity<>(attributes, HttpStatus.OK);
	}

	@RequestMapping(value = "/dependencies", method = RequestMethod.POST)
	public ResponseEntity<DependenceDto> createDependence(@PathVariable("specId") String specId,
			@RequestBody DependenceDto dependenceDTO) {
		log.debug("createDependence product:{}, path:{}", dependenceDTO.getProduct(), dependenceDTO.getPath());

		DesignInterface adi = this.factory.createDesignInterface();

		Dependence dependence = adi.createDependence(dependenceDTO);

		return new ResponseEntity<>(dependence.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/dependencies", method = RequestMethod.GET)
	public ResponseEntity<DependenceDto[]> getDependencies(@PathVariable("specId") String specId) {
		log.debug("getDependencies specId:{}", specId);

		DesignInterface adi = this.factory.createDesignInterface();

		Set<Dependence> dependencies = adi.getDependencies(specId);

		Set<DependenceDto> dependenciesDTO;
		dependenciesDTO = dependencies.stream().map(dep -> dep.getDTO()).collect(Collectors.toSet());

		log.debug("getDependencies dependenciesDTO.size:{}", dependenciesDTO.size());

		DependenceDto[] depsArray = dependenciesDTO.toArray(new DependenceDto[dependenciesDTO.size()]);

		return new ResponseEntity<>(depsArray, HttpStatus.OK);
	}

	@RequestMapping(value = "/dependencies/{depExtId}/check", method = RequestMethod.GET)
	public ResponseEntity<Boolean> checkDependence(@PathVariable("specId") String specId,
			@PathVariable("depExtId") String depExtId) {
		log.debug("checkDependence productExtId:{}", depExtId);

		DesignInterface adi = this.factory.createDesignInterface();

		boolean result = adi.checkDependence(depExtId);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/dependencies/{depExtId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteDependence(@PathVariable("specId") String specId,
			@PathVariable("depExtId") String depExtId) {
		log.debug("deleteDependence productExtId:{}", depExtId);

		DesignInterface adi = this.factory.createDesignInterface();

		adi.deleteDependence(depExtId);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
