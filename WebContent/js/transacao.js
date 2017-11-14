$(document).ready(function () {
	$("#addTransacao").click(function() {
		$.post("http://localhost:8080/database/transferencia",
				$("form").serialize(),
				function (data, status) {
					$("#messages").html(data + "<br>" + status);
				})
	});
	
	$("#removeTransacao").click(function() {
		$.post("http://localhost:8080/database/transferencia/delete",
				$("form").serialize(),
				function (data, status) {
					$("#messages").html(data + "<br>" + status);
				})
	});
	
	$("#updateTransacao").click(function () {
		$.post("http://localhost:8080/database/transferencia/update",
				$("form").serialize(),
				function (data, status) {
					$("#messages").html(data + "<br>" + status);
				})
	});
	
	$("#consultaTransacao").click(function () {
		$.get("http://localhost:8080/database/transferencia",
				function (data, status) {
			$("#messages").html(status + "<br>" + data);
		})
	});
})