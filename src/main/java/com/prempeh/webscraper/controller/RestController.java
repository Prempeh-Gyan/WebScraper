package com.prempeh.webscraper.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.prempeh.webscraper.service.ScrapingService;
import com.prempeh.webscraper.utility.SummarizeLinksUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * This controller is for mapping RESTful requests. Both the getLinksOnPage and
 * postLinksOnPage methods return a JSON representation of the summary returned
 * by the SummarizeLinksUtil Class
 * 
 * @author Prince Prempeh Gyan
 * @version 1.0 <br/>
 *          Date: 09/09/2017
 *
 */
@org.springframework.web.bind.annotation.RestController
@Slf4j
public class RestController {

	@Autowired
	private ScrapingService scrapingService;

	@RequestMapping(value = { "/summarizeLinksOnPage" }, method = { RequestMethod.GET, RequestMethod.POST})
	public Map<String, Long> getLinksOnPage(@RequestParam(value = "url") String url) throws IOException {

		log.info("Restful Request to summarizeLinksOnPage with url : {}", url);

		return SummarizeLinksUtil.getSummary(url, scrapingService);
	}

}
