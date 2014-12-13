<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Fibbonacci Sequence Service</title>
</head>
<body>
<h1>Fibbonacci Sequence Service</h1>
<h3>Please specify length of sequence:</h3>
<form action="FibonacciServlet" method="GET">
Length: <input type="text" name="length" value="">
<input type="submit" value="Submit"/>
</form>
</body>
</html>