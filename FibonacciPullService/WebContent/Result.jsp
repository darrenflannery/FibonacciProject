<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Result</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"&></script>

<script type="text/javascript">


</script> 

</head>
<body>

<BR>
<h1>Fibonacci Pull Service</h1>
<BR>
<h2>Job Number: "<%= request.getAttribute("jobNumber") %>"</h2>
<BR>
<h3>Length Specified: "<%= request.getAttribute("length") %>"</h3>
<BR>

<h2>Result is: "<%= request.getAttribute("result") %>"</h2> 

<%
    //Set refresh, autoload time as 10 seconds
   if(request.getAttribute("result")==null)
   {
   	response.setIntHeader("Refresh", 10);
  }
 
%>
<BR>
<h4>If you're result is "null", then your request is still processing.</h4>
<h4> This page will auto refresh in 10 seconds.</h4> 
<BR>
<a href="FibonacciForm.jsp">Back To Start</a> 
</body>
</html>