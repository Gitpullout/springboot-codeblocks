package com.simply.restservices.hello;

import java.util.Locale;

import org.apache.tomcat.util.http.parser.AcceptLanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@Autowired
	private ResourceBundleMessageSource messageSource;

	// simple method
	// uri - /helloworld
	// GET Method two method = 1)RequestMapping 2)Get Mapping
	// @RequestMapping(method=RequestMethod.GET, path="/helloworld")
	@GetMapping("helloworld1")
	public String hellworld() {
		return "Hello World";
	}

	@GetMapping("/helloworld-bean")
	public UserDetails helloWorldBean() {
		return new UserDetails("krishna", "prasad", "Bangalore");
	}

	@GetMapping("/hello-int")
	public String getMessageIiI18NFormat(@RequestHeader(name= "Accept-Language",required = false) String locale) {
		return messageSource.getMessage("label.hello",null, new Locale(locale));
	}
	
	@GetMapping("/hello-int2")
	public String getMessageIiI18NFormat2() {
		return messageSource.getMessage("label.hello",null, LocaleContextHolder.getLocale());
	}
}
