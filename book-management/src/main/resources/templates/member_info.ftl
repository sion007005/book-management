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
	 
	 .content-container .content {
	   position: relative;
       margin: 0 auto;
       width: 300px;
	 }
	 
	 .content .content-row {
	   margin-bottom: 10px;
	 }
	 
		#modal {
		display: none;
		  position:relative;
		  width:100%;
		  height:100%;
		  z-index:1;
		}
		
		#modal h2 {
		  margin:0;   
		}
		
		#modal button {
		  display:inline-block;
		  width:100px;
		  margin-left:calc(100% - 100px - 10px);
		}
		
		#modal .modal_content {
		  width:300px;
		  margin:100px auto;
		  padding:20px 10px;
		  background:#fff;
		  border:2px solid #666;
		}
		
		#modal .modal_layer {
		  position:fixed;
		  top:0;
		  left:0;
		  width:100%;
		  height:100%;
		  background:rgba(0, 0, 0, 0.5);
		  z-index:-1;
		}   
    </style>
	  
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