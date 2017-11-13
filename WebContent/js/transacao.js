$(document).ready(function () {
	$("#addTransacao").click(function() {
		$.post("http://localhost:8080/database/transferencia",
				{
			origem: $("#cntOrigem").val(),
			destino: $("#cntDestino").val(),
			quantia: $("#quantia").val()
				},
				function (data, status) {
					$("#messages").html(data + " " + status);
				})
	});
	
	$("#removeTransacao").click(function() {
		$.delete("http://localhost:8080/database/transferencia",
				{
			origem: $("#cntOrigem").val(),
			destino: $("#cntDestino").val(),
			quantia: $("#quantia").val()
				},
				function (data, status) {
					
				})
	});
	
	$("#updateTransacao").click(function () {
		$.put("http://localhost:8080/database/transferencia",
				{
			origem: $("#cntOrigem").val(),
			origemNova: $("#cntOrigemNova").val(),
			destino: $("#cntDestino").val(),
			destinoNovo: $("#cntDestinoNova").val(),
			quantia: $("#quantia").val(),
			quantiaNova: $("#quantiaNova").val()
				},
				function (data, status) {
					
				})
	});
	
	$("#consultaTransacao").click(function () {
		$.get("http://localhost:8080/database/transferencia",
				function (data, status) {
			$("#messages").html(status + " " + data);
		})
	});
})