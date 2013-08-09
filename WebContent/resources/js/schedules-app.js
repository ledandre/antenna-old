function addVideo(id, name) {
	var position = $('#schedule-table tbody tr').length;

	$('#schedule-table tbody').append('<tr id="tr-' + position + '"><td>' + 
	'<a href="#" class="options-link" data-toggle="tooltip" title="Remover da lista de reprodução" data-original-title="Remover da lista de reprodução">' +
	'<i class="icon-minus-sign"></i>' +
	'</a>' +
	'<a href="#" class="options-link" data-toggle="tooltip" title="Preview" data-original-title="Preview">' +
	'<i class="icon-facetime-video"></i>' +
	'</a>' +
	'</td>' +
	'<td>' + name + '</td></tr>');
	
	$('#hidden-schedule-form').append('<input id="vd-' + position + '" type="hidden" name="schedule.videoList.video.id" value="' + id + '">');
	
	$('.submit-hidden-form').removeAttr('disabled');
	$('.submit-hidden-form').removeClass('disabled');
}

function removeVideo(id) {
	$('#tr-' + id).remove();
	$('#vd-' + id).remove();
}