package com.prempeh.webscraper.service;

import java.io.IOException;
import java.util.Map;
/**
 * This is the WebScrapingService interface defining the action required to retrieve a summary of the links on a web page
 * 
 * @author Prince Prempeh Gyan
 * @version 1.0 <br/>
 *          Date: 19/10/2017
 *
 */
public interface WebScrapingService {

	Map<String, Long> getSummaryOfLinksOnPage(String url) throws IOException;
}
