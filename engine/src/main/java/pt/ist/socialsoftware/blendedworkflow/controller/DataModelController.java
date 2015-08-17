package pt.ist.socialsoftware.blendedworkflow.controller;

import java.util.Arrays;
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

import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttributeGroup;
import pt.ist.socialsoftware.blendedworkflow.domain.BWDependence;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWProduct;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRule;
import pt.ist.socialsoftware.blendedworkflow.service.design.AtomicDesignInterface;
import pt.ist.socialsoftware.blendedworkflow.service.dto.AttributeDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.AttributeGroupDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DependenceDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.EntityDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ProductDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.RelationDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.RuleDTO;

@RestController
@RequestMapping(value = "/specs/{specId}/datamodel")
public class DataModelController {
    private static Logger log = LoggerFactory
            .getLogger(DataModelController.class);

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public ResponseEntity<String> cleanDataModel(
            @PathVariable("specId") String specId) {
        log.debug("cleanDataModel specId:{}", specId);

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        adi.cleanDataModel(specId);

        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping(value = "/products/{atts}/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<ProductDTO> getProduct(
            @PathVariable("specId") String specId,
            @PathVariable("atts") String atts) {
        log.debug("getProduct atts:{}", atts);

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        String[] attsArray = atts.split(",");

        BWProduct product = adi.getProduct(specId, Arrays.asList(attsArray));

        return new ResponseEntity<ProductDTO>(product.getDTO(), HttpStatus.OK);
    }

    @RequestMapping(value = "/entities/{entityName}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<EntityDTO> getEntityByName(
            @PathVariable("specId") String specId,
            @PathVariable("entityName") String entityName) {
        log.debug("getProduct entityName:{}", entityName);

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        BWEntity entity = adi.getEntityByName(specId, entityName);

        return new ResponseEntity<EntityDTO>(entity.getDTO(), HttpStatus.OK);
    }

    @RequestMapping(value = "/entities", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<EntityDTO> createEntity(
            @PathVariable("specId") String specId,
            @RequestBody EntityDTO entDTO) {
        log.debug("createEntity specId:{}, name:{}, exists:{}", specId,
                entDTO.getName(), entDTO.getExists());

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        BWEntity entity = adi.createEntity(entDTO);

        return new ResponseEntity<EntityDTO>(entity.getDTO(),
                HttpStatus.CREATED);
    }

    @RequestMapping(value = "/attributes", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<AttributeDTO> createAttribute(
            @PathVariable("specId") String specId,
            @RequestBody AttributeDTO attDTO) {
        log.debug(
                "createAttribute entityExtId:{}, groupExtId:{}, name:{}, type:{}",
                attDTO.getEntityExtId(), attDTO.getGroupExtId(),
                attDTO.getName(), attDTO.getType());

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        BWAttribute attribute = adi.createAttribute(attDTO);

        return new ResponseEntity<AttributeDTO>(attribute.getDTO(),
                HttpStatus.CREATED);
    }

    @RequestMapping(value = "/attributegroups", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<AttributeGroupDTO> createGroupAttribute(
            @PathVariable("specId") String specId,
            @RequestBody AttributeGroupDTO groupAttributeDTO) {
        log.debug("createGroupAttribute entityExtId:{},  name:{}, mandatory:{}",
                groupAttributeDTO.getEntityExtId(), groupAttributeDTO.getName(),
                groupAttributeDTO.isMandatory());

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        BWAttributeGroup group = adi.createAttributeGroup(groupAttributeDTO);

        return new ResponseEntity<AttributeGroupDTO>(group.getDTO(),
                HttpStatus.CREATED);
    }

    @RequestMapping(value = "/relations", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<RelationDTO> createRelation(
            @PathVariable("specId") String specId,
            @RequestBody RelationDTO relDTO) {
        log.debug("createRelation {}, {}, {}", relDTO.getName(),
                relDTO.getEntOneName(), relDTO.getEntTwoName());

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        BWRelation relation = adi.createRelation(relDTO);

        return new ResponseEntity<RelationDTO>(relation.getDTO(),
                HttpStatus.CREATED);
    }

    @RequestMapping(value = "/rules", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<RuleDTO> createEntity(
            @PathVariable("specId") String specId,
            @RequestBody RuleDTO ruleDTO) {
        log.debug("createEntity specId:{}, name:{}, expression:{}",
                ruleDTO.getSpecId(), ruleDTO.getName(),
                ruleDTO.getExpression().toString());

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        BWRule rule = adi.createRule(ruleDTO);

        return new ResponseEntity<RuleDTO>(rule.getDTO(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/dependencies", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<DependenceDTO> createDependence(
            @PathVariable("specId") String specId,
            @RequestBody DependenceDTO dependenceDTO) {
        log.debug("createDependence productExtId:{}, path:{}",
                dependenceDTO.getProductExtId(), dependenceDTO.getPath());

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        BWDependence dependence = adi.createDependence(dependenceDTO);

        return new ResponseEntity<DependenceDTO>(dependence.getDTO(),
                HttpStatus.CREATED);
    }

    @RequestMapping(value = "/dependencies", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<DependenceDTO[]> getDependencies(
            @PathVariable("specId") String specId) {
        log.debug("getDependencies specId:{}", specId);

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        Set<BWDependence> dependencies = adi.getDependencies(specId);

        Set<DependenceDTO> dependenciesDTO;
        dependenciesDTO = dependencies.stream().map(dep -> dep.getDTO())
                .collect(Collectors.toSet());

        log.debug("getDependencies dependenciesDTO.size:{}",
                dependenciesDTO.size());

        DependenceDTO[] depsArray = dependenciesDTO
                .toArray(new DependenceDTO[dependenciesDTO.size()]);

        return new ResponseEntity<DependenceDTO[]>(depsArray, HttpStatus.OK);
    }

    @RequestMapping(value = "/dependencies/{depExtId}/check", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<Boolean> checkDependence(
            @PathVariable("specId") String specId,
            @PathVariable("depExtId") String depExtId) {
        log.debug("checkDependence productExtId:{}", depExtId);

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        boolean result = adi.checkDependence(depExtId);

        return new ResponseEntity<Boolean>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/dependencies/{depExtId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteDependence(
            @PathVariable("specId") String specId,
            @PathVariable("depExtId") String depExtId) {
        log.debug("deleteDependence productExtId:{}", depExtId);

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        adi.deleteDependence(depExtId);

        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
