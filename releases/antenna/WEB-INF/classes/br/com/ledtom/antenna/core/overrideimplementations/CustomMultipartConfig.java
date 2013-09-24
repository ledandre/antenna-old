package br.com.ledtom.antenna.core.overrideimplementations;

import br.com.caelum.vraptor.interceptor.multipart.DefaultMultipartConfig;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.ledtom.antenna.configuration.Config;

@Component
@ApplicationScoped
public class CustomMultipartConfig extends DefaultMultipartConfig {
	
    public long getSizeLimit() {
        return Config.getVideoSizeLimit() * 1024 * 1024;
    }
}