package com.sap.calcacademy.calculator.test;

import org.junit.Test;

public class CalculatorServiceTest {

    @Test
    public void testRequestExpression_whenNoResults() {
        String get = "GET http://localhost:8081/com.sap.calcacademy.calculator/results/result/1 HTTP/1.1";
        String expected = null;
     }
    
    public void testPostRequest() {
        String post = "POST http://localhost:8081/com.sap.calcacademy.calculator/results/result/expression HTTP/1.1 (19.1-5.2*(20-0.1)-1)/2";
    }
}
