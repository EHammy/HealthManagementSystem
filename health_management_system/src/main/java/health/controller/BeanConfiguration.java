package health.controller;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import health.beans.User;
import health.beans.Address;


@Configuration
public class BeanConfiguration {
	
	@Bean
	public User user() {
		User bean = new User("Kassidi Freel");
		return bean;
	}
	
	@Bean
	public Address address() {
		Address bean = new Address("700 Edison Road", "Dauphin", "PA");
		return bean;
	}
}