package com.simply.restservices;


import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
//
@SpringBootApplication
public class SpringbootCodeblocksApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCodeblocksApplication.class, args);
	}
	
	 @Bean
	public LocaleResolver localResolver() {
		AcceptHeaderLocaleResolver localeReslover = new AcceptHeaderLocaleResolver();
		localeReslover.setDefaultLocale(Locale.US);
		return localeReslover;		
	}
	
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource  = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	} 

}
 