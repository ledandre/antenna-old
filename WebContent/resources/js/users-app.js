function sendEditForm() {
	$("#editMethod").attr("value", "put");
	$("#userForm").submit();
}

function confirmRemove(userId, username) {
	var confirmed = confirm("Tem certeza que deseja excluir o usuário '" + username + "'?");
	
	if (confirmed) {
		$("#deleteForm").attr("action", "users/" + userId);
		$("#deleteForm").submit();

	} else {
		return;
	}
}