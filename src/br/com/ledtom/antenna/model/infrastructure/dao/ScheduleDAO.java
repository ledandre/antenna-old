package br.com.ledtom.antenna.model.infrastructure.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.caelum.vraptor.http.route.ResourceNotFoundException;
import br.com.ledtom.antenna.model.entity.Channel;
import br.com.ledtom.antenna.model.entity.Schedule;
import br.com.ledtom.antenna.model.entity.VideoList;
import br.com.ledtom.antenna.model.infrastructure.ScheduleRepository;

public class ScheduleDAO implements ScheduleRepository {
	EntityManagerFactory factory;
	EntityManager entityManager;
	
	public ScheduleDAO() {
		factory = Persistence.createEntityManagerFactory("antennaPU");
		entityManager = factory.createEntityManager();
	}
	
	public Schedule find(Long id) throws ResourceNotFoundException {
		return entityManager.find(Schedule.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Schedule> list() {
		Query query = entityManager.createQuery("SELECT C FROM " + Schedule.class.getSimpleName() + " C");
		List<Schedule> schedules = query.getResultList();

		return schedules;
	}
	
	@SuppressWarnings("unchecked")
	public List<Schedule> list(Channel channel) {
		Query query = entityManager.createQuery("SELECT C FROM " + Schedule.class.getSimpleName() + " C WHERE C.channel = :channel");
		query.setParameter("channel", channel);

		List<Schedule> schedules = query.getResultList();

		return schedules;
	}
	
	public Schedule save(Schedule schedule) {
		entityManager.getTransaction().begin();
		
		if (schedule.getId() == null){
			entityManager.persist(schedule);			
		} else {
			entityManager.merge(schedule);
		}
		
		entityManager.getTransaction().commit();
		
		return schedule;
	}
	
	public void delete(Schedule schedule) {
		entityManager.getTransaction().begin();
		entityManager.remove(schedule);
		entityManager.getTransaction().commit();
	}
	
	public void removeVideoLists(Schedule schedule) {
		String tableName = new StringBuilder().append(Schedule.class.getSimpleName()).append("_").append(VideoList.class.getSimpleName()).toString();

		entityManager.getTransaction().begin();
		Query query = entityManager.createNativeQuery("DELETE FROM " + tableName + " WHERE schedule_id = " + schedule.getId());
		query.executeUpdate();
		entityManager.getTransaction().commit();

		for (VideoList videoList : schedule.getVideoList()) {
			entityManager.getTransaction().begin();
			entityManager.remove(videoList);
			entityManager.getTransaction().commit();
		}
	}
}
