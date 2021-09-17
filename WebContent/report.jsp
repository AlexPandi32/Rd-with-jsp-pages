<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Report</title>
<style>
table, td, th {border: 1px solid black;}
table {border-collapse: collapse;width: 60%;}
th {height: 30px;}
.button-update {background-color: blue;color: white;}
.button-delete {background-color: red;color: white;}
</style>
</head>
<body>
<a href="homepage.jsp">
<button >Home</button>
</a>
	<h2>Ransom Report</h2>
	<div style="margin-top: 40px;">
		<s:if test="noData==true">
			<table>
				<thead>
					<tr style="background-color: #E0E0E1;">
						<th>Time Of Action</th>
						<th>Encrypted By</th>
						<th>Encrypted File</th>
					</tr>
				</thead>
				<s:iterator value="evtList">
					<tr>
						<td><s:property value="Time" /></td>
						<td><s:property value="Process" /></td>
						<td><s:property value="Object" /></td>
					</tr>
				</s:iterator>
			</table>
		</s:if>
		<s:else>
			<div style="color: red;">No Data Found.</div>
		</s:else>
	</div>
</body>
</html>