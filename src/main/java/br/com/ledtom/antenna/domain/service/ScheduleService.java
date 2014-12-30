package br.com.ledtom.antenna.domain.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.ledtom.antenna.model.entity.Channel;
import br.com.ledtom.antenna.model.entity.Schedule;
import br.com.ledtom.antenna.model.entity.Video;
import br.com.ledtom.antenna.model.entity.VideoList;
import br.com.ledtom.antenna.model.infrastructure.ChannelRepository;
import br.com.ledtom.antenna.model.infrastructure.ScheduleRepository;
import br.com.ledtom.antenna.model.infrastructure.VideoRepository;
import br.com.ledtom.antenna.model.infrastructure.dao.ChannelDAO;
import br.com.ledtom.antenna.model.infrastructure.dao.ScheduleDAO;
import br.com.ledtom.antenna.model.infrastructure.dao.VideoDAO;

public class ScheduleService {
	private ScheduleRepository repository;
	private ChannelRepository channelRepository;
	private VideoRepository videoRepository;

	public ScheduleService() {
		repository = new ScheduleDAO();
		channelRepository = new ChannelDAO();
		videoRepository = new VideoDAO();
	}
	
	public Schedule find(long id){
		return repository.find(id);
	}
	
	public List<Schedule> list() {
		return repository.list();
	}
	
	public List<Schedule> list(Long id) {
		return repository.list(channelRepository.find(id));
	}
	
	public Schedule save(Schedule schedule) {
		return repository.save(schedule);
	}

	public void delete(Schedule schedule) {
		repository.delete(schedule);
	}

	public Channel getChannel(Long id) {
		return channelRepository.find(id);
	}
	
	public List<Video> getVideos() {
		return videoRepository.list();
	}
	
	//This method is an ugly method, but to keep the position of videos, it was necessary '-
	public List<VideoList> prepareSchedule(List<String> videosAndPositions) {
		Map<Integer, Long> videosAndPositionsMap = new HashMap<Integer, Long>();
		
		List<VideoList> videoList = new ArrayList<VideoList>();
		
		for (String videoAndPosition : videosAndPositions) {
			String[] videoData = videoAndPosition.split(":");
			videosAndPositionsMap.put(Integer.parseInt(videoData[1]), Long.parseLong(videoData[0]));
		}
		
		int countdown = videosAndPositions.size();
		int index = -1;

		do {
			index++;
			if (!videosAndPositionsMap.containsKey(index)) continue;

			VideoList list = new VideoList();
			list.setVideo(videoRepository.find(videosAndPositionsMap.get(index)));
			list.setPosition(index);
			videoList.add(list);
			countdown--;

		} while (countdown > 0);

		return videoList;
	}

	public void cleanSchedule(long id) {
		Schedule schedule = find(id);
		repository.removeVideoLists(schedule);
		schedule.setVideoList(null);
		save(schedule);
	}
}