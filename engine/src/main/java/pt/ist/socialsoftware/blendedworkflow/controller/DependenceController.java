package pt.ist.socialsoftware.blendedworkflow.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pt.ist.socialsoftware.blendedworkflow.domain.BWDependence;
import pt.ist.socialsoftware.blendedworkflow.service.design.AtomicDesignInterface;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DependenceDTO;

@Controller
@RequestMapping(value = "/dependencies")
public class DependenceController {

    private static Logger log = LoggerFactory
            .getLogger(DependenceController.class);

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<DependenceDTO> createDependence(
            @RequestBody DependenceDTO dependenceDTO) {
        log.debug("createDependence productExtId:{}, path:{}",
                dependenceDTO.getProductExtId(), dependenceDTO.getPath());

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        BWDependence dependence = adi.createDependence(dependenceDTO);

        return new ResponseEntity<DependenceDTO>(dependence.getDTO(),
                HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{depExtId}/check", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<Boolean> checkDependence(
            @PathVariable("depExtId") String depExtId) {
        log.debug("checkDependence productExtId:{}", depExtId);

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        boolean result = adi.checkDependence(depExtId);

        return new ResponseEntity<Boolean>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{depExtId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteDependence(
            @PathVariable("depExtId") String depExtId) {
        log.debug("deleteDependence productExtId:{}", depExtId);

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        adi.deleteDependence(depExtId);

        return new ResponseEntity<String>(HttpStatus.OK);
    }

}
