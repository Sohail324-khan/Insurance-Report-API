<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insurance Report</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h3 class="pb-3 pt-3">Report Application</h3>
		<form:form action="search" modelAttribute="request" method="Post">
		<font color="Red" style="font-size:20px" >${msg} </font>
			<table>
				<tr>
					<td>Plan Name :</td>
					<td><form:select path="planName">
							<form:option value="">-Select-</form:option>
							<form:options items="${plans}" />
						</form:select></td>

					<td>Plan Status :</td>
					<td><form:select path="planStatus">
							<form:option value="">-Select-</form:option>
							<form:options items="${status}" />
						</form:select></td>

					<td>Select Gender :</td>
					<td><form:select path="gender">
							<form:option value="">-Select-</form:option>
							<form:options items="${gender}" />
						</form:select></td>
				</tr>
				<tr>
					<td>Start Date :</td>
					<td><form:input path="startDate" type="date" /></td>
					<td>End Date :</td>
					<td><form:input path="endDate" type="date" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Search"
						class="btn btn-primary"></td>	
					
					<td><a href="/" class="btn btn-danger">Reset</a></td>
		
					<%-- <td><form:button value="/excel" >Export-Excel</form:button></td> --%>
				</tr>
			</table>
			<hr />
		</form:form>
		<table class="table table-striped-columns table-hover table-bordered ">
			<thead>
				<tr>
					<th>ID</th>
					<th>Holder Name</th>
					<th>Gender</th>
					<th>Plan name</th>
					<th>Plan Status</th>
					<th>Plan Start Date</th>
					<th>Plan End Date</th>
					<th>Benifit Amount (Rs)</th>
					<th>Reason for Denied</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${reportData}" var="data" varStatus="index">

					<tr>
						<td>${index.count}</td>
						<td>${data.citizenName}</td>
						<td>${data.gender}</td>
						<td>${data.planName}</td>
						<td>${data.planStatus}</td>
						<td>${data.startDate}</td>
						<td>${data.endDate}</td>
						<td>${data.benifitAmount}</td>
						<td>${data.deniedReason}</td>
					</tr>
				</c:forEach>
				<tr>
					<c:if test="${empty reportData }">
						<td colspan="9" style="text-align: center">No records Found</td>
					</c:if>
				</tr>
			</tbody>
		</table>
		<hr />
		 Export :<a href="excel">Excel</a> <a href="pdf">Pdf</a> 
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
</body>
</html>