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

import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.service.design.AtomicDesignInterface;
import pt.ist.socialsoftware.blendedworkflow.service.dto.EntityDTO;

@Controller
@RequestMapping(value = "/entities")
public class EntityController {

    private static Logger log = LoggerFactory.getLogger(EntityController.class);

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<EntityDTO> createEntity(
            @RequestBody EntityDTO entDTO) {
        log.debug("createEntity dataModelExtId:{}, name:{}, exists:{}",
                entDTO.getDataModelExtId(), entDTO.getName(),
                entDTO.getExists());

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        BWEntity entity = adi.createEntity(entDTO);

        return new ResponseEntity<EntityDTO>(entity.getDTO(),
                HttpStatus.CREATED);
    }

}
