package ru.netology.TestContainer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestContainerApplicationTests {
	@Autowired
	TestRestTemplate restTemplate;
	@Container
	private static final GenericContainer<?> myAppProd=new GenericContainer<>("prodapp")
			.withExposedPorts(8081);
	@Container
	private static final GenericContainer<?> myAppDev=new GenericContainer<>("devapp")
			.withExposedPorts(8080);




	@Test
	void contextLoads() {

		ResponseEntity<String> entityFromProd = restTemplate.getForEntity("http://localhost:"
				+ myAppProd.getMappedPort(8081)+"/profile", String.class);
		System.out.println("Prod " + entityFromProd.getBody());
		ResponseEntity<String> entityFromDev = restTemplate.getForEntity("http://localhost:"
				+ myAppDev.getMappedPort(8080)+"/profile", String.class);
		System.out.println("Dev " + entityFromDev.getBody());
		assertEquals("Current profile is dev",entityFromDev.getBody());
	}


}
