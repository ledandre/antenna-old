package br.com.ledtom.antenna.domain.service;

import java.util.List;

import br.com.ledtom.antenna.model.entity.Machine;
import br.com.ledtom.antenna.model.enums.MachineStatus;
import br.com.ledtom.antenna.model.infrastructure.MachineRepository;
import br.com.ledtom.antenna.model.infrastructure.dao.MachineDAO;

public class MachineService {
	private MachineRepository repository;

	public MachineService() {
		repository = new MachineDAO();
	}

	public Machine find(long id){
		return repository.find(id);
	}

	public List<Machine> list() {
		return repository.list();
	}

	public List<Machine> listPending() {
		return repository.listPending();
	}

	public Machine save(Machine machine) {
		return repository.save(machine);
	}

	public void delete(Machine machine) {
		repository.delete(machine);
	}

	public void accept(Machine machine) {
		machine.setStatus(MachineStatus.ACCEPTED);
		save(machine);
	}

	public void notify(String hash) {
		Machine machine = repository.findByHash(hash);
		machine.setStatus(MachineStatus.SYNCHRONIZED);
	}

	public void unsync(Machine machine) {
		machine.setStatus(MachineStatus.UNSYNCHRONIZED);
		save(machine);
	}
}
