package com.prempeh.webscraper.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.prempeh.webscraper.service.ScrapingService;
import com.prempeh.webscraper.utility.SummarizeLinksUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * This controller is for mapping all index page requests. The controller serves
 * an HTML page to clients that make GET and POST requests to it
 * 
 * @author Prince Prempeh Gyan
 * @version 1.0 <br/>
 *          Date: 09/09/2017
 */

@org.springframework.stereotype.Controller
@Slf4j
public class ViewController {

	/**
	 * The ScrapingService provides a method for extracting all links that appear on
	 * a particular page
	 */

	@Autowired
	private ScrapingService scrapingService;

	/**
	 * The postIndexPage method maps POST requests to the "/index" Request Mapping.
	 * This method expects that a url parameter is submitted through the body of the
	 * post request.
	 * 
	 * @param url
	 *            - The url is a string representing the address to the web page
	 *            from which the extraction of links will be made
	 * @param model
	 *            - The model is used to bind the summarized data of the links to
	 *            the UI as an attribute called "pageSummary"
	 * @return - The postIndexPage method also returns an HTML page as its response
	 * @throws IOException
	 *             - Since it is possible that the url being passed to the method is
	 *             malformed or references an unknown host/server, this method
	 *             throws the IOException which is handled in the
	 *             RestResponseEntityExceptionHandler Class
	 */
	@RequestMapping(value = { "/index" }, method = RequestMethod.POST)
	public String postIndexPage(@RequestParam(value = "url") String url, Model model) throws IOException {

		log.info("POST Request to Index.html with url : {}", url);

		/**
		 * The SummarizeLinksUtil Class contains a static method that takes a list of
		 * links and returns a summary of the unique links and their corresponding
		 * frequencies of appearance on the given url or web page
		 */
		model.addAttribute("pageSummary", SummarizeLinksUtil.getSummary(url, scrapingService));

		log.info("Rendering Page");

		return "index";
	}
}
