package com.prempeh.webscraper.integratedTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.HttpStatus.OK;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.prempeh.webscraper.config.WebScraperApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { WebScraperApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegratedTest {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void getRequestTest() {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/summarizeLinksOnPage?url=http://www.google.com"), HttpMethod.GET, entity,
				String.class);

		assertTrue(response.hasBody());
		assertTrue(!response.getBody().isEmpty());
		assertThat(response.getStatusCodeValue(), is(equalTo(OK.value())));
	}

	@Test
	public void postRequestTest() {

		HttpEntity<String> entity = new HttpEntity<String>("url", headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/summarizeLinksOnPage?url=http://www.google.com"), HttpMethod.POST, entity,
				String.class);

		assertTrue(response.hasBody());
		assertTrue(!response.getBody().isEmpty());
		assertThat(response.getStatusCodeValue(), is(equalTo(OK.value())));

	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
