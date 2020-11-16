<!DOCTYPE html>
<html lang="ko">
  <head>
    <#include "/common/head.ftl">
  </head>
  
  <body>
  	<div><#include "/common/header.ftl"></div>
    <div><#include "/common/menu.ftl"></div>
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
                <option value="TITLE">제목</option>
                <option value="AUTHOR">저자</option>
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
                <option value="TITLE">제목</option>
                <option value="AUTHOR">저자</option>
                <option value="PRICE">가격</option>
                <option value="YEAR">출판년도</option>
                <option value="STOCK">재고</option>
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
         <#list bookList as item>
          <div class="group_flex">
          <!-- 가급적 절대경로를 쓸 것 -->
            <div><a href="/books/info?id=${item.id}">${item.title}</a></div>
            <div>${item.author}</div>
            <div>${item.price}</div>
            <div>${item.year?replace(",","")}</div>
            <div>${item.stock}</div>
          </div>
         </#list>
        </div>
      </div>
    </div>    
    <div><#include "/common/footer.ftl"></div>
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