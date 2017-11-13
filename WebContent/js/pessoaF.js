$("#addPessoaF").click(function() {
	$.post("http://localhost:8080/database/pessoafisica",
			{
		cpf: $("#CPF").val(),
		nomeComp: $("#NomeComp").val(),
		dataNasc: $("#dataNasc").val()
			},
			function (data, status) {
				$("#messages").innerHTML = data + " " + status;
		});
});

$("#removePessoaF").click(function() {
	
});

$("#updatePessoaF").click(function () {
	
});

$("#consultaPessoaF").click(function () {
	
});