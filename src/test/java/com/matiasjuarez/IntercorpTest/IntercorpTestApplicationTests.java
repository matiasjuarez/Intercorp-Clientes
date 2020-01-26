package com.matiasjuarez.IntercorpTest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZoneId;

class IntercorpTestApplicationTests {

	@Test
	void contextLoads() {
		ZoneId.getAvailableZoneIds().forEach(
				System.out::println
		);
	}

}
