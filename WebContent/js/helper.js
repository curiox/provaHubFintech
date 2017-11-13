$(document).ready(function() {
		$.put = function(url, data, callback, type){
			if($.isFunction(data) ) {
				type = type || callback,
				callback = data,
				data = {}
			}
			
			return $.ajax({
				url: url,
				type: 'PUT',
				success: callback,
				data: data,
				contentType: type
			});
		}
		
		$.delete = function(url, data, callback, type){
			if ($.isFunction(data)) {
				type = type || callback,
				callback = data,
				data = {}
			}
			
			return $.ajax({
				url: url,
				type: 'DELETE',
				success: callback,
				data: data,
				contentType: type
			});
		}
		
		$.patch = function(url, data, callback, type){
			if ($.isFunction(data)) {
				type = type || callback,
				callback = data,
				data = {}
			}
			
			return $.ajax({
				url: url,
				type: 'PATCH',
				success: callback,
				data: data,
				contentType: type
			});
		}
})