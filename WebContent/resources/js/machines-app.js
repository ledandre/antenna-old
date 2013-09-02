
function acceptMachine(machineId, action) {
	$.ajax({
		type: "POST",
		url: action + '/' + machineId,
		cache: false,
		data: {
			'_method' : 'PUT',
			'machine.id' : machineId
		}
	}).done(function( html ){});
}

function confirmRemove(machineId, machineName) {
	var confirmed = confirm("Tem certeza que deseja recusar a conexão da máquina " + machineName + "?");
	
	if (confirmed) {
		$("#deleteForm").attr("action", "machines/" +machineId);
		$("#deleteForm").submit();

	} else {
		return;
	}
}