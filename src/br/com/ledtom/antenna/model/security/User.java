package br.com.ledtom.antenna.model.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
public class User {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Long id;
	
	@Column(name = "name")
	@Getter @Setter private String name;
	
	@Column(name = "username", unique = true)
	@Getter @Setter private String username;
	
	@Column(name = "password")
	@Getter @Setter private String password;
	
}
