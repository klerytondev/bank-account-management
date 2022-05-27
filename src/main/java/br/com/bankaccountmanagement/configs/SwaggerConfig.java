package br.com.bankaccountmanagement.configs;

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

/*
 * Esta classe é reponsável por configurar e habilitar o Swagger na aplicação
 * 
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	  @Bean
	    public Docket api() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("br.com.kleryton.bankingsystem"))
	                .paths(PathSelectors.any())
	                .build()
	                .apiInfo(showMetadata());
	    }

	    private ApiInfo showMetadata() {
	        return new ApiInfoBuilder()
	                .title("Bank Account Management - API Restfull")
	                .description("Bank Account Management")
	                .version("1.0")
	                .license("Apache License Version 2.0")
	                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
	                .contact(new Contact("Kleryton de Souza", "https://github.com/kleryton.dev/", "klerytondev@gmail.com"))
	                .build();
	    }
}
