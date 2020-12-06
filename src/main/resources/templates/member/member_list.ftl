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
      	<div><form action="/members/form"><button type="submit" class="btn-join">New Member</button></form></div>
      </div>
      <div class="title">
        <h1>Member List</h1>
      </div>
      <form name="searchForm" action="/members/search?page=${pagenation.curPage}" method="GET">
      <div class="search-container">
        <div class="search-area">
          <div class="group_flex">
            <div class="select-box">
              <select name="search-type" id="form-search-select">
              <#if searchCondition??>
              	<option value="" noSelected>검색기준</option>
              	<#if searchCondition.searchType == "NAME">
                <option value="NAME" selected>이름</option>
                <#else> 
                <option value="NAME">이름</option>
                </#if>
                
                <#if searchCondition.searchType == "EMAIL">
                <option value="EMAIL" selected>이메일</option>
                <#else> 
                <option value="EMAIL">이메일</option>
                </#if> 
                
                <#if searchCondition.searchType == "PHONE">
                <option value="PHONE" selected>휴대폰</option>
                <#else> 
                <option value="PHONE">휴대폰</option>
                </#if>
                
              <#else>
              	<option value="" noSelected>검색기준</option>
                <option value="NAME">이름</option>
                <option value="EMAIL">이메일</option>
                <option value="PHONE">휴대폰</option>
               </#if>
              </select>               
       	    </div>
       	    <div class="input input-box keyword">
       	    <#if searchCondition??>
			  <input type="text" class="input input-text" placeholder="검색어*" name="keyword" value=${searchCondition.keyword}>
			<#else>
			  <input type="text" class="input input-text" placeholder="검색어*" name="keyword" value="">
			</#if>
			</div>
			<button type="submit" class="button btn-submit"><div class="search-button">검색</div></button>
       	  </div>
       	  <div class="age_flex_box">
       	    <div class="age-text">나이: </div>
       	  	<div class="input input-box age">
       	  	  <#if searchCondition??>
			  <input type="number" class="input input-text" maxlength=2 name="age-from" value=${searchCondition.ageFrom}>
			  <#else>
			  <input type="number" class="input input-text" maxlength=2 name="age-from" value=1>
			  </#if>
			</div>
	        <div class="age-text">부터</div>
	        <div class="input input-box age" style="margin-left:2px">
	        <#if searchCondition??>
			  <input type="number" class="input input-text" maxlength=2 name="age-to" value=${searchCondition.ageTo}>
			  <#else>
			  <input type="number" class="input input-text" maxlength=2 name="age-to" value=100>
			  </#if>
			</div>
			<div class="age-text">까지</div>
		  </div>
       	</div>
      </div>     

      <div id="list-container">
        <div class="list-area">
          	<div class="select-box">
              <select name="order-type" id="form-order-select">
                <option value="" noSelected>정렬기준</option>
                <option value="NAME">이름</option>
                <option value="AGE">나이</option>
                <option value="EMAIL">이메일</option>
                <option value="PHONE">휴대폰</option>
              </select>               
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
	</form>
      <div id="result-container">
        <div class="result-area">
         <#list memberList as item>
          <div class="group_flex">
            <div><a href="/members/info?id=${item.id}">${item.name}</a></div>
            <div>${item.gender}</div>
            <div>${item.age}</div>
            <div>${item.phone}</div>
            <div>${item.email}</div>
          </div>
         </#list>
        </div>
      	<div class="pagebar"><#include "../pagenation.ftl"></div>
      </div>
    </div>    
    <div><#include "/common/footer.ftl"></div>
    <script>
	  const orderSelectBox = document.querySelector('#form-order-select');
      orderSelectBox.addEventListener("click", submitOrderType);
      
      function submitOrderType(e) {
    	  const selected = e.target.value;
    	  
    	  if(selected === '' || selected === undefined) {
    		  return;
    	  }
    	  
    	  searchForm.submit();
      }
     
    </script>
  </body>
</html>