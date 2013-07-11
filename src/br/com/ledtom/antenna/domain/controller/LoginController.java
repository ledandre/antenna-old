package br.com.ledtom.antenna.domain.controller;

import lombok.AllArgsConstructor;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.ledtom.antenna.sessioncomponents.ApplicationInfo;

@Resource
@AllArgsConstructor
public class LoginController {
	private final Result result;
	private final ApplicationInfo appInfo;
	
	@Path("/")
	public void login() {}
}
