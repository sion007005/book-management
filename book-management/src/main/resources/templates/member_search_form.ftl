<html>
	<head>
	  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet"> <!--CDN 링크 -->
	  <link href="https://fonts.googleapis.com/css2?family=Caveat:wght@700&display=swap" rel="stylesheet">
	  <link href="/static/style.css" rel="stylesheet">
	<style>
	/*
html, body, div, span, applet, object, iframe, h1, h2, h3, h4, h5, h6, p, blockquote, pre, a, abbr, acronym, address, big, cite, code, del, dfn, em, img, ins, kbd, q, s, samp, small, strike, strong, sub, sup, tt, var, b, u, i, center, dl, dt, dd, ol, ul, li, fieldset, form, label, legend, table, caption, tbody, tfoot, thead, tr, th, td, article, aside, canvas, details, embed, figure, figcaption, footer, header, hgroup, menu, nav, output, ruby, section, summary, time, mark, audio, video {
    margin: 0;
    padding: 0;
    border: 0;
    font-size: 100%;
    vertical-align: baseline;
}

body {
    font-size: 14px;
    line-height: 1.4;
    color: #212329;
    letter-spacing: -0.04em;
    font-family: 'Roboto', 'Noto Sans KR', sans-serif,'Dotum', helvetica;
}

html, body, #wrapper {
    height: 100%;
}

input {
    border-radius: 0;
    outline: 0;
}

input:disabled {
    color: #d7dbe6;
}

.input {
    display: block;
    position: relative;
    width: 100%;
    height: 36px;
    padding: 4px 0 3px;
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

p {
    display: block;
    /* margin-block-start: 1em;
    margin-block-end: 1em; */
    margin-inline-start: 0px;
    margin-inline-end: 0px;
}

.join-signup-container .signup-container {
    margin: 0 auto;
    padding: 80px 0 60px;
    width: 328px;
}

.signup-container {
    position: relative;
    margin: 0 auto;
    padding: 50px 0 80px;
    width: 1112px;
}
  

 .signup-container h1 {
    display: block;
    margin-bottom: 40px;
    text-align: center;
    font-size: 40px;
    color:  #ccc;
    font-family: 'Caveat', cursive;
}

.form {
    display: block;
}

.form-input {
    position: relative;
}

.form-input input {
    padding: 12px 14px;
    border: 1px solid #d7dbe6;
    font-size: 16px;
    line-height: 24px;
    letter-spacing: -0.6px;
    box-sizing: border-box;
    transition: border-color 0.15s ease-in-out;
}

.form-input input::placeholder {
    color: #d7dbe6;
}

.signup-container .form-input input {
    width: 328px;
    height: 50px;
}

.form-input .input.error{
    border-color: red;
}

.signup-container .form-input.form-search {
    overflow: hidden;
}

.signup-container .form-input.form-search input {
    float: left;
    width: 150px;
}

.signup-container .form-input.form-search span {
    float: left;
    display: block;
    padding-top: 15px;
    width: 28px;
    text-align: center;
    font-family: 'NotoSansKR';
}

.signup-guide {
    margin: 50px 0 20px;
    font-size: 16px;
    padding-right: 16px;
    line-height: 25px;
    letter-spacing: -0.4px;
    color: #212329;
    font-weight: normal;
}

.signup-guide:first-child {
    margin-top: 0;
}

.form-input {
    margin-top: 10px;
}

.form-input:disabled {
    color: #82929f;
}


button {
    border: 2px solid #ccc;
    display: inline-block;
    padding: 16px 0 14px;
    text-align: center;
    /*color: #bbc0cd;*/
    color: gray;
    font-weight: bold;
    font-size: 18px;
    border-radius: 0;
    background: none;
    width: 100%;
    margin-top: 10px;
}

.form-input.has-button .btn-small {
    position: absolute;
    top: 0;
    right: 0;
    border-color: #1a7cff;
    color: #1a7cff;
}




/* .btn-small:disabled {
    border-color: #ccc;
    color: #bbc0cd;
} */

.form-input.has-button .btn-small:disabled {
    border-color: #ccc;
    color: #bbc0cd;
}

.hidden {
    display: none;
}

#keyword-input {
	display: none;
}

#age-input {
	display: none;
}


signup-container .form-input .input-age {
    width: 155px;
    height: 50px;
    display: inline;
}


.input-age {
    width: 155px;
}


*/
	</style>

	</head>
	
	<body>
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