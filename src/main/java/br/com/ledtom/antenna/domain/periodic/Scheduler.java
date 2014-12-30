package br.com.ledtom.antenna.domain.periodic;

import java.util.Timer;
import java.util.TimerTask;

public class Scheduler extends Timer {

	public void scheduleTask(TimerTask task, long interval) {
		super.schedule(task, 0, interval);
	}
}