package br.com.ledtom.antenna.domain.service;

import java.util.List;

import br.com.ledtom.antenna.model.entity.Channel;
import br.com.ledtom.antenna.model.infrastructure.ChannelRepository;
import br.com.ledtom.antenna.model.infrastructure.dao.ChannelDAO;

public class ChannelService {
	private ChannelRepository repository;
	
	public ChannelService() {
		repository = new ChannelDAO();
	}
	
	public Channel find(long id){
		return repository.find(id);
	}
	
	public List<Channel> list() {
		return repository.list();
	}
	
	public Channel save(Channel channel) {
		return repository.save(channel);
	}
	
	public void delete(Channel channel) {
		repository.delete(channel);
	}
}
