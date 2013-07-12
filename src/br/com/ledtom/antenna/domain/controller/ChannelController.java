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

@Resource
@AllArgsConstructor
public class ChannelController {
	private final Result result;
	private final ChannelService service;
	
	@Get
	@Path("/channels")
	public void list(){
		result.include("channels", service.list());
	}
	
	@Get
	@Path("/channels/form")
	public void form(){}
	
	@Post
	@Path("/channels")
	public void create(Channel channel){
		service.save(channel);
	}
	
	@Put
	@Path("/channels/{id}")
	public void edit(long id){}
	
	@Delete
	@Path("/channels/{id}")
	public void delete(long id){}
}
