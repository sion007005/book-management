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
					<input type="text" class="input input-name" data-type="name" placeholder="이름*" name="name" value=${("'${member.name}'")!''}>
				</div>
				<div class="form-input">
					<input type="text" class="input input-name" data-type="gender" placeholder="성별*" name="gender" value=${("'${member.gender}'")!''}>
				</div>
				<div class="form-input">
				  <input type="text" class="input input-age" data-type="age" placeholder="나이*" name="age" value=${("'${member.age}'")!''}>
				</div>
					<#if member??>
					 <#assign idx = member.email?index_of('@')>
					<div class="form-input form-email" style="display:none;">
                      <input type="text" name="email-front" value=${member.email?substring(0,idx)} class="input email-front" data-type="email" id="email-front" placeholder="이메일 앞자리*"  readOnly/>
					  <span>@</span>
					  <input type="text" name="email-end" value=${member.email?substring(idx+1)} class="input email-end" id="email-end" data-type="check-email" placeholder="이메일 뒷자리*" readOnly />
					</div>
					<#else>
					<div class="form-input form-email">
					  <input type="text" name="email-front" class="input email-front" id="email-front" data-type="email" placeholder="이메일 앞자리*" value=""> 
					  <span>@</span>
					  <input type="text" class="input email-end" name="form-email-select" data-type="check-email" id="email-end" placeholder="이메일 뒷자리*">
					</div>
					</#if>	
					<div style="display:none;" class="form-input form-select-container">
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
				<div id="message" class="message"></div>
				<div id="error-message" class="error-message"></div>
				
				<div class="form-input">
					<#if member??>
					<input type="password" style="display:none;" class="input password" name="password" data-type="password" maxlength="20" value=${member.password}/>
					<#else>
				</div>
				<div class="form-input">
					<input class="input password" name="password" data-type="password"
					placeholder="비밀번호 설정*" type="password" maxlength="20" 
					onkeyup="this.value=this.value.replace(/' '/g,'');">
					</#if>
				</div>
				<div class="form-input has-button">
					<input class="input phone" name="phone" data-type="phone"
						placeholder="휴대폰*" type="tel" maxlength="11" 
						onkeyup="this.value=this.value.replace(/[^0-9]/g,'');" value=${("'${member.phone}'")!''}>
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
        	
    		if (selected === '이메일 선택') {
    			return; 
    		}
    		
    		if(selected === '직접입력') {
        		emailInput.placeholder = '';
        		emailInput.value = '';
        		emailInput.disabled = false;
        		return;
        	} 
    		
       		emailInput.value = selected;
       		emailInput.disabled = true;
    	};
	</script>
	<script>
		$(document).ready(function(){
	    	$('#email-front').blur(checkEmail);
	    	$('#email-end').blur(checkEmail);
	    })	
	    
	    function checkEmail() {
	   		const action = '/check/email';
	   		let emailFront = $("#email-front").val();
	   		let emailEnd = $("#email-end").val();
	   		const params = "email=" + emailFront + "@" + emailEnd;
	   		const regex = RegExp(/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i);
	
	   		if (!emailFront || !emailEnd) {
	   			$("#message").text("");
	   			$("#error-message").text("이메일을 입력해주세요.");
	   			return;
	   		}
	   		
	   		if (!regex.test(emailFront + "@" + emailEnd)) {
	   			console.log("이메일 :" +emailFront + "@" + emailEnd );
	   			$("#message").text("");
	   			$("#error-message").text("이메일 형식이 올바르지 않습니다.");
	   			return;
	   		}
	   		
	   		$("#form-email-select").prop("disabled", true);
	   		
	   		$.ajax({
	   			type: 'POST',
	   			url: action,
	   			data: params,
	   			dataType: "json",
	   			success: function(res) {
	   				if (!res.valid) {
	   					$("#error-message").text(res.errorMessage);
	   					$("#message").text("");
	   					return;
	   				}
	   				
	   				$("#message").text(res.message);
	   				$("#error-message").text("");
	   			},
	   			error: function(e) {
	   				alert("예기치 않은 오류가 발생했습니다. 다시 시도해주세요.");
	   			}
	   		})
	    	}
	</script>
  </body>
</html>