<html>
  <style>
           div {width: 300px; margin: 20px auto; text-align: center;}
  </style>
  <body>
    <div>
    <h2>Hello World!</h2>
        <%-- This is a servlet comment, it will not be printed to the web page --%>
        <!-- this is an html/xml comment, it will be printed to the web page -->
        <!-- you can use servlet tags in html comment, i.e. |<%= request.getParameter("hello")%>| -->
        <%= request.getParameter("hello")%>
    </div>
  </body>
</html>
