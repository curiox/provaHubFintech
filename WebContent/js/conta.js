$("#addConta").click(function() {
	
	$.post("/database/conta",
	{
		nome: $("#Nome").val(),
		cnpj: $("#CNPJ").val(),
		cpf: $("#CPF").val(),
		tipoConta: $("#tipoConta").val()
	},
	function (data, status) {
		
	});
});

$("#removeConta").click(function() {
	
});

$("#updateConta").click(function () {
	
});

$("#consultaConta").click(function () {
	
});