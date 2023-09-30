package com.simply.restservices.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

		//simple method
		//uri - /helloworld
		//GET Method two method = 1)RequestMapping 2)Get Mapping
		//@RequestMapping(method=RequestMethod.GET, path="/helloworld")
		@GetMapping("helloworld1")
		public String hellworld()
		{
			return "Hello World";
		}
		
		@GetMapping("/helloworld-bean")
		public UserDetails helloWorldBean() {
			return new UserDetails("krishna","prasad","Bangalore");
		}
}
