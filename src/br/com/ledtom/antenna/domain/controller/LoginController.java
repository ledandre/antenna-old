package br.com.ledtom.antenna.domain.controller;

import lombok.AllArgsConstructor;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.ledtom.antenna.configuration.Config;
import br.com.ledtom.antenna.core.security.Cryptography;
import br.com.ledtom.antenna.domain.service.LoginService;
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
		userSession.setUser(service.doLogin(user.getUsername(), user.getPassword()));

		if (userSession.getUser() == null) {
			result.include("error", Config.getLoginErrorMessage());
			result.forwardTo(this).login();
		} else {
			result.redirectTo(ChannelController.class).list();
		}
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
	
	@Delete
	@Path("/users/{user.id}")
	public void delete(User user) {
		service.delete(service.find(user.getId()));
		result.redirectTo(this).list();
	}
}
