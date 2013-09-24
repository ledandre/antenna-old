function sendEditForm() {
	var action = $("#videoForm").attr("action");
	$("#videoForm").attr("action", action + "/edit");
	$("#videoForm").submit();
}

function confirmRemove(videoId, videoName) {
	var confirmed = confirm("Tem certeza que deseja excluir o vídeo " + videoName + "?");
	
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
 
	var formData = new FormData();
	formData.append("file", document.getElementById('file').files[0]);
	formData.append("video.id", $("#id").val());
	formData.append("video.name", $("#name").val());
	formData.append("video.description", $("#description").val());
	request.open("POST", uploadURI);
	request.send(formData);
}

function uploadProgress(event) {
  if (event.lengthComputable) {
    var percent = Math.round(event.loaded * 100 / event.total);
    $("#progressbar").css("width", percent + "%");
  } else {
   //TODO adicionar tratamento de erro. 
  }
}


function updateChannelSchedule() {
	$('#periods').html('<img src="resources/img/ajax-loader.gif">');
	$.ajax({
		type: "GET",
		url: "period",
		cache: false
	}).done(function( html ) {
		$('#periods').html(html);
	});
}
