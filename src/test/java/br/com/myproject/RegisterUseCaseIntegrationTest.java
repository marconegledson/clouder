package br.com.myproject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.myproject.domain.User;
import br.com.myproject.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class RegisterUseCaseIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private UserRepository userRepository;

	@Test
	@Order(1)   
	void postTest() throws Exception {
		User user = new User(null, "Marcone", "marconegledson@gmail.com", "dfasdfsd@adf)*");

		mockMvc.perform(
				post("/api/user/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user)))
				.andExpect(status().isCreated());

		mockMvc.perform(
				get("/api/user/search")
				.param("name", "Marcone")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
	        	.andExpect(content().contentType(MediaType.APPLICATION_JSON))
	        	.andExpect(jsonPath("$.length()", is(1)))
	            .andExpect(jsonPath("$[0].name", is("Marcone")));
				
	}
	
	@Test
	@Order(2)  
	void putTest() throws Exception {
		User user = new User(null, "Joao", "addii@gmail.com", "dfasdfsd@adf)*");

		mockMvc.perform(
				post("/api/user/")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(user)))
				.andExpect(status().isCreated());

		User entity = userRepository.findByName("Joao").stream().findFirst().get();
		assertThat(entity.getName()).isEqualTo("Joao");
		entity.setName("Vitor");
		
		mockMvc.perform(
				put("/api/user/{id}", entity.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(entity)))
				.andExpect(status().isOk())
	        	.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		
		mockMvc.perform(
				get("/api/user/search")
				.param("name", "Vitor")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
	        	.andExpect(content().contentType(MediaType.APPLICATION_JSON))
	        	.andExpect(jsonPath("$", hasSize(1)))
	            .andExpect(jsonPath("$[0].name", is("Vitor")));
		
	}
	
	@Test
	@Order(3)  
	void deleteTest() throws Exception {
		User entity = userRepository.findByName("Vitor").stream().findFirst().get();
		
		mockMvc.perform(
				get("/api/user/{id}", entity.getId())
				.contentType("application/json"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is(entity.getName())))
	        	.andExpect(content().contentType(MediaType.APPLICATION_JSON));

		mockMvc.perform(
				delete("/api/user/{id}", entity.getId())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
		mockMvc.perform(
				get("/api/user/{id}", entity.getId())
				.contentType("application/json"))
				.andExpect(status().isNotFound())
	        	.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		
	}

}
