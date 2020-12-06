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
          <h1>Category Details</h1>
        </div>
      <div class="content">
      <div class="content-row">
        <span>카테고리 ID:</span>
        <span>${category.id}</span>
      </div>
      <div class="content-row">
        <span>카테고리 명:</span>
        <span>${category.name}</span>
      </div>
      <div class="content-row">
        <span>등록일:</span>
        <span>${category.createdAt?string("yyyy-MM-dd hh:mm:ss")}</span>
      </div>
      <div class="content-row">
        <span>최종 수정일:</span>
        <span>${category.updatedAt?string("yyyy-MM-dd hh:mm:ss")}</span>
      </div>
      <a href="/categories/list">목록</a>
      <a href="/categories/form?id=${category.id}">수정</a>
        <button type="submit" id="modal_opne_btn">삭제</button>
      </div>
     <div id="modal">
          <div class="modal_content">
            <p>정말 삭제하시겠어요?</p>
            <form action="/categories/remove?id=${category.id}" method="POST">
              <button type="submit" id="modal_remove_btn">삭제</button>
            </form>
            <button type="button" id="modal_close_btn">닫기</button>
          </div>
          <div class="modal_layer"></div>
      </div>
    </div>
     </div>  
     <div><#include "/common/footer.ftl"></div>  
    <script>
     document.getElementById("modal_opne_btn").onclick = function() {
       document.getElementById("modal").style.display="block";
     }
     
     document.getElementById("modal_remove_btn").onclick = function() {
         document.getElementById("modal").style.display="none";
     } 
            
     document.getElementById("modal_close_btn").onclick = function() {
       document.getElementById("modal").style.display="none";
     }      
   </script>
  </body>
</html>