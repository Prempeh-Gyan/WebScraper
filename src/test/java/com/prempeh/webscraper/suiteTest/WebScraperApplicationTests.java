package com.prempeh.webscraper.suiteTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.prempeh.webscraper.unitTest.RestControllerTest;
import com.prempeh.webscraper.unitTest.WebScrapingServiceTest;

@RunWith(Suite.class)
@SuiteClasses({ WebScrapingServiceTest.class, RestControllerTest.class })
public class WebScraperApplicationTests {

}
