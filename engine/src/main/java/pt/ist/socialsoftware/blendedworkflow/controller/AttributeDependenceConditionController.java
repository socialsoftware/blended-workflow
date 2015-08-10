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

import pt.ist.socialsoftware.blendedworkflow.domain.BWDependence;
import pt.ist.socialsoftware.blendedworkflow.service.design.AtomicDesignInterface;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DependenceDTO;

@Controller
@RequestMapping(value = "/attributedependenceconditions")
public class AttributeDependenceConditionController {
    private static Logger log = LoggerFactory
            .getLogger(AttributeDependenceConditionController.class);

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<DependenceDTO> createAttributeDependenceCondition(
            @RequestBody DependenceDTO dependenceDTO) {
        log.debug("createAttributeDependenceCondition productExtId:{}, path:{}",
                dependenceDTO.getProductExtId(), dependenceDTO.getPath1());

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        BWDependence dependence = adi
                .createAttributeDependenceCondition(dependenceDTO);

        return new ResponseEntity<DependenceDTO>(dependence.getDTO(),
                HttpStatus.CREATED);
    }

}
