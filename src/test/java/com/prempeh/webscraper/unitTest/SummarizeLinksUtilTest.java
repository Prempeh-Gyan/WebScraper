package com.prempeh.webscraper.unitTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.prempeh.webscraper.service.ScrapingService;
import com.prempeh.webscraper.utility.SummarizeLinksUtil;
@RunWith(MockitoJUnitRunner.class)
public class SummarizeLinksUtilTest {

	@Mock
	ScrapingService scrapingService;

	String url = "someRandomDummyUrl";

	@InjectMocks
	SummarizeLinksUtil summarizeLinksUtil;

	@Test
	public final void testGetSummary() throws IOException {

		List<String> listOfLinks = new ArrayList<>();

		listOfLinks.add("www.a.com");
		listOfLinks.add("www.a.com");
		listOfLinks.add("www.a.com");
		listOfLinks.add("www.a.com");
		listOfLinks.add("www.a.com");
		listOfLinks.add("www.a.com");
		listOfLinks.add("www.a.com");
		listOfLinks.add("www.a.com");
		listOfLinks.add("www.b.com");
		listOfLinks.add("www.b.com");
		listOfLinks.add("www.c.com");
		listOfLinks.add("www.c.com");
		listOfLinks.add("www.d.com");
		listOfLinks.add("www.d.com");
		listOfLinks.add("www.e.com");
		listOfLinks.add("www.e.com");

		when(scrapingService.getAllLinksOnThisPage(url)).thenReturn(listOfLinks);

		Map<String, Long> getSummary = SummarizeLinksUtil.getSummary(url, scrapingService);
		
		getSummary.forEach((a, b) -> {
			if (b > 2) {
				getSummary.remove(a);
			}
		});

		assertTrue(getSummary.get("www.e.com") == 2);

		assertTrue(getSummary.size() == 5);

	}

}
