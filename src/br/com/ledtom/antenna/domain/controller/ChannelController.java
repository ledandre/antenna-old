package br.com.ledtom.antenna.domain.controller;

import lombok.AllArgsConstructor;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.ledtom.antenna.domain.service.ChannelService;
import br.com.ledtom.antenna.model.entity.Channel;
import br.com.ledtom.antenna.model.security.Restricted;
import br.com.ledtom.antenna.sessioncomponents.ApplicationInfo;

@Resource
@AllArgsConstructor
public class ChannelController {
	private final Result result;
	private final ChannelService service;
	private final ApplicationInfo appInfo;
	
	@Get @Restricted
	@Path("/channels")
	public void list(){
		result.include("channels", service.list());
	}
	
	@Get @Restricted
	@Path("/channels/form")
	public void form(){}
	
	@Get @Restricted
	@Path("/channels/edit/{channel.id}")
	public void form(Channel channel){
		result.include("channel", service.find(channel.getId()));
		result.forwardTo(this).form();
	}
	
	@Post @Restricted
	@Path("/channels")
	public void create(Channel channel){
		service.save(channel);
		result.redirectTo(this).list();
	}
	
	@Put @Restricted
	@Path("/channels")
	public void edit(Channel channel){
		service.save(channel);
		result.redirectTo(this).list();
	}
	
	@Delete @Restricted
	@Path("/channels/{channel.id}")
	public void delete(Channel channel){
		service.delete(service.find(channel.getId()));
		result.redirectTo(this).list();
	}
}