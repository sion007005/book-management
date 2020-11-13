<html>
  <head>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet"> <!--CDN 링크 -->
	<link href="https://fonts.googleapis.com/css2?family=Caveat:wght@700&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap" rel="stylesheet">
	<link href="/static/style.css" rel="stylesheet">
	<link rel="SHORTCUT ICON" href="/favicon.ico" />
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
                <option value="" noSelected>검색기준</option>
                <option value="NAME">이름</option>
                <option value="EMAIL">이메일</option>
                <option value="PHONE">휴대폰</option>
              </select>               
       	    </div>
       	    <div class="input input-box keyword">
			  <input type="text" class="input input-text" placeholder="검색어*" name="keyword" value="">
			</div>
			<button type="submit" class="button btn-submit"><div class="search-button">검색</div></button>
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
         <#list memberList as item>
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
	  const orderSelectBox = document.querySelector('#order-select');
      orderSelectBox.addEventListener("click", submitOrderType);
      
      function submitOrderType(e) {
    	  const selected = e.target.value;
    	  
    	  if(selected === '' || selected === undefined) {
    		  return;
    	  }
    	  
    	  orderSelectBox.submit();
      }
     
    </script>
  </body>
</html>