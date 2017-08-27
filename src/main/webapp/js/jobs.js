$(function () {
	
	const client = require('./client.js');
	
	// REGISTER EVENT LISTENERS =============================================================
	$("#jobForm").submit(function (event){
			event.preventDefault();
	    	var $jobCreateForm = $(this);
	    	var $select = $("#reqTo");
	    	var formData = {
	    		DESCRIPTION: $jobCreateForm.find('input[name="jobDesciption"]').val(),
	    		JOB_REMARKS: $('textarea#jobRemarks').val(),
	    		REQUEST_TO: $('#reqTo').find(":selected").text(),
	    	};
	    	doCreateJob(formData);
	    	$("#squarespaceModal").modal("hide");
	    });





	// FUNCTIONS //
	function doCreateJob(formData){
		$.ajax({
			type: "POST",
			url: "job/create",
			data: JSON.stringify(formData),
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			headers: client.createAuthorizationTokenHeader(),
			success : function(data, textStatus, errorThrown){
				alert(data);
			},
			error: function(jqXHR, textStatus, errorThrown){
				alert("Error occured with status : " + jqXHR.status);
			}
		});
	}
	
	
});


