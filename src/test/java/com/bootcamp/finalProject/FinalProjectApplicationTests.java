package com.bootcamp.finalProject;

import com.bootcamp.finalProject.controllers.PartController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class FinalProjectApplicationTests {

	PartController partController;
	@BeforeEach
	void setUp(){
		PartController partController = new PartController();
	}

	@Test
	void contextLoads() {
	}
	@Test
	void shouldReturnExceptionSinceTheParamOfOrderingDoesNotExist(){
		Map<String, String> order = new HashMap<>();
		order.put("queryType", "V");
		order.put("order", "23");
		order.put("date", "2020-01-11");
		Assertions.assertThrows(partController.obtainList);
	}
	@Test
	void shouldReturnExceptionSinceParamOfSearchDoesNotExist(){
		Map<String, String> queryType = new HashMap<>();
		queryType.put("queryType", "er");
		Assertions.assertThrows(partController.obtainList);
	}
	@Test
	void shouldReturnExceptionSinceParamOfDateIsInABadFormat(){
		Map<String, String> order = new HashMap<>();
		order.put("queryType", "V");
		order.put("order", "2");
		order.put("date", "11-12-2019");
		Assertions.assertThrows(partController.obtainList);
	}

}
