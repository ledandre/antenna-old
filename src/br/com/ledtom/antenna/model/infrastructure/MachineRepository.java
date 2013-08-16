package br.com.ledtom.antenna.model.infrastructure;

import java.util.List;

import br.com.caelum.vraptor.http.route.ResourceNotFoundException;
import br.com.ledtom.antenna.model.entity.Machine;

public interface MachineRepository {
	public Machine find(Long id) throws ResourceNotFoundException;
	
	public Machine findByHash(String hash);

	public List<Machine> list();
	
	public List<Machine> listPending();
	
	public Machine save(Machine machine);
	
	public void delete(Machine machine);
	
}
