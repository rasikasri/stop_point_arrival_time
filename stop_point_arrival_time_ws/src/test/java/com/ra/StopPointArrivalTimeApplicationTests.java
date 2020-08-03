package com.ra;

import com.ra.ws.controller.ArrivalSearchController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class StopPointArrivalTimeApplicationTests {

	@Autowired
	private ArrivalSearchController controller;

	@Test
	void contextLoads() {
		assertNotNull(controller, "Context loading error");
	}

}
