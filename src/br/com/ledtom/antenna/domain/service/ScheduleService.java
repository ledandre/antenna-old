package br.com.ledtom.antenna.domain.service;

import java.util.List;

import br.com.ledtom.antenna.model.entity.Schedule;
import br.com.ledtom.antenna.model.infrastructure.ScheduleRepository;
import br.com.ledtom.antenna.model.infrastructure.dao.ScheduleDAO;

public class ScheduleService {
	private ScheduleRepository repository;
	
	public ScheduleService() {
		repository = new ScheduleDAO();
	}
	
	public Schedule find(long id){
		return repository.find(id);
	}
	
	public List<Schedule> list() {
		return repository.list();
	}
	
	public Schedule save(Schedule schedule) {
		return repository.save(schedule);
	}
	
	public void delete(Schedule schedule) {
		repository.delete(schedule);
	}
}