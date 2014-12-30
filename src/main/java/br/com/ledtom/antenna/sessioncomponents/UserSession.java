package br.com.ledtom.antenna.sessioncomponents;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.ledtom.antenna.model.security.User;
/**
 * Controls the activity of a user session in the system
 * @author Eduardo
 */

@Component
@SessionScoped
public class UserSession {
	private User user;
	
	public boolean isLogged() {
		return user != null;
	}
	
	public void logout() {
		user = null;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
