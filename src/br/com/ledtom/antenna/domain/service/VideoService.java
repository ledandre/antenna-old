package br.com.ledtom.antenna.domain.service;

import java.util.List;

import br.com.ledtom.antenna.model.entity.Video;
import br.com.ledtom.antenna.model.infrastructure.VideoRepository;
import br.com.ledtom.antenna.model.infrastructure.dao.VideoDAO;

public class VideoService {
private VideoRepository repository;
	
	public VideoService() {
		repository = new VideoDAO();
	}
	
	public Video find(long id){
		return repository.find(id);
	}
	
	public List<Video> list() {
		return repository.list();
	}
	
	public Video save(Video video) {
		return repository.save(video);
	}
	
	public void delete(Video video) {
		repository.delete(video);
	}
}
