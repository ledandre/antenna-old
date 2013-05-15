$(document).ready(function() {
	$.getScript("../../resources/style/js/jquery.dataTables.min.js", function(){
		$('#videos').dataTable({
			"bAutoWidth": false,
			"oLanguage" : {
			"sProcessing":   "Processando...",
			"sLengthMenu":   "Mostrar _MENU_ registros",
			"sZeroRecords":  "Não foram encontrados resultados",
			"sInfo":         "Mostrando de _START_ até _END_ de _TOTAL_ registros",
			"sInfoEmpty":    "Mostrando de 0 até 0 de 0 registros",
			"sInfoFiltered": "(filtrado de _MAX_ registros no total)",
			"sInfoPostFix":  "",
			"sSearch":       "Buscar:",
			"sUrl":          "",
			"oPaginate": {
				"sFirst":    "Primeiro",
				"sPrevious": "Anterior",
				"sNext":     "Seguinte",
				"sLast":     "Último"
			}
		}
		
		});
	});
	
	$("#tabs").tabs();
	checkUnique();
});

$(function() {
    $("#schedule-unique, #schedule-morning, #schedule-afternoon, #schedule-night").sortable({
        placeholder: "ui-state-highlight"
    });
    $( "#schedule-unique, #schedule-morning, #schedule-afternoon, #schedule-night" ).disableSelection();
});

/* Functions to controls the schedule creation */
function loadSchedule(period, videoId, videoName){
	var list = document.getElementById("schedule-" + period);
	var item = document.createElement("li");
	var removeArrow = document.createElement("img");
	var name = document.createTextNode(videoName);

	removeArrow.setAttribute("class", "actionArrow");
	removeArrow.setAttribute("src", "/Antenna/resources/images/removeVideo.png");
	removeArrow.setAttribute("onclick", "javascript:removeVideo(this);");
	
	item.setAttribute("class", videoId + " ui-state-default");
	item.appendChild(removeArrow);
	item.appendChild(name);
	item.setAttribute("id", videoId + "schedule-" + period);
	list.appendChild(item);
}

function addVideo(id, name){
	var selected = document.getElementById("selected");
	var schedule = document.getElementById(selected.value);

	if (document.getElementById(id + selected.value) == null){
		var videoName = document.createTextNode(name);
		var removeArrow = document.createElement("img");
		
		removeArrow.setAttribute("class", "actionArrow");
		removeArrow.setAttribute("src", "/Antenna/resources/images/removeVideo.png");
		removeArrow.setAttribute("onclick", "javascript:removeVideo(this);");
		
		var video = document.createElement('li');
		video.setAttribute("class", id + " ui-state-default");
		video.setAttribute("id", id + selected.value);
		video.appendChild(removeArrow);
		video.appendChild(videoName);
		
		schedule.appendChild(video);		
	}else{
		alert("Não é permitido adicionar o mesmo vídeo mais de uma vez na programação.");
	}
}

function removeVideo(element){
	var selected = document.getElementById("selected");
	var schedule = document.getElementById(selected.value);
	
	schedule.removeChild(element.parentNode);
}


function createSchedulesList(){
	var selected = document.getElementById("selected");
	
	if(selected.value == "schedule-unique"){
		prepareScheduleVideosOnForm("unique", $("#schedule-unique").html());
		//prepareScheduleVideosOnForm("unique");
	}else{
		prepareScheduleVideosOnForm("morning", $("#schedule-morning").html());
		prepareScheduleVideosOnForm("afternoon", $("#schedule-afternoon").html());
		prepareScheduleVideosOnForm("night", $("#schedule-night").html());
		/*
		prepareScheduleVideosOnForm("morning");
		prepareScheduleVideosOnForm("afternoon");
		prepareScheduleVideosOnForm("night");
		*/
	}
	
	var form = document.getElementById("form");
	form.submit();
}

/*
 * Sorry, but JS forced me to make this disgusting, ugly and creep method =/
 */
function prepareScheduleVideosOnForm(identify, content){
	var position = content.indexOf("<li class=\"");
	
	while(position != -1){
		position += 11;
		var videoId = content.substring(position, content.indexOf(" ", position));
		var input = document.createElement("input");
		input.setAttribute("type", "hidden");
		input.setAttribute("name", "videos"+identify);
		input.setAttribute("value", videoId);
		
		form.appendChild(input);
		
		position = content.indexOf("<li class=\"", position);
	}
}

/*
function prepareScheduleVideosOnForm(identify){
	var form = document.getElementById("form");
	var element = document.getElementById("schedule-unique");
	
	if(identify == "morning"){
		element = document.getElementById("schedule-morning");
	}else if(identify == "afternoon"){
		element = document.getElementById("schedule-afternoon");
	}else if(identify == "night"){
		element = document.getElementById("schedule-night");
	}
	
	videos = element.childNodes;

	for(var i = 0; i < videos.length; i++){
		video = videos.item(i);
		
		videoId = video.getAttribute("class");
		videoId = videoId.replace(" ui-state-default", "");
		
		var input = document.createElement("input");
		input.setAttribute("type", "hidden");
		input.setAttribute("name", "videos"+identify);
		input.setAttribute("value", videoId);
		
		form.appendChild(input);
	}
}
*/


function selectList(list){
	document.getElementById("selected").value = list;
}

function checkUnique(){
	var tabs = document.getElementById("tabs");
	var uniqueList = document.getElementById("unique");
	var element = document.getElementById("checkUnique");

	if(element.checked == true){
		document.getElementById("selected").value = "schedule-unique";
		tabs.style.display = "none";
		uniqueList.style.display = "block";
	}else{
		document.getElementById("selected").value = "schedule-morning";
		tabs.style.display = "block";
		uniqueList.style.display = "none";
	}
	
	
}

function selectUnique(){
	document.getElementById("checkUnique").checked = true;
	checkUnique();
}