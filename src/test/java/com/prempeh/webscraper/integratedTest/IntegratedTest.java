package com.prempeh.webscraper.integratedTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Map;

import org.springframework.test.context.junit4.SpringRunner;

import com.prempeh.webscraper.config.WebScraperApplication;
import com.prempeh.webscraper.service.ScrapingService;
import com.prempeh.webscraper.utility.SummarizeLinksUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WebScraperApplication.class})
public class IntegratedTest {

	@Autowired
	ScrapingService scrapingService;

	String url = "http://www.google.com";

	@Test
	public void contextLoads() throws IOException {

		Map<String, Long> summary = SummarizeLinksUtil.getSummary(url, scrapingService);

		assertTrue(summary.size() > 0);

	}

}
