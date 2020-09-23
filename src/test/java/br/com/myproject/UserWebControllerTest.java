package br.com.myproject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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

      List<User> entities = service.getByName("Marcone Almeida");
      assertThat(entities.get(0).getEmail()).isEqualTo("marconegledson@gmail.com");
    }
    
    
    @Test
    public void getAll()throws Exception {
    	/*User user = new User();
    	user.setName("Marcone");
    	
    	List<User> allEmployees = Arrays.asList(user);
        given(service.getAll()).willReturn(allEmployees);*/
     
    	mockMvc.perform(get("/api/user/")
        	      .contentType(MediaType.APPLICATION_JSON))
        	      .andExpect(status().isOk())
        	      .andExpect(content()
        	      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        	      ;
        	      //.andExpect(jsonPath("$[0].name", is("bob")));
    }

}
