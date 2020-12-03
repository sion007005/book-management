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
      	메인화면
      	<span>${today?string("yyyy-MM-dd hh:mm:ss")}</span>
      </div>
    </div>  
  </body>
</html>