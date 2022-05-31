package br.com.bankaccountmanagement.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * @author: Kleryton de souza
 * 
 * Esta classe é reponsável por configurar e habilitar o Swagger na aplicação
 * 
 */

@Configuration
public class SwaggerConfig {
		      
	    @Bean
        public OpenAPI customOpenAPI() {
	    	Contact contact = new Contact();
         return new OpenAPI()
              .info(new Info()
              .title("Bank Account Management - API Restful")
              .version("1.0")
              .description("Bank Account Management")
              .contact(contact.name("Kleryton de Souza"))
              .contact(contact.url("https://github.com/klerytondev"))
              .contact(contact.email("klerytondev@gmail.com"))
              .termsOfService("http://swagger.io/terms/")
              .license(new License().name("Apache 2.0").url("http://springdoc.org")));
	    }
}
