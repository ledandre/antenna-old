package br.com.ledtom.antenna.model.infrastructure;

import java.util.List;

import br.com.caelum.vraptor.http.route.ResourceNotFoundException;
import br.com.ledtom.antenna.model.entity.Channel;
import br.com.ledtom.antenna.model.entity.Schedule;

public interface ScheduleRepository {
	public Schedule find(Long id) throws ResourceNotFoundException;
	
	public List<Schedule> list();
	
	public List<Schedule> list(Channel channel);
	
	public Schedule save(Schedule schedule);
	
	public void delete(Schedule schedule);
	
	public void removeVideoLists(Schedule schedule);
}
