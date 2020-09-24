package br.com.myproject.service;

import java.text.MessageFormat;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.myproject.domain.User;
import br.com.myproject.repository.UserRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User findOne(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("id {0} not found", id)));
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public User update(Long id, User user) {
		User entity =  findOne(id);
		BeanUtils.copyProperties(user, entity, "id");
		return save(entity);
	}
	
	public void delete(Long id) {
		userRepository.deleteById(id);
	}
	
	public void delete(User user) {
		userRepository.delete(user);
	}

	public List<User> findByName(String name) {
		return userRepository.findByName(name);
	}


}
