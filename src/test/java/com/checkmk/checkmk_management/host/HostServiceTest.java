package com.checkmk.checkmk_management.host;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClient;

@ExtendWith(MockitoExtension.class)
public class HostServiceTest {
    
    @Mock
    private RestClient restClient;

    @InjectMocks
    private HostService hostService;

    
}
