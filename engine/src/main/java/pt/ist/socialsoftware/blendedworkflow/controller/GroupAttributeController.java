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

import pt.ist.socialsoftware.blendedworkflow.domain.BWAttributeGroup;
import pt.ist.socialsoftware.blendedworkflow.service.design.AtomicDesignInterface;
import pt.ist.socialsoftware.blendedworkflow.service.dto.AttributeGroupDTO;

@Controller
@RequestMapping(value = "/groupattributes")
public class GroupAttributeController {
    private static Logger log = LoggerFactory
            .getLogger(GroupAttributeController.class);

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<AttributeGroupDTO> createGroupAttribute(
            @RequestBody AttributeGroupDTO groupAttributeDTO) {
        log.debug("createGroupAttribute entityExtId:{},  name:{}, mandatory:{}",
                groupAttributeDTO.getEntityExtId(), groupAttributeDTO.getName(),
                groupAttributeDTO.isMandatory());

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        BWAttributeGroup group = adi.createAttributeGroup(groupAttributeDTO);

        return new ResponseEntity<AttributeGroupDTO>(group.getDTO(),
                HttpStatus.CREATED);
    }

}
