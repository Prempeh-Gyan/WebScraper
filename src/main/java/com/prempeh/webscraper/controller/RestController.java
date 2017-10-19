package com.prempeh.webscraper.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.prempeh.webscraper.service.WebScrapingService;

import lombok.extern.slf4j.Slf4j;

/**
 * This controller is for mapping RESTful requests. The getLinksOnPage method
 * returns a JSON representation of the summary returned by the
 * WebScrapingService
 * 
 * @author Prince Prempeh Gyan
 * @version 1.1 <br/>
 *          Date: 19/10/2017
 *
 */
@org.springframework.web.bind.annotation.RestController
@Slf4j
public class RestController {

	@Autowired
	private WebScrapingService webScrapingService;

	@RequestMapping(value = { "/summarizeLinksOnPage" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Long> getLinksOnPage(@RequestParam(value = "url") String url) throws IOException {

		log.info("Restful Request to the WebScrapingService endpoint with parameter: url = {}", url);

		return webScrapingService.getSummaryOfLinksOnPage(url);
	}

}
