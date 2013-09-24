package br.com.ledtom.antenna.sessioncomponents;

import lombok.Getter;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.ledtom.antenna.configuration.Config;

@Component
@SessionScoped
public class ApplicationInfo {
	@Getter private String name;
	@Getter	private String version;
	
	public ApplicationInfo(String name, String version) {
		this.name = Config.getApplicationName();
		this.version = Config.getApplicationVersion();
	}
}
