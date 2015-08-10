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

import pt.ist.socialsoftware.blendedworkflow.domain.MULCondition;
import pt.ist.socialsoftware.blendedworkflow.service.design.AtomicDesignInterface;
import pt.ist.socialsoftware.blendedworkflow.service.dto.MulConditionDTO;

@Controller
@RequestMapping(value = "/entityinvariantconditions")
public class EntityInvariantConditionController {
    private static Logger log = LoggerFactory
            .getLogger(EntityInvariantConditionController.class);

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<MulConditionDTO> createEntityInvariantCondition(
            @RequestBody MulConditionDTO mulConditionDTO) {
        log.debug(
                "createDependence conditionModelExtId:{}, rolePath:{}, cardinality:{}",
                mulConditionDTO.getConditionModelExtId(),
                mulConditionDTO.getRolePath(),
                mulConditionDTO.getCardinality());

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        MULCondition mulCondition = adi
                .createEntityInvariantCondition(mulConditionDTO);

        return new ResponseEntity<MulConditionDTO>(mulCondition.getDTO(),
                HttpStatus.CREATED);
    }

}
