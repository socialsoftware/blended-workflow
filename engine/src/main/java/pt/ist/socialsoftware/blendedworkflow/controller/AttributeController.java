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

import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute;
import pt.ist.socialsoftware.blendedworkflow.service.design.AtomicDesignInterface;
import pt.ist.socialsoftware.blendedworkflow.service.dto.AttributeDTO;

@Controller
@RequestMapping(value = "/attributes")
public class AttributeController {
    private static Logger log = LoggerFactory
            .getLogger(AttributeController.class);

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<AttributeDTO> createAttribute(
            @RequestBody AttributeDTO attDTO) {
        log.debug(
                "createAttribute entityExtId:{}, groupExtId:{}, name:{}, type:{}",
                attDTO.getEntityExtId(), attDTO.getGroupExtId(),
                attDTO.getName(), attDTO.getType());

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        BWAttribute attribute = adi.createAttribute(attDTO);

        return new ResponseEntity<AttributeDTO>(attribute.getDTO(),
                HttpStatus.CREATED);
    }

}
