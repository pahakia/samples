<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
  <script src="//plus.google.com/js/client:plusone.js"></script>
  <style>
    div#create-user-div {width: 700px; margin: 20px auto; text-align: center;}
  </style>
  <script>
  function onSignInCallback(authResult) {
    if (authResult['access_token']) {
      console.log(authResult.code);
      $.ajax({
        type: 'POST',
        url: "<c:url value="gplus"/>?state=<c:out value="${state}"/>&${_csrf.parameterName}=${_csrf.token}",
        contentType: 'application/octet-stream; charset=utf-8',
        success: function(result) {
          console.log(result);
          var url = atob('<c:out value="${param.o}"/>');
          window.location=url;
        },
        processData: false,
        data: authResult.code
      });
    } else if (authResult['error']) {
//      alert("onSignInCallback: error: " + authResult['error']);
    }
  }
  </script>
  
  <title>Sign in</title>
</head>
<body>
  <div id="create-user-div">
    <h1>Please sign in with google+.</h1>

  <div id="gConnect">
    <button class="g-signin"
        data-scope="https://www.googleapis.com/auth/plus.login"
        data-requestvisibleactions="http://schemas.google.com/AddActivity"
        data-clientId="<c:out value="${clientId}"/>"
        data-accesstype="offline"
        data-callback="onSignInCallback"
        data-theme="dark"
        data-cookiepolicy="single_host_origin">
    </button>
  </div>
  </div>
</body>
</html>