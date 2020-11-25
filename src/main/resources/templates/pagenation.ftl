<#if pagenation.startPage != 0>
<nav aria-label="Page navigation example">
  <ul class="pagination">
  	<#if (pagenation.curBlock)?number != 1>
  	<li class="page-item">
  	  <#if searchCondition??>
      <a class="page-link" href="${path}?page=1&search-type=${searchCondition.searchType}&keyword=${searchCondition.keyword}" aria-label="Previous">
      <#else>
      <a class="page-link" href="${path}?page=1" aria-label="Previous">
      </#if>
        <span aria-hidden="true">처음</span>
        <span class="sr-only">First</span>
      </a>
    </li>
    <li class="page-item">
      <#if searchCondition??>
      <a class="page-link" href="${path}?page=${(pagenation.curBlock?number - 1) * 10}&search-type=${searchCondition.searchType}&keyword=${searchCondition.keyword}" aria-label="Previous">
      <#else> 
      <a class="page-link" href="${path}?page=${(pagenation.curBlock?number - 1) * 10}" aria-label="Previous">
      </#if>
    <#else>
    <li class="page-item">
      <a class="page-link" href="#" aria-label="Previous">
    </#if>
        <span aria-hidden="true">&laquo;</span>
        <span class="sr-only">Previous</span>
      </a>
    </li>
    <#list pagenation.startPage..pagenation.endPage as i > 
    	<li class="page-item">
    	<#if pagenation.curPage?number == i>
    		<#if searchCondition??>
    		<a class="page-link curPage" href="${path}?page=${i}&search-type=${searchCondition.searchType}&keyword=${searchCondition.keyword}">
            	${i}
            </a>
    		<#else>
        	<a class="page-link curPage" href="${path}?page=${i}">
            	${i}
            </a>
            </#if>
         <#else>
         	<#if searchCondition??>
    		<a class="page-link" href="${path}?page=${i}&search-type=${searchCondition.searchType}&keyword=${searchCondition.keyword}">
            	${i}
            </a>
    		<#else>
        	<a class="page-link" href="${path}?page=${i}">
            	${i}
            </a>
            </#if>
         </#if>
        </li>
    <#assign i=i+1?int>
    </#list>
    
  	<#if (pagenation.curBlock)?number != pagenation.totalBlockCnt>
  	<li class="page-item">
  	<#if searchCondition??>
      <a class="page-link" href="${path}?page=${pagenation.curBlock?number * 10 + 1}&search-type=${searchCondition.searchType}&keyword=${searchCondition.keyword}" aria-label="Next">
    <#else>
      <a class="page-link" href="${path}?page=${pagenation.curBlock?number * 10 + 1}" aria-label="Next">
    </#if>
        <span aria-hidden="true">&raquo;</span>
        <span class="sr-only">Next</span>
      </a>
    </li>
    <li class="page-item">
    <#if searchCondition??>
      <a class="page-link" href="${path}?page=${pagenation.totalPageCnt}&search-type=${searchCondition.searchType}&keyword=${searchCondition.keyword}" aria-label="Previous">
    <#else>
      <a class="page-link" href="${path}?page=${pagenation.totalPageCnt}" aria-label="Previous">
    </#if>    
        <span aria-hidden="true">마지막</span>
        <span class="sr-only">Last</span>
      </a>
    </li>
    </#if>
  </ul>
</nav>
<#else>
<div>검색 결과가 없습니다.</div>
</#if>