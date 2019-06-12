var request;
	 if (navigator.appName == "Microsoft Internet Explorer") // for Internet Explorer we open ActiveXObject and for the others XHR object 
	 {
	   request = new ActiveXObject("Microsoft.XMLHTTP");
	 }
	 else
	 {
	   request = new XMLHttpRequest();//
	 }
	 
	 var namespace={};
	 
	//function logIn- sending an AJAX request with parameters: username and password, to login to our user
 	 namespace.logIn=function()
   	   {
	    		
	        request.abort();
		    
	        var username=document.getElementById("user");
	        var password=document.getElementById("pas");
	        var params = "username="+username.value+"&password="+password.value;

	        request.open("POST",window.location.origin + "/ToDoList/Router/User/logIn", true);

	        //Send the proper header information along with the request
	        request.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	        request.onreadystatechange= function()
		        {
		        	
		            if (request.readyState == 4 && request.status == 200)
		            {
		            	var msg=JSON.parse(request.response);
		            	 if(msg.result=="success")//moving to the next page if the msg is success from the request of logIn 
	     				{
		            		request.open("GET",window.location.origin + "/ToDoList/Router/Item/getItems?userId="+msg.id, true);
		            		//sending a request to get all items that the user had
		     				 request.onreadystatechange= function()
		     				   {
								if (request.readyState == 4 && request.status == 200)
		     						{
		     							var msg=JSON.parse(request.response);
		     							if(msg.result=="success")//moving to the next page if the msg is success from the request of getItems 
		     							{
		     								 window.location.href = "home.jsp";      
		     							}
		     							else alert(msg.result);
		     						}
		     					}; 
		     					request.send(null);
	     				}
	     				else alert(msg.result);
		            }
		              
		        };
		        if(!username.value || !password.value)//error with at least one of the fields and alerting the user with error
		        	alert("Missing username field/password field")
		       else request.send(params);
   		}
 	 
 	//function signUp- sending an AJAX request with parameters: username and password, to sigh up our user
 	namespace.signUp=function() 
	   {
	        request.abort();
	        var username=document.getElementById("user");
	        var password=document.getElementById("pas");
	        var params = "username="+username.value+"&password="+password.value;
	        request.open("POST",window.location.origin +"/ToDoList/Router/User/signUp", true);
	        
	        //Send the proper header information along with the request
	        request.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	        request.onreadystatechange= function()
		        {
		            if (request.readyState == 4 && request.status == 200)
		            {
		            	var response=request.response;
		            	var msg=JSON.parse(request.response);
		            	 if(msg.result=="success") //moving to the next page if the msg is success from the request of signUp 
		     			{
		            		 window.location.href = "home.jsp";	
		     			}
		     			else alert(msg.result);
		                      }
		        };
		        if(username.value=="" || password.value=="")//error with at least one of the fields and alerting the user with error
		        	alert("Missing username field/password field")
		       else request.send(params);
		}