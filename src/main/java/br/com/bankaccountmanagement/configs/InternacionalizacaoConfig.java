package br.com.bankaccountmanagement.configs;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 /* @author: Kleryton de souza
/*
* Esta classe é responsável por permitir que o arquivo 
* message.properties possa ser utilizado. Ela configura o nome do arquivo
* e o local geográfico onde essse arquivo será usado.
*/

@Configuration
public class InternacionalizacaoConfig {
	
	@Bean
	public MessageSource MessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("ISO-8859-1");
		messageSource.setDefaultLocale(Locale.getDefault());
		return messageSource;
	}

	@Bean
	public LocalValidatorFactoryBean validatorFactoryBean() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(MessageSource());
		return bean;
	}

}
