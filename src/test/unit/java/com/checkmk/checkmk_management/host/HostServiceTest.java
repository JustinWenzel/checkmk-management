package com.checkmk.checkmk_management.host;

import static org.mockito.Mockito.verify;

import org.hibernate.mapping.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

/* 
@ExtendWith(MockitoExtension.class)
public class HostServiceTest {
    
    @Mock
    private RestClient restClient;

    @Mock
    private RestClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private RestClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private RestClient.RequestBodyUriSpec requestBodyUriSpec;

    @Mock
    private RestClient.RequestBodySpec requestBodySpec;

    @Mock
    private RestClient.ResponseSpec responseSpec;

    @InjectMocks
    private HostService hostService;

    
    void shouldCreateHostSuccessfully(String folderName) {
        HostFormDTO hostFormDTO = new HostFormDTO();
        hostFormDTO.setName("testComputer");
        hostFormDTO.setIpAdress("1.1.1.1");
        hostFormDTO.setFolderName(folderName);


        when(restClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri("/domain-types/host_config/collections/all")).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(Map.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve().thenReturn(responseSpec));
        when(responseSpec.toBodilessEntity()).thenReturn(ResponseEntity.ok().build()); // 200 with no body

        ArgumentCaptor<Map<String,Object> captor = ArgumentCaptor.forClas  
    }
}
*/
