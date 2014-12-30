package br.com.ledtom.antenna.domain.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.ledtom.antenna.configuration.Config;
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
	
	public void upload(UploadedFile videoFile) {
		StringBuilder absoluteFilePath = new StringBuilder();
		absoluteFilePath.append(Config.getVideoRepositoryPath()).append(StringUtils.stripAccents(videoFile.getFileName()));
		try {
			IOUtils.copyLarge(videoFile.getFile(), new FileOutputStream(new File(absoluteFilePath.toString())));
			videoFile.getFile().close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteOldVideo(long videoId) {
		Video video = find(videoId);
		
		StringBuilder absoluteFilePath = new StringBuilder();
		absoluteFilePath.append(Config.getVideoRepositoryPath()).append(video.getFile());
		File oldVideo = new File(absoluteFilePath.toString());
		
		oldVideo.delete();
	}
}
