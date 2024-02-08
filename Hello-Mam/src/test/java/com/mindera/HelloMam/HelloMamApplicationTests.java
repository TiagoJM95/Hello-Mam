package com.mindera.HelloMam;

import com.mindera.HelloMam.entities.Media;
import com.mindera.HelloMam.entities.Rating;
import com.mindera.HelloMam.entities.User;
import com.mindera.HelloMam.externals.models.ExternalGame;
import com.mindera.HelloMam.repositories.MediaRepository;
import com.mindera.HelloMam.repositories.RatingRepository;
import com.mindera.HelloMam.repositories.UserRepository;
import com.mindera.HelloMam.services.implementations.ExternalGameServiceImpl;
import com.mindera.HelloMam.services.implementations.ExternalMovieServiceImpl;
import com.mindera.HelloMam.services.implementations.MediaServiceImpl;
import com.mindera.HelloMam.services.implementations.UserServiceImpl;
import com.mindera.HelloMam.services.interfaces.MediaService;
import com.mindera.HelloMam.services.interfaces.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static com.mindera.HelloMam.enums.MediaType.MOVIE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.testcontainers.containers.MySQLContainer;
import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Testcontainers
@Import(RedisTestConfiguration.class)
@Transactional
public class HelloMamApplicationTests {

	@Container
	public static MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:8.0.26")
			.withDatabaseName("test")
			.withUsername("test")
			.withPassword("test");

	static {
		mysqlContainer.start();
	}

	@DynamicPropertySource
	static void mysqlProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
		registry.add("spring.datasource.username", mysqlContainer::getUsername);
		registry.add("spring.datasource.password", mysqlContainer::getPassword);
		registry.add("spring.datasource.driver-class-name", () -> "com.mysql.cj.jdbc.Driver");
	}

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MediaRepository mediaRepository;

	@MockBean
	private RatingRepository ratingRepository;

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private ExternalGameServiceImpl externalGameServiceImpl;

	@MockBean
	private ExternalMovieServiceImpl externalMovieServiceImpl;


	@BeforeEach
	void setUp() {

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
	@DisplayName("Test create user and that it returns the correct user data")
	void testCreateUser() throws Exception {
		User user = new User();
		user.setId(1L);
		user.setUsername("testuser");
		user.setName("Test User");
		user.setEmail("test@test.com");
		user.setDateOfBirth(LocalDate.parse("1990-01-01"));

		Mockito.when(userRepository.save(any(User.class))).thenReturn(user);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"username\": \"testuser\", \"name\": \"Test User\", \"email\": \"test@test.com\", \"dateOfBirth\": \"1990-01-01\"}"))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.username", is("testuser")))
				.andExpect(jsonPath("$.name", is("Test User")))
				.andExpect(jsonPath("$.email", is("test@test.com")))
				.andExpect(jsonPath("$.dateOfBirth", is("1990-01-01")));

		Mockito.verify(userRepository, times(1)).save(any(User.class));
	}



	//External API Connection Tests
	@Test
	@DisplayName("Test Movie External API Connection")
	void testGetMoviesIntegration() throws Exception {
		mockMvc.perform(get("/api/v1/movies/")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("Test Game External API Connection")
	void testGetGamesIntegration() throws Exception {
		mockMvc.perform(get("/api/v1/games/")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("Test Get Top Games External API Connection")
	void testGetTopGamesIntegration() throws Exception {
		mockMvc.perform(get("/api/v1/games/top/")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}



	@Test
	@DisplayName("Test Get Top Movies External API Connection")
	void testGetTopMoviesIntegration() throws Exception {
		mockMvc.perform(get("/api/v1/movies/top/")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("Test Get Movie by ID External API Connection")
	void testGetMovieByIdIntegration() throws Exception {
		mockMvc.perform(get("/api/v1/movies/id/679/")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("Test Get Game by ID External API Connection")
	void testGetGameByIdIntegration() throws Exception {
		ExternalGame game = new ExternalGame();
		int id = 256090;

		Mockito.when(externalGameServiceImpl.getVideogameById(id)).thenReturn(game);

		mockMvc.perform(get("/api/v1/games/id/{id}", 256090)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}


}
