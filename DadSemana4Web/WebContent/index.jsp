<%@page import="java.rmi.Naming"%>
<%@page import="edu.cibertec.rmi.InterfaceRMI"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>




<%
InterfaceRMI iRmi= null; 
iRmi = (InterfaceRMI)Naming.lookup("//127.0.0.1:9094/servi");
out.print(iRmi.Suma(2, 2));
out.print(iRmi.Saludar());


%>
</body>
</html>