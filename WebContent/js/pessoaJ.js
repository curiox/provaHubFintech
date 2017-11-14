$(document).ready(function () {
	$("#addPessoaJ").click(function() {
		$.post("http://localhost:8080/database/pessoajuridica",
				{
			cnpj: $("#CNPJ").val(),
			razSoc: $("#razSoc").val(),
			nomFan: $("#nomFan").val()
				},
				function (data, status) {
					$("#messages").html(status + "<br>" + data);
				})
	});
	
	$("#removePessoaJ").click(function() {
		$.delete("http://localhost:8080/database/pessoajuridica",
				{
			cnpj: $("#CNPJ").val(),
			razSoc: $("#razSoc").val(),
			nomFan: $("#nomFan").val()
				},
				function (data, status) {
					$("#messages").html(status + "<br>" + data);
				})
	});
	
	$("#updatePessoaJ").click(function () {
		$.put("http://localhost:8080/database/pessoajuridica",
				{
			cnpj: $("#CNPJ").val(),
			cnpjnovo: $("#CNPJNovo").val(),
			razSoc: $("#razSoc").val(),
			razSocNovo: $("#razSocNovo").val(),
			nomFan: $("#nomFan").val(),
			nomFanNovo: $("#nomFanNovo").val()
				},
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