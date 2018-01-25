package pt.ist.socialsoftware.blendedworkflow.designer.remote.utils;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BWResponseErrorHandler implements ResponseErrorHandler {
	private static final Logger log = LoggerFactory.getLogger(BWResponseErrorHandler.class);

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (RestUtil.isError(response.getStatusCode())) {
				BWError error = mapper.readValue(response.getBody(), BWError.class);
				log.error("Response error: {} {} {}", response.getStatusCode(), response.getStatusText(),
						error.getMessage());
				throw new RepositoryException(error);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return RestUtil.isError(response.getStatusCode());
	}

}
