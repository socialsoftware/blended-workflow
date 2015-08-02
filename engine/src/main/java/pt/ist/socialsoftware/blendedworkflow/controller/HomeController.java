package pt.ist.socialsoftware.blendedworkflow.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pt.ist.socialsoftware.blendedworkflow.service.design.AtomicDesignInterface;

@Controller
public class HomeController {
    private static Logger log = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showHome() {
        log.debug("showHome()");

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        adi.loadDataYYYSpecification("20", "novo");

        return "home";
    }

    @RequestMapping(value = "/dataspecification/{specId}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateEmployee(
            @PathVariable("specId") String specId) {

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        adi.loadDataYYYSpecification(specId, "novo");

        System.out.println(specId);
        return new ResponseEntity<String>("novo", HttpStatus.OK);
    }

}
