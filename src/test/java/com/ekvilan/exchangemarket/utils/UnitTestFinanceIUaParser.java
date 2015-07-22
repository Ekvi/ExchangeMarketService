package com.ekvilan.exchangemarket.utils;


import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class UnitTestFinanceIUaParser {
    private FinanceIUaParser parser = new FinanceIUaParser();
    @Test
    public void testConvertBase64() {
        assertEquals("6575633", parser.convertBase64("NjU3NTYzMw=="));
        assertEquals("5749286", parser.convertBase64("NTc0OTI4Ng=="));
    }

    @Test
    public void testExtractFirstPart() {
        assertEquals("+38095", parser.extractFirstPart("+38 095 Показать"));
        assertEquals("+38099", parser.extractFirstPart("+38 099 Показать"));
    }
}
