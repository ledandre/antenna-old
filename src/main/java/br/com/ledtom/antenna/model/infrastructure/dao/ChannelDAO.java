package br.com.ledtom.antenna.model.infrastructure.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.caelum.vraptor.http.route.ResourceNotFoundException;
import br.com.ledtom.antenna.model.entity.Channel;
import br.com.ledtom.antenna.model.infrastructure.ChannelRepository;

public class ChannelDAO implements ChannelRepository {
	EntityManagerFactory factory;
	EntityManager entityManager;
	
	public ChannelDAO() {
		factory = Persistence.createEntityManagerFactory("antennaPU");
		entityManager = factory.createEntityManager();
	}
	
	public Channel find(Long id) throws ResourceNotFoundException {
		return entityManager.find(Channel.class, id);
	}
	
	public List<Channel> list() {
		Query query = entityManager.createQuery("SELECT C FROM " + Channel.class.getSimpleName() + " C");
		List<Channel> channels = query.getResultList();

		return channels;
	}
	
	public Channel save(Channel channel) {
		entityManager.getTransaction().begin();
		
		if (channel.getId() == null){
			entityManager.persist(channel);			
		} else {
			entityManager.merge(channel);
		}
		
		entityManager.getTransaction().commit();
		
		return channel;
	}
	
	public void delete(Channel channel) {
		entityManager.getTransaction().begin();
		entityManager.remove(channel);
		entityManager.getTransaction().commit();
	}
}
