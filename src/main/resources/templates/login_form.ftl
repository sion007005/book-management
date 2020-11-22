<!DOCTYPE html>
<html lang="ko">
  <head>
    <#include "/common/head.ftl">
    <script src="http://code.jquery.com/jquery-3.1.0.js"></script>
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
					<input type="text" class="input input-name" data-type="name" placeholder="이메일*" name="email" id="email" value=${("'${email}'")!''}>
				</div>
				<div class="form-input">
					<input type="text" class="input input-gender" data-type="gender" placeholder="비밀번호*" name="password" id="password" value="">
				</div>
			<#if message??>
				<p class="error-msg">${message}</p>
			</#if>
			<input type="hidden" name="returnUrl" id="returnUrl" value=${returnUrl}>
				<div class="form-input">
					<button class="btn-signup button button-primary" id="btnLogin" type="submit">로그인하기</button>
				</div>
			</form>
		</div>
	</div> 
	<div><#include "/common/footer.ftl"></div>   
	<script>
    $(document).ready(function() {
        $("#btnLogin").click(function(e) {
        	const action = $('#loginForm').attr("action");
        	const userEmail = $("#email").val();
        	const password = $("#password").val();
            const params = "email="+userEmail+"&password="+password;
            const returnUrl = $("#returnUrl").val();
 
            if (userEmail == "") {
                alert("이메일을 입력해주세요");
                $("#email").focus();
                return;
            }

			const exp = /[a-z0-9]$/; //이메일 검사 정규식
			
			/* if(!exp.test(userEmail)){
				alert("이메일 형식이 올바르지 않습니다.");
				$("#userEmail").focus();
				return;
			} */
 
            if (password == "") {
                alert("비밀번호를 입력해주세요");
                $("#password").focus();
                return;
            }
 
            $.ajax({
                type : "POST",
                data : params,
                dataType: "json",
                url : action,
                success : function(data) { //로그인 성공 시 보려던 페이지로 redirection
                	if (data.login) {
	                	location.href=returnUrl;
                	} else {
	 					alert("로그인 실패");                		
                	}
                }
            });
 
            /*             
             document.form1.action=""
             document.from1.submit();
             */
        });
 
    });
</script>
  </body>
</html>