package com.checkmk.checkmk_management.host;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.checkmk.checkmk_management.host.dto.HostFormDTO;

@Component
public class HostPayloadMapper {
    
    public Map<String,Object> toCreatePayload(HostFormDTO hostFormDTO, String properFolderName) {
        
        Map<String,Object> payload = Map.of("folder", properFolderName.trim(),
                                            "host_name", hostFormDTO.getName().trim(),
                                            "attributes", Map.of("ipaddress", hostFormDTO.getIpAdress().trim()));

        return payload;
    }
}
