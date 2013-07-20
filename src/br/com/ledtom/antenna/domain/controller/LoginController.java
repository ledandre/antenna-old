package br.com.ledtom.antenna.domain.controller;

import lombok.AllArgsConstructor;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.ledtom.antenna.core.security.Cryptography;
import br.com.ledtom.antenna.domain.service.LoginService;
import br.com.ledtom.antenna.model.security.InvalidLoginDataException;
import br.com.ledtom.antenna.model.security.User;
import br.com.ledtom.antenna.sessioncomponents.ApplicationInfo;
import br.com.ledtom.antenna.sessioncomponents.UserSession;

@Resource
@AllArgsConstructor
public class LoginController {
	private final Result result;
	private final ApplicationInfo appInfo;
	private final LoginService service;
	private final UserSession userSession;
	
	@Get
	@Path("/")
	public void login() {}
	
	@Post
	@Path("/")
	public void login(User user) {
		try {
			userSession.setUser(service.doLogin(user.getUsername(), user.getPassword()));
		} catch (InvalidLoginDataException e) {
			result.include("error", e.getMessage());
		}
		
		result.forwardTo(ChannelController.class).list();
	}
	
	@Get
	@Path("/users")
	public void list() {
		result.include("users", service.list());
	}
	
	@Get
	@Path("/users/form")
	public void form() {}
	
	@Post
	@Path("/users")
	public void create(User user) {
		String encryptPassword = Cryptography.codify(user.getPassword());
		user.setPassword(encryptPassword);
		
		service.save(user);
		result.redirectTo(this).list();
	}
}
