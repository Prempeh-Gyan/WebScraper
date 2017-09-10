package com.prempeh.webscraper.unitTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.http.HttpStatus.OK;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.prempeh.webscraper.config.WebScraperApplication;
import com.prempeh.webscraper.controller.RestController;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RestController.class)
@ContextConfiguration(classes = WebScraperApplication.class)
public class RestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetRequests() throws IOException {

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("http://localhost:8080/summarizeLinksOnPage?url=http://www.google.com")
				.accept(MediaType.APPLICATION_JSON);

		try {

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();

			System.out.println(result.getResponse());

			assertThat(result.getResponse().getContentAsString(), (result.getResponse().getContentAsString() != null
					&& !result.getResponse().getContentAsString().isEmpty()));

			assertThat(result.getResponse().getStatus(), is(equalTo(OK.value())));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testPostRequest() throws IOException {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("http://localhost:8080/summarizeLinksOnPage")
				.param("url", "http://www.google.com").accept(MediaType.APPLICATION_JSON);

		try {

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();

			System.out.println(result.getResponse());

			assertThat(result.getResponse().getContentAsString(), (result.getResponse().getContentAsString() != null
					&& !result.getResponse().getContentAsString().isEmpty()));

			assertThat(result.getResponse().getStatus(), is(equalTo(OK.value())));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
