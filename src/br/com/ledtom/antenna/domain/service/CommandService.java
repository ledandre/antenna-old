package br.com.ledtom.antenna.domain.service;

import java.util.Calendar;
import java.util.List;

import br.com.ledtom.antenna.model.entity.Command;
import br.com.ledtom.antenna.model.entity.Machine;
import br.com.ledtom.antenna.model.enums.CommandStatus;
import br.com.ledtom.antenna.model.enums.MachineCommand;
import br.com.ledtom.antenna.model.infrastructure.CommandRepository;
import br.com.ledtom.antenna.model.infrastructure.dao.CommandDAO;

public class CommandService {
	private CommandRepository repository;

	public CommandService() {
		repository = new CommandDAO();
	}

	public Command find(long id){
		return repository.find(id);
	}

	public List<Command> list() {
		return repository.list();
	}

	public Command save(Command command) {
		return repository.save(command);
	}

	public void delete(Command command) {
		repository.delete(command);
	}
	
	public void create(MachineCommand command, String arguments, Machine machine) {
		Command newCommand = new Command();
		newCommand.setCommand(command);
		newCommand.setArgument(arguments);
		newCommand.setMachine(machine);
		newCommand.setRequested(Calendar.getInstance().getTime());
		newCommand.setStatus(CommandStatus.PENDING);
		
		save(newCommand);
	}
	
	public void markAsExecuted(long commandId) {
		Command command = find(commandId);
		command.setExecuted(Calendar.getInstance().getTime());
		command.setStatus(CommandStatus.EXECUTED);
		save(command);
	}
	
	public void markAsExecuting(Command command) {
		command.setStatus(CommandStatus.EXECUTING);
		save(command);
	}
}
