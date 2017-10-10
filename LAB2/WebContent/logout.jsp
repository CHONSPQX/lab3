<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>logout || LMS</title>
</head>
<body>
<%
try
{      
    session.removeAttribute("user");
    session.invalidate();                               
    response.sendRedirect("login.jsp");          
}
catch (Exception sqle)
{
    System.out.println("error UserValidateServlet message : " + sqle.getMessage());
    System.out.println("error UserValidateServlet exception : " + sqle);
}
%>
</body>
</html>