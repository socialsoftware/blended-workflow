package pt.ist.socialsoftware.blendedworkflow.controller;

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

import pt.ist.socialsoftware.blendedworkflow.domain.BWDependence;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRule;
import pt.ist.socialsoftware.blendedworkflow.domain.DEFAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DEFEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.MULCondition;
import pt.ist.socialsoftware.blendedworkflow.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DefEntityConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DefAttributeConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DependenceDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.MulConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.RuleDTO;

@RestController
@RequestMapping(value = "/specs/{specId}/conditionmodel")
public class ConditionModelController {
    private static Logger log = LoggerFactory
            .getLogger(ConditionModelController.class);

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public ResponseEntity<String> cleanConditionModel(
            @PathVariable("specId") String specId) {
        log.debug("cleanConditionModel specId:{}", specId);

        DesignInterface adi = DesignInterface.getInstance();

        adi.cleanConditionModel(specId);

        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping(value = "/entityachieveconditions", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<DefEntityConditionDTO> createEntityAchieveCondition(
            @PathVariable("specId") String specId,
            @RequestBody DefEntityConditionDTO eacDTO) {
        log.debug("createEntityAchieveCondition entityName:{}, exists:{}",
                eacDTO.getEntityName(), eacDTO.isExists());

        DesignInterface adi = DesignInterface.getInstance();

        DEFEntityCondition defCondition = adi
                .createEntityAchieveCondition(eacDTO);

        return new ResponseEntity<DefEntityConditionDTO>(defCondition.getDTO(),
                HttpStatus.CREATED);
    }

    @RequestMapping(value = "/attributeachieveconditions", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<DefAttributeConditionDTO> createAttributeAchieveCondition(
            @PathVariable("specId") String specId,
            @RequestBody DefAttributeConditionDTO aacDTO) {
        log.debug("createAttributeAchieveCondition paths:{}, mandatory:{}",
                aacDTO.getPaths(), aacDTO.isMandatory());

        DesignInterface adi = DesignInterface.getInstance();

        DEFAttributeCondition defCondition = adi
                .createAttributeAchieveCondition(aacDTO);

        return new ResponseEntity<DefAttributeConditionDTO>(
                defCondition.getDTO(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/entitydependenceconditions", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<DependenceDTO> createEntityDependenceCondition(
            @PathVariable("specId") String specId,
            @RequestBody DependenceDTO dependenceDTO) {
        log.debug("createEntityDependenceCondition entityExtId:{}, path:{}",
                dependenceDTO.getProductExtId(), dependenceDTO.getPath());

        DesignInterface adi = DesignInterface.getInstance();

        BWDependence dependence = adi
                .createEntityDependenceCondition(dependenceDTO);

        return new ResponseEntity<DependenceDTO>(dependence.getDTO(),
                HttpStatus.CREATED);
    }

    @RequestMapping(value = "/attributedependenceconditions", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<DependenceDTO> createAttributeDependenceCondition(
            @PathVariable("specId") String specId,
            @RequestBody DependenceDTO dependenceDTO) {
        log.debug("createAttributeDependenceCondition productExtId:{}, path:{}",
                dependenceDTO.getProductExtId(), dependenceDTO.getPath());

        DesignInterface adi = DesignInterface.getInstance();

        BWDependence dependence = adi
                .createAttributeDependence(dependenceDTO);

        return new ResponseEntity<DependenceDTO>(dependence.getDTO(),
                HttpStatus.CREATED);
    }

    @RequestMapping(value = "/entityinvariantconditions", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<MulConditionDTO> createEntityInvariantCondition(
            @PathVariable("specId") String specId,
            @RequestBody MulConditionDTO mulConditionDTO) {
        log.debug("createDependence specId:{}, rolePath:{}, cardinality:{}",
                mulConditionDTO.getSpecId(), mulConditionDTO.getRolePath(),
                mulConditionDTO.getCardinality());

        DesignInterface adi = DesignInterface.getInstance();

        MULCondition mulCondition = adi
                .createEntityInvariantCondition(mulConditionDTO);

        return new ResponseEntity<MulConditionDTO>(mulCondition.getDTO(),
                HttpStatus.CREATED);
    }

    @RequestMapping(value = "/attributeinvariantconditions", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<RuleDTO> createAttributeInvariantCondition(
            @PathVariable("specId") String specId,
            @RequestBody RuleDTO ruleDTO) {
        log.debug("createAttributeInvariantCondition specId:{}, name:{}",
                ruleDTO.getSpecId(), ruleDTO.getName());

        DesignInterface adi = DesignInterface.getInstance();

        BWRule rule = adi.createAttributeInvariant(ruleDTO);

        return new ResponseEntity<RuleDTO>(rule.getDTO(), HttpStatus.CREATED);
    }

}
