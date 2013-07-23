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

function upload(uploadURI) {
 	var request = XMLHttpRequest();
 	request.upload.addEventListener("progress", uploadProgress, false);
 
	//envia o form
	var formData = new FormData();
	formData.append("file", document.getElementById('file').files[0]);
	request.open("POST", uploadURI);
	request.send(formData);
}

function uploadProgress(event) {
  if (event.lengthComputable) {
    var percent = Math.round(event.loaded * 100 / event.total); //cálculo simples de porcentagem.
    document.getElementById('progressbar').value = percent; //atualiza o valor da progress bar.
  } else {
    //não é possível computar o progresso =/
  }
}
