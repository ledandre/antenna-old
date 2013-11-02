package br.com.ledtom.antenna.domain.structs;

import java.util.List;

import br.com.ledtom.antenna.model.entity.Command;

public final class GetCommandsResponse {
	private final List<Command> commands;
	
	public GetCommandsResponse(List<Command> commands) {
		this.commands = commands;
	}

	public List<Command> getCommands() {
		return commands;
	}
}
