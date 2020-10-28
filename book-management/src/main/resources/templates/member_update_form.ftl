<html>
  <head>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet"> <!--CDN 링크 -->
	<link href="https://fonts.googleapis.com/css2?family=Caveat:wght@700&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap" rel="stylesheet">
	<style>
	  
	  body, button, dd, dl, dt, fieldset, form, h1, h2, h3, h4, h5, h6, 
	  input, legend, li, ol, p, select, table, td, textarea, th, ul {
        margin: 0;
        padding: 0;
      }
      
	  .input {
	    display: block;
	    position: relative;
	    width: 100%;
	    /*width: 328px;*/
    	height: 50px;
	    margin: 0;
	    border-radius: 0;
	    border: 2px solid #32424B;
	    font-size: 17px;
	    line-height: 1.63;
	    letter-spacing: -0.5px;
	    color: black;
	    background: #fff;
	    box-sizing: border-box;
	    white-space: nowrap;
	    overflow: hidden;
	    text-overflow: ellipsis;
        outline: 0;
        margin-bottom: 10px;
      }
      
      .button {
        display: inline-block;
        padding: 16px 0 14px;
        width: 100%;
        text-align: center;
        font-size: 18px;
        font-weight: bold;
      }
      
      input:disabled {
        color: #d7dbe6;
      }
       
	  #wrapper {
	    overflow: hidden;
        min-width: 1190px;
	  }
	  
	  .btn-join {
	    height: 30px;
        width: 120px;
        font-size: 17px;
        background-color: #fff;
        border: 2px solid #828282;
        cursor: pointer;
        font-family: 'Caveat', cursive;
	  }
	  
	  .btn-join:hover {
        background-color: #32424B;
        border: 2px solid #fff;
        color: #fff;
        transition-duration: 0.3s;
	  }
	  
	  #wrapper h1 {
	    display: block;
        margin: 0 0 30;
        text-align: center;
        font-size: 40px;
        color:  #32424B;
        font-family: 'Caveat', cursive;
	  }
	  
	 .content-container {
	   padding: 60px 0;
       background: beige;
       position: relative;
       margin: 0 auto;
       width: 800px;
       height: 83%;
	 }
	 
	 .content-container form {
	   padding-top: 20px;
       width: 400px;
       margin: 0 auto;
	 }
	  
	 .content-container form div {
	   maring-bottom: 10px;
	 }
	 
	 .content-container input {
	   padding: 12px 14px;
       height: auto;
       border: 1px solid #d7dbe6;
       font-size: 16px;
       line-height: 24px;
       letter-spacing: -0.6px;
       box-sizing: border-box;
	 }
	 
	 .content-container .form-input.form-email {
       overflow: hidden;
     }
     
     .content-container .form-input.form-email input {
       float: left;
       width: 185px;
     }
     
    .content-container .form-input.form-email span {
      float: left;
      display: block;
      padding-top: 15px;
      width: 28px;
      text-align: center;
      font-family: 'NotoSansKR';
    }

    .content-container .form-input.form-email-select select {
      margin-bottom: 10px;
      padding: 5px 12px;
      font-size: 16px;
      height: 50px;
      border: 1px solid #d7dbe6;
      box-sizing: border-box;
      background-size: 16px 16px;
      width: 400px;
    }
    
    .form-input.has-button {
    position: relative;
    padding-right: 106px;
    }
    
    .form-input.has-button .btn-small {
    position: absolute;
    top: 0;
    right: 0;
    border-color: #1a7cff;
    color: #1a7cff;
}
.signup-container .has-button input {
    width: 220px;
    height: 50px;
}
.btn-small {
    display: inline-block;
    position: absolute;
    top: 0;
    right: 0;
    padding: 14px 0 12px;
    text-align: center;
    font-weight: bold;
    font-size: 16px;
    line-height: 22px;
    border: 1px solid;
    background: #fff; 
    width: 98px;
}

.form-input.has-button .btn-small:disabled {
    border-color: #ccc;
    color: #bbc0cd;
}
	  
    </style>
  </head>
  
  <body>
    <div id="wrapper">
      <div class="content-container">
        <div class="title">
          <h1>Member Update</h1>
        </div>
        <form action="./update?id=${body.id}" method="POST">
				<div class="form-input">
					<input type="text" class="input input-name" data-type="name" placeholder="이름*" name="name" value="${body.name}">
				</div>
				<div class="form-input">
					<input type="text" class="input input-gender" data-type="gender" placeholder="성별*" name="gender" value="${body.gender}">
				</div>
				<div class="form-input">
					<input type="text" class="input input-age" data-type="age" placeholder="나이*" name="age" value="${body.age}">
				</div>
				 <div class="form-input form-email">
				   <#assign idx = body.email?index_of('@')>
                      <input type="text" name="email-front" value="${body.email?substring(0,idx)}" class="input email-front" data-type="email" placeholder="이메일 앞자리*">
                      <span>@</span>
                      <input type="text" name="email-end" class="input email-end" data-type="check-email" placeholder="이메일 뒷자리*" value="${body.email?substring(idx+1)}" disabled>
                    </div>
                    <div class="form-input form-email-select">
                        <select name="form-email-select" id="form-email-select">
                            <option value="${body.email?substring(idx+1)}" noSelected>이메일 선택</option>
                            <option value="naver.com">naver.com</option>
                            <option value="hanmail.net">hanmail.net</option>
                            <option value="nate.com">nate.com</option>
                            <option value="gmail.com">gmail.com</option>
                            <option value="직접입력">직접입력</option>
                        </select>
                    </div>
				   <div class="form-input has-button">
                      <input class="input phone" name="phone" value="${body.phone}"
                             data-type="phone" placeholder="휴대폰*" type="tel" maxlength="11" onkeyup="this.value=this.value.replace(/[^0-9]/g,'');">
                      <button class="btn-get-code btn-small" disabled><span>인증받기</span></button>
                   </div>
                   <div class="form-input">
                     <button class="btn-signup button button-primary" type="submit">수정하기</button>
                   </div>
			</form>
      </div>
    </div>    
    
    
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