package br.com.myproject.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter @Setter @NoArgsConstructor
public class User {
	
	@Id
	@Column(name = "id", nullable = false)
	@SequenceGenerator(sequenceName = "user_sequence", name = "user")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
	private Long id;
	
	@NotBlank
	@Column(name = "name", nullable = false)
	private String name;
	
	@NotBlank
	@Column(name = "email", nullable = false)
	private String email;
	
	@NotBlank
	@Column(name = "password", nullable = false)
	private String password;
	

}
