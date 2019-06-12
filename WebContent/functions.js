
var usId;
var request;
	 if (navigator.appName == "Microsoft Internet Explorer")// for Internet Explorer we open ActiveXObject and for the others XHR object
	 {
	   request = new ActiveXObject("Microsoft.XMLHTTP");
	 }
	 else
	 {
	   request = new XMLHttpRequest();
	 }

var curItemId;
	 
	//remember the item the user is chenging now
	 function curItem(item){
		 curItemId=item.getAttribute("data-item-id");
	 }


var namespace={};//create the namespace for it
	 
	//update item in memory and in the list that the user sees
	 namespace.updateItem= function(){
	    		
        	request.abort();
        	
			//get all the information from the pop up
	        var date=document.getElementById("update");
	        var time=document.getElementById("uptime");
	        var value=document.getElementById("upitem");
	        var done=document.getElementById("checkbox-"+curItemId+"a").checked;
	        
	       //open the request to update the item
	       request.open("GET",window.location.origin + "/ToDoList/Router/Item/update?userId="+usId+"&id="+curItemId+"&deadLine="+date.value+" "+time.value+"&value="+value.value+"&done="+done, true);
	       request.onreadystatechange= function()
		   {
		        	
				if (request.readyState == 4 && request.status == 200)
				{
					var msg=JSON.parse(request.response);
	            	 if(msg.result=="success")
					{
	            		 var newItemId=parseInt(msg.id); //get the id that was given to the item in the table
	            		 var hide=document.getElementById("itemId"+curItemId);
						 hide.style.display = 'none';//delete the old item view from the list
						 namespace.addManual(newItemId,date.value,time.value,value.value,done); //add updated version to the list   
	            		 //clean the filds in the pop up 
	            		 date.value="";
	            		 time.value="";
	            		 value.value="";
					}
					else alert(msg.result);//if the update was not successful show alert with the erro massage
				}
			};
		   if(!date.value || !time.value ||!value.value)//check if all filds were filled before update
	        	alert("Please fill in all the fields")
		  else request.send(null); //send the request 
		  
	}
  	 
	
	 //delete item from memory and list 
   	   namespace.deleteItem=function() {
   		  request.abort();
		    
	        //open request to delete the item from the memory
	        request.open("GET",window.location.origin + "/ToDoList/Router/Item/deleteItem?userId="+usId+"&id="+curItemId, true);
	        request.onreadystatechange= function()
	        {
	        	
	            if (request.readyState == 4 && request.status == 200)
	            {
	            	var msg=JSON.parse(request.response);
	            	 if(msg.result=="success")// the item was deleted succesfuly
	            		{
	            			var hide=document.getElementById("itemId"+curItemId);
							hide.style.display = 'none';// hide the item in the list
	            		}
	            	 else alert(msg.result);//if the delete was not successful show alert with the erro massage
	           	  
	            }
	            
	        };
	        request.send(null);//send the request
	       
    		}
   	   
	 //this function gets all the item parameters and dynamicaly builds its view in the list (it does not add the item to the memory) 
 	 namespace.addManual=function(itemId,date,time,value,done)// add item manualy to the list
 	 {
		var newLi = document.createElement('LI');
		newLi.className='ui-field-contain';
		newLi.id = "itemId"+itemId;
 		newLi.style.border="1px solid #38184f";
 		newLi.style.borderRadius = "20px";
 		newLi.style.padding="2% 2% 2% 2%";
 		
 		var newField = document.createElement('fieldset');
 		newField.className = 'ui-grid-b';
 		
 		var title =value+" - "+date+" "+time;//set the text that will put un the label
 		
 		var node = document.createElement('div');  
 		node.className="ui-block-a"
 		node.style.width="90%";
 		node.id="check"+itemId;
 		
 		//check if the item was done and check checkbox according to the answer 
 		if(done){
 			node.innerHTML = '<input type="checkbox" id="checkbox-'+itemId+'a" name="' + itemId + '"class="custom" checked><label id="cbl'+itemId+'" for="checkbox-'+itemId+'a">'+ title +'</label>';       
 		}   
 		else{
 			node.innerHTML = '<input type="checkbox" id="checkbox-'+itemId+'a" name="' + itemId + '"class="custom"><label id="cbl'+itemId+'" for="checkbox-'+itemId+'a">'+ title +'</label>';       
 		}
 		var div2=document.createElement('div');  
 		div2.className="ui-block-b"
 		div2.style.width= "fit-content";
 		div2.innerHTML = '<a href="#popupUpdate" data-transition="pop" data-rel="popup" data-item-id="'+itemId+'" class="ui-btn ui-btn-inline ui-shadow ui-corner-all ui-icon-edit ui-btn-icon-notext" onclick="curItem(this)">edit</a><input type="button" data-icon="delete" data-iconpos="notext" data-item-id="'+itemId+'" onclick="curItem(this);namespace.deleteItem();" >';       
 		            	   	        
 		 //add to the list
 		newField.appendChild(node);
 		newField.appendChild(div2);
 		newLi.appendChild(newField);
 		document.getElementById("listView").appendChild(newLi);  
 		$("#listView").trigger("create");//refreshing the list so the styles will show right
 	 }
   
 	//add the item in memory and view
   	namespace.add=function (){
   		
   	 	var date=document.getElementById("date-1");
     	var time=document.getElementById("time-1");
     	var value=document.getElementById("item");
     	var done=0;
     	
     	//open a request to add an item
     	 request.open("GET",window.location.origin + "/ToDoList/Router/Item/addItem?userId="+usId+"&id="+curItemId+"&deadLine="+date.value+" "+time.value+"&value="+value.value+"&done="+done, true);
	        request.onreadystatechange= function()
	        {
	        	
	            if (request.readyState == 4 && request.status == 200)
	            {
	            	var msg=JSON.parse(request.response);
	            	 if(msg.result=="success")//the request succeded
	            		{
	            		 	var itemId=parseInt(msg.id);//get new item id
	            		 	namespace.addManual(itemId,date.value,time.value,value.value,done);// ad item to the view
	            	        //clear the filds in the new item pop up
	            	        date.value="";
		            		time.value="";
		            		value.value="";
	            		}
	            	 else alert(msg.result);//if additem was not successful show alert with the erro massage
	           	  
	            }
	            
	        };
	        if(!date.value || !time.value ||!value.value)//check that all the filds in the pop up were filled
	        	alert("Please fill in all the fields")
		  else request.send(null); //sent request
   	}
 	//this function will be called every time a checkbox will be checked/unchecked
    $(document).on('change', '[type=checkbox]',function(){
        	
        	//reading the id of the checked checkbox
        	var checkId=this.id;
        	
        	//extracting the item id out of the checkbox id
            var checkIdArr=checkId.split("-");
            var itemId=checkIdArr[1].substring(0, checkIdArr[1].length - 1);
            
            //geting the text out of the relevant label
  	   	   var label=document.getElementById("cbl"+itemId);
            
            //extracting the information out of the text
 		   var data=label.textContent;
 		   var dataArr=data.split(" - ");
    	       var deadLine=dataArr[1].split(" ");
    	       var date=deadLine[0];
    	       var time=deadLine[1];

            if($(this).is(":checked")){//change from done=false to done=true
            	//open update request to update the item
            	request.open("GET",window.location.origin + "/ToDoList/Router/Item/update?userId="+usId+"&id="+itemId+"&deadLine="+date+" "+time+"&value="+dataArr[0]+"&done="+true, true);
     	       request.onreadystatechange= function()
     		   {
     		        	
     				if (request.readyState == 4 && request.status == 200)
     				{
     					var msg=JSON.parse(request.response);
     	            	 if(msg.result=="success")//the request succeded
     					{
     	            		 var newItemId=parseInt(msg.id); //get the new item id
     	            		 var hide=document.getElementById("itemId"+itemId);
     						 hide.style.display = 'none';//hide old item view
     						namespace.addManual(newItemId,date,time,dataArr[0],true); //add the updated item view          
     					}
     					else alert(msg.result);//if the update was not successful show alert with the erro massage
     				}
     			};
     		  request.send(null);  //sent request 
            }
            else if($(this).is(":not(:checked)")){//change from done=true to done=false
            	//open update request to update the item
            	request.open("GET",window.location.origin + "/ToDoList/Router/Item/update?userId="+usId+"&id="+itemId+"&deadLine="+date+" "+time+"&value="+dataArr[0]+"&done="+false, true);
      	       request.onreadystatechange= function()
      		   {
      		        	
      				if (request.readyState == 4 && request.status == 200)
      				{
      					var msg=JSON.parse(request.response);
      	            	 if(msg.result=="success")//the request succeded
      					{
      	            		 var newItemId=parseInt(msg.id); //get the new item id
      	            		 var hide=document.getElementById("itemId"+itemId);
      						 hide.style.display = 'none';//hide old item view
      						namespace.addManual(newItemId,date,time,dataArr[0],false); //add the updated item view          
      					}
      					else alert(msg.result);//if the update was not successful show alert with the erro massage
      				}
      			};
      		  request.send(null); //sent request   

            }

        });

    
    function logout()
    {
    	window.location.href = "logout.jsp";//go to the logout window
    }
 
