<html>
  <style>
           div {width: 300px; margin: 20px auto;}
  </style>
  <body>
    <div>
        <h2>JSP Examples</h2>
        <%-- This is a servlet comment, it will not be printed to the web page --%>
        <!-- this is an html/xml comment, it will be printed to the web page -->
        <!-- you can use servlet tags in html comment, i.e. |<%= request.getParameter("hello")%>| -->
        <a href="jsp.jsp?hello=abc">jsp page</a><br>
        <a href="jsp-if.jsp">jsp with if with no query string</a><br>
        <a href="jsp-if.jsp?hello=abc">jsp with if with query string</a><br>
    </div>
    <div>
        <h2>JQuery Examples</h2>
        <a href="jquery-dialog.jsp">simple jquery dialog</a><br>
        <a href="dialog.jsp">more complex jquery dialog</a><br>
        <a href="jquery-gplus.jsp">jquery google+ sign in button</a><br>
    </div>
  </body>
</html>
