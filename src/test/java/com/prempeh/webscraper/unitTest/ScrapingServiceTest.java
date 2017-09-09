package com.prempeh.webscraper.unitTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import org.junit.Test;

import com.prempeh.webscraper.service.ScrapingService;


public class ScrapingServiceTest {

	private ScrapingService scrapingService = new ScrapingService();

	@Test
	public final void testGetAllLinksOnThisPage() throws IOException {

		String url = "http://www.google.com";

		List<String> extractedLinksFromPage = scrapingService.getAllLinksOnThisPage(url);

		assertTrue(extractedLinksFromPage.size() > 0);

	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void invalidURLTest() throws IOException {

		String url = "someWrongInput";

		List<String> extractedLinksFromPage = scrapingService.getAllLinksOnThisPage(url);

	}

	@Test(expected = IllegalArgumentException.class)
	public void noURL() throws IOException {

		String url = "";

		@SuppressWarnings("unused")
		List<String> extractedLinksFromPage = scrapingService.getAllLinksOnThisPage(url);

	}

	@Test(expected = UnknownHostException.class)
	public void nonexistenceHostName() throws IOException {

		String url = "http://somethingThatDoesNotExist";

		@SuppressWarnings("unused")
		List<String> extractedLinksFromPage = scrapingService.getAllLinksOnThisPage(url);

	}

}
