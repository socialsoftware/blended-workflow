package pt.ist.socialsoftware.blendedworkflow.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pt.ist.socialsoftware.blendedworkflow.domain.BWDependence;
import pt.ist.socialsoftware.blendedworkflow.service.design.AtomicDesignInterface;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DependenceDTO;

@Controller
@RequestMapping(value = "/datamodels")
public class DataModelController {
    private static Logger log = LoggerFactory
            .getLogger(DataModelController.class);

    @RequestMapping(value = "/{extId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public ResponseEntity<String> cleanDataModel(
            @PathVariable("extId") String extId) {
        log.debug("cleanDataModel extId:{}", extId);

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        adi.cleanDataModel(extId);

        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{dataModelExtId}/dependencies", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<DependenceDTO[]> getDependencies(
            @PathVariable("dataModelExtId") String dataModelExtId) {
        log.debug("getDependencies dataModelExtId:{}", dataModelExtId);

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        Set<BWDependence> dependencies = adi.getDependencies(dataModelExtId);

        Set<DependenceDTO> dependenciesDTO;
        dependenciesDTO = dependencies.stream().map(dep -> dep.getDTO())
                .collect(Collectors.toSet());

        log.debug("getDependencies dependenciesDTO.size:{}",
                dependenciesDTO.size());

        DependenceDTO[] depsArray = dependenciesDTO
                .toArray(new DependenceDTO[dependenciesDTO.size()]);

        return new ResponseEntity<DependenceDTO[]>(depsArray, HttpStatus.OK);
    }

}
