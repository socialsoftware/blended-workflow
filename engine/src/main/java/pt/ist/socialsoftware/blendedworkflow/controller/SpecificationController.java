package pt.ist.socialsoftware.blendedworkflow.controller;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ProductDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.SpecDTO;

@RestController
@RequestMapping(value = "/specs")
public class SpecificationController {
    private static Logger log = LoggerFactory
            .getLogger(SpecificationController.class);

    @RequestMapping(value = "/{specId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteSpecification(
            @PathVariable("specId") String specId) {
        log.debug("deleteSpecification specId:{}", specId);

        DesignInterface adi = DesignInterface.getInstance();

        adi.deleteSpecification(specId);

        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{specId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<SpecDTO> getSpecBySpecId(
            @PathVariable("specId") String specId) {
        log.debug("getSpecBySpecId sepcId:{}", specId);

        DesignInterface adi = DesignInterface.getInstance();

        Specification spec = adi.getSpecBySpecId(specId);

        return new ResponseEntity<SpecDTO>(spec.getDTO(), HttpStatus.OK);
    }

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<SpecDTO> createSpec(@RequestBody SpecDTO specDTO) {
        log.debug("createSpec specId:{}, name:{}", specDTO.getSpecId(),
                specDTO.getName());
        DesignInterface adi = DesignInterface.getInstance();

        Specification spec = adi.createSpecification(specDTO);

        return new ResponseEntity<SpecDTO>(spec.getDTO(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{specId}/print", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<Boolean> printSpecModels(
            @PathVariable("specId") String specId) {
        log.debug("printSpecModels specId:{}", specId);

        DesignInterface adi = DesignInterface.getInstance();

        adi.printSpecificationModels(specId);

        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @RequestMapping(value = "/{specId}/pathsource", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<ProductDTO> getSourceOfPath(
            @PathVariable(value = "specId") String specId,
            @RequestParam(value = "path") String path) {
        log.debug("getSourceOfPath specId:{}, path:{}", specId, path);

        DesignInterface adi = DesignInterface.getInstance();

        ProductDTO productDTO = adi.getSourceOfPath(specId, path);

        return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/{specId}/pathtarget", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<ProductDTO> getTargetOfPath(
            @PathVariable("specId") String specId,
            @RequestParam("path") String path) {
        log.debug("getTargetOfPath specId:{}, path:{}", specId, path);

        DesignInterface adi = DesignInterface.getInstance();

        ProductDTO productDTO = adi.getTargetOfPath(specId, path);

        return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/{specId}/pathdep", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<String[]> getDependencesOfPaths(
            @PathVariable("specId") String specId,
            @RequestParam("paths") String paths) {
        log.debug("getDependencesOfPaths specId:{}, paths:{}", specId, paths);

        DesignInterface adi = DesignInterface.getInstance();

        Set<String> result = adi.getDependencePaths(specId, Arrays
                .asList(paths.split(",")).stream().collect(Collectors.toSet()));

        return new ResponseEntity<String[]>(
                result.toArray(new String[result.size()]), HttpStatus.OK);
    }

}
