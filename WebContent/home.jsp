<%@page import="java.util.List"%> 
<%@page import="java.util.ArrayList"%> 
<%@page import="il.ac.hit.model.Item"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%> 

<!DOCTYPE html>
<html lang="en">
<head>

    <!--define jquery and our css links/documents -->
    <meta charset="UTF-8">
    
    <title>ToDoList</title>
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
    <link rel="stylesheet" href="themes/mytheme.min.css" />
    <link rel="stylesheet" href="themes/jquery.mobile.icons.min.css" />
    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile.structure-1.4.5.min.css" />
    <link rel="stylesheet" href="styles.css">
    
    <script>
	 <%  
		int userId = (int) session.getAttribute("userId"); //get curent user id
		List <Item> itemList=(List<Item>)session.getAttribute("items");// get all the curent users items
	%> 
		
	usId=<%=userId%>;//save the users id
	</script>
	<script src="functions.js"></script>
   

</head>
<body background="themes/back_l.jpg" id="body">


<!--second page with the to do list of the user-->
<!--each task is contains checkbox with the name of the task and buttons to edit and delete the task -->
<div data-role="page" id="home" >
    <div>
    <button onclick=logout() id="iconBack" class="ui-btn ui-corner-all ui-shadow ui-btn-b ui-icon-back ui-btn-icon-left">Log out</button>
    </div>
    <div data-role="content"  id="content" data-theme="b" >

         <div data-role="header">
            <h1 id="title">My List:</h1>
        </div>

        <!--button to add a new task -->
        <div id="add"><a href="#popupLogin" data-transition="pop" data-rel="popup" class="ui-btn ui-shadow ui-corner-all ui-icon-plus ui-btn-icon-notext">Add</a></div>

        <div data-role="popup" id="popupLogin" data-theme="a" class="ui-corner-all">
            <form>
                <div id="newItemForm">
                    <h3>Add a new item</h3>
                    <label for="item">Name:</label>
                    <input type="text" name="item" id="item" data-theme="a">
                    <label for="date-1">Date:</label>
                    <input type="date" data-clear-btn="false" name="date-1" id="date-1" value="" data-theme="a">
                    <label for="time-1">Time:</label>
                    <input type="time" data-clear-btn="false" name="time-1" id="time-1" value="" data-theme="a">
                    <a href="#" data-rel="back" class="ui-btn ui-corner-all ui-shadow ui-btn-a ui-icon-check ui-btn-icon-notext ui-btn-right" onclick="namespace.add();">Done</a>
                </div>
            </form>
        </div>

        <div data-role="popup" id="popupUpdate" data-theme="a" class="ui-corner-all">
            <form>
                <div id="updateForm">
                    <h3>Update item</h3>
                    <label for="item">Name:</label>
                    <input type="text" name="item" id="upitem" value="" data-theme="a">
                    <label for="date-1">Date:</label>
                    <input type="date" data-clear-btn="false" name="date" id="update" value="" data-theme="a">
                    <label for="time-1">Time:</label>
                    <input type="time" data-clear-btn="false" name="time" id="uptime" value="" data-theme="a">
                    <a href="#" data-rel="back" class="ui-btn ui-corner-all ui-shadow ui-btn-a ui-icon-check ui-btn-icon-notext ui-btn-right" onclick="namespace.updateItem();">Close</a>
                </div>
            </form>
        </div>

        <!--task1-->
        <form id="list">
         <ul data-role="listview" data-inset="true" id="listView">

            
          	 <%    
          	 if(itemList!=null)
          	 {
	         	 for(int i=0;i<itemList.size();i++)//build the list by the items in the itemList
	         	 {
	         	 %>  
	             <li class="ui-field-contain" id=<%="itemId"+itemList.get(i).getId()%> >
	                 <fieldset class="ui-grid-b">
	                     <div class="ui-block-a"  id=<%="check"+itemList.get(i).getId()%> style="width:90%">
	                     <%if(itemList.get(i).getDone()) {%>
	                         <input type="checkbox" name=<%="checkbox-"+itemList.get(i).getId()+"a"%> id=<%="checkbox-"+itemList.get(i).getId()+"a"%> class="custom" checked/>
	                         <%}
	                     else {%>
	                     	<input type="checkbox" name=<%="checkbox-"+itemList.get(i).getId()+"a"%> id=<%="checkbox-"+itemList.get(i).getId()+"a"%> class="custom"/>
	                     	<%} %>
	                         <label id=<%="cbl"+itemList.get(i).getId()%> for=<%="checkbox-"+itemList.get(i).getId()+"a"%>><%=itemList.get(i).getValue()+" - "+itemList.get(i).getDeadLine() %></label>
	                     </div>
	                     <div class="ui-block-b" id=<%="icon"+itemList.get(i).getId() %> style="width:fit-content">
	                         <a href="#popupUpdate" data-transition="pop" data-rel="popup" data-item-id=<%=itemList.get(i).getId() %> class="ui-btn ui-btn-inline ui-shadow ui-corner-all ui-icon-edit ui-btn-icon-notext" onclick="curItem(this)">edit</a>
	                        <input type="button" data-icon="delete" data-iconpos="notext" data-item-id=<%=itemList.get(i).getId() %> onclick="curItem(this);namespace.deleteItem();" >
	                     </div>
	                 </fieldset>           
	             </li>
             <%
             	}	
	         }%> 


         </ul>
        </form>
    </div>
</div>
</body>
</html>