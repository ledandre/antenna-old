package br.com.ledtom.antenna.domain.controller;

import lombok.AllArgsConstructor;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
@AllArgsConstructor
public class ChannelController {
	private final Result result;
	
	@Get
	@Path("/channels")
	public void list(){}
	
	@Get
	@Path("/channels/form")
	public void form(){}
	
	@Post
	@Path("/channels")
	public void create(){}
	
	@Put
	@Path("/channels/{id}")
	public void edit(long id){}
	
	@Delete
	@Path("/channels/{id}")
	public void delete(long id){}
}
