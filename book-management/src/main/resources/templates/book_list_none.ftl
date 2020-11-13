<html>
  <head>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet"> <!--CDN 링크 -->
	<link href="https://fonts.googleapis.com/css2?family=Caveat:wght@700&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap" rel="stylesheet">
	<link href="/static/style.css" rel="stylesheet">
  </head>
  
  <body>
    <div id="wrapper">
      <div class="button join-container">
      	<div><form action="/books/form"><button type="submit" class="btn-join">New Book</button></form></div>
      </div>
      <div class="title">
        <h1>Book List</h1>
      </div>
      <form action="./search" method="GET">
      <div class="search-container">
        <div class="search-area">
          <div class="group_flex">
       	   <!-- TODO 카테고리 리스트 받아와서 카테고리 검색 select box 만들기  -->
            <div class="select-box">
              <select name="search-type" id="form-search-select">
                <option value="" noSelected>검색기준</option>
                <option value="title">제목</option>
                <option value="author">저자</option>
              </select>               
       	    </div>
       	    <div class="input input-box keyword">
			  <input type="text" class="input input-text" placeholder="검색어*" name="keyword" value="">
			</div>
			<button type="submit" class="button btn-submit"><span>검색</span></button>
       	  </div>
       	</div>
      </div>     
      </form>
      <div id="list-container">
        <div class="list-area">
          	<div class="select-box">
              <form action="/books/list" id="order-select">
              <select name="order-type" id="form-search-select">
                <option value="" noSelected>정렬기준</option>
                <option value="title">제목</option>
                <option value="author">저자</option>
                <option value="price">가격</option>
                <option value="year">출판년도</option>
                <option value="stock">재고</option>
              </select>               
              </form>
          	</div>
          <div class="group_flex">
            <div>제목</div>
            <div>저자</div>
            <div>가격</div>
            <div>출판년도</div>
            <div>재고</div>
          </div>
        </div>
      </div>
      <div id="result-container">
        <div class="result-area">
          <div class="group_flex">
            <div class="no-result-msg">검색 결과가 없습니다.</div>
          </div>
        </div>
      </div>
    </div>    

  </body>
</html>