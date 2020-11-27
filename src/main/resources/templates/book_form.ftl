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
        <div class="file-upload-container">
        <img src="" class="book-img" id="image">
         <form class="form-input" id="fileForm" action="/file/upload?imageType=book" method="post" enctype="multipart/form-data">
			<span class="file" id="file">
    			<input type="file" id="uploadFile" name="file" onchange="displayImage(this)">
    			<input type="button" id="btn-upload" value="등록하기" />
			</span>
			<p id="success-msg" class="success-msg"></p>
			<p id="error-msg" class="error-msg"></p>
		</form>
		</div>
        <#if book??>
          <form action="/books/update?id=${book.id}" method="POST">
        <#else>
          <form action="/books/create" method="POST">
        </#if>
            <div class="form-input form-select-container">
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
          		    <inpupt type="submit" value="수정하기" class="btn-signup button button-primary"></button>
                   	 <#else>
                     <input type="submit" value="등록하기" class="btn-signup button button-primary"></button>
                     </#if>
                </div>
                <#if book??>
                <div class="form-input" style="display:none">
					<input type="text" class="input input-created" data-type="created" placeholder=등록일* name="createdAt" value="${book.createdAt?string('yyyy-MM-dd hh:mm:ss')}">
		  		</div>
		  		<div class="form-input" style="display:none">
					<input type="text" data-type="file-name" placeholder=이미지* name="img-file-name" id="img-file-name" value=${("'${book.imgFileName}'")!''}>
		  		</div>
		  		</#if>
			</form>
      </div>
    </div> 
    <#include "/common/footer.ftl">
    <script>
    $(document).ready(function(){
    	$('#btn-upload').click(function(e) {
    		e.preventDefault();
    		const form = $("#fileForm")[0];
    		const formData = new FormData(form);
    		
    		$.ajax({
    			type: 'POST',
    			url: '/file/upload',
    			data: formData,
    			dataType: "json",
    			contentType: false,
    			processData: false,
    			success: function(res) {
    				if (res.uploaded) {
    				$("#success-msg").text(res.message);
    				$("#img-file-name").val(res.fileName);
    				alert("파일명 " + res.fileName);
    				}
    			},
    			error: function(e) {
    				$("#error-msg").text("예기치 않은 오류가 발생했습니다. 다시 시도해주세요.");
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