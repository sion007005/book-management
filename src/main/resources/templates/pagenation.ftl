<#if pagenation.startPage != 0>
<nav aria-label="Page navigation example">
  <ul class="pagination">
  	<#if (pagenation.prev)??>
    <li class="page-item">
      <a class="page-link" href="/books/list?page=${pagenation.curPage} - 10" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
        <span class="sr-only">Previous</span>
      </a>
    </li>
    <#else>
    <li></li>
    </#if>
    <#list pagenation.startPage..pagenation.endPage as i > 
    	<li class="page-item">
    		<#if searchCondition??>
    		<a class="page-link" href="${path}?page=${i}&search-type=${searchCondition.searchType}&keyword=${searchCondition.keyword}">
            	${i}
            </a>
    		<#else>
        	<a class="page-link" href="${path}?page=${i}">
            	${i}
            </a>
            </#if>
        </li>
    <#assign i=i+1?int>
    </#list>
    <#if pagenation.next??>
    <li class="page-item">
      <a class="page-link" href="/books/list?page=${pagenation.curPage} + 10" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
        <span class="sr-only">Next</span>
      </a>
    </li>
    </#if>
  </ul>
</nav>
<#else>
<div>검색 결과가 없습니다.</div>
</#if>