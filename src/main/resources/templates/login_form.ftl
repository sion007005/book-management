<!DOCTYPE html>
<html lang="ko">
  <head>
    <#include "/common/head.ftl">
    <script src="http://code.jquery.com/jquery-3.1.0.js"></script>
	<script>
    $(document).ready(function() {
        alert("로그인 준비!");
        
        $("#btnLogin").click(function() {
        	var action = $('#loginForm').attr("action");
            //var userEmail = $("#userEmail").val();
            //var password = $("#userPassword").val();
            var data = {
            		userEmail: $("#userEmail").val(),
            		password: $("#userPassword").val()
            };
 
            if (userEmail == "") {
 
                alert("이메일을 입력해주세요");
                $("#userEmail").focus();
                return;
            }

			var exp = /[a-z0-9]$/; //영문자와 숫자
			
			if(!exp.test(userEmail)){
				alert("영문자와 숫자만 입력가능합니다.");
				$("#userid").focus();
				return;
			}
 
            if (password == "") {
                alert("비밀번호를 입력해주세요");
                $("#userPassword").focus();
                return;
            }
 
            //비동기 ajax 방식으로 데이터 주고 받는 방법
            var data = "userEmail=" + userEmail + "&password=" + password;
 
            $.ajax({
                type : "POST",
                data : data,
                //datatype은 json으로 
                url : action,
                success : function(value) { //로그인 성공 시 보려던 페이지로 redirection
                    console.log("value: " + value);
                	location.href="";
 
                }
 
            });
 
            /*             
             document.form1.action=""
             document.from1.submit();
             */
        });
 
    });
</script>
  </head>
  
  <body>
  	<div><#include "/common/header.ftl"></div>
    <div><#include "/common/menu.ftl"></div>
    <div id="wrapper">
		<div class="content-container">
			<div class="title">
				<h1>Login</h1>
			</div>
			<form action="/login" method="POST" id="loginForm"  name="loginForm">
				<div class="form-input">
					<input type="text" class="input input-name" data-type="name" placeholder="이메일*" name="email" id="userEmail" value=${("'${email}'")!''}>
				</div>
				<div class="form-input">
					<input type="text" class="input input-gender" data-type="gender" placeholder="비밀번호*" name="password" id="userPassword" value="">
				</div>
			<#if message??>
				<p class="error-msg">${message}</p>
			</#if>
			<input type="hidden" name="returnUrl" value=${returnUrl}>
				<div class="form-input">
					<button class="btn-signup button button-primary" id="btnLogin" type="submit">로그인하기</button>
				</div>
			</form>
		</div>
	</div> 
	<div><#include "/common/footer.ftl"></div>   
  </body>
</html>