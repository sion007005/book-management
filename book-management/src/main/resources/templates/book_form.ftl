<html>
  <head>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet"> <!--CDN 링크 -->
	<link href="https://fonts.googleapis.com/css2?family=Caveat:wght@700&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap" rel="stylesheet">
	<style>
	  
	  body, button, dd, dl, dt, fieldset, form, h1, h2, h3, h4, h5, h6, 
	  input, legend, li, ol, p, select, table, td, textarea, th, ul {
        margin: 0;
        padding: 0;
      }
      
	  .input {
	    display: block;
	    position: relative;
	    width: 100%;
	    /*width: 328px;*/
    	height: 50px;
	    margin: 0;
	    border-radius: 0;
	    border: 2px solid #32424B;
	    font-size: 17px;
	    line-height: 1.63;
	    letter-spacing: -0.5px;
	    color: black;
	    background: #fff;
	    box-sizing: border-box;
	    white-space: nowrap;
	    overflow: hidden;
	    text-overflow: ellipsis;
        outline: 0;
        margin-bottom: 10px;
      }
      
      .button {
        display: inline-block;
        padding: 16px 0 14px;
        width: 100%;
        text-align: center;
        font-size: 18px;
        font-weight: bold;
      }
      
      input:disabled {
        color: #d7dbe6;
      }
       
	  #wrapper {
	    overflow: hidden;
        min-width: 1190px;
	  }
	  
	  .btn-join {
	    height: 30px;
        width: 120px;
        font-size: 17px;
        background-color: #fff;
        border: 2px solid #828282;
        cursor: pointer;
        font-family: 'Caveat', cursive;
	  }
	  
	  .btn-join:hover {
        background-color: #32424B;
        border: 2px solid #fff;
        color: #fff;
        transition-duration: 0.3s;
	  }
	  
	  #wrapper h1 {
	    display: block;
        margin: 0 0 30;
        text-align: center;
        font-size: 40px;
        color:  #32424B;
        font-family: 'Caveat', cursive;
	  }
	  
	 .content-container {
	   padding: 60px 0;
       background: beige;
       position: relative;
       margin: 0 auto;
       width: 800px;
       height: 83%;
	 }
	 
	 .content-container form {
	   padding-top: 20px;
       width: 400px;
       margin: 0 auto;
	 }
	  
	 .content-container form div {
	   maring-bottom: 10px;
	 }
	 
	 .content-container input {
	   padding: 12px 14px;
       height: auto;
       border: 1px solid #d7dbe6;
       font-size: 16px;
       line-height: 24px;
       letter-spacing: -0.6px;
       box-sizing: border-box;
	 }
	 
	 .content-container .form-input.form-category {
       overflow: hidden;
     }
     
     .content-container .form-input.form-category input {
       float: left;
       width: 185px;
     }
     
    .content-container .form-input.form-category span {
      float: left;
      display: block;
      padding-top: 15px;
      width: 28px;
      text-align: center;
      font-family: 'NotoSansKR';
    }

    .content-container .form-input.form-category-select select {
      margin-bottom: 10px;
      padding: 5px 12px;
      font-size: 16px;
      height: 50px;
      border: 1px solid #d7dbe6;
      box-sizing: border-box;
      background-size: 16px 16px;
      width: 400px;
    }
    
    .form-input.has-button {
    position: relative;
    padding-right: 106px;
    }
    
    .form-input.has-button .btn-small {
    position: absolute;
    top: 0;
    right: 0;
    border-color: #1a7cff;
    color: #1a7cff;
}
.signup-container .has-button input {
    width: 220px;
    height: 50px;
}
.btn-small {
    display: inline-block;
    position: absolute;
    top: 0;
    right: 0;
    padding: 14px 0 12px;
    text-align: center;
    font-weight: bold;
    font-size: 16px;
    line-height: 22px;
    border: 1px solid;
    background: #fff; 
    width: 98px;
}

.form-input.has-button .btn-small:disabled {
    border-color: #ccc;
    color: #bbc0cd;
}
	  
    </style>
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