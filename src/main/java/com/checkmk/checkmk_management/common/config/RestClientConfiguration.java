package com.checkmk.checkmk_management.common.config;

import java.time.Duration;

import javax.net.ssl.SSLContext;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.ssl.DefaultClientTlsStrategy;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
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
    
    @Bean
    public RestClient restClient() throws Exception {
        return RestClient.builder()
            .baseUrl(baseURL)
            .defaultHeaders(header -> {
                header.setBasicAuth(username, password);
                header.setContentType(MediaType.APPLICATION_JSON);
            })
            // 4xx Client Errors
            .defaultStatusHandler(
                HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new CheckmkClientException(response.getStatusText());
                }
            )
            // 5xx Server Errors
            .defaultStatusHandler(
                HttpStatusCode::is5xxServerError, (request, response) -> {
                    throw new CheckmkServerException(response.getStatusText());
                }
            )
            .requestFactory(createRequestFactory(timeoutSeconds))
            .build();
    }

    // Client request factory (How to make HTTP request)
    private ClientHttpRequestFactory createRequestFactory(int timeoutSeconds) throws Exception {

        // Trust all certificates
        SSLContext sslContext = SSLContextBuilder.create()
                .loadTrustMaterial(null, (chain, authType) -> true)
                .build();

        
        DefaultClientTlsStrategy tlsStrategy = new DefaultClientTlsStrategy(
                sslContext,
                NoopHostnameVerifier.INSTANCE);

        
        HttpClientConnectionManager connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
                .setTlsSocketStrategy(tlsStrategy)
                .build();

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory(httpClient);

        // Allowed duration for connection
        requestFactory.setConnectTimeout(Duration.ofSeconds(timeoutSeconds));
        // After successful connection, allowed duration for response
        requestFactory.setReadTimeout(Duration.ofSeconds(timeoutSeconds));

        return requestFactory;
    }
}
