/**
 * 
 */
package jp.co.axa.apidemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2 REST API Interface
 * 
 * @author Peter
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * Create Docket for Swagger2 UI
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("jp.co.axa.apidemo")).paths(PathSelectors.any()).build()
				.apiInfo(apiInfo());

	}

	/**
	 * Create the API Informations.
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Spring Boot REST API -  Swagger2, H2 Database, Hazelcast Cache, Security, and UnitTest Demo ")
				.description("The Spring Boot REST API for Demo.").termsOfServiceUrl("https://www.example.com/api")
				.contact(new Contact("Peter Paul", "", "petpaul_84@hotmail.com")).license("Apache License Version 2.0")
				.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0").version("0.0.1").build();
	}

}
