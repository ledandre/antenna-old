package br.com.ledtom.antenna.domain.service;

import java.util.List;

import br.com.ledtom.antenna.model.entity.Channel;
import br.com.ledtom.antenna.model.entity.Schedule;
import br.com.ledtom.antenna.model.infrastructure.ChannelRepository;
import br.com.ledtom.antenna.model.infrastructure.ScheduleRepository;
import br.com.ledtom.antenna.model.infrastructure.dao.ChannelDAO;
import br.com.ledtom.antenna.model.infrastructure.dao.ScheduleDAO;

public class ScheduleService {
	private ScheduleRepository repository;
	private ChannelRepository channelRepository;

	public ScheduleService() {
		repository = new ScheduleDAO();
		channelRepository = new ChannelDAO();
	}
	
	public Schedule find(long id){
		return repository.find(id);
	}
	
	public List<Schedule> list() {
		return repository.list();
	}
	
	public List<Schedule> list(Long id) {
		return repository.list(channelRepository.find(id));
	}
	
	public Schedule save(Schedule schedule) {
		return repository.save(schedule);
	}
	
	public void delete(Schedule schedule) {
		repository.delete(schedule);
	}

	public Channel getChannel(Long id) {
		return channelRepository.find(id);
	}
}