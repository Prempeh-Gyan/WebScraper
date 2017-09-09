package com.prempeh.webscraper.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
/**
 * This ScrapingService Class takes a url, uses Jsoup to connect to the page,
 * extract links from the page and return a list of all the links on the page
 * 
 * @author Prince Prempeh Gyan
 * @version 1.0 <br/>
 *          Date: 09/09/2017
 *
 */
@Service
@Data
@Slf4j
public class ScrapingService {

	/**
	 * @param url
	 *            - The String representing the url of the web page to be accessed
	 * @return - Returns a list of strings representing the links from a particular
	 *         web page
	 * @throws IOException
	 *             - Throws an exception if the url is malformed or the host is
	 *             unknown
	 */
	public List<String> getAllLinksOnThisPage(String url) throws IOException {

		List<String> linksExtractedOnPage = new ArrayList<>();

		log.info("Jsoup is connecting to : {}", url);

		/**
		 * When Jsoup connects to the url, it parses the resource as an HTML Document
		 * and saves it into the "webPage" variable for use
		 */
		Document webPage = Jsoup.connect(url).get();

		log.info("Extracting anchor tags from {}", url);

		/**
		 * The Elements map to actual elements in the HTML document saved in the
		 * "webPage" variable. By calling the "select" method on the "webPage" variable,
		 * the links on the page that appear in the anchor tag can be extracted by
		 * passing "a[href]" as an argument to method "select". The result is a list of
		 * anchor tag elements saved in the "linksOnPage" variable
		 */
		Elements linksOnPage = webPage.select("a[href]");

		/**
		 * Once the elements have been extracted, they need to be processed into actual
		 * URIs. Java 8s streams and lambdas are used here to enhance performance. For
		 * the purposes of this application only the host names of the URIs are of
		 * interest at this level, the "schemes" and the "paths" are not necessary. All
		 * extracted host names are added to the "linksExtractedOnPage" variable.
		 * At this point the list may contain duplicate host names.
		 */
		linksOnPage.parallelStream().forEach(linkOnPage -> {

			try {

				URI uri = new URI(linkOnPage.attr("abs:href"));

				String link = uri.getHost();

				log.info("Tag <a href= '{}'>", uri);
				log.info("HostName = {}", link);

				linksExtractedOnPage.add(link);

			} catch (URISyntaxException e) {

				System.err.println("URISyntaxException : " + "url = " + linkOnPage.attr("abs:href") + "\nMessage = "
						+ e.getMessage());
				System.out.println("URISyntaxException : " + "url = " + linkOnPage.attr("abs:href") + "\nMessage = "
						+ e.getMessage());

			}
		});

		log.info("Returning list of HostNames to caller");

		return linksExtractedOnPage;
	}
}
