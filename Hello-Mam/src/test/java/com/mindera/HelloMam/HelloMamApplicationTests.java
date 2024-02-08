package com.mindera.HelloMam;

import com.mindera.HelloMam.repositories.MediaRepository;
import com.mindera.HelloMam.repositories.RatingRepository;
import com.mindera.HelloMam.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.mockito.Mockito;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Import(RedisTestConfiguration.class)
@Transactional
public class HelloMamApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${external.api.baseurl}")
	private String externalApiBaseUrl;

	@MockBean
	private MediaRepository mediaRepository;

	@MockBean
	private RatingRepository ratingRepository;

	@MockBean
	private UserRepository userRepository;

	@BeforeEach
	void setUp() {
		Mockito.when(mediaRepository.findAll()).thenReturn(new ArrayList<>());
		Mockito.when(ratingRepository.findAll()).thenReturn(new ArrayList<>());
		Mockito.when(userRepository.findAll()).thenReturn(new ArrayList<>());
	}

//	@AfterEach
//	void tearDown() {
//		Mockito.reset(mediaRepository, ratingRepository, userRepository);
//	}

	@Test
	void contextLoads() {
	}

	@Test
	@DisplayName("Test if the media list is empty")
	void testMediaListIsEmpty() throws Exception {
		mockMvc.perform(get("/api/v1/media/")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("[]")));
	}

	@Test
	@DisplayName("Test if the rating list is empty")
	void testRatingListIsEmpty() throws Exception {
		mockMvc.perform(get("/api/v1/rating/")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("[]")));
	}

	@Test
	@DisplayName("Test if the user list is empty")
	void testUserListIsEmpty() throws Exception {
		mockMvc.perform(get("/api/v1/user/")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("[]")));
	}


	@Test
	@DisplayName("Test Movie External API Connection")
	void testGetMoviesIntegration() {
		String url = externalApiBaseUrl + "movies/";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		// Assertions based on the expected response
		assertEquals(HttpStatus.OK, response.getStatusCode());
		// Further assertions on the response body, if necessary
	}

	@Test
	@DisplayName("Test Game External API Connection")
	void testGetGamesIntegration() {
		String url = externalApiBaseUrl + "videogames/";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		// Assertions based on the expected response
		assertEquals(HttpStatus.OK, response.getStatusCode());
		// Further assertions on the response body, if necessary
	}

	@Test
	@DisplayName("Test Get Top Games External API Connection")
	void testgetTopGamesIntegration() {
		String url = externalApiBaseUrl + "videogames/top";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		// Assertions based on the expected response
		assertEquals(HttpStatus.OK, response.getStatusCode());
		// Further assertions on the response body, if necessary
	}

	@Test
	@DisplayName("Test Get Top Movies External API Connection")
	void testGetTopMoviesIntegration() {
		String url = externalApiBaseUrl + "movies/top";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		// Assertions based on the expected response
		assertEquals(HttpStatus.OK, response.getStatusCode());
		// Further assertions on the response body, if necessary
	}

	@Test
	@DisplayName("Test create user")
	void testCreateUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\": \"Test User\", \"username\": \"testuser\", \"email\": \"test@test.com\", \"dateOfBirth\": \"1990-01-01\", \"interests\": []}, \"ratings\": [], \"active\": true"))
				.andExpect(status().isOk());
	}




}
