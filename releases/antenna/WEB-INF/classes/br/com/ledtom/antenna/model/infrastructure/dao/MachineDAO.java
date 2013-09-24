package br.com.ledtom.antenna.model.infrastructure.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.caelum.vraptor.http.route.ResourceNotFoundException;
import br.com.ledtom.antenna.model.entity.Machine;
import br.com.ledtom.antenna.model.enums.MachineStatus;
import br.com.ledtom.antenna.model.infrastructure.MachineRepository;

public class MachineDAO implements MachineRepository{
	EntityManagerFactory factory;
	EntityManager entityManager;
	
	public MachineDAO() {
		factory = Persistence.createEntityManagerFactory("antennaPU");
		entityManager = factory.createEntityManager();
	}
	
	public Machine find(Long id) throws ResourceNotFoundException {
		return entityManager.find(Machine.class, id);
	}
	
	public Machine findByHash(String hash) throws NoResultException {
		Query query = entityManager.createQuery("SELECT M FROM " + Machine.class.getSimpleName() + 
				" M WHERE M.hash = :hash");
		query.setParameter("hash", hash);
		
		return (Machine) query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Machine> list() {
		Query query = entityManager.createQuery("SELECT M FROM " + Machine.class.getSimpleName() + 
				" M WHERE M.status != :status");
		query.setParameter("status", MachineStatus.PENDING);
		List<Machine> channels = query.getResultList();

		return channels;
	}
	
	@SuppressWarnings("unchecked")
	public List<Machine> listPending() {
		Query query = entityManager.createQuery("SELECT M FROM " + Machine.class.getSimpleName() + 
				" M WHERE M.status = :status");
		query.setParameter("status", MachineStatus.PENDING);
		List<Machine> channels = query.getResultList();

		return channels;
	}

	public Machine save(Machine machine) {
		entityManager.getTransaction().begin();
		
		if (machine.getId() == null){
			entityManager.persist(machine);			
		} else {
			entityManager.merge(machine);
		}
		
		entityManager.getTransaction().commit();
		
		return machine;
	}
	
	public void delete(Machine machine) {
		entityManager.getTransaction().begin();
		entityManager.remove(machine);
		entityManager.getTransaction().commit();
	}
}
