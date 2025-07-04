package com.spring.tests;

import com.spring.demo.Application; // Ana uygulamanın main class'ı
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
public class ApplicationTests {

	@Test
	void contextLoads() {
		// Basit bir context load testi
	}
}
