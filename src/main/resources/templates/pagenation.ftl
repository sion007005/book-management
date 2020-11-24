<nav aria-label="Page navigation example">
  <ul class="pagination">
  	<#if (pagenation.prev)??>
    <li class="page-item">
      <a class="page-link" href="/books/list?page=${pagenation.curPage} - 1" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
        <span class="sr-only">Previous</span>
      </a>
    </li>
    <#else>
    <li></li>
    </#if>
    <#list pagenation.startPage..pagenation.endPage as i > 
    	<li class="page-item">
        	<a class="page-link" href="/books/list?page=${i}">
            	${i}
            </a>
        </li>
    <#assign i=i+1?int>
    </#list>
    <#if pagenation.next??>
    <li class="page-item">
      <a class="page-link" href="/books/list?page=${pagenation.curPage} + 1" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
        <span class="sr-only">Next</span>
      </a>
    </li>
    </#if>
  </ul>
</nav>