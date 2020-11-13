<!DOCTYPE html>
<html lang="ko">
  <head>
    <#include "/common/head.ftl">
  </head>
  
  <body>
  	<div><#include "/common/header.ftl"></div>
    <div><#include "/common/menu.ftl"></div>
    <div id="wrapper">
      <div class="content-container">
        <div class="title">
          <h1>Category Update</h1>
        </div>
        <form action="/categories/update?id=${body.id}" method="POST">
		  <div class="form-input form-category">
            <div class="form-input form-category-select">
			  <div class="form-input">
				<div>카테고리명:</div> 
				<input type="text" class="input input-name" data-type="name" placeholder="카테고리 명*" name="name" value="${body.name}">
			  </div>
			  <div class="form-input" style="display:none">
			    <input type="text" class="input input-created" data-type="created" placeholder=등록일* name="createdAt" value="${body.createdAt?string('yyyy-MM-dd hh:mm:ss')}">
			  </div>
              <div class="form-input">
                <button class="btn-signup button button-primary" type="submit">수정하기</button>
              </div>
            </div>
          </div>     
	    </form>
      </div>
    </div>  
    <div><#include "/common/footer.ftl"></div>  
  </body>
</html>