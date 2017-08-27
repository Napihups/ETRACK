/**
 * Created by stephan on 20.03.16.
 */

$(function () {
    // VARIABLES =============================================================
    var TOKEN_KEY = "jwtToken"
    var $notLoggedIn = $("#notLoggedIn");
    var $loggedIn = $("#loggedIn").hide();
    var $loggedInBody = $("#loggedInBody");
    var $response = $("#response");
    var $login = $("#login");
    var $userInfo = $("#userInfo").hide();
    var $loginNotif = $("#loginNotif").hide();
    var $bodyContent = $("#bodyContent");
    
    // FUNCTIONS =============================================================
    function getJwtToken() {
        return localStorage.getItem(TOKEN_KEY);
    }

    function setJwtToken(token) {
        localStorage.setItem(TOKEN_KEY, token);
    }

    function removeJwtToken() {
        localStorage.removeItem(TOKEN_KEY);
    }

    function doLogin(loginData) {
        $.ajax({
            url: "/login",
            type: "POST",
            data: JSON.stringify(loginData),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                setJwtToken(data.TOKEN);
                // fetch data for User Homepage
                if(data.TOKEN){
                	fetchHomePage();
                } else {
                	$loginNotif.show()
                	.html("<p>Invalid Username or Password !</p>");
                }
                
            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status === 401) {
                    $('#loginErrorModal')
                            .modal("show")
                            .find(".modal-body")
                            .empty()
                            .html("<p>Spring exception:<br>" + jqXHR.responseJSON.exception + "</p>");
                } else {
                    throw new Error("an unexpected error occured: " + errorThrown);
                }
            }
        });
    }

    //===================NAPIHUP CODE===========================================// 
    function fetchHomePage(){
    	$.ajax({
    		type: "GET",
    		url: "home/dashboard",
    		contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
    		success : function(data) {
    		    document.open();
    		    document.write(data);
    		    document.close();
    		}
    	});
    }
    
    function doCreateJob(formData){
		$.ajax({
			type: "POST",
			url: "job/create",
			data: JSON.stringify(formData),
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			headers: createAuthorizationTokenHeader(),
			success : function(data, textStatus, errorThrown){
				if(data.SUCCESS){
					doCreateJobView();
					alert(data.MSG);
				}else {
					alert(data.MSG);
				}
			},
			error: function(jqXHR, textStatus, errorThrown){
				alert("Error occured with status : " + jqXHR.status);
			}
		});
	}
    
    function doCreateJobView() {
    	$.ajax({
    		type: "GET",
    		url: "job/112",
    		contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            success : function(data) {
    		    document.open();
    		    document.write(data);
    		    document.close();
    		    
    		}
    	});
    }
    function doForceLogout() {
    	$.ajax({
    		type: "GET",
    		url: "/",
    		contentType: "application/json; charset=utf-8",
    		headers: createAuthorizationTokenHeader(),
    		success : function(data) {
    			
    		}
    	});
    }
    
  //=================================================================================// 


    function createAuthorizationTokenHeader() {
        var token = getJwtToken();
        if (token) {
            return {"Authorization": token};
        } else {
            return {};
        }
    }

   

    function showTokenInformation() {
        var jwtToken = getJwtToken();
        var decodedToken = jwt_decode(jwtToken);
        console.log(decodedToken);

        $loggedInBody.append($("<h4>").text("Token"));
        $loggedInBody.append($("<div>").text(jwtToken).css("word-break", "break-all"));
        $loggedInBody.append($("<h4>").text("Token claims"));

        var $table = $("<table>")
                .addClass("table table-striped");
        appendKeyValue($table, "sub", decodedToken.sub);
        appendKeyValue($table, "audience", decodedToken.audience);
        appendKeyValue($table, "created", new Date(decodedToken.created).toString());
        appendKeyValue($table, "exp", decodedToken.exp);

        $loggedInBody.append($table);

        $loggedIn.show();
    }

    function appendKeyValue($table, key, value) {
        var $row = $("<tr>")
                .append($("<td>").text(key))
                .append($("<td>").text(value));
        $table.append($row);
    }

    function showResponse(statusCode, message) {
        $response
                .empty()
                .text("status code: " + statusCode + "\n-------------------------\n" + message);
    }
    
    
   

    // REGISTER EVENT LISTENERS =============================================================
    $("#loginForm").submit(function (event) {
        event.preventDefault();

        var $form = $(this);
        var formData = {
            USERNAME: $form.find('input[name="username"]').val(),
            PASSWORD: $form.find('input[name="password"]').val()
        };

        doLogin(formData);
    });

    $("#logoutButton").click(function (){
    	removeJwtToken();
    	window.location = "/";
    });
    
    
    
    $("#jobForm").submit(function (event){
		event.preventDefault();
    	var $jobCreateForm = $(this);
    	var $select = $("#reqTo");
    	var formData = {
    		DESCRIPTION: $jobCreateForm.find('input[name="jobDesciption"]').val(),
    		REMARKS: $('textarea#jobRemarks').val(),
    		REQUEST_TO: $('#reqTo').find(":selected").text(),
    	};
    	doCreateJob(formData);
    	$("#squarespaceModal").modal("hide");
    });

   
    
    


    
    //============LOG CLICK FUNCTION=============================================//
    $loggedIn.click(function () {
        $loggedIn
                .toggleClass("text-hidden")
                .toggleClass("text-shown");
    });
    
   

    
});