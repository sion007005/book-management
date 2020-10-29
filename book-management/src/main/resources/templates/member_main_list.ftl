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
	    height: 36px;
	    margin: 0;
	    border-radius: 0;
	    border: 1px solid #fff;
	    border-bottom: 1px solid #d7dbe6;
	    font-size: 17px;
	    line-height: 1.63;
	    letter-spacing: -0.5px;
	    color: black;
	    background: #fff;
	    box-sizing: border-box;
	    white-space: nowrap;
	    overflow: hidden;
	    text-overflow: ellipsis;
      }
       
	  #wrapper {
	    overflow: hidden;
        min-width: 1190px;
	  }
	  
	  #wrapper .join-container {
	  	position: relative;
        width: 1190px;
        padding: 15px 30px;
        margin: 0 auto;
	  }
	  
	  .join-container div {
	    width: 1190;
        display: flex;
        margin: 0 auto;
        justify-content: flex-end;
        color: #32424B;
        
	  }
	  
	  .join-container div .btn-join {
	    height: 30px;
        width: 120px;
        font-size: 17px;
        background-color: #fff;
        border: 2px solid #828282;
        cursor: pointer;
        font-family: 'Caveat', cursive;
	  }
	  
	  .join-container div .btn-join:hover {
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
	  
	  .search-container {
	    position: relative;
	  }
	  
	  .search-container .search-area {
	    position: relative;
	    height: 120px;
	    border-boton: 1px solid #e4e8eb;
	    border-radius: 2px;
	  }
	  
	  .search-container .group_flex {
	    display: flex;
        justify-content: center;
	    position: relative;
	    width: 1130px;
	    margin: 0 auto;
	    padding: 0 30px;	
	  }
	  
	  
	  .search-container .select-box {
	    height: 40px;	
	  }
	  
	  .search-container #form-search-select {
	  	padding: 5px 10px;
	  	width: 130px;
	  	height: 40px;
	  	border: 2px solid #828282;
	  	border-radius: 2px;
	  }
	  
	  .search-container .input-box {
	  	position: relative;
	  	margin: 0 2 0 30;
        width: 400px;
	  	height: 40px;
	  	border: 2px solid #828282;
	 	border-radius: 2px;
	 	border-radius: 2px;
	  }
	  
	  .search-container .input-box .input-text {
	  	width: 400px;
	  	padding: 13px;
	  	margin: 1px;
	  	font-size: 15px;
	  	line-height: 24px;
	  	font-weight: 300;
	  	border-bottom: none;
	  	outline: 0;
	  }
	  
	  .search-container .btn-submit {
	  	height: 40px;
	  	width: 62px;
	  	background-color: #fff;
	  	border: 2px solid #828282;
	  	cursor: pointer;
	  }
	  
	  .search-container .age {
	    margin: 2px 0 0 10px;
	    height: 34px;
	    width: 36px;
        font-size: 30px;
	  }
	  
	  .search-container .age .input-text{
	    padding: 5px;
	  	margin: 1px;
	  	font-size: 10px;
	  	line-height: 14px;
	  	font-weight: 300;
	  	height: auto;
	  	border-bottom: none;
	  	outline: 0;
	  }
	  
	  .search-container .age_flex_box {
	  	display: flex;
	  }
	  
	  .search-container .age-text {
	    font-size: 13px;
        line-height: 34px;
        margin-left: 1px;
	  }
	  
	  .search-container .age-text:nth-child(1) {
	    margin-left: 450px;
	  }
	  
	  #order-select {
        padding: 5px 12px;
        width: 70px;
        border: 2px solid #32424B;
        border-radius: 2px;
	  }
	  
	  #order-select select {
        border: none;
        background: none;
        font-color: #32424B;
	  }
	  
	  #list-container {
	  	position: relative;
	  	width: 1130px;
	  	padding: 15px 30px;
	  	margin: 0 auto;
	  	background: beige;
	  }
	  
	  #list-container .group_flex {
	    width: 800px;
	  	display: flex;
	  	margin: 0 auto;	
	  	justify-content: space-between;
	  	color: #32424B;
	  	font-family: 'Noto Sans KR', sans-serif;
	  }
	  
	  
	  #result-container {
	  	position: relative;
	  	width: 1130px;
	  	padding: 15px 30px;
	  	margin: 0 auto;
	  	background: beige;
	  }
	  
	  #result-container .group_flex {
	    width: 800px;
	  	display: flex;
	  	margin: 2px auto;	
	  	justify-content: space-between;
	  	color: #565051;
	  	font-family: 'Noto Sans KR', sans-serif;
	  }
	  
	  #result-container .group_flex a {
	    text-decoration: none;
	    color: #565051;
	  }
	  
	   #result-container .group_flex a:hover {
	    color: gray;
	  }
	  
	  .button-delete {
	  	background: beige;
        padding: 1px 1px;
        color: #32424B;
	  }
	  
	  .button-delete:hover {
	  	background: #32424B;
	  	color: #eee;
	  }
	  
	  
    </style>
  </head>
  
  <body>
    <div id="wrapper">
      <div class="button join-container">
      	<div><form action="./form"><button type="submit" class="btn-join">New Member</button></form></div>
      </div>
      <div class="title">
        <h1>Member List</h1>
      </div>
      <form action="./search" method="GET">
      <div class="search-container">
        <div class="search-area">
          <div class="group_flex">
            <div class="select-box">
              <select name="search-type" id="form-search-select">
                <option value="age" noSelected>나이로만 검색</option>
                <option value="name">이름</option>
                <option value="email">이메일</option>
                <option value="phone">휴대폰</option>
              </select>               
       	    </div>
       	    <div class="input input-box keyword">
			  <input type="text" class="input input-text" placeholder="검색어*" name="keyword" value="" disabled>
			</div>
			<button type="submit" class="button btn-submit"><span>검색</span></button>
       	  </div>
       	  <div class="age_flex_box">
       	    <div class="age-text">나이: </div>
       	  	<div class="input input-box age">
			  <input type="number" class="input input-text" maxlength=2 name="age-from" value=1>
			</div>
	        <div class="age-text">부터</div>
	        <div class="input input-box age" style="margin-left:2px">
			  <input type="number" class="input input-text" maxlength=2 name="age-to" value=100>
			</div>
			<div class="age-text">까지</div>
		  </div>
       	</div>
      </div>     
      </form>
      <div id="list-container">
        <div class="list-area">
          	<div class="select-box">
              <form action="./list" id="order-select">
              <select name="order-type" id="form-order-select">
                <option value="" noSelected>정렬기준</option>
                <option value="NAME">이름</option>
                <option value="AGE">나이</option>
                <option value="EMAIL">이메일</option>
                <option value="PHONE">휴대폰</option>
              </select>               
              </form>
          	</div>
          <div class="group_flex">
            <div>이름</div>
            <div>성별</div>
            <div>나이</div>
            <div>휴대폰</div>
            <div>이메일</div>
          </div>
        </div>
      </div>
      <div id="result-container">
        <div class="result-area">
         <#list body as item>
          <div class="group_flex">
          <!-- 가급적 절대경로를 쓸 것 -->
            <div><a href="/members/info?id=${item.id}">${item.name}</a></div>
            <div>${item.gender}</div>
            <div>${item.age}</div>
            <div>${item.phone}</div>
            <div>${item.email}</div>
          </div>
         </#list>
        </div>
      </div>
    </div>    
    
    <script>
      const searchSelectBox = document.querySelector('.select-box');
	  const orderSelectBox = document.querySelector('#order-select');
	  const keywordInput = document.querySelector('.input-text');
	  const removeBtn = document.querySelector('.button-delete');
	  
      orderSelectBox.addEventListener("click", submitOrderType);
      searchSelectBox.addEventListener("click", toggleKeywordInput);
      
      function toggleKeywordInput(e) {
    	  const selected = e.target.value;
    	  
    	  if(selected === 'age') {
    		  keywordInput.disabled = true;
    	  } else {
    		  keywordInput.disabled = false;
    	  }
      }
      
      function submitOrderType(e) {
    	  const selected = e.target.value;
    	  
    	  if(selected === 'noSelected' || selected === undefined) {
    		  return;
    	  }
    	  
    	  orderSelectBox.submit();
      }
     
    </script>
  </body>
</html>