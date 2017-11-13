$(document).ready(function () {
	$("#addConta").click(function() {
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth()+1; //January is 0!
		var yyyy = today.getFullYear();
	
		if(dd<10) {
		    dd = '0'+dd
		} 
	
		if(mm<10) {
		    mm = '0'+mm
		} 
	
		today = dd + '/' + mm + '/' + yyyy;
		
		$.post("http://localhost:8080/database/conta",
		{
			nome: $("#Nome").val(),
			cnpj: $("#CNPJ").val(),
			cpf: $("#CPF").val(),
			tipoConta: $("#tipoConta").val(),
			data: today
		},
		function (data, status) {
			
		});
	});
	
	$("#removeConta").click(function() {
		$.delete("http://localhost:8080/database/conta",
				{
			nome: $("#Nome").val(),
			cnpj: $("#CNPJ").val(),
			cpf: $("#CPF").val(),
			tipoConta: $("#tipoConta").val(),
			data: today
				},
				function (data, status) {
					
				})
	});
	
	$("#updateConta").click(function () {
		$.put("http://localhost:8080/database/conta",
				{
			nome: $("#Nome").val(),
			cnpj: $("#CNPJ").val(),
			cpf: $("#CPF").val(),
			tipoConta: $("#tipoConta").val(),
			data: today
				},
				function (data, status) {
					
				})
	});
	
	$("#consultaConta").click(function () {
		$.get("http://localhost:8080/database/conta",
				function (data, status) {
					$("#messages").html(status + " " + data);
				})
	});
})