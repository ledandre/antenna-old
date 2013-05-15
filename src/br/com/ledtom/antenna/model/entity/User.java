package br.com.ledtom.antenna.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

/**
 * The User entity provides user object for a session in the system 
 * @author Eduardo Andre
 * @author Leandro Andre
 */

@Entity
@Table (name = "users")
public class User extends GenericEntity implements Serializable{
	@Transient
	private static final long serialVersionUID = 2708941650127831039L;
	
	@Getter @Setter
	@Column(name = "username", length = 12, nullable = false)
	private String username;
	
	@Getter @Setter
	@Column(name = "email", length = 70, nullable = false)
	private String email;
	
	@Getter @Setter
	@Column(name = "password", length = 12, nullable = false)
	private String password;

	/**
	 * Does the login.
	 * @param username The user username
	 * @param password The user password
	 * @return Returns an {@link User} object, if the login info is right. <code>null</code> if don't.
	 */
	public static User doLogin(String username, String password) {
		try{
			EntityManager em = getEntityManager();
			Query query = em.createQuery("SELECT U FROM User U WHERE U.username = :username AND U.password = :password");
			query.setParameter("username", username);
			query.setParameter("password", password);
			
			User loggedUser = (User) query.getSingleResult();
			if (loggedUser != null) loggedUser.setPassword(null);
			
			return loggedUser;
		}catch(NoResultException e){
			return null;
		}
	}	
}