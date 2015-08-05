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

import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.service.design.AtomicDesignInterface;
import pt.ist.socialsoftware.blendedworkflow.service.dto.SpecDTO;

@Controller
@RequestMapping(value = "/specs")
public class SpecificationController {
    private static Logger log = LoggerFactory
            .getLogger(SpecificationController.class);

    @RequestMapping(value = "/{specId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<SpecDTO> getSpecBySpecId(
            @PathVariable("specId") String specId) {
        log.debug("getSpecBySpecId sepcId:{}", specId);

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        BWSpecification spec = adi.getSpecBySpecId(specId);

        return new ResponseEntity<SpecDTO>(spec.getDTO(), HttpStatus.OK);
    }

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<SpecDTO> createSpec(@RequestBody SpecDTO specDTO) {
        log.debug("createSpec specId:{}, name:{}", specDTO.getSpecId(),
                specDTO.getName());
        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        BWSpecification spec = adi.createSpecification(specDTO);

        return new ResponseEntity<SpecDTO>(spec.getDTO(), HttpStatus.CREATED);
    }

}
