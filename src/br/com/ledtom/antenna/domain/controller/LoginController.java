package br.com.ledtom.antenna.domain.controller;

import lombok.AllArgsConstructor;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.blank.IndexController;
import br.com.ledtom.antenna.model.entity.User;
import br.com.ledtom.antenna.modules.security.Restricted;
import br.com.ledtom.antenna.modules.security.UserSession;
/**
 * 
 * Controls the users log on the system by validating their usernames and passwords and getting login and logout actions
 * @author Eduardo Andre
 * @author Leandro Andre
 *
 */
@Resource @AllArgsConstructor
public class LoginController {
	private final Result result;
	private UserSession userSession;
	
	@Get("/login")
	public void login() {
		//result.include("appInfo", applicationInfo);
	}
	
	@Post("/login")
	public void login(User user) {
		User validatedUser = User.doLogin(user.getUsername(), user.getPassword());
		
		if(validatedUser != null) {
			userSession.setUser(validatedUser);
			result.redirectTo(IndexController.class).index();
		} else {
			result.include("Erro", "Nome de usuário ou senha incorretos.").redirectTo(this).login();
		}
	}
	
	@Restricted
	@Get("/logout")
	public void logout() {
		userSession.logout();
		result.redirectTo(this).login();
	}
}