function sendEditForm() {
	$("#editMethod").attr("value", "put");
	$("#channelForm").submit();
}

function confirmRemove(channelId, channelName) {
	var confirmed = confirm("Tem certeza que deseja excluir o canal " + channelName + "?");
	
	if (confirmed) {
		$("#deleteForm").attr("action", "channels/" + channelId);
		$("#deleteForm").submit();

	} else {
		return;
	}
}