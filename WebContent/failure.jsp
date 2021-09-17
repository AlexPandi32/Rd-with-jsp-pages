<%@ taglib prefix="s" uri="/struts-tags" %>
<head>
<style>
.buttons {background-color: red;color: white;}
</style>
</head>
Hello <s:property value="userName" />, Username password is invalid
<br>
<br>
Your credentials are invalid try again :
<a href="login.jsp"><button class="buttons" type="button">Login</button></a>