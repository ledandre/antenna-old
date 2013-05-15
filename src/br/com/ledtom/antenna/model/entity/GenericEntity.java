package br.com.ledtom.antenna.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import lombok.Getter;
import lombok.Setter;

/**
 * This class is a generic entity, that contains generic methods to 
 * CRUD the entities of the project.
 * @author Leandro Andre
 *
 */
@MappedSuperclass
public abstract class GenericEntity {
	private static EntityManagerFactory factory;
	
	public static EntityManager getEntityManager(){
		if(factory == null){
			factory = Persistence.createEntityManagerFactory("antennaPU");
		}
		return factory.createEntityManager();
	}
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter 
	@Column(name = "id")
	private Integer id;
	
	/**
	 * Saves a new object, or the changes on it.
	 */
	public void save() {
		new Thread(new Runnable(){
			EntityManager em = getEntityManager();
			public void run() {
				em.getTransaction().begin();
				if (GenericEntity.this.id == null) {
					em.persist(GenericEntity.this);
				} else {
					em.merge(GenericEntity.this);
				}
				em.getTransaction().commit();
			}
		}).start();
	}
	
	public void delete() {
		new Thread(new Runnable(){
			EntityManager em = getEntityManager();
			public void run() {
				em.getTransaction().begin();
				em.remove(GenericEntity.this);
				em.getTransaction().commit();
			}
		}).start();
	}
	
	public static Object find(int id, Class<? extends GenericEntity> t) {
		EntityManager em = getEntityManager();
		try{
			return em.createQuery("SELECT T FROM " + t.getSimpleName() + " T " +
						" WHERE T.id = :id").setParameter("id", id).getSingleResult();
			
		}catch(NoResultException e) {
			return null;
		}
	} 

	@SuppressWarnings("unchecked")
	public static List<GenericEntity> list(Class<? extends GenericEntity> t){
		EntityManager em = getEntityManager();
		try{
			return em.createQuery("SELECT T FROM " + t.getSimpleName() + " T").getResultList();			
		}catch(NoResultException e) {
			return null;
		}
	}
}