package br.com.myproject;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.myproject.domain.User;
import br.com.myproject.repository.UserRepository;

@DataJpaTest
@RunWith(SpringRunner.class)
public class UserRepositoryIntegrationTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void testSave() {
		User user = new User();
		user.setName("Marcone Almeida");
		user.setEmail("marconegledson@gmail.com");
		user.setPassword("didjijdidi");
		
		user = userRepository.save(user);
		
		assertNotNull(user.getId());
	}

}
