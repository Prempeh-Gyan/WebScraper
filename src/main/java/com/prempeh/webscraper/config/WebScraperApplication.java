package com.prempeh.webscraper.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@ComponentScan(basePackages = { "com.prempeh.webscraper" })
@Slf4j
public class WebScraperApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {

		log.info("Spring Boot Start...");

		SpringApplication.run(WebScraperApplication.class, args);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
	}
}
