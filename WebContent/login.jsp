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
<link rel="stylesheet" href="styles.css">

<script src="loging.js"></script>
</head>

<body background="themes/back_l.jpg">

	<!--first dialog page to login or sign up to the app-->

	<div data-role="page" id="login" data-dialog="true">
		<div data-role="content">
			<h1 class="signUpTitle">Login</h1>
			<hr>
			<form id="myForm">
				<div id="signIn">
					     <label for="user" class="formTitle">User Name:</label>      <input
						type="text" data-clear-btn="true" name="user" id="user">
					     <label for="pas" class="formTitle">Password:</label>      <input
						type="password" data-clear-btn="true" name="pas" id="pas">
				</div>
				<fieldset class="ui-grid-a" id="signBtn">
					<input type="button" class="ui-block-a" id="log" data-theme="a" value="Login" onclick="namespace.logIn();">
					<input type="button"  class="ui-block-b" id="sign" data-theme="a" value="Sign Up" onclick="namespace.signUp();">
				</fieldset>
			</form>
		</div>

	</div>

</body>
</html>