package br.com.ledtom.antenna.model.infrastructure;

import java.util.List;

import br.com.caelum.vraptor.http.route.ResourceNotFoundException;
import br.com.ledtom.antenna.model.entity.Command;
import br.com.ledtom.antenna.model.entity.Machine;

public interface CommandRepository {
	public Command find(Long id) throws ResourceNotFoundException;
	
	public List<Command> list();
	
	public List<Command> listPendingByMachine(Machine machine);
	
	public Command save(Command command);
	
	public void delete(Command command);

}
