package br.com.myproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.myproject.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	@Query("Select u From User u Where u.name = :name")
	List<User> findByName(String name);

}
