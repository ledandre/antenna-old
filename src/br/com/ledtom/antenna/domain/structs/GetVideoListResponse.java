package br.com.ledtom.antenna.domain.structs;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import br.com.ledtom.antenna.model.entity.Video;

public final class GetVideoListResponse {
	private final List<Video> videos;
	
	public GetVideoListResponse(List<Video> videos) {
		this.videos = videos;
	}

	public Set<Video> getVideos() {
		return new TreeSet<Video>(videos);
	}
}
