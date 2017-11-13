$(document).ready(function () {	
	$("#addPessoaF").click(function() {
		$.post("http://localhost:8080/database/pessoafisica",
				{
			cpf: $("#CPF").val(),
			nomeComp: $("#NomeComp").val(),
			dataNasc: $("#dataNasc").val()
				},
				function (data, status) {
					$("#messages").html(data + " " + status);
			});
	});

	$("#removePessoaF").click(function() {
		
	});

	$("#updatePessoaF").click(function () {
		
	});

	$("#consultaPessoaF").click(function () {
		$.get("http://localhost:8080/database/pessoafisica",
				function (data, status) {
				$("#messages").html(status + " " + data);
		});
	});
})