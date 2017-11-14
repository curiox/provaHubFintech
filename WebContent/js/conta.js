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
		$("#account-form").serialize(),
		function (data, status) {
			$("#messages").html(status + "<br>" + data);
		});
	});
	
	$("#removeConta").click(function() {
		
		$.delete("http://localhost:8080/database/conta",
				$("#account-form").serialize(),
				function (data, status) {
					$("#messages").html(status + "<br>" + data);
				})
	});
	
	$("#updateConta").click(function () {
		var today = getDate();
		
		$.put("http://localhost:8080/database/conta",
				$("#account-form").serialize(),
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