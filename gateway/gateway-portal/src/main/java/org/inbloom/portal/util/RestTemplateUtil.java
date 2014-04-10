package org.inbloom.portal.util;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * @author benjaminmorgan
 *         Date: 4/10/14
 */
public class RestTemplateUtil {

    public static RestTemplate noErrorHandlers()
    {
        RestTemplate rest = new RestTemplate();

        rest.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
                return false;
            }

            @Override
            public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
            }
        });

        return rest;
    }
}
