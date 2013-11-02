package br.com.ledtom.antenna.model.infrastructure.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.caelum.vraptor.http.route.ResourceNotFoundException;
import br.com.ledtom.antenna.model.entity.Command;
import br.com.ledtom.antenna.model.entity.Machine;
import br.com.ledtom.antenna.model.enums.CommandStatus;
import br.com.ledtom.antenna.model.infrastructure.CommandRepository;

public class CommandDAO implements CommandRepository{
	EntityManagerFactory factory;
	EntityManager entityManager;
	
	public CommandDAO() {
		factory = Persistence.createEntityManagerFactory("antennaPU");
		entityManager = factory.createEntityManager();
	}
	
	public Command find(Long id) throws ResourceNotFoundException {
		return entityManager.find(Command.class, id);
	}
	
	public List<Command> list() {
		Query query = entityManager.createQuery("SELECT C FROM " + Command.class.getSimpleName());

		@SuppressWarnings("unchecked")
		List<Command> channels = query.getResultList();

		return channels;
	}

	@SuppressWarnings("unchecked")
	public List<Command> listPendingByMachine(Machine machine) {
		Query query = entityManager.createQuery("SELECT C FROM " + Command.class.getSimpleName() + 
				" C WHERE C.machine = :machine AND C.status = :status");
		query.setParameter("machine", machine);
		query.setParameter("status", CommandStatus.PENDING);
		
		List<Command> commands = new ArrayList<Command>();
		
		try {
			commands = query.getResultList();
		} catch(NoResultException e) {}

		return commands;
	}
	
	public Command save(Command command) {
		entityManager.getTransaction().begin();
		
		if (command.getId() == null){
			entityManager.persist(command);			
		} else {
			entityManager.merge(command);
		}
		
		entityManager.getTransaction().commit();
		
		return command;
	}
	
	public void delete(Command command) {
		entityManager.getTransaction().begin();
		entityManager.remove(command);
		entityManager.getTransaction().commit();
	}
}
