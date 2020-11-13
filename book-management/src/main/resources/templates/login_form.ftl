<!DOCTYPE html>
<html lang="ko">
  <head>
    <#include "/common/head.ftl">
  </head>
  
  <body>
  	<div><#include "/common/header.ftl"></div>
    <div><#include "/common/menu.ftl"></div>
    <div id="wrapper">
		<div class="content-container">
			<div class="title">
				<h1>Login</h1>
			</div>
			<form action="/login" method="POST">
			<#if email??>
				<div class="form-input">
					<input type="text" class="input input-name" data-type="name" placeholder="이메일*" name="email" value=${email}>
				</div>
			<#else>
				<div class="form-input">
					<input type="text" class="input input-name" data-type="name" placeholder="이메일*" name="email" value="">
				</div>
			</#if>
				<div class="form-input">
					<input type="text" class="input input-gender" data-type="gender" placeholder="비밀번호*" name="password" value="">
				</div>
			<#if message??>
				<p class="error-msg">${message}</p>
			</#if>
				<div class="form-input">
					<button class="btn-signup button button-primary" type="submit">로그인하기</button>
				</div>
			</form>
		</div>
	</div> 
	<div><#include "/common/footer.ftl"></div>   
  </body>
</html>