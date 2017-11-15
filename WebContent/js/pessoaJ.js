$(document).ready(function () {
	$("#addPessoaJ").click(function() {
		$.post("http://localhost:8080/database/pessoajuridica",
				$("form").serialize(),
				function (data, status) {
					$("#messages").html(status + "<br>" + data);
				})
	});
	
	$("#removePessoaJ").click(function() {
		$.post("http://localhost:8080/database/pessoajuridica/delete",
				$("form").serialize(),
				function (data, status) {
					$("#messages").html(status + "<br>" + data);
				})
	});
	
	$("#updatePessoaJ").click(function () {
		$.post("http://localhost:8080/database/pessoajuridica/update",
				$("form").serialize(),
				function (data, status) {
					$("#messages").html(status + "<br>" + data);
				})
	});
	
	$("#consultaPessoaJ").click(function () {
		$.get("http://localhost:8080/database/pessoajuridica",
				function (data, status) {
			$("#messages").html(status + "<br>" + data);
		})
	});
})