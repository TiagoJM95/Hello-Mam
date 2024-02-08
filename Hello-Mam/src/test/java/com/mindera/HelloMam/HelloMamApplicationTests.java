
package com.mindera.HelloMam;

import com.mindera.HelloMam.repositories.MediaRepository;
import com.mindera.HelloMam.repositories.RatingRepository;
import com.mindera.HelloMam.repositories.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;



@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Import(RedisTestConfiguration.class)
class HelloMamApplicationTests {

	@Autowired
	private MockMvc mockMvc;

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


	@Test
	void contextLoads() {
	}

	@Test
	@DisplayName("Test if the media list is empty")
	void testMediaListIsEmpty() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/media/")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("[]")));
	}

	@Test
	@DisplayName("Test if the rating list is empty")
	void testRatingListIsEmpty() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/rating/")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("[]")));
	}

	@Test
	@DisplayName("Test if the user list is empty")
	void testUserListIsEmpty() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("[]")));
	}







}

