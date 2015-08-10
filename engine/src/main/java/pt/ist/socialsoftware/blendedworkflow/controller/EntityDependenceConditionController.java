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
@RequestMapping(value = "/entitydependenceconditions")
public class EntityDependenceConditionController {
    private static Logger log = LoggerFactory
            .getLogger(EntityDependenceConditionController.class);

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<DependenceDTO> createEntityDependenceCondition(
            @RequestBody DependenceDTO dependenceDTO) {
        log.debug(
                "createEntityDependenceCondition conditionModelExtId:{}, path1:{}, path2:{}",
                dependenceDTO.getConditionModelExtId(),
                dependenceDTO.getPath1(), dependenceDTO.getPath2());

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        BWDependence dependence = adi
                .createEntityDependenceCondition(dependenceDTO);

        return new ResponseEntity<DependenceDTO>(dependence.getDTO(),
                HttpStatus.CREATED);
    }

}
