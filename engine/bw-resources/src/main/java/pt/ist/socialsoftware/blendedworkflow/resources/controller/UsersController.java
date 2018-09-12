package pt.ist.socialsoftware.blendedworkflow.resources.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pt.ist.socialsoftware.blendedworkflow.resources.service.design.DesignResourcesInterface;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.UserDto;
import pt.ist.socialsoftware.blendedworkflow.resources.utils.ResourcesFactory;

import javax.inject.Inject;

@RestController
@RequestMapping(value = "/users")
public class UsersController {
    private static Logger log = LoggerFactory.getLogger(UsersController.class);

    @Inject
    private ResourcesFactory factory;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<UserDto[]> createResourceModel(@PathVariable("specId") String specId) {
        log.debug("CreateResourceModel: {}", specId);

        DesignResourcesInterface designer = this.factory.createDesignInterface();

        UserDto[] users = designer.getUsers().stream()
                .toArray(size -> new UserDto[size]);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }


}
