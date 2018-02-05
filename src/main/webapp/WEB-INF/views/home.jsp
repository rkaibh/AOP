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
	$("form").on("submit", function( event ) {
	  event.preventDefault();
	  $.ajax({url:"login", data:$(this).serialize(), type:"post"})
	   .done(function(result){
		  console.log(result);
		  $("#login").hide();
		  $("#logout").show();
		  $("#loginModal").modal("hide");
	  });
	});
	
	$("#logout").on("click", function(){
		$.ajax({url:"logout", type:"post"})
		   .done(function(result){
			  console.log(result);
			  $("#logout").hide();
			  $("#login").show();
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
      <li class="active"><a href="home">HOME</a></li>
      <li><a href="about">ABOUT</a></li>
      <li><a href="menu">MENU</a></li>
      <li><a href="where">WHERE</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li id="login" data-toggle="modal" data-target="#loginModal" style="cursor: pointer;"><a><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      <li id="logout" style="cursor: pointer; display: none;"><a><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
    </ul>
  </div>
</nav>
<div class="container">
  <h1>My First Bootstrap Page</h1>
</div>

<!-- Modal -->
<div id="loginModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Login</h4>
      </div>
      <div class="modal-body">
		  <form>
			<div class="form-group">
			  <label for="email">Email address:</label>
			  <input type="email" class="form-control" id="email" name="email">
			</div>
			<div class="form-group">
			  <label for="pwd">Password:</label>
			  <input type="password" class="form-control" id="pwd" name="pwd">
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
		  </form>
      </div>
    </div>

  </div>
</div>

</body>
</html>