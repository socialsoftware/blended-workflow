package pt.ist.socialsoftware.blendedworkflow.service.design;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourceInterface {
    private static Logger log = LoggerFactory.getLogger(ResourceInterface.class);

    private static ResourceInterface instance;

    public static ResourceInterface getInstance() {
        if (instance == null) {
            instance = new ResourceInterface();
        }
        return instance;
    }

    private ResourceInterface() {
    }
}
