package br.com.ledtom.antenna.domain.controller;

import lombok.AllArgsConstructor;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.ledtom.antenna.core.security.Restricted;
import br.com.ledtom.antenna.domain.service.ScheduleService;
import br.com.ledtom.antenna.model.entity.Schedule;
import br.com.ledtom.antenna.sessioncomponents.ApplicationInfo;

@Resource
@AllArgsConstructor
public class ScheduleController {
	private final Result result;
	private final ScheduleService service;
	private final ApplicationInfo applicationInfo;
	
	@Get @Restricted
	@Path("/schedules")
	public void list(){
		result.include("schedules", service.list());
	}
	
	@Get @Restricted
	@Path("/schedules/form")
	public void form(){}
	
	@Get @Restricted
	@Path("/schedules/edit/{schedule.id}")
	public void form(Schedule schedule){
		result.include("schedule", service.find(schedule.getId()));
		result.forwardTo(this).form();
	}
	
	@Post @Restricted
	@Path("/schedules")
	public void create(Schedule schedule){
		service.save(schedule);
		result.redirectTo(this).list();
	}
	
	@Put @Restricted
	@Path("/schedules")
	public void edit(Schedule schedule){
		service.save(schedule);
		result.redirectTo(this).list();
	}
	
	@Delete @Restricted
	@Path("/schedules/{schedule.id}")
	public void delete(Schedule schedule){
		service.delete(service.find(schedule.getId()));
		result.redirectTo(this).list();
	}
}