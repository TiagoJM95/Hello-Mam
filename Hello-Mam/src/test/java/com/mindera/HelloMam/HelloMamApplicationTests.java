package com.mindera.HelloMam;

import com.mindera.HelloMam.repositories.MediaRepository;
import com.mindera.HelloMam.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class HelloMamApplicationTests {

	@Autowired
	private MockMvc mockMvc;

@Autowired
private UserRepository userRepository;
//
//	@Autowired
//	private RatingRepository ratingRepository;
//
@Autowired
private MediaRepository mediaRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@AfterEach
	void tearDown() {
		//reset database
		userRepository.deleteAll();
		mediaRepository.deleteAll();
		jdbcTemplate.execute("ALTER TABLE user AUTO_INCREMENT = 1");
		jdbcTemplate.execute("ALTER TABLE media AUTO_INCREMENT = 1");
	}


	@Test
	void contextLoads() {
	}

	@Test
	@DisplayName("Test to ascertain if a get to home returns a 200 OK status code")
	void getHomeReturns200Ok() throws Exception {
		this.mockMvc.perform(get("/")).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("Test to determine if a get to an empty User database returns an empty database")
	void getToUserEmptyDB() throws Exception {
		this.mockMvc.perform(get("/user/")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("[]")));
	}

	@Test
	@DisplayName("Test to determine the creation of a User and that it returns with id 1")
	void createUserAndCheckIdNumber() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/user/")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\":\"Mam\",\"age\":60,\"email\":\"mam@test.com\"}"))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.userId").value(1));
	}

	@Test
	@DisplayName("Test to determine the creation of a User and that it returns with all values correctly attributed")
	void createUserAndCheckValues() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/user/")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\":\"Mrs Mam\",\"username\":\"Mam\",\"email\":\"mam@test.com\",\"dateOfBirth\":\"1960-01-01\"}"))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.userId").value(1))
				.andExpect(jsonPath("$.name").value("Mrs Mam"))
				.andExpect(jsonPath("$.username").value("Mam"))
				.andExpect(jsonPath("$.email").value("mam@test.com"))
				.andExpect(jsonPath("$.dateOfBirth").value("1960-01-01"));
	}

	@Test
	@DisplayName("Test to determine if a get to an empty Rating database returns an empty database")
	public void getToRatingEmptyDB() throws Exception {
		this.mockMvc.perform(get("/api/v1/ratingsAPI/")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("[]")));
	}

	@Test
	@DisplayName("Test to determine the creation of a Rating and that it returns with id 1")
	public void createRatingAndCheckIdNumber() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/ratingsAPI")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"rating\":5,\"comment\":\"Mam is the best\",\"userId\":1}"))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.ratingId").value(1));
	}

	@Test
	@DisplayName("Test to determine the creation of a Rating and that it returns with all values correctly attributed")
	public void createRatingAndCheckValues() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/ratingsAPI")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"mediaId\":1,\"userId\":1,\"rating\":5}"))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.ratingId").value(1))
				.andExpect(jsonPath("$.rating").value(5))
				.andExpect(jsonPath("$.mediaId").value(1))
				.andExpect(jsonPath("$.userId").value(1));
	}

	@Test
	@DisplayName("Test to determine if a get to an empty Media database returns an empty database")
	public void getToMediaEmptyDB() throws Exception {
		this.mockMvc.perform(get("/api/v1/media/")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("[]")));
	}

	@Test
	@DisplayName("Test to determine the creation of a Media and that it returns with id 1")
	public void createMediaAndCheckIdNumber() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/media/")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"refId\":\"refIdTest\",\"type\":\"MOVIE\"}"))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1));
	}

	@Test
	@DisplayName("Test to determine the creation of a Media and that it returns with all values correctly attributed")
	public void createMediaAndCheckValues() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/media/")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"refId\":\"refIdTest\",\"type\":\"MOVIE\"}"))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.refId").value("refIdTest"))
				.andExpect(jsonPath("$.type").value("MOVIE"));
	}




}
