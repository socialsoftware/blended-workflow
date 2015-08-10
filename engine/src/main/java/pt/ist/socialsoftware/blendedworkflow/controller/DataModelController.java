package pt.ist.socialsoftware.blendedworkflow.controller;

import java.util.Arrays;
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
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWProduct;
import pt.ist.socialsoftware.blendedworkflow.service.design.AtomicDesignInterface;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DependenceDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.EntityDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ProductDTO;

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

    @RequestMapping(value = "{dataModelExtId}/products/{atts}/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<ProductDTO> getProduct(
            @PathVariable("dataModelExtId") String dataModelExtId,
            @PathVariable("atts") String atts) {
        log.debug("getProduct atts:{}", atts);

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        String[] attsArray = atts.split(",");

        BWProduct product = adi.getProduct(dataModelExtId,
                Arrays.asList(attsArray));

        return new ResponseEntity<ProductDTO>(product.getDTO(), HttpStatus.OK);
    }

    @RequestMapping(value = "{dataModelExtId}/entities/{entityName}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<EntityDTO> getEntityByName(
            @PathVariable("dataModelExtId") String dataModelExtId,
            @PathVariable("entityName") String entityName) {
        log.debug("getProduct entityName:{}", entityName);

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        BWEntity entity = adi.getEntityByName(dataModelExtId, entityName);

        return new ResponseEntity<EntityDTO>(entity.getDTO(), HttpStatus.OK);
    }

}
