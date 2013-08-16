package br.com.ledtom.antenna.domain.controller;

import lombok.AllArgsConstructor;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.ledtom.antenna.domain.service.MachineService;
import br.com.ledtom.antenna.model.entity.Machine;
import br.com.ledtom.antenna.sessioncomponents.ApplicationInfo;

@Resource
@AllArgsConstructor
public class MachineController {
	private final Result result;
	private final MachineService service;
	
	@SuppressWarnings("unused")
	private ApplicationInfo appInfo;
	
	@Get @Path("/machines")
	public void list() {
		result.include("machines", service.list());
	}
	
	@Get @Path("/machines/pending")
	public void listPending() {
		result.include("machines", service.listPending());
	}
	
	@Get @Path("/machines/requestSync/{name}/{hash}")
	public void requestSync(String name, String hash) {
		Machine machine = Machine.create(name, hash);
		service.save(machine);
	}
	
	@Put @Path("/machines/accept/{id}")
	public void accept(Machine machine) {
		service.accept(machine);
	}
	
	@Get @Path("/machines/notify/{hash}")
	public void notify(String hash) {
		
	}
}
