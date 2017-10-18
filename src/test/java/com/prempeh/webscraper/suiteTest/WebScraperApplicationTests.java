package com.prempeh.webscraper.suiteTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.prempeh.webscraper.unitTest.RestControllerTest;
import com.prempeh.webscraper.unitTest.ScrapingServiceTest;
import com.prempeh.webscraper.unitTest.SummarizeLinksUtilTest;

@RunWith(Suite.class)
@SuiteClasses({ SummarizeLinksUtilTest.class, ScrapingServiceTest.class, RestControllerTest.class })
public class WebScraperApplicationTests {

}
