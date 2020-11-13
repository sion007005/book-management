<html>
  <head>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet"> <!--CDN 링크 -->
	<link href="https://fonts.googleapis.com/css2?family=Caveat:wght@700&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap" rel="stylesheet">
	<link href="/static/style.css" rel="stylesheet">
  </head>
  
  <body>
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
  </body>
</html>