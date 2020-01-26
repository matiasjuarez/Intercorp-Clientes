package com.matiasjuarez.IntercorpTest;

import com.matiasjuarez.IntercorpTest.service.deathstimationstrategies.BasicDeathCalculationStrategy;
import com.matiasjuarez.IntercorpTest.service.deathstimationstrategies.DeathCalculationStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IntercorpTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntercorpTestApplication.class, args);
	}

	@Bean
	public DeathCalculationStrategy getDeathCalculationStrategy() {
		return new BasicDeathCalculationStrategy();
	}
}
