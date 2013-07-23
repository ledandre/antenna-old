function sendEditForm() {
	$("#editMethod").attr("value", "put");
	$("#videoForm").submit();
}

function confirmRemove(videoId, videoName) {
	var confirmed = confirm("Tem certeza que deseja excluir o v�deo " + videoName + "?");
	
	if (confirmed) {
		$("#deleteForm").attr("action", "videos/" + videoId);
		$("#deleteForm").submit();

	} else {
		return;
	}
}