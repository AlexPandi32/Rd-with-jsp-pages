<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>Report</title>
<style>
.buttons {background-color: red;color: white;}
</style>
</head>
<body>
<s:form action="signup">
    <s:textfield name="userName" label="Enter Username" /><br>
	<s:password name="password" label="Enter Password" /><br>
    <s:submit value="Click" align="center"/> 
</s:form>
</body>
</html>