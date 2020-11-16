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
				<#if member??>
				<h1>Member Update</h1>
				<#else>
				<h1>Member Register</h1>
				</#if>
			</div>
			<#if member??>
			<form action="/members/update?id=${member.id}" method="POST">
			<#else>
			<form action="/members/create" method="POST">
			</#if>
				<div class="form-input">
				  <#if member??>
					<input type="text" class="input input-name" data-type="name" placeholder="이름*" name="name" value="${member.name}">
				  <#else>
					<input type="text" class="input input-name" data-type="name" placeholder="이름*" name="name" value="">
				  </#if>
				</div>
				<div class="form-input">
				  <#if member??>
					<input type="text" class="input input-name" data-type="gender"
						placeholder="이름*" name="gender" value="${member.gender}">
				  <#else>
					<input type="text" class="input input-gender" data-type="gender"
						placeholder="성별*" name="gender" value="">
				  </#if>
				</div>
				<div class="form-input">
				  <#if member??>
				    <input type="text" class="input input-age" data-type="age"
						placeholder="나이*" name="age" value="${member.age}">
				  <#else>
				  <input type="text" class="input input-age" data-type="age"
						placeholder="나이*" name="age" value="">
				  </#if>
				</div>
				<div class="form-input form-email">
					<#if member??>
					 <#assign idx = member.email?index_of('@')>
                      <input type="text" name="email-front" value="${member.email?substring(0,idx)}" class="input email-front" data-type="email" placeholder="이메일 앞자리*">
					  <span>@</span>
					  <input type="text" name="email-end" class="input email-end" data-type="check-email" placeholder="이메일 뒷자리*" value="${member.email?substring(idx+1)}" disabled>
					<#else>
					  <input type="text" name="email-front" class="input email-front" data-type="email" placeholder="이메일 앞자리*" value=""> 
					  <span>@</span>
					  <input type="text" class="input email-end" data-type="check-email" placeholder="이메일 뒷자리*" disabled>
					</#if>	
				</div>
				<div class="form-input form-email-select">
					<select name="form-email-select" id="form-email-select">
					  <#if member??>
					 <#assign idx = member.email?index_of('@')>
						<option value="이메일 선택">이메일 선택</option>
						
						<#if member.email?substring(idx+1) == "naver.com">
						<option value="naver.com" selected>naver.com</option>
						<#else>
						<option value="naver.com">naver.com</option>
						</#if>
						
						<#if member.email?substring(idx+1) == "hanmail.net">
						<option value="hanmail.net" selected>hanmail.net</option>
						<#else>
						<option value="hanmail.net">hanmail.net</option>
						</#if>
						
						<#if member.email?substring(idx+1) == "nate.com">
						<option value="nate.com" selected>nate.com</option>
						<#else>
						<option value="nate.com">nate.com</option>
						</#if>
						
						<#if member.email?substring(idx+1) == "gmail.com">
						<option value="gmail.com" selected>gmail.com</option>
						<#else>
						<option value="gmail.com">gmail.com</option>
						</#if>						
						<option value="직접입력">직접입력</option>
					  
					  <#else>
					    <option value="이메일 선택" noSelected>이메일 선택</option>
						<option value="naver.com">naver.com</option>
						<option value="hanmail.net">hanmail.net</option>
						<option value="nate.com">nate.com</option>
						<option value="gmail.com">gmail.com</option>
						<option value="직접입력">직접입력</option>
					  </#if>
					</select>
				</div>
				<div class="form-input">
					<#if member??>
					<input class="input password" name="password" data-type="password"
					placeholder="비밀번호 변경*" type="password" maxlength="20" value=${member.password}
					onkeyup="this.value=this.value.replace(/' '/g,'');">
					<#else>
					<input class="input password" name="password" data-type="password"
					placeholder="비밀번호 설정*" type="password" maxlength="20" 
					onkeyup="this.value=this.value.replace(/' '/g,'');">
					</#if>
				</div>
				<div class="form-input has-button">
					<#if member??>
					<input class="input phone" name="phone" data-type="phone"
						placeholder="휴대폰*" type="tel" maxlength="11" value=${member.phone}
						onkeyup="this.value=this.value.replace(/[^0-9]/g,'');">
					<#else>
					<input class="input phone" name="phone" data-type="phone"
					placeholder="휴대폰*" type="tel" maxlength="11" 
					onkeyup="this.value=this.value.replace(/[^0-9]/g,'');">
					</#if>
					<button class="btn-get-code btn-small" disabled>
						<span>인증받기</span>
					</button>
				</div>
		  		<#if member??>
				  <div class="form-input" style="display:none">
					<input type="text" class="input input-created" data-type="created" placeholder=등록일* name="createdAt" value="${member.createdAt?string('yyyy-MM-dd hh:mm:ss')}">
		  		</div>
		  		</#if>
				<div class="form-input">
					<#if member??>
					<button class="btn-signup button button-primary" type="submit">수정하기</button>
					<#else>
					<button class="btn-signup button button-primary" type="submit">등록하기</button>
					</#if>
				</div>
			</form>
		</div>
	</div>    
    
    <div><#include "/common/footer.ftl"></div>
    <script>
    	const selectEmailBtn = document.querySelector('#form-email-select');
		selectEmailBtn.addEventListener('click', getEmailAddress);
		
		function getEmailAddress(e) {
    		const selected = e.target.value;
    		const emailInput = document.querySelector('.email-end');
        	
    		if(selected === '직접입력') {
        		emailInput.placeholder = '';
        		emailInput.value = '';
        		emailInput.disabled = false;
        	} else {
        		emailInput.value = selected;
        		emailInput.disabled = true;
        	}
    	};
	</script>
  </body>
</html>