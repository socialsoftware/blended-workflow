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

import pt.ist.socialsoftware.blendedworkflow.domain.DEFAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.service.design.AtomicDesignInterface;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DefAttributeConditionDTO;

@Controller
@RequestMapping(value = "/attributeachieveconditions")
public class AttributeAchieveConditionController {
    private static Logger log = LoggerFactory
            .getLogger(AttributeAchieveConditionController.class);

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<DefAttributeConditionDTO> createAttributeAchieveCondition(
            @RequestBody DefAttributeConditionDTO aacDTO) {
        log.debug("createEntityAchieveCondition paths:{}, mandatory:{}",
                aacDTO.getPaths(), aacDTO.isMandatory());

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        DEFAttributeCondition defCondition = adi
                .createAttributeAchieveCondition(aacDTO);

        return new ResponseEntity<DefAttributeConditionDTO>(
                defCondition.getDTO(), HttpStatus.CREATED);
    }

}
