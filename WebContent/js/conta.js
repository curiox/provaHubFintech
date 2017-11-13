$(document).ready(function () {
	
	getDate = function () {
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
	
		return today = dd + '/' + mm + '/' + yyyy;
	}
	
	$("#addConta").click(function() {
		var today = getDate();
		
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
		var today = getDate();
		
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
		var today = getDate();
		
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