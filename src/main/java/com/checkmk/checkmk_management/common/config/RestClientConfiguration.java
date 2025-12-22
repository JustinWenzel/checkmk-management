package com.checkmk.checkmk_management.common.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import com.checkmk.checkmk_management.common.exception.CheckmkClientException;

//Checkmk API client
@Configuration
public class RestClientConfiguration {

    @Value("${checkmk-api.base-url}")
    private String baseURL;

    @Value("${checkmk-api.site-ID}")
    private String site;

    @Value("${checkmk-api.user}")
    private String username;

    @Value("${checkmk-api.password}")
    private String password;

    @Value("${checkmk-api.SSL-enable}")
    private Boolean SSLEnable;

    @Value("${checkmk-api.timeout-seconds}")
    private int timeoutSeconds;
    
    public RestClient restClient(){
    return RestClient.builder()
        .baseUrl(baseURL)
        .defaultHeaders(header -> {
            header.setBasicAuth(username, password);
            header.setContentType(MediaType.APPLICATION_JSON);
        })
        // 4xx Client Errors
        .defaultStatusHandler(
            HttpStatusCode::is4xxClientError,
            (request, response) -> {
                throw new CheckmkClientException(response.getStatusText());
            }
        )
        // 5xx Server Errors
        .defaultStatusHandler(
            HttpStatusCode::is5xxServerError,
            (request, response) -> {
                throw new CheckmkServerException(response.getStatusText());
            }
        )
        .requestFactory(createRequestFactory(timeoutSeconds))
        .build();
}

    // Simple factory (How to make HTTP request) implements super interface factory (Makes HTTP request possible)
    @Bean
    public ClientHttpRequestFactory createRequestFactory(int timeoutSeconds){
        // Default factory
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        // Allowed duration for connection
        factory.setConnectTimeout(Duration.ofSeconds(timeoutSeconds));
        // After successfull connection, allowed duration for response
        factory.setReadTimeout(Duration.ofSeconds(timeoutSeconds));
        
        return factory;
    } 
}
