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
          <h1>Member Details</h1>
        </div>
      <div class="content">
      <div class="content-row">
        <span>이름:</span>
        <span>${member.name}</span>
      </div>
      <div class="content-row">
        <span>성별:</span>
        <span>${member.gender}</span>
      </div>
      <div class="content-row">
        <span>나이:</span>
        <span>${member.age}</span>
      </div>
      <div class="content-row">	
        <span>휴대폰:</span>
        <span>${member.phone}</span>
      </div>
      <div class="content-row">
        <span>이메일:</span>
        <span>${member.email}</span>
      </div>
      <div class="content-row">
        <span>등록일:</span>
        <span>${member.createdAt?string("yyyy-MM-dd hh:mm:ss")}</span>
      </div>
      <div class="content-row">
        <span>최종 수정일:</span>
        <span>${member.updatedAt?string("yyyy-MM-dd hh:mm:ss")}</span>
      </div>
      <a href="/members/list">목록</a>
      <a href="/members/update?id=${member.id}">수정</a>
        <button type="submit" id="modal_opne_btn">삭제</button>
      </div>
     <div id="modal">
          <div class="modal_content">
            <h2>소중한 우리의 Member...</h2>
            <p>정말 삭제하시겠어요?</p>
            <form action="/members/remove?id=${member.id}" method="POST">
              <button type="submit" id="modal_remove_btn">삭제</button>
            </form>
            <button type="button" id="modal_close_btn">닫기</button>
          </div>
          <div class="modal_layer"></div>
      </div>
    </div>
     </div>    
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