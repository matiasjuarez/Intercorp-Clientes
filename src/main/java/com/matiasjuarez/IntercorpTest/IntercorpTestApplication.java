package com.matiasjuarez.IntercorpTest;

import com.matiasjuarez.IntercorpTest.model.client.ClienteHelper;
import com.matiasjuarez.IntercorpTest.service.DateHandler;
import com.matiasjuarez.IntercorpTest.service.deathstimationstrategies.BasicDeathCalculationStrategy;
import com.matiasjuarez.IntercorpTest.service.deathstimationstrategies.DeathCalculationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IntercorpTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntercorpTestApplication.class, args);
	}

	@Bean
	@Autowired
	public DeathCalculationStrategy getDeathCalculationStrategy(DateHandler dateHandler, ClienteHelper clienteHelper) {
		return new BasicDeathCalculationStrategy(dateHandler, clienteHelper);
	}
}
