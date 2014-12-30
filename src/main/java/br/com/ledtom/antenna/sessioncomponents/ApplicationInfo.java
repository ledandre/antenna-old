package br.com.ledtom.antenna.sessioncomponents;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.ledtom.antenna.configuration.Config;

@Component
@SessionScoped
public class ApplicationInfo {
	private String name;
	private String version;

	public ApplicationInfo(String name, String version) {
		this.name = Config.getApplicationName();
		this.version = Config.getApplicationVersion();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
