package br.com.ledtom.antenna.modules.security;
/**
 * @author Leandro Andre
 * This annotation controls the system's access.
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Restricted {
	/**
	 * Define which profiles can access the method.
	 */
	Profile canAccess() default Profile.ADMINISTRATOR;
}