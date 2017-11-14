$(document).ready(function () {
	/*function showValues() {
		var str = $("form").serialize();
		$("#messages").html(str);
	}
	
	$("#addPessoaF").on("click", showValues);*/
	$("#addPessoaF").click(function() {		
		$.post("http://localhost:8080/database/pessoafisica",
				$("form").serialize(),
				function (data, status) {
					$("#messages").html(data + " " + status);
			});
	});

	$("#removePessoaF").click(function() {
		$.post("http://localhost:8080/database/pessoafisica/delete",
				$("form").serialize(),
				function (data, status) {
					$("#messages").html(status + "<br>" + data);
				})
	});

	$("#updatePessoaF").click(function () {
		$.post("http://localhost:8080/database/pessoafisica/update",
				$("form").serialize(),
				function (data, status) {
					$("#messages").html(status + "<br>" + data);
				})
	});

	$("#consultaPessoaF").click(function () {
		$.get("http://localhost:8080/database/pessoafisica",
				function (data, status) {
				$("#messages").html(status + "<br>" + data);
		});
	});
})