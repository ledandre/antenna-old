package br.com.ledtom.antenna.core.security;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.ledtom.antenna.domain.controller.LoginController;
import br.com.ledtom.antenna.model.security.Restricted;
import br.com.ledtom.antenna.sessioncomponents.UserSession;

@Intercepts
public class LoginInterceptor implements Interceptor {
	private Result result;
	private UserSession userSession;
	
	public LoginInterceptor(Result result, UserSession userSession) {
		this.result = result;
		this.userSession = userSession;
	}
	
	public boolean accepts(ResourceMethod method) {
		return (method.getMethod().isAnnotationPresent(Restricted.class) ||
				method.getResource().getType().isAnnotationPresent(Restricted.class));
	}
	
	public void intercept(InterceptorStack stack, ResourceMethod method,
			Object resourceInstance) throws InterceptionException {
		if(userSession.isLogged()){
			stack.next(method, resourceInstance);
		}else{
			result.redirectTo(LoginController.class).login();
		}
	}
}