package com.spring.tests;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.demo.Application;

@SpringBootTest(classes = Application.class)
public class SpringContextTest {

    @Test
    public void contextLoads() {
        // Sadece context'in başarıyla yüklenip yüklenmediğini kontrol eder.
    }
}
