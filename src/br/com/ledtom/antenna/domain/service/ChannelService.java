package br.com.ledtom.antenna.domain.service;

import java.util.List;

import br.com.ledtom.antenna.model.entity.Channel;
import br.com.ledtom.antenna.model.infrastructure.ChannelRepository;

public class ChannelService {
	private ChannelRepository repository;
	
	public List<Channel> list() {
		return repository.list();
	}
	
	public Channel save(Channel channel) {
		return repository.save(channel);
	}
}
