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

import pt.ist.socialsoftware.blendedworkflow.domain.DEFEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.service.design.AtomicDesignInterface;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DEFEntityConditionDTO;

@Controller
@RequestMapping(value = "/entityachieveconditions")
public class EntityAchieveConditionController {
    private static Logger log = LoggerFactory
            .getLogger(EntityAchieveConditionController.class);

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<DEFEntityConditionDTO> createEntityAchieveCondition(
            @RequestBody DEFEntityConditionDTO eacDTO) {
        log.debug("createEntityAchieveCondition entityName:{}, exists:{}",
                eacDTO.getEntityName(), eacDTO.isExists());

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        DEFEntityCondition defCondition = adi
                .createEntityAchieveCondition(eacDTO);

        return new ResponseEntity<DEFEntityConditionDTO>(defCondition.getDTO(),
                HttpStatus.CREATED);
    }

}
