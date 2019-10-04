<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>회원가입</title>
		<script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
	</head>
	<body>
		<div class="panel-body">
	    <div class="row">
	        <div class="col-lg-6">
	             <form:form role="form" commandName="registerRequest" action="/joinConfirm" method="post">
	                <div class="form-group input-group">
	                    <form:input type="text" class="form-control" placeholder="NAME" path="username"/>
	                    <form:errors path="username"/>
	                </div>
	                <div class="form-group input-group">
	                    <form:input type="text" class="form-control" placeholder="Gender" path="gender"/>
	                    <form:errors path="gender"/>
	                </div>
	                <div class="form-group input-group">
	                    <form:input type="text" class="form-control" placeholder="PhoneNumber" path="phone"/>
	                    <form:errors path="phone"/>
	                </div>
	                <div class="form-group input-group">
	                    <form:input type="text" class="form-control" placeholder="생년월일 ex)19870425" path="birth"/>
	                    <form:errors path="birth"/>
	                </div>
	                <div class="form-group input-group">
	                    <form:input type="text" class="form-control" placeholder="ID" path="userid"/>
	                    <form:errors path="userid"/>
						<button type="button" class="idCheck">중복확인</button>
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
	                    <form:errors path="adr1"/>
	                </div>
	                <div class="form-group input-group">
	                    <form:input type="text" class="form-control" placeholder="주소2" path="adr2"/>
	                    <form:errors path="adr2"/>
	                </div>
	                <div class="form-group input-group">
	                    <form:input type="text" class="form-control" placeholder="주소3" path="adr3"/>
	                    <form:errors path="adr3"/>
	                </div>
	                <button type="submit" id="submit" disabled="disabled">가입하기</button>
	                <button type="reset">처음으로</button>
	            </form:form>
	        </div>
	    </div>
	</div>
	<script>
	$("#userid").keyup(function(){
		
		 $("#submit").attr("disabled", "disabled");
		 
		});
	
	$(".idCheck").click(function(){
 
	var query = {userId : $("#userid").val()};
 
	$.ajax({
		url : "/idCheck",
		type : "post",
		data : query,
		success : function(data) {
  		
		if(data == 1) {
			alert("아이디가 존재합니다. 다른 아이디를 입력해주세요.");
			
			$("#submit").attr("disabled", "disabled");
		} else {
			alert("사용 가능한 아이디입니다.");
				
			$("#submit").removeAttr("disabled");
			}
		}
	});  // ajax 끝
	});
	
	</script>
</body>
</html>