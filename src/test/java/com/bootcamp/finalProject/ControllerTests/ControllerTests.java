package com.bootcamp.finalProject.ControllerTests;

import com.bootcamp.finalProject.controllers.PartController;
import com.bootcamp.finalProject.exceptions.InternalExceptionHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class ControllerTests {

	PartController partController;
	@BeforeEach
	void setUp(){
		PartController partController = new PartController();
	}
	@Test
	void shouldReturnExceptionSinceTheParamOfOrderingDoesNotExist(){
		Map<String, String> order = new HashMap<>();
		order.put("queryType", "V");
		order.put("order", "23");
		order.put("date", "2020-01-11");
		Assertions.assertThrows(InternalExceptionHandler.class, () -> partController.obtainList(order));

	}
	@Test
	void shouldReturnExceptionSinceParamOfSearchDoesNotExist() throws Exception {
		Map<String, String> queryType = new HashMap<>();
		queryType.put("queryType", "er");
		Assertions.assertThrows(InternalExceptionHandler.class, () -> partController.obtainList(queryType));
	}
	@Test
	void shouldReturnExceptionSinceParamOfDateIsInABadFormat(){
		Map<String, String> order = new HashMap<>();
		order.put("queryType", "V");
		order.put("order", "2");
		order.put("date", "11-12-2019");
		Assertions.assertThrows(InternalExceptionHandler.class, () -> partController.obtainList(order));
	}
	@Test
	void shouldReturnExceptionSinceParamOfDateIsUsingSlashInsteadOfHyphen(){
		Map<String, String> order = new HashMap<>();
		order.put("queryType", "V");
		order.put("order", "2");
		order.put("date", "2020/02/01");
		Assertions.assertThrows(InternalExceptionHandler.class, () -> partController.obtainList(order));
	}
	@Test
	void shouldReturnExceptionSinceParamOfDateIsInTheFuture(){
		Map<String, String> order = new HashMap<>();
		order.put("queryType", "V");
		order.put("order", "2");
		order.put("date", "2025-12-12");
		Assertions.assertThrows(InternalExceptionHandler.class, () -> partController.obtainList(order));
	}
	@Test
	void shouldNotThrowErrorSinceParamsAreCorrect(){
		Map<String, String> order = new HashMap<>();
		order.put("queryType", "V");
		order.put("order", "2");
		order.put("date", "2020-12-12");
		Assertions.assertDoesNotThrow(() -> partController.obtainList(order));
	}
}
