package com.practice.RazorPaySample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.EnableScheduling;

@PropertySources({@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true) })
@SpringBootApplication
public class RazorPaySampleApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {

		SpringApplication.run(RazorPaySampleApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RazorPaySampleApplication.class);
	}
}
