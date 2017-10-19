package com.prempeh.webscraper.serviceImpl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.prempeh.webscraper.service.WebScrapingService;

import lombok.extern.slf4j.Slf4j;
/**
 * This WebScrapingService Implementation takes a url, uses Jsoup to connect to the page,
 * extract links from the page and return a list of all the links on the page
 * 
 * @author Prince Prempeh Gyan
 * @version 1.1 <br/>
 *          Date: 19/10/2017
 *
 */
@Service
@Slf4j
public class WebScrapingServiceImpl implements WebScrapingService {

	@Override
	public Map<String, Long> getSummaryOfLinksOnPage(String url) throws IOException {
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
		 * extracted host names are added to the "linksExtractedOnPage" variable. At
		 * this point the list may contain duplicate host names.
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

		return getSummary(linksExtractedOnPage);
	}

	private Map<String, Long> getSummary(List<String> linksOnPage) {
		
		log.info("List of HostNames recieved");
		log.info("Removing empty HostNames from list");
		log.info("Grouping identical HostNames and counting");
		log.info("Creating a Map with unique HostNames as key and their frequencies as values");

		/**
		 * Since Maps have unique keys, while the stream is being processed the filtered
		 * host names which have no empty or null elements are grouped into a Map of Key
		 * Value pairs, where the host names are saved as Keys and their corresponding
		 * frequencies are saved as values. The result is then returned to the caller.
		 */

		Map<String, Long> SummaryOfLinksOnPage = linksOnPage.parallelStream()
				.filter(link -> (link != null && !link.isEmpty()))
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));		
		return SummaryOfLinksOnPage;
	}

}
