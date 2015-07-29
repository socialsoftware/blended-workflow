package org.blended.common.repository;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.service.design.AtomicDesignInterface;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ProductDTO;

public class CommonInterface {
    private static Logger log = LoggerFactory.getLogger(CommonInterface.class);

    private static CommonInterface instance = null;

    public static CommonInterface getInstance() {
        if (instance == null) {
            instance = new CommonInterface();
        }
        return instance;
    }

    private AtomicDesignInterface adi = null;

    private CommonInterface() {
        adi = AtomicDesignInterface.getInstance();
    }

    public ProductDTO getSourceOfPath(String specId, String path) {
        log.debug("getSourceOfPath Path:{}", path);
        return adi.getSourceOfPath(specId, path);
    }

    public ProductDTO getTargetOfPath(String specId, String path) {
        log.debug("getTargetOfPath Path:{}", path);
        return adi.getTargetOfPath(specId, path);
    }

    public Set<String> getDependencePaths(String specId,
            Set<String> sucConditions) {
        log.debug("getDependencePaths Path:{}", sucConditions);
        return adi.getDependencePaths(specId, sucConditions);
    }

}
