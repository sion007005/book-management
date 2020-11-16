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
      	<div><form action="/categories/form"><button type="submit" class="btn-join">New Category</button></form></div>
      </div>
      <div class="title">
        <h1>Category List</h1>
      </div>
      <form action="/categories/search" method="GET">
      <div class="search-container">
        <div class="search-area">
          <div class="group_flex">
            <div class="select-box">
              <select name="search-type" id="form-search-select">
                <option value="" noSelected>검색기준</option>
                <option value="NAME">카테고리 이름</option>
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
              <form action="/categories/list" id="order-select">
              <select name="order-type" id="form-search-select">
                <option value="" noSelected>정렬기준</option>
                <option value="ID">카테고리 ID</option>
                <option value="NAME">카테고리 이름</option>
              </select>               
              </form>
          	</div>
          <div class="group_flex">
            <div>카테고리 ID</div>
            <div>카테고리 명</div>
          </div>
        </div>
      </div>
      <div id="result-container">
        <div class="result-area">
         <#list categoryList as item>
          <div class="group_flex">
          <!-- 가급적 절대경로를 쓸 것 -->
            <div>${item.id}</div>
            <div><a href="/categories/info?id=${item.id}">${item.name}</a></div>
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