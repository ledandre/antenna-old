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
	@Path("/schedules/{channelId}")
	public void list(Long channelId) {
		result.include("schedules", service.list(channelId));
		result.include("channel", service.getChannel(channelId));
		result.forwardTo(this).form();
	}
	
	@Get @Restricted
	@Path("/schedules/form")
	public void form(){}
	
	@Get @Restricted
	@Path("/schedules/edit/{schedule.id}")
	public void form(Schedule schedule) {
		schedule = service.find(schedule.getId());
		result.include("schedule", schedule);
		result.include("channel", schedule.getChannel());
		result.forwardTo(this).form();
	}
	
	@Post @Restricted
	@Path("/schedules")
	public void create(Schedule schedule) {
		service.save(schedule);
		result.redirectTo(this).list(schedule.getChannel().getId());
	}
	
	@Put @Restricted
	@Path("/schedules")
	public void edit(Schedule schedule) {
		service.save(schedule);
		result.redirectTo(this).list(schedule.getChannel().getId());
	}
	
	@Delete @Restricted
	@Path("/schedules/{schedule.id}")
	public void delete(Schedule schedule) {
		schedule = service.find(schedule.getId());
		long channelId = schedule.getChannel().getId();
		service.delete(schedule);
		result.redirectTo(this).list(channelId);
	}
}