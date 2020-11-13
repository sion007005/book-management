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
          <h1>Book Details</h1>
        </div>
      <div class="content">
      <div class="content-row">
        <span>제목:</span>
        <span>${book.title}</span>
      </div>
      <div class="content-row">
        <span>저자:</span>
        <span>${book.author}</span>
      </div>
      <div class="content-row">
        <span>출판년도:</span>
        <span>${book.year?replace(",","")}</span>
      </div>
      <div class="content-row">	
        <span>가격:</span>
        <span>${book.price}</span>
      </div>
      <div class="content-row">
        <span>재고:</span>
        <span>${book.stock}</span>
      </div>
       <div class="content-row">
        <span>등록일:</span>
        <span>${book.createdAt?string("yyyy-MM-dd hh:mm:ss")}</span>
      </div>
      <div class="content-row">
        <span>최종 수정일:</span>
        <span>${book.updatedAt?string("yyyy-MM-dd hh:mm:ss")}</span>
      </div>
      <a href="/books/list">목록</a>
      <a href="/books/update?id=${book.id}">수정</a>
        <button type="submit" id="modal_opne_btn">삭제</button>
      </div>
     <div id="modal">
          <div class="modal_content">
            <h2>소중한 우리의 Book...</h2>
            <p>정말 삭제하시겠어요?</p>
            <form action="/books/remove?id=${book.id}" method="POST">
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