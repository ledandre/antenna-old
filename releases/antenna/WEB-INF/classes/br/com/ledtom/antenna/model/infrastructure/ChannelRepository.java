package br.com.ledtom.antenna.model.infrastructure;

import java.util.List;

import br.com.caelum.vraptor.http.route.ResourceNotFoundException;
import br.com.ledtom.antenna.model.entity.Channel;

public interface ChannelRepository {
	public Channel find(Long id) throws ResourceNotFoundException;
	
	public List<Channel> list();
	
	public Channel save(Channel channel);
	
	public void delete(Channel channel);
}
