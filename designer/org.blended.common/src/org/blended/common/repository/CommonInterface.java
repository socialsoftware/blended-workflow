package org.blended.common.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class CommonInterface {
    private static Logger log = LoggerFactory.getLogger(CommonInterface.class);

    final static String SERVER_ADDRESS = "http://localhost:8080/";

    private static CommonInterface instance = null;

    public static CommonInterface getInstance() {
        if (instance == null) {
            instance = new CommonInterface();
        }
        return instance;
    }

    // private AtomicDesignInterface adi = null;
    //
    // private CommonInterface() {
    // adi = AtomicDesignInterface.getInstance();
    // }
    //
    // public ProductDTO getSourceOfPath(String specId, String path) {
    // log.debug("getSourceOfPath Path:{}", path);
    // return adi.getSourceOfPath(specId, path);
    // }
    //
    // public ProductDTO getTargetOfPath(String specId, String path) {
    // log.debug("getTargetOfPath Path:{}", path);
    // return adi.getTargetOfPath(specId, path);
    // }
    //
    // public Set<String> getDependencePaths(String specId,
    // Set<String> sucConditions) {
    // log.debug("getDependencePaths Path:{}", sucConditions);
    // return adi.getDependencePaths(specId, sucConditions);
    // }

    public String loadDataSpecification(String specId, String name) {
        log.debug("loadDataSpecification: {}", specId);

        // BWNotification notification = new BWNotification();

        final String uri = SERVER_ADDRESS + "/";
        //
        // Map<String, String> params = new HashMap<String, String>();
        // params.put("specId", "1");
        //
        RestTemplate restTemplate = new RestTemplate();
        // restTemplate.put(uri, null, params);

        String result = null;
        try {
            result = restTemplate.getForObject(uri, String.class);
        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

}
