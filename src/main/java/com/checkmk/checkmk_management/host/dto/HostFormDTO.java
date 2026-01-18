package com.checkmk.checkmk_management.host.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class HostFormDTO {

    @NotBlank(message = "Give a hostname")
    private String name;

    @NotBlank(message = "Give a proper IP Adress")
    @Pattern(regexp = "^((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)$" + //
                "", message = "Not a valid ip format")
    private String ipAdress;

    private String folderName;

}
