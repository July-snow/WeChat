<%--
  Created by IntelliJ IDEA.
  User: Silence 
  Time: 14:40 2017/11/14
  Description:
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>

    <form action="servlet/WeChatServlet" method="get">
      <input type="text" name="signature"/>
        <input type="text" name="timestamp"/>
        
      <input type="submit" value="submit"/>
    </form>
  </body>
</html>
