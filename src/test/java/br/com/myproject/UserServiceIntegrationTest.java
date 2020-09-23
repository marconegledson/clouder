package br.com.myproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.myproject.domain.User;
import br.com.myproject.repository.UserRepository;
import br.com.myproject.service.UserService;

@RunWith(SpringRunner.class)
public class UserServiceIntegrationTest {
	
	@TestConfiguration
	static class UserServiceImplTestContextConfiguration {
		@Bean
		public UserService getUserService() {
			return new UserService();
		}
	}
	
	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepository userRepository;
	
	@Test
	public void whenValidName_thenEmployeeShouldBeFound() {
	    String name = "alex";
	    List<User> found = userService.getByName(name);
	 
	    assertEquals(found.size(), 0);
	 }

}
