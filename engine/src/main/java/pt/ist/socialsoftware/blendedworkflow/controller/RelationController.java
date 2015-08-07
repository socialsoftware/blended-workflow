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

import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation;
import pt.ist.socialsoftware.blendedworkflow.service.design.AtomicDesignInterface;
import pt.ist.socialsoftware.blendedworkflow.service.dto.RelationDTO;

@Controller
@RequestMapping(value = "/relations")
public class RelationController {
    private static Logger log = LoggerFactory
            .getLogger(RelationController.class);

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<RelationDTO> createRelation(
            @RequestBody RelationDTO relDTO) {
        log.debug("createRelation {}, {}, {}", relDTO.getName(),
                relDTO.getEntOneName(), relDTO.getEntTwoName());

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        BWRelation relation = adi.createRelation(relDTO);

        return new ResponseEntity<RelationDTO>(relation.getDTO(),
                HttpStatus.CREATED);
    }

}
