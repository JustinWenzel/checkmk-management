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
        String folderName = hostFormDTO.getFolderName();

        // ~ for proper Checkmk API format
        if (folderName.isEmpty()) {
            hostFormDTO.setFolderName("~");
        }
        hostFormDTO.setFolderName("~" + folderName);

        // String/Object for proper functionality with other APIs
        Map<String,Object> payload = Map.of("folder", hostFormDTO.getFolderName().trim(), "host_name",
                                            hostFormDTO.getName().trim(), "attributes", 
                                            Map.of("ipaddress", hostFormDTO.getIpAdress().trim()));


        checkmkRestClient.post()
            .uri("/domain-types/host_config/collections/all")
            .body(payload)
            .retrieve() // Request
            .toBodilessEntity(); // Only HTTP metadata no response entity


    }
    
}
