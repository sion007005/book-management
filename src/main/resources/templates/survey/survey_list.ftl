<!DOCTYPE html>
<html lang="ko">
  <head>
    <#include "/common/head.ftl">
  </head>
  
  <body>
  	<#include "/common/header.ftl">
    <#include "/common/menu.ftl">
    <div id="wrapper">
      <div class="title">
        <h1>등록된 설문 리스트</h1>
      </div>
      <table class="table">
		<tr>
			<th>설문 번호</th>
			<th>설문 시작일</th>
			<th>설문 마감일</th>
			<th>설문 주제</th>
			<th>설문 대상</th>
			<th>마감 여부</th>
			<th>참여자수</th>
		</tr>
		<#list surveyList as survey>
		<tr>
			<td>${survey.idx}</td>
			<td>${survey.startDate}</td>
			<td>${survey.endDate}</td>
			<td>${survey.subject}</td>
			<td>${survey.target}</td>
			<td>
				<#if (survey.isClosed == 0)??>
          			진행(예정)
          		<#else>
					마감
				</#if>
			</td>
			<td><a href="/survey/result?surveyIdx=${survey.idx}">${survey.joinMemberCount}</a></td>
		</tr>
		</#list>
	</table>
    </div>    
    <#include "/common/footer.ftl">
  </body>
</html>