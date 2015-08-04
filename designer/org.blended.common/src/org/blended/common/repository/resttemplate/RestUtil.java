package org.blended.common.repository.resttemplate;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

public class RestUtil {

    public static RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new BWResponseErrorHandler());

        return restTemplate;
    }

    public static boolean isError(HttpStatus status) {
        HttpStatus.Series series = status.series();
        return (HttpStatus.Series.CLIENT_ERROR.equals(series)
                || HttpStatus.Series.SERVER_ERROR.equals(series));
    }

}
