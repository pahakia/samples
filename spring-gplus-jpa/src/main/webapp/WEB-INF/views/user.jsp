<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Protected page</title>
  <style>
    div#create-user-div {width: 700px; margin: 20px auto; text-align: left;}
  </style>
</head>
<body>
  <div id="create-user-div">
  	<h1>Title : ${title}</h1>
	<h1>Message : ${message}</h1>
 
	<c:if test="${!empty token}">
		<h2>Welcome : ${token } 
                 | <a href="<c:url value="/revoke" />" > Logout</a></h2> 
                 
       User details:<br>
       <c:out value="${user}"/><br>
	</c:if>
    <c:if test="${empty token}">
        Something is wrong, non-authenticated user is allowed accessing this page!<br>
        if you use jetty and see 'No Spring WebApplicationInitializer types detected on classpath'
        in console (or jetty) log, please use Jetty 9.x instead of 8.x. <br>
        Eclipse Jetty Integration comes with Jetty 8.x.  That does not quite work with servlet 3.0.
    </c:if>
    </div>
</body>
</html>