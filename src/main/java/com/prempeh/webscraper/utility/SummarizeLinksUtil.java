package com.prempeh.webscraper.utility;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.prempeh.webscraper.service.ScrapingService;

import lombok.extern.slf4j.Slf4j;

/**
 * This Class uses streams and lambdas to process the links returned by the
 * ScrapingService, thereby removing empty links and grouping them according to
 * their host names
 * 
 * @author Prince Prempeh Gyan
 * @version 1.0 <br/>
 *          Date: 09/09/2017
 *
 */
@Slf4j
public class SummarizeLinksUtil {

	public static Map<String, Long> getSummary(String url, ScrapingService scrapingService) throws IOException {

		List<String> linksOnPage = scrapingService.getAllLinksOnThisPage(url);

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
				.filter(link -> (link != null && link != ""))
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		log.info("Returning results of URLs and their corresponding frequencies of appearnace on {}", url);
		return SummaryOfLinksOnPage;
	}

}
