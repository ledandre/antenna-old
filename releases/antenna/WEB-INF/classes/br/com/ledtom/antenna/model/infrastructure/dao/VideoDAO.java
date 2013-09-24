package br.com.ledtom.antenna.model.infrastructure.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.caelum.vraptor.http.route.ResourceNotFoundException;
import br.com.ledtom.antenna.model.entity.Video;
import br.com.ledtom.antenna.model.infrastructure.VideoRepository;

public class VideoDAO implements VideoRepository{
	EntityManagerFactory factory;
	EntityManager entityManager;
	
	public VideoDAO() {
		factory = Persistence.createEntityManagerFactory("antennaPU");
		entityManager = factory.createEntityManager();
	}
	
	public Video find(Long id) throws ResourceNotFoundException {
		return entityManager.find(Video.class, id);
	}
	
	public List<Video> list() {
		Query query = entityManager.createQuery("SELECT V FROM " + Video.class.getSimpleName() + " V");
		List<Video> channels = query.getResultList();

		return channels;
	}
	
	public Video save(Video video) {
		entityManager.getTransaction().begin();
		
		if (video.getId() == null){
			entityManager.persist(video);			
		} else {
			entityManager.merge(video);
		}
		
		entityManager.getTransaction().commit();
		
		return video;
	}
	
	public void delete(Video video) {
		entityManager.getTransaction().begin();
		entityManager.remove(video);
		entityManager.getTransaction().commit();
	}
}
