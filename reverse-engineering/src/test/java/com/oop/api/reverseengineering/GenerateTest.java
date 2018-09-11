package com.oop.api.reverseengineering;

import com.oop.api.reverseengineering.generate.Generate;
import org.junit.Test;

public class GenerateTest {
    @Test
    public void test(){
        try {
            Generate.generate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
