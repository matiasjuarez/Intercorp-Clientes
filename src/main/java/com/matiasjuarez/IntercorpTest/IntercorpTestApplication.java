package com.matiasjuarez.IntercorpTest;

import com.matiasjuarez.IntercorpTest.model.client.ClienteHelper;
import com.matiasjuarez.IntercorpTest.service.DateHandler;
import com.matiasjuarez.IntercorpTest.service.deathstimationstrategies.BasicDeathCalculationStrategy;
import com.matiasjuarez.IntercorpTest.service.deathstimationstrategies.DeathCalculationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@SpringBootApplication
@EnableSwagger2
public class IntercorpTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntercorpTestApplication.class, args);
	}

	@Bean
	@Autowired
	public DeathCalculationStrategy getDeathCalculationStrategy(DateHandler dateHandler, ClienteHelper clienteHelper) {
		return new BasicDeathCalculationStrategy(dateHandler, clienteHelper);
	}

	@Bean
	public Docket swaggerAPI() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.apiInfo(new ApiInfo(
						"Intercorp challenge",
						"Application to store Clients and perform some calculations over their data",
						"1.0",
						"Terms of service",
						new Contact("Matias Juarez", "", "matiasjuarez400@gmail.com"),
						"Apache License Version 2.0",
						"https://www.apache.org/licenses/LICENSE-2.0",
						new ArrayList<>()
				));
	}


}
