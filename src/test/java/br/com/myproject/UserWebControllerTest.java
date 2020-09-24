package br.com.myproject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.myproject.domain.User;
import br.com.myproject.service.UserService;
import br.com.myproject.web.UserWebController;

@RunWith(SpringRunner.class)
@WebMvcTest(UserWebController.class)
public class UserWebControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
 
    @MockBean
    private UserService service;
    
    @Autowired
    private ObjectMapper objectMapper;

    
    @Test
    void registrationWorksThroughAllLayers() throws Exception {
    	User user = new User();
		user.setName("Marcone Almeida");
		user.setEmail("marconegledson@gmail.com");
		user.setPassword("didjijdidi");
      

      mockMvc.perform(post("/api/user/")
              .contentType("application/json")
              .content(objectMapper.writeValueAsString(user)))
              .andExpect(status().isCreated());

      List<User> entities = service.findByName("Marcone Almeida");
      assertThat(entities.get(0).getEmail()).isEqualTo("marconegledson@gmail.com");
    }
    
   

}
