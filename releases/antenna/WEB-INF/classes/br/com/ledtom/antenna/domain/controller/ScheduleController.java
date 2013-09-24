package br.com.ledtom.antenna.domain.controller;

import java.util.List;

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
	@SuppressWarnings("unused")
	private final ApplicationInfo applicationInfo;
	
	@Get @Restricted
	@Path("/schedules/{channelId}")
	public void list(Long channelId) {
		List<Schedule> schedules = service.list(channelId);
		result.include("schedule", schedules == null || schedules.isEmpty() ? null : schedules.get(0)); //for now, we will have only one schedule per channel.
		result.include("channelId", channelId);
		result.include("videos", service.getVideos());

		result.forwardTo(this).form();
	}
	
	@Get @Restricted
	@Path("/schedules/form")
	public void form(){}
	
	@Post @Restricted
	@Path("/schedules")
	public void save(Schedule schedule, List<String> videosAndPositions) {
		if (schedule.getId() != null) service.cleanSchedule(schedule.getId());
		
		if (videosAndPositions != null) schedule.setVideoList(service.prepareSchedule(videosAndPositions));			
		
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