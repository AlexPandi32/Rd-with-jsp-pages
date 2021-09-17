<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<style>
.buttons {background-color: red;color: white;}
</style>
</head>
<body>
<s:form action="login">
    <s:textfield name="userName" label="Enter Username" /><br>
	<s:password name="password" label="Enter Password" /><br>
    <s:submit value="Click" align="center"/> 
</s:form>
If New User :<a href="signup.jsp"><button class="buttons" type="button">SignUp</button></a>

</body>
</html>