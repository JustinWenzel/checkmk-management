package com.checkmk.checkmk_management.host;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HostService {

    private final RestClient checkmkRestClient;

    public void createHost(HostFormDTO hostFormDTO){
        // String/Object for proper functionality with other APIs
        Map<String,Object> payload = Map.of("folder", hostFormDTO.getFolderName(), "host_name",
                                            hostFormDTO.getName(), "attributes", 
                                            Map.of("ipaddress", hostFormDTO.getIpAdress()));


        checkmkRestClient.post().uri("/domain-types/host_config/collections/all").body(payload).retrieve();


    }
    
}
