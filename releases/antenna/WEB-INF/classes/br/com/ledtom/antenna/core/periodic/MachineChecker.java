package br.com.ledtom.antenna.core.periodic;

import java.util.Iterator;
import java.util.List;

import lombok.AllArgsConstructor;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.ledtom.antenna.core.annotations.Periodic;
import br.com.ledtom.antenna.domain.service.MachineService;
import br.com.ledtom.antenna.model.entity.Machine;

/**
 * This class makes periodic checks to verify if all machines are synchronized.
 * 
 * @author Leandro Andre
 *
 */
@ApplicationScoped
@AllArgsConstructor
public class MachineChecker {
	private final MachineService service;

	@Periodic
	public void checkAllMachines() {
		List<Machine> machines = service.list();
		
		Iterator<Machine> iterator = machines.iterator();
		
		while (iterator.hasNext()) {
			//TODO create a warning object
		}
	}
}
