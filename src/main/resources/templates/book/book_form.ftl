<!DOCTYPE html>
<html lang="ko">
  <head>
    <#include "/common/head.ftl">
  </head>
  
  <body>
  <#include "/common/header.ftl">
  <#include "/common/menu.ftl">
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
          <form action="/books/update?id=${book.id}" method="POST" enctype="multipart/form-data" id="sumbitForm" name="sumbitForm"  onsubmit="return false;">
        <#else>
          <form action="/books/create" method="POST" enctype="multipart/form-data" id="sumbitForm" name="sumbitForm"  onsubmit="return false;">
        </#if>
            <div class="form-input form-select-container">
	            <span class="file" id="file">
	            	<#if book??>
        			<img src="http://localhost:3000/image?imgPath=${book.imgPath}" class="book-img" id="image">	
					<input type="hidden" name="imgPath" value="${book.imgPath}">
					<#else>
        			<img src="" class="book-img" id="image">	
	    			</#if>
	    			<input type="file" id="uploadFile" name="file" onchange="displayImage(this)">
				</span>
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
				<div class="form-input">
					<input type="text" class="input input-name" data-type="title" placeholder="제목*" name="title" value=${("'${book.title}'")!''}>
				</div>
				<div class="form-input">
					<input type="text" class="input input-gender" data-type="author" placeholder="저자*" name="author" value=${("'${book.author}'")!''}>
				</div>
				<div class="form-input">
					<input type="text" class="input input-age" data-type="price" placeholder="가격*" name="price" value=${("'${book.price}'")!''}>
				</div>
				<div class="form-input">
					<#if book??>
					<input type="text" class="input input-age" data-type="year" placeholder="출판년도*" name="year" value=${book.year?replace(',','')}>
					<#else>
					<input type="text" class="input input-gender" data-type="author" placeholder="출판년도*" name="year" value="">
					</#if>
				</div>
				<div class="form-input">
					<input type="text" class="input input-age" data-type="stock" placeholder="재고*" name="stock" value=${("'${book.stock}'")!''}>
				</div>
				
                <div class="form-input">
                	<#if book??>
          		    <input type="submit" value="수정하기" class="btn-signup button button-primary btnSubmit"></button>
                   	 <#else>
                     <input type="submit" onclick="" value="등록하기" class="btn-signup button button-primary btnSubmit"></button>
                     </#if>
                </div>
                <#if book??>
                <div class="form-input" style="display:none">
					<input type="text" class="input input-created" data-type="created" placeholder=등록일* name="createdAt" value="${book.createdAt?string('yyyy-MM-dd hh:mm:ss')}">
		  		</div>
		  		<div class="form-input" style="display:none">
					<input type="text" data-type="file-name" placeholder=이미지* name="img-file-name" id="img-file-name" value=${("'${book.imgPath}'")!''}>
		  		</div>
		  		</#if>
			</form>
      </div>
    </div> 
    <#include "/common/footer.ftl">
    <script>
    $(document).ready(function(){
    	$('.btnSubmit').click(function(e) {
    		e.preventDefault();
    		const form = $('#sumbitForm')[0];
    		const params = $("#sumbitForm").serialize();
    		const action = $('#sumbitForm').attr("action");
    		const data = new FormData(form);
    		const returnUrl = $("#returnUrl").val();
    		
    		$("#btnSubmit").prop("disabled", true);
    		
    		$.ajax({
    			type: 'POST',
    			enctype: 'multipart/form-data',
    			url: action,
    			data: data,
    			processData: false,
                contentType: false,
    			dataType: "json",
    			success: function(res) {
    				$("#btnSubmit").prop("disabled", false);
    				
    				if (res.proccessed) {
    					alert("등록 성공!");
    					location.href="./info?id="+ res.bookId;
    				}
    			},
    			error: function(e) {
    				alert("예기치 않은 오류가 발생했습니다. 다시 시도해주세요.");
    			}
    		})
    	})
    })
    
    function displayImage(tag){
        const reader = new FileReader();
        reader.readAsDataURL(tag.files[0]);
        reader.onload = function() {
        
        // 여기서의 this는 reader객체
        $("#image").attr("src", this.result);
        } 
    }
    </script>
  </body>
</html>