package br.com.ledtom.antenna.domain.service;

import java.util.List;

import br.com.ledtom.antenna.core.security.Cryptography;
import br.com.ledtom.antenna.model.infrastructure.UserRepository;
import br.com.ledtom.antenna.model.infrastructure.dao.UserDAO;
import br.com.ledtom.antenna.model.security.InvalidLoginDataException;
import br.com.ledtom.antenna.model.security.User;

public class LoginService {
	private UserRepository repository;
	
	public LoginService() {
		repository = new UserDAO();
	}
	
	public User find(long id) {
		return repository.find(id);
	}
	
	public User doLogin(String username, String password) throws InvalidLoginDataException {
		User loggedUser = repository.validateLogin(username, Cryptography.codify(password));
		
		if (loggedUser != null)
			loggedUser.setPassword(null);
		
		return loggedUser;
	}
	
	public List<User> list() {
		return repository.list();
	}
	
	public User save(User user) {
		return repository.save(user);
	}
	
	public void delete(User user) {
		repository.delete(user);
	}
}
