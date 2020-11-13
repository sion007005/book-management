<html>
  <head>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet"> <!--CDN 링크 -->
	<link href="https://fonts.googleapis.com/css2?family=Caveat:wght@700&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap" rel="stylesheet">
	<link href="/static/style.css" rel="stylesheet">
  </head>
  
  <body>
    <div id="wrapper">
      <div class="content-container">
        <div class="title">
          <#if book??>
          	<h1>Book Update</h1>
          <#else>
          	<h1>Book Register</h1>
          </#if>
        </div>
        <#if book??>
          <form action="./update?id=${book.id}" method="POST">
        <#else>
          <form action="./create" method="POST">
        </#if>
		  <div class="form-input form-category">
            <div class="form-input form-category-select">
              <select name="form-category-select" id="form-category-select">
              		<#if book??>
                 		<option value="카테고리 선택">카테고리 선택</option>
              			<#list categoryList as item>
              				<#if item.id?number == book.categoryId?number>
                 				<option value="${item.id}" selected>${item.name}</option>
                 			<#else>
                 				<option value="${item.id}">${item.name}</option>
                 			</#if>
               			</#list>             
               		<#else>
                 		<option value="카테고리 선택" noSelected>카테고리 선택</option>
               			<#list categoryList as item>
               				<option value="${item.id}">${item.name}</option>
               			</#list> 
               		</#if>
               </select>            
             </div>
          </div>  
				<div class="form-input">
					<#if book??>
					<input type="text" class="input input-name" data-type="title" placeholder="제목*" name="title" value="${book.title}">
					<#else>
					<input type="text" class="input input-name" data-type="title" placeholder="제목*" name="title" value="">
					</#if>
				</div>
				<div class="form-input">
					<#if book??>
					<input type="text" class="input input-gender" data-type="author" placeholder="저자*" name="author" value="${book.author}">
					<#else>
					<input type="text" class="input input-gender" data-type="author" placeholder="저자*" name="author" value="">
					</#if>
				</div>
				<div class="form-input">
					<#if book??>
					<input type="text" class="input input-age" data-type="price" placeholder="가격*" name="price" value="${book.price?replace(',','')}">
					<#else>
					<input type="text" class="input input-gender" data-type="author" placeholder="가격*" name="price" value="">
					</#if>
				</div>
				<div class="form-input">
					<#if book??>
					<input type="text" class="input input-age" data-type="year" placeholder="출판년도*" name="year" value="${book.year?replace(',','')}">
					<#else>
					<input type="text" class="input input-gender" data-type="author" placeholder="출판년도*" name="year" value="">
					</#if>
				</div>
				<div class="form-input">
					<#if book??>
					<input type="text" class="input input-age" data-type="stock" placeholder="재고*" name="stock" value="${book.stock}">
					<#else>
					<input type="text" class="input input-gender" data-type="author" placeholder="재고*" name="stock" value="">
					</#if>
				</div>
                <div class="form-input">
                	<#if book??>
          		    <button class="btn-signup button button-primary" type="submit">수정하기</button>
                   	 <#else>
                     <button class="btn-signup button button-primary" type="submit">등록하기</button>
                     </#if>
                </div>
                <#if book??>
                <div class="form-input" style="display:none">
					<input type="text" class="input input-created" data-type="created" placeholder=등록일* name="createdAt" value="${book.createdAt?string('yyyy-MM-dd hh:mm:ss')}">
		  		</div>
		  		</#if>
			</form>
      </div>
    </div>    
  </body>
</html>