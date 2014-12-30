package br.com.ledtom.antenna.core.periodic;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.ledtom.antenna.configuration.Config;
import br.com.ledtom.antenna.domain.periodic.Scheduler;
import br.com.ledtom.antenna.domain.periodic.Synchronizer;

/**
 * This class makes periodic checks to verify if all machines are synchronized.
 * 
 * @author Leandro Andre
 *
 */
public class MachineChecker implements ServletContextListener {
	public void contextInitialized(ServletContextEvent event) {
		Scheduler scheduler = new Scheduler();
		scheduler.scheduleTask(Synchronizer.getInstance(), Config.getMachineCheckSyncTime() * 60 * 1000);
	}
	
	public void contextDestroyed(ServletContextEvent event) {
		//TODO do nothing ? =p
	}
}
