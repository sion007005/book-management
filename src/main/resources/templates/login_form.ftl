<!DOCTYPE html>
<html lang="ko">
  <head>
    <#include "/common/head.ftl">
  </head>
  <body>
  	<#include "/common/header.ftl">
    <#include "/common/menu.ftl">
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
					<input type="password" class="input input-gender" data-type="gender" placeholder="비밀번호*" name="password" id="password" value="">
				</div>
				<p id="error-msg" class="error-msg"></p>
				<input type="hidden" name="returnUrl" id="returnUrl" value=${("'${returnUrl}'")!'/'}>
				<div class="form-input">
					<button class="btn-signup button button-primary" id="btnLogin" type="submit">로그인하기</button>
				</div>
			</form>
		</div>
	</div> 
	<#include "/common/footer.ftl">  
	<script>
    $(document).ready(function() {
        $("#btnLogin").click(function(e) {
        	e.preventDefault();
        	$("#btnLogin").attr("disabled", true); 	
        	const action = $('#loginForm').attr("action");
        	const userEmail = $("#email").val();
        	const password = $("#password").val();
        	const returnUrl = $("#returnUrl").val();
            const params = "email="+userEmail+"&password="+password+"&returnUrl="+returnUrl;
 
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
                success : function(res) { //로그인 성공 시 보려던 페이지로 redirection
                	if(res.login) {
                		alert("로그인성공");
                		console.log("success",res)
	                	location.href = res.returnUrl;
                	} else {
                		console.log("fail",res);
                		$("#password").val("");
						$("#error-msg").text(res.errorMessage);					
                		$("#btnLogin").attr("disabled", false); 	
                	}
                },
                fail: function(e) {
                	console.log(e);
					$("#error-msg").text("예기치 않은 오류가 발생했습니다. 다시 시도해주세요.");
					$("#btnLogin").attr("disabled", false);
                }
            });
        });
    });
	</script>
	</body>
</html>