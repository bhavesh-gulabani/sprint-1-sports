package com.cg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@Import({SpringDataRestConfiguration.class, BeanValidatorPluginsConfiguration.class})
public class ApplicationSwaggerConfig {
	
	@Bean
	public Docket sportsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo());
	}

	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder()
				.title("Sports Shoppee API")
				.version("1.0")
				.description("API for online sports shoppee")
				.contact(new Contact("Bhavesh Gulabani", "https://bhavesh-gulabani.com", "bhaveshgulabani1@gmail.com"))
				.license("Apache Lisence Version 2.0")
				.build();
	}
}
