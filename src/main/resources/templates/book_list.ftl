<!DOCTYPE html>
<html lang="ko">
  <head>
    <#include "/common/head.ftl">
  </head>
  
  <body>
  	<#include "/common/header.ftl">
    <#include "/common/menu.ftl">
    <div id="wrapper">
      <div class="button join-container">
      	<div><form action="/books/form"><button type="submit" class="btn-join">New Book</button></form></div>
      </div>
      <div class="title">
        <h1>Book List</h1>
      </div>
      <form name="searchForm" action="/books/search?page=${pagenation.curPage}" method="GET">
      <div class="search-container">
        <div class="search-area">
          <div class="group_flex">
            <div class="select-box">
              <select name="search-type" id="form-search-select">
              <#if searchCondition??>
                <option value="" noSelected>검색기준</option>
                <#if searchCondition.searchType == "TITLE">
                <option value="TITLE" selected>제목</option>
                <#else>
                <option value="TITLE" selected>제목</option>
				</#if>
				
				<#if searchCondition.searchType == "AUTHOR">                
                <option value="AUTHOR" selected>저자</option>
				<#else>
                <option value="AUTHOR">저자</option>
              	</#if>
              <#else>
                <option value="" noSelected>검색기준</option>
                <option value="TITLE">제목</option>
                <option value="AUTHOR">저자</option>
              </#if>
              </select>               
       	    </div>
       	    <div class="input input-box keyword">
       	    <#if searchCondition??>
			  <input type="text" class="input input-text" placeholder="검색어*" name="keyword" value=${("'${searchCondition.keyword}'")!''}>
			<#else>
			  <input type="text" class="input input-text" placeholder="검색어*" name="keyword" value="">
			</#if>
			</div>
			<button type="submit" class="button btn-submit"><span>검색</span></button>
       	  </div>
       	</div>
      </div>     
      
      <div id="list-container">
        <div class="list-area">
          	<div class="select-box">
              <select name="order-type" id="form-order-select">
                <option value="" noSelected>정렬기준</option>
                <option value="TITLE">제목</option>
                <option value="AUTHOR">저자</option>
                <option value="PRICE">가격</option>
                <option value="YEAR">출판년도</option>
                <option value="STOCK">재고</option>
              </select>               
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
      </form>
      <div id="result-container">
        <div class="result-area">
        <#if bookList??>
         <#list bookList as item>
          <div class="group_flex">
            <div><a href="/books/info?id=${item.id?replace(',','')}">${item.title}</a></div>
            <div>${item.author}</div>
            <div>${item.price}</div>
            <div>${item.year?replace(",","")}</div>
            <div>${item.stock}</div>
          </div>
         </#list>
         </#if>
         <#if message??>
          <div class="no-result-msg">${message}</div>
         </#if>
        </div>
        <#if pagenation??>
        <div class="pagebar"><#include "pagenation.ftl"></div>
      	</#if>
      </div>
    </div>    
    <#include "/common/footer.ftl">
    <script>
		const orderSelectBox = document.querySelector('#form-order-select');
		orderSelectBox.addEventListener("click", submitOrderType);
    
		function submitOrderType(e) {
    		console.log("clicked!")
  	    	const selected = e.target.value;
  	  
  	    	if(selected === '' || selected === undefined) {
  				return;
  	    	}
  	  
		searchForm.submit();
    	}
    </script>
  </body>
</html>