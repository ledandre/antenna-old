package br.com.ledtom.antenna.domain.controller;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.ledtom.antenna.core.security.Restricted;
import br.com.ledtom.antenna.domain.service.VideoService;
import br.com.ledtom.antenna.model.entity.Video;
import br.com.ledtom.antenna.sessioncomponents.ApplicationInfo;

@Resource
@AllArgsConstructor
public class VideoController {
	private final Result result;
	private final ApplicationInfo applicationInfo;
	private final VideoService service;
	
	@Get @Restricted
	@Path("/videos")
	public void list(){
		result.include("videos", service.list());
	}
	
	@Get @Restricted
	@Path("/videos/form")
	public void form(){}
	
	@Get @Restricted
	@Path("/videos/edit/{video.id}")
	public void form(Video video) {
		result.include("video", service.find(video.getId()));
		result.forwardTo(this).form();
	}
	
	@Post @Restricted
	@Path("/videos")
	public void create(Video video, UploadedFile videoFile) {
		service.upload(videoFile);
		video.setFile(StringUtils.stripAccents(videoFile.getFileName()));
		service.save(video);
		result.redirectTo(this).list();
	}
	
	@Post @Restricted
	@Path("/videos/edit")
	public void edit(Video video, UploadedFile videoFile) {
		if (video.getId() != null && videoFile != null) {
			service.deleteOldVideo(video.getId());
			service.upload(videoFile);
			video.setFile(videoFile.getFileName());
		}

		service.save(video);
		result.redirectTo(this).list();
	}	

	@Delete @Restricted
	@Path("/videos/{video.id}")
	public void delete(Video video) {
		service.deleteOldVideo(video.getId());
		service.delete(service.find(video.getId()));
		result.redirectTo(this).list();
	}
}
