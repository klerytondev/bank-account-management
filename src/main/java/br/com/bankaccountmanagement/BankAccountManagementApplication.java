package br.com.bankaccountmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//Inserido para corrigir erro de requizição do Swagger na base URL: localhost:8080/
@EnableSwagger2
public class BankAccountManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountManagementApplication.class, args);
	}

}
