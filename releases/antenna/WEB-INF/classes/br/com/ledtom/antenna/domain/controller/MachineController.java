package br.com.ledtom.antenna.domain.controller;

import javax.persistence.NoResultException;

import lombok.AllArgsConstructor;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.ledtom.antenna.core.security.Restricted;
import br.com.ledtom.antenna.domain.service.ChannelService;
import br.com.ledtom.antenna.domain.service.MachineService;
import br.com.ledtom.antenna.domain.structs.CheckMachineStatusResponse;
import br.com.ledtom.antenna.domain.structs.GetVideoListResponse;
import br.com.ledtom.antenna.domain.structs.NotifyResponse;
import br.com.ledtom.antenna.domain.structs.RequestSyncResponse;
import br.com.ledtom.antenna.model.entity.Channel;
import br.com.ledtom.antenna.model.entity.Machine;
import br.com.ledtom.antenna.model.enums.MachineStatus;
import br.com.ledtom.antenna.sessioncomponents.ApplicationInfo;

@Resource
@AllArgsConstructor
public class MachineController {
	private final Result result;
	private final MachineService service;
	private final ChannelService channelService;
	
	@SuppressWarnings("unused")
	private ApplicationInfo appInfo;
	
	@Restricted
	@Get @Path("/machines")
	public void list() {
		result.include("channels", channelService.list());
		result.include("machines", service.list());
	}
	
	@Restricted
	@Get @Path("/machines/pending")
	public void listPending() {
		result.include("machines", service.listPending());
	}
	
	@Get @Path("/machines/requestSync/{name}/{hash}")
	public void requestSync(String name, String hash) {
		Machine machine = Machine.create(name, hash);
		service.save(machine);
		RequestSyncResponse response = new RequestSyncResponse(0);

		result.use(Results.json()).withoutRoot().from(response).serialize();
	}

	@Restricted
	@Put @Path("/machines/accept/{id}")
	public void accept(Machine machine) {
		service.accept(service.find(machine.getId()));
		result.redirectTo(this).list();
	}

	@Get @Path("/machines/notify/{hash}")
	public void notify(String hash) {
		NotifyResponse response = null;
		try {
			service.notify(hash);
			response = new NotifyResponse(0);
		} catch(NoResultException e) {
			response = new NotifyResponse(1);
		} catch(IllegalStateException e) {
			response = new NotifyResponse(1);
		}
		
		result.use(Results.json()).withoutRoot().from(response).serialize();
	}

	@Get @Path("/machines/checkStatus/{hash}")
	public void checkStatus(String hash) {
		CheckMachineStatusResponse response = null;

		try {
			response = new CheckMachineStatusResponse(service.checkStatus(hash));

		} catch(NoResultException e) {
			response = new CheckMachineStatusResponse(MachineStatus.INEXISTENT);
		}
		
		result.use(Results.json()).withoutRoot().from(response).serialize();
	}

	@Restricted
	@Put @Path("/machines/setChannel/{machine}/{channel}")
	public void setChannel(Machine machine, Channel channel) {
		service.setChannel(service.find(machine.getId()), channelService.find(channel.getId()));
		result.redirectTo(this).list();
	}

	@Get @Path("/machines/getSchedule/{hash}")
	public void getSchedule(String hash) {
		GetVideoListResponse response = new GetVideoListResponse(service.getVideoList(hash));
		System.out.println("im on the getschedule > " + response.getVideos().toString());
		result.use(Results.json()).withoutRoot().from(response).include("videos").serialize();
	}
	
	@Get @Path("/machines/getCommandQueue/{hash}")
	public void getCommandQueue(String hash) {
		
	}
}