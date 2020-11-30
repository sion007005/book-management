<div class="menu-list">
	<div>
		<span><a href="/members/list">회원목록</a></span>&nbsp;&nbsp;
		<span><a href="/books/list">도서목록</a></span>&nbsp;&nbsp;
		<span><a href="/categories/list">카테고리목록</a></span>
	</div>
	<#if user.name??>
	<div class="menu-user">
		<span><a href="/members/info?id=${user.memberId}">${user.name} 님(일반회원)</a> | <a href="/logout">로그아웃</a></span>
	</div>
	</#if>	
</div>