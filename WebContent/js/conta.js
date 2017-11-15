$(document).ready(function () {
	$("#addConta").click(function() {
		$.post("http://localhost:8080/database/conta",
				$("form").serialize(),
				function (data, status) {
					$("#messages").html(status + "<br>" + data);
				});
	});
	
	$("#removeConta").click(function() {
		$.post("http://localhost:8080/database/conta/delete",
				$("form").serialize(),
				function (data, status) {
					$("#messages").html(status + "<br>" + data);
				})
	});
	
	$("#updateConta").click(function () {
		$.post("http://localhost:8080/database/conta/update",
				$("form").serialize(),
				function (data, status) {
					$("#messages").html(status + "<br>" + data);
				})
	});
	
	$("#consultaConta").click(function () {
		$.get("http://localhost:8080/database/conta",
				function (data, status) {
					$("#messages").html(status + "<br>" + data);
				})
	});
})