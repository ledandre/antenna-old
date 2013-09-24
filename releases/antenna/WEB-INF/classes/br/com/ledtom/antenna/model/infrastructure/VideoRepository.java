package br.com.ledtom.antenna.model.infrastructure;

import java.util.List;

import br.com.caelum.vraptor.http.route.ResourceNotFoundException;
import br.com.ledtom.antenna.model.entity.Video;

public interface VideoRepository {
	public Video find(Long id) throws ResourceNotFoundException;
	
	public List<Video> list();
	
	public Video save(Video video);
	
	public void delete(Video video);
}
