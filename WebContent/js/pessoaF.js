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
		$.delete("http://localhost:8080/database/pessoafisica",
				{
			cpf: $("#CPF").val(),
			nomeComp: $("#NomeComp").val(),
			dataNasc: $("#dataNasc").val()
				},
				function (data, status) {
					
				})
	});

	$("#updatePessoaF").click(function () {
		$.put("http://localhost:8080/database/pessoafisica",
				{
			cpf: $("#CPF").val(),
			cpfNovo: $("#CPFNovo").val,
			nomeComp: $("#NomeComp").val(),
			nomeCompNovo: $("#NomeCompNovo").val(),
			dataNasc: $("#dataNasc").val(),
			dataNascNovo: $("#dataNascNovo").val()
				},
				function (data, status) {
					
				})
	});

	$("#consultaPessoaF").click(function () {
		$.get("http://localhost:8080/database/pessoafisica",
				function (data, status) {
				$("#messages").html(status + "<br>" + data);
		});
	});
})