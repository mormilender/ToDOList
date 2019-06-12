<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1255">
<title>LogIn</title>
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script
	src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
<link rel="stylesheet" href="themes/mytheme.min.css" />
<link rel="stylesheet" href="themes/jquery.mobile.icons.min.css" />
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.4.5/jquery.mobile.structure-1.4.5.min.css" />
<link rel="stylesheet" href="styles.css"/>
<script>

// function that moves to login page again after we logged out of our user 

var namespace={};
namespace.loginAgain=function()
{
	window.location.href = "login.jsp";
}

</script>

</head>

<body background="themes/back_l.jpg">
	<%     /// remove attributes : user id and items from this  session 
	        session.removeAttribute("userId");
	        session.removeAttribute("items");
	        session.invalidate();//invalidats this session and then unbinds any objects that bound to the session
	%>

	<div data-role="page" id="logout" data-dialog="true">
		<div data-role="content">
			<h1 class="signUpTitle">You are logged out</h1>
			<hr>
			<br>
		
		<button onclick="namespace.loginAgain()" id="BackLogin" class="ui-btn ui-corner-all ui-shadow ui-btn-b ui-icon-back ui-btn-icon-left">Log out</button>
		</div>

	</div>

</body>
</html>