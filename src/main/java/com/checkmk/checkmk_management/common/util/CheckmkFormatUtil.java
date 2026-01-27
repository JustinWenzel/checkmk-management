package com.checkmk.checkmk_management.common.util;

import lombok.experimental.UtilityClass;

@UtilityClass // Final class with private constructor
public class CheckmkFormatUtil {

    // Checkmk API expects the folder to start with "~"
    public String formatFolder(String folderName) {
    //"" or " "
        if (folderName != null && !folderName.isBlank()) {
                return "~" + folderName.trim();
            } else {
                return "~";
            }
    }
}
