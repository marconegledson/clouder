package br.com.myproject.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.myproject.domain.User;
import br.com.myproject.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserWebController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public List<User> getAll(){
		return userService.getAll();
	}
	
	@GetMapping("/{id}")
	public User getOne(@PathVariable(required = true) Long id){
		return userService.getOne(id);
	}
	
	@GetMapping("/search")
	public List<User> getByName(@RequestParam(value = "name") String name){
		return userService.getByName(name);
	}
	
	@PostMapping("/")
	public User save(@Valid @RequestBody User user){
		return userService.save(user);
	}
	
	@PutMapping("/{id}")
	public User update(@PathVariable(required = true) Long id, @Valid @RequestBody User user) {
		return userService.update(id, user);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable(required = true) Long id) {
		userService.delete(id);
	}

}
