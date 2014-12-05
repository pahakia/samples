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
  <style>
    body { font-size: 62.5%; }
    div#create-user-div {width: 700px; margin: 20px auto; text-align: center;}
  </style>
  <script>
    var type;
    var elem;
    $(function() {
    var dialog, form;
 
    dialog = $( "#dialog-form" ).dialog({
      autoOpen: false,
      height: 130,
      width: 250,
      modal: true,
      close: function() {
        $('#gplusone').remove();
      }
    });
 
    $( "a.secure" ).on( "click", function(event) {
      var url = $(this).attr('href');
      var elem1 = $(this);
      event.preventDefault();
      $.ajax({
        url : "<c:url value="/loggedin"/>",
        type: "GET",
        success: function(data, textStatus, jqXHR)
        {
            if ("true" == data) {
              window.location=url;
            } else {
              type="link";
              elem = elem1;
              dialog.dialog( "open" );
              var po = document.createElement('script');
              po.id = "gplusone";
              po.type = 'text/javascript'; po.async = true;
              po.src = 'https://plus.google.com/js/client:plusone.js';
              var s = $('#gConnect')[0];
              s.parentNode.insertBefore(po, s);
            }
        },
        error: function (jqXHR, textStatus, errorThrown)
        {
           alert("a.secure: error: " + errorThrown);
        }
      });
    });

    $( "input.secure,  button.secure" ).on( "click", function(event) {
      var button = $(this);
      var elem1 = $(this);
      event.preventDefault();
      $.ajax({
        url : "<c:url value="/loggedin"/>",
        type: "GET",
        success: function(data, textStatus, jqXHR)
        {
            if ("true" == data) {
              button.unbind("click");
              button.click();
            } else {
              type = "other";
              elem = elem1;
              dialog.dialog( "open" );
              var po = document.createElement('script');
              po.id = "gplusone";
              po.type = 'text/javascript'; po.async = true;
              po.src = 'https://plus.google.com/js/client:plusone.js';
              var s = $('#gConnect')[0];
              s.parentNode.insertBefore(po, s);
            }
        },
        error: function (jqXHR, textStatus, errorThrown)
        {
           alert("input button.secure: error: " + errorThrown);
        }
      });
    });
  });
  function onSignInCallback(authResult) {
    if (authResult['access_token']) {
      console.log(authResult.code);
      $.ajax({
        type: 'POST',
        url: "<c:url value="gplus"/>?state=<c:out value="${state}"/>&${_csrf.parameterName}=${_csrf.token}",
        contentType: 'application/octet-stream; charset=utf-8',
        success: function(result) {
          console.log(result);
          if (type == "link") {
            var url = elem.attr("href");
            window.location=url;
          } else {
            elem.unbind("click");
            elem.click();
          }
        },
        processData: false,
        data: authResult.code
      });
    } else if (authResult['error']) {
//      alert("onSignInCallback: error: " + authResult['error']);
    }
  }
  </script>
  
  <title>Hello World</title>
</head>
<body>
  <h1>Title : ${title}</h1>
  This page demonstrates how to exchange the google+ token for session token.<br>
  Message : ${message}<br>
  <a class="secure" href="<c:url value="/user?p1=abc"/>">secure link</a><br>
  <a class="non-secure" href="<c:url value="/public?p2=def"/>">public link</a> <br>

  form example:
  <form action="<c:url value="/user"/>">
    <input class="secure" type="submit" value="secure submit">
  </form>
  <form action="<c:url value="/public"/>">
    <input class="non-secure" type="submit" value="non-secure submit">
  </form>
  <form action="<c:url value="/user"/>">
    <button class="secure">secure button</button>
  </form>
  <form action="<c:url value="/public"/>">
    <button class="non-secure">non-secure button</button>
  </form>

<div id="dialog-form" title="Sign in with Google+">
  <p>Please sign in with google+.</p>
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