package com.checkmk.checkmk_management.host;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.checkmk.checkmk_management.common.util.CheckmkFormatUtil;
import com.checkmk.checkmk_management.host.dto.HostFormDTO;
import com.checkmk.checkmk_management.host.exception.HostNameIsNotGivenException;
import com.checkmk.checkmk_management.host.exception.IPAdressIsNotGivenException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HostService {

    private final RestClient checkmkRestClient;
    private final HostPayloadMapper mapper;

    public void createHost(HostFormDTO hostFormDTO){

        if (hostFormDTO.getName().isEmpty() || hostFormDTO.getName().isBlank()) {
            throw new HostNameIsNotGivenException("A hostname is required");
        }

        if (hostFormDTO.getIpAdress().isEmpty() || hostFormDTO.getIpAdress().isBlank()) {
            throw new IPAdressIsNotGivenException("An IP adress is required");
        }

        String properFolderName = CheckmkFormatUtil.formatFolder(hostFormDTO.getFolderName());

        // String/Object for proper functionality with other APIs
        Map<String,Object> payload = mapper.toCreatePayload(hostFormDTO, properFolderName);


        checkmkRestClient.post()
            .uri("/domain-types/host_config/collections/all")
            .body(payload)
            .retrieve() // Request
            .toBodilessEntity(); // Only HTTP metadata no response entity


    }
    
}
