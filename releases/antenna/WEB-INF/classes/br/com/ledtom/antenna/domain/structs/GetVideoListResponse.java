package br.com.ledtom.antenna.domain.structs;

import java.util.List;

import br.com.ledtom.antenna.model.entity.Video;

public final class GetVideoListResponse {
	private final List<Video> videos;
	
	public GetVideoListResponse(List<Video> videos) {
		this.videos = videos;
	}

	public List<Video> getVideos() {
		return videos;
	}
}
