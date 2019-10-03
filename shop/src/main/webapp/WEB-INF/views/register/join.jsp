<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>회원가입</title>
	</head>
	<body>
		<div class="panel-body">
	    <div class="row">
	        <div class="col-lg-6">
	             <form:form role="form" commandName="registerRequest" action="/register/joinconfirm" method="post">
	                <div class="form-group input-group">
	                    <form:input type="text" class="form-control" placeholder="NAME" path="username"/>
	                </div>
	                <div class="form-group input-group">
	                    <form:input type="text" class="form-control" placeholder="Gender" path="gender"/>
	                </div>
	                <div class="form-group input-group">
	                    <form:input type="text" class="form-control" placeholder="PhoneNumber" path="phone"/>
	                </div>
	                <div class="form-group input-group">
	                    <form:input type="text" class="form-control" placeholder="생년월일 ex)19870425" path="birth"/>
	                </div>
	                <div class="form-group input-group">
	                    <form:input type="text" class="form-control" placeholder="ID" path="userid"/>
	                    <form:errors path="userid"/>
	                </div>
						<div class="form-group input-group">
	                    <form:password class="form-control" placeholder="Password" path="userpw"/>
	                    <form:errors path="userpw"/>
	                </div>
	                <div class="form-group input-group">
	                    <form:password class="form-control" placeholder="Password Check" path="checkuserPw"/>
	                    <form:errors path="checkuserPw"/>
	               	</div>
	                <div class="form-group input-group">
	                    <form:input type="email" class="form-control" placeholder="Email" path="email"/>
	                    <form:errors path="email"/>
	                </div>
	                <div class="form-group input-group">
	                    <form:input type="text" class="form-control" placeholder="주소1" path="adr1"/>
	                </div>
	                <div class="form-group input-group">
	                    <form:input type="text" class="form-control" placeholder="주소2" path="adr2"/>
	                </div>
	                <div class="form-group input-group">
	                    <form:input type="text" class="form-control" placeholder="주소3" path="adr3"/>
	                </div>
	                <button type="submit" class="btn btn-default">가입하기</button>
	                <button type="reset" class="btn btn-default">취소하기</button>
	            </form:form>
	        </div>
	    </div>
	</div>
</body>
</html>