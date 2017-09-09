package com.prempeh.webscraper.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@ComponentScan(basePackages = { "com.prempeh.webscraper" })
@Slf4j
public class WebScraperApplication {

	public static void main(String[] args) {

		log.info("Spring Boot Started");

		SpringApplication.run(WebScraperApplication.class, args);
	}
}
