package com.registrationsystem.workshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer()
		{
			@Override
			public void addCorsMappings(CorsRegistry registry) 
			{
				registry.addMapping("/**")
				.allowedMethods("GET","POST","DELET","PUT")
				.allowedHeaders("Authorization")
				.allowedHeaders("*")				
				.allowedOrigins("*");//Aqui voce coloca endereco e porta de que esta acessando e * e generico
				//.allowedOrigins("http://localhost:3000");//endereco e porta especifico
			}
		};
	}
}
