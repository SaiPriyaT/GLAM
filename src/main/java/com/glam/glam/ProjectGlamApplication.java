package com.glam.glam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

@ComponentScan(basePackages={"com.glam.controller","com.glam.services","com.glam.interceptor"})
@EntityScan("com.glam.beans")
@EnableJpaRepositories("com.glam.repository")
public class ProjectGlamApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(ProjectGlamApplication.class, args);
	}
	
	
	

	
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder) {

	return springApplicationBuilder.sources(ProjectGlamApplication.class);
	}

}
