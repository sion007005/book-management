<!DOCTYPE html>
<html lang="ko">
  <head>
    <#include "/common/head.ftl">
  </head>
	
	<body>
	<div><#include "/common/header.ftl"></div>
    <div><#include "/common/menu.ftl"></div>
	  <div id="wrapper">
        <div class="join-signup-container">
          <div class="signup-container">
            <h1>Member Search</h1>
            <form action="./search" method="GET">	
              <div class="form-input form-search-select">
                <select name="search-type" id="form-search-select">
                  <option value="검색기준" noSelected>검색기준</option>
                  <option value="name">이름</option>
                  <option value="age">나이</option>
                  <option value="email">이메일</option>
                  <option value="phone">휴대폰</option>
                </select>               
       	      </div>
              <div class="form-input keyword" id="keyword-input">
			    <input type="text" class="input input-keyword" placeholder="검색어*" name="keyword" value="">
			  </div>
			  <div class="form-input keyword" id="age-input">
			    <input type="text" class="input input-age" placeholder="시작 나이*" name="age-from" value=""> ~ 
			    <input type="text" class="input input-age" placeholder="끝 나이*" name="age-to" value="">
			  </div>
			  <button type="submit" class="button"><span>검색하기</span></button>
			  <a href="./intro"><button class="button"><span>초기화면으로</span></button></a>
			</form>
		  </div>
        </div>
      </div>
      <div><#include "/common/footer.ftl"></div>
      <script>
        const selectedSearchType = document.querySelector('#form-search-select');
        const keywordInput = document.querySelector('#keyword-input');
        const ageInput = document.querySelector('#age-input');
        selectedSearchType.addEventListener('click', getSearchForm);

        function getSearchForm(e) {
          const selected = e.target.value;
        console.log("클릭 ", selected);
          const emailInput = document.querySelector('.email-end');

          if(selected === '검색기준') {
            keywordInput.style.display = 'none';
            ageInput.style.display = 'none';
    	  } else if(selected === 'age') {
    	    keywordInput.style.display = 'none';
            ageInput.style.display = 'inline-block';
          } else {
          console.log("나이말고클릭");
            ageInput.style.display = 'none';
            keywordInput.style.display = 'block';
          }
};
      </script>
	</body>
</html>