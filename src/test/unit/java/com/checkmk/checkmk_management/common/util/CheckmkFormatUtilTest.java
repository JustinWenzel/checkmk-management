package com.checkmk.checkmk_management.common.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class CheckmkFormatUtilTest {

    @ParameterizedTest
    @CsvSource({
        "testFolder, ~testFolder",
        "    testFolder, ~testFolder",
        "testFolder    , ~testFolder",
        "/testFolder, ~/testFolder",
        " , ~"
    })
    void shouldApplyTilde(String input, String expected){
        assertEquals(expected, CheckmkFormatUtil.formatFolder(input));

    }

    
}
