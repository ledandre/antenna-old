package br.com.ledtom.antenna.domain.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.persistence.NoResultException;

import br.com.ledtom.antenna.model.entity.Channel;
import br.com.ledtom.antenna.model.entity.Command;
import br.com.ledtom.antenna.model.entity.Machine;
import br.com.ledtom.antenna.model.entity.Schedule;
import br.com.ledtom.antenna.model.entity.Video;
import br.com.ledtom.antenna.model.entity.VideoList;
import br.com.ledtom.antenna.model.enums.MachineStatus;
import br.com.ledtom.antenna.model.infrastructure.CommandRepository;
import br.com.ledtom.antenna.model.infrastructure.MachineRepository;
import br.com.ledtom.antenna.model.infrastructure.dao.CommandDAO;
import br.com.ledtom.antenna.model.infrastructure.dao.MachineDAO;

public class MachineService {
	private MachineRepository repository;
	private CommandRepository commandRepository;

	public MachineService() {
		repository = new MachineDAO();
		commandRepository = new CommandDAO();
	}

	public Machine find(long id){
		return repository.find(id);
	}

	public List<Machine> list() {
		return repository.list();
	}

	public List<Machine> listPending() {
		return repository.listPending();
	}

	public Machine save(Machine machine) {
		return repository.save(machine);
	}

	public void delete(Machine machine) {
		repository.delete(machine);
	}

	public void accept(Machine machine) {
		machine.setStatus(MachineStatus.ACCEPTED);
		save(machine);
	}

	public void notify(String hash) throws NoResultException, IllegalStateException {
		Machine machine = repository.findByHash(hash);
		if (machine.getStatus() == MachineStatus.PENDING) throw new IllegalStateException("This machine is not on a valid status to perform this action.");

		machine.setStatus(MachineStatus.SYNCHRONIZED);
		machine.setLastUpdated(Calendar.getInstance().getTime());
		save(machine);
	}
	
	public MachineStatus checkStatus(String hash) throws NoResultException {
		Machine machine = repository.findByHash(hash);
		return machine.getStatus();
	}

	public void unsync(Machine machine) {
		machine.setStatus(MachineStatus.UNSYNCHRONIZED);
		save(machine);
	}
	
	public void setChannel(Machine machine, Channel channel) {
		machine.setChannel(channel);
		save(machine);
	}
	
	public List<Video> getVideoList(String hash) {
		Machine machine = repository.findByHash(hash);
		List<Video> videos = new ArrayList<Video>();
		System.out.println("getting channel");
		if (machine.getChannel() != null) {
			List<Schedule> schedules = machine.getChannel().getSchedules();
			System.out.println("listing schedules");
			for(Schedule schedule : schedules) {
				Iterator<VideoList> iterator = schedule.getVideoList().iterator();
				System.out.println("iterating...");
				while(iterator.hasNext()) {
					System.out.println("getting videos...");
					VideoList videolist = iterator.next();
					System.out.println("video> " + videolist.getVideo().getName());
					videos.add(videolist.getVideo());
				}
			}			
		}

		return videos;
	}
	
	public List<Command> getCommands(String hash) {
		Machine machine = repository.findByHash(hash);
		
		return commandRepository.listPendingByMachine(machine);
	}
	
	
}
