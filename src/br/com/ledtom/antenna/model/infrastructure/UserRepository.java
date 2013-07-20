package br.com.ledtom.antenna.model.infrastructure;

import java.util.List;

import br.com.caelum.vraptor.http.route.ResourceNotFoundException;
import br.com.ledtom.antenna.model.security.InvalidLoginDataException;
import br.com.ledtom.antenna.model.security.User;

public interface UserRepository {
	public User find(Long id) throws ResourceNotFoundException;
	
	public User validateLogin(String username, String password) throws InvalidLoginDataException;
	
	public List<User> list();
	
	public User save(User user);
	
	public void delete(User user);
}
