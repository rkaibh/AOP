<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Main</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
$(document).ready(function(){
	
	$.ajax({url:"jsonData", type:"get"})
	 .done(function(result){
		  console.log(result);
	});
	
	$("#logout").on("click", function(){
		$.ajax({url:"logout", type:"post"})
		   .done(function(result){
			  location.href = "home";
		  });
	});
});
</script>
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">AOP</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="home">HOME</a></li>
      <li><a href="about">ABOUT</a></li>
      <li class="active"><a href="menu">MENU</a></li>
      <li><a href="where">WHERE</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li id="logout" style="cursor: pointer;"><a><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
    </ul>
  </div>
</nav>
<div class="container">
  <h1>Menu Page</h1>
</div>

</body>
</html>