lastPosition = $('#schedule-table tbody tr').length;

function addVideo(id, name) {

	$('#schedule-table tbody').append('<tr id="tr-' + lastPosition + '"><td>' + 
	'<a href="#" onclick="javscript:removeVideo(' + lastPosition + '); return false;" class="options-link" data-toggle="tooltip" title="Remover da lista de reprodução" data-original-title="Remover da lista de reprodução">' +
	'<i class="icon-minus-sign"></i>' +
	'</a>' +
	'<a href="#" class="options-link" data-toggle="tooltip" title="Preview" data-original-title="Preview">' +
	'<i class="icon-facetime-video"></i>' +
	'</a>' +
	'</td>' +
	'<td>' + name + '</td></tr>');
	
	$('#hidden-schedule-form').append('<input id="vd-' + lastPosition + '" type="hidden" name="videosAndPositions[' + lastPosition + ']" value="' + id + ':' + lastPosition + '">');
	
	$('.submit-hidden-form').removeAttr('disabled');
	$('.submit-hidden-form').removeClass('disabled');
	
	lastPosition++;
}

function removeVideo(id) {
	$('#tr-' + id).remove();
	$('#vd-' + id).remove();
	
	$('.submit-hidden-form').removeAttr('disabled');
	$('.submit-hidden-form').removeClass('disabled');
}