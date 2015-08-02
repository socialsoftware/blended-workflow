package pt.ist.socialsoftware.blendedworkflow.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import pt.ist.socialsoftware.blendedworkflow.service.BWException;

@ControllerAdvice
public class BWExceptionHandler {
    private final static Logger logger = LoggerFactory
            .getLogger(BWExceptionHandler.class);

    @ExceptionHandler({ BWException.class })
    public ModelAndView handleBWException(BWException bwe) {
        logger.debug("handleBWException: {}, {}", bwe.getError().name(),
                bwe.getMessage());

        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("type", bwe.getError().name());
        modelAndView.addObject("message", bwe.getMessage());
        return modelAndView;
    }

}
