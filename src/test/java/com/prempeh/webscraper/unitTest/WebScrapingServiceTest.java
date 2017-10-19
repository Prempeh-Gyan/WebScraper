package com.prempeh.webscraper.unitTest;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;

import org.junit.Test;

import com.prempeh.webscraper.service.WebScrapingService;
import com.prempeh.webscraper.serviceImpl.WebScrapingServiceImpl;


public class WebScrapingServiceTest {


	private WebScrapingService webScrapingService = new WebScrapingServiceImpl();

	@Test
	public final void testGetAllLinksOnThisPage() throws IOException {

		String url = "http://www.google.com";

		Map<String, Long> extractedLinksFromPage = webScrapingService.getSummaryOfLinksOnPage(url);

		assertTrue(extractedLinksFromPage.size() > 0);

	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void invalidURLTest() throws IOException {

		String url = "someWrongInput";

		Map<String, Long> extractedLinksFromPage = webScrapingService.getSummaryOfLinksOnPage(url);

	}

	@Test(expected = IllegalArgumentException.class)
	public void noURL() throws IOException {

		String url = "";

		@SuppressWarnings("unused")
		Map<String, Long> extractedLinksFromPage = webScrapingService.getSummaryOfLinksOnPage(url);

	}

	@Test(expected = UnknownHostException.class)
	public void nonexistenceHostName() throws IOException {

		String url = "http://www.SomeUrlThatDoesNotExist";

		@SuppressWarnings("unused")
		Map<String, Long> extractedLinksFromPage = webScrapingService.getSummaryOfLinksOnPage(url);

	}
}
