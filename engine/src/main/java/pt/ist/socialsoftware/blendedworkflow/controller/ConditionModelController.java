package pt.ist.socialsoftware.blendedworkflow.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pt.ist.socialsoftware.blendedworkflow.service.design.AtomicDesignInterface;

@Controller
@RequestMapping(value = "/conditionmodels")
public class ConditionModelController {
    private static Logger log = LoggerFactory
            .getLogger(ConditionModelController.class);

    @RequestMapping(value = "/{extId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public ResponseEntity<String> cleanConditionModel(
            @PathVariable("extId") String extId) {
        log.debug("cleanConditionModel extId:{}", extId);

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        adi.cleanConditionModel(extId);

        return new ResponseEntity<String>(HttpStatus.OK);
    }

}
