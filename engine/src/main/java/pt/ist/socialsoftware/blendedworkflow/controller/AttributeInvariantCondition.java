package pt.ist.socialsoftware.blendedworkflow.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pt.ist.socialsoftware.blendedworkflow.domain.BWRule;
import pt.ist.socialsoftware.blendedworkflow.service.design.AtomicDesignInterface;
import pt.ist.socialsoftware.blendedworkflow.service.dto.RuleDTO;

@Controller
@RequestMapping(value = "/attributeinvariantconditions")
public class AttributeInvariantCondition {
    private static Logger log = LoggerFactory
            .getLogger(AttributeInvariantCondition.class);

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<RuleDTO> createAttributeInvariantCondition(
            @RequestBody RuleDTO ruleDTO) {
        log.debug(
                "createAttributeInvariantCondition conditionModelExtId:{}, name:{}",
                ruleDTO.getConditionModelExtId(), ruleDTO.getName());

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        BWRule rule = adi.createAttributeInvariantCondition(ruleDTO);

        return new ResponseEntity<RuleDTO>(rule.getDTO(), HttpStatus.CREATED);
    }

}
