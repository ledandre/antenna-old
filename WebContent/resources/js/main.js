/* Form submits */
function turnEditForm(type){
	var form = document.forms[0];
	form.action = "/Antenna/"+type+"/edit";
	document.getElementById("method").value = "put";
}

/* Confirm deletes */
function confirmDeleteCategory(href, categoryId) {
	var option = confirm("Os vídeos cadastrados nessa categoria serão descategorizados. Tem certeza que deseja excluir esta categoria de video?");
	if(option == true) {
		var form = document.createElement("form");
		var method = document.createElement("input");
		method.name = "_method";
		method.value = "delete";
		form.appendChild(method);
		
		form.action = href+categoryId;
		form.method = "post";
		form.submit();
	}
}

function confirmDeleteChannelCategory(href, categoryId) {
	var option = confirm("Os canais cadastrados nessa categoria serão descategorizados. Tem certeza que deseja excluir esta categoria de canal?");
	if(option == true) {
		var form = document.createElement("form");
		var method = document.createElement("input");
		method.name = "_method";
		method.value = "delete";
		form.appendChild(method);
		
		form.action = href+categoryId;
		form.method = "post";
		form.submit();
	}
}

function confirmDeleteVideo(href, categoryId) {
	var resposta = confirm("Tem certeza que deseja excluir este video?");
	if(resposta == true) {
		var form = document.createElement("form");
		var method = document.createElement("input");
		method.name = "_method";
		method.value = "delete";
		form.appendChild(method);
		
		form.action = href+categoryId;
		form.method = "post";
		form.submit();
	}
}

function confirmDeleteChannel(href, categoryId) {
	var resposta = confirm("Tem certeza que deseja excluir este canal?");
	if(resposta == true) {
		var form = document.createElement("form");
		var method = document.createElement("input");
		method.name = "_method";
		method.value = "delete";
		form.appendChild(method);
		
		form.action = href+categoryId;
		form.method = "post";
		form.submit();
	}
}

/* Previews */
function closePreview(){
	var video = document.getElementById('preview');
	video.pause();
	document.getElementById('previewPopup').style.display = 'none';
}
function openPreview(videoRepository, video, extension){
	document.getElementById('preview').src = videoRepository+video+extension;
	document.getElementById('previewPopup').style.display = 'block';
}

/* Messages */
function closeAddMessage(){
    document.getElementById('addSuccess').style.display = 'none';
}

function closeEditMessage(){
    document.getElementById('editSuccess').style.display = 'none';
}

/* Video form edit */
function hideLimitDate(){
	document.getElementById("limitDate").style.display = 'none';
}
function editLimitDate(){
	document.getElementById("limitDate").style.display = 'block';
}
function checkOptArgs() {
	var select = document.getElementById("command");
	var chDiv = document.getElementById("channelDiv");
	if (select.value == "ALTERAR_CANAL") {
		chDiv.setAttribute("style", "");
	} else if(chDiv.getAttribute("style") == "") {
		chDiv.setAttribute("style", "display:none");
	}
}