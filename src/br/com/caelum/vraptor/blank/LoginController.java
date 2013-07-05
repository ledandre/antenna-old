package br.com.caelum.vraptor.blank;

import lombok.AllArgsConstructor;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
@AllArgsConstructor
public class LoginController {
	private final Result result;
	
	public LoginController(Result result) {
		this.result = result;
	}
	
	@Path("/")
	public void login() {}
}
