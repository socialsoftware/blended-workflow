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
@RequestMapping(value = "/rules")
public class RuleController {
    private static Logger log = LoggerFactory.getLogger(RuleController.class);

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<RuleDTO> createEntity(@RequestBody RuleDTO ruleDTO) {
        log.debug("createEntity dataModelExtId:{}, name:{}, expression:{}",
                ruleDTO.getDataModelExtId(), ruleDTO.getName(),
                ruleDTO.getExpression().toString());

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        BWRule rule = adi.createRule(ruleDTO);

        return new ResponseEntity<RuleDTO>(rule.getDTO(), HttpStatus.CREATED);
    }

}
