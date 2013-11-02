package br.com.ledtom.antenna.domain.controller;

import lombok.AllArgsConstructor;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.ledtom.antenna.core.security.Restricted;
import br.com.ledtom.antenna.domain.service.CommandService;
import br.com.ledtom.antenna.model.entity.Machine;
import br.com.ledtom.antenna.model.enums.MachineCommand;

@Resource
@AllArgsConstructor
public class CommandController {
	private final Result result;
	private final CommandService service;
	
	@Restricted
	public void create(MachineCommand command, String arguments, Machine machine) {
		service.create(command, arguments, machine);
		result.redirectTo(MachineController.class).list();
	}
	
	public void commandExecutedNotify(long commandId) {
		service.markAsExecuted(commandId);
	}
}