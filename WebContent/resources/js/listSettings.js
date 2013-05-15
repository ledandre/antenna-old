/* File: listSettings.js
 * This script configures the datatable resource for all jsp pages using it
 * @author: Eduardo Andre
 */

$(document).ready(function() {
	$.getScript("../resources/style/js/jquery.dataTables.min.js", function(){
		$('#videos').dataTable({
			"oLanguage" : {
			"sProcessing":   "Processando...",
			"sLengthMenu":   "Mostrar _MENU_ registros",
			"sZeroRecords":  "N�o foram encontrados resultados",
			"sInfo":         "Mostrando de _START_ at� _END_ de _TOTAL_ registros",
			"sInfoEmpty":    "Mostrando de 0 at� 0 de 0 registros",
			"sInfoFiltered": "(filtrado de _MAX_ registros no total)",
			"sInfoPostFix":  "",
			"sSearch":       "Buscar:",
			"sUrl":          "",
			"oPaginate": {
				"sFirst":    "Primeiro",
				"sPrevious": "Anterior",
				"sNext":     "Seguinte",
				"sLast":     "�ltimo"
			}
		}
		
		});
		
		$('#categories').dataTable({
			"oLanguage" : {
				"sProcessing":   "Processando...",
				"sLengthMenu":   "Mostrar _MENU_ registros",
				"sZeroRecords":  "N�o foram encontrados resultados",
				"sInfo":         "Mostrando de _START_ at� _END_ de _TOTAL_ registros",
				"sInfoEmpty":    "Mostrando de 0 at� 0 de 0 registros",
				"sInfoFiltered": "(filtrado de _MAX_ registros no total)",
				"sInfoPostFix":  "",
				"sSearch":       "Buscar:",
				"sUrl":          "",
				"oPaginate": {
					"sFirst":    "Primeiro",
					"sPrevious": "Anterior",
					"sNext":     "Seguinte",
					"sLast":     "�ltimo"
				}
			}
		});
	});
});