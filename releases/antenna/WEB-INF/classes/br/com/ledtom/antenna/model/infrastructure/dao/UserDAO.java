package br.com.ledtom.antenna.model.infrastructure.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.caelum.vraptor.http.route.ResourceNotFoundException;
import br.com.ledtom.antenna.model.infrastructure.UserRepository;
import br.com.ledtom.antenna.model.security.User;

public class UserDAO implements UserRepository {
	EntityManagerFactory factory;
	EntityManager entityManager;
	
	public UserDAO() {
		factory = Persistence.createEntityManagerFactory("antennaPU");
		entityManager = factory.createEntityManager();
	}
	
	public User find(Long id) throws ResourceNotFoundException {
		return entityManager.find(User.class, id);
	}
	
	public List<User> list() {
		Query query = entityManager.createQuery("SELECT U FROM " + User.class.getSimpleName() + " U");
		return query.getResultList();
	}
	
	public User save(User user) {
		entityManager.getTransaction().begin();
		
		if (user.getId() == null) {
			entityManager.persist(user);			
		} else {
			entityManager.merge(user);
		}
		
		entityManager.getTransaction().commit();
		
		return user;
	}
	
	public void delete(User user) {
		entityManager.getTransaction().begin();
		entityManager.remove(user);
		entityManager.getTransaction().commit();
	}
	
	public User validateLogin(String username, String password) {
		Query authQuery = entityManager.createQuery("SELECT U FROM " + User.class.getSimpleName() + " U " +
				"WHERE U.username = :username AND U.password = :password");
		authQuery.setParameter("username", username);
		authQuery.setParameter("password", password);
		
		try {
			return (User) authQuery.getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}
}
