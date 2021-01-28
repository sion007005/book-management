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
  		<form name="myform" method="post" action="/survey/create">
			<h2>${surveyResult.survey.subject} 설문 결과</h2>
			<br />
			<table class="table table-hover">
				<tr>
					<th>설문 기간</th>
					<td>${surveyResult.survey.startDate?substring(0,12)} ~ ${surveyResult.survey.endDate?substring(0,12)}</td>
				</tr>
				<tr>
					<th>설문 대상</th>
					<td>${surveyResult.survey.target}</td>
				</tr>
			</table>
			<br />
			
			<table class="table">
		      <tr style="background-color:;">
		        <td rowspan="2"></td>
		        <td colspan="5">점수배점</td>
		      </tr>
		      <tr style="">
		        <td>매우만족(5)</td>
		        <td>만족(4)</td>
		        <td>보통(3)</td>
		        <td>불만(2)</td>
		        <td>매우불만(1)</td>
		      </tr>
		      <#list surveyResult.surveyResultRows as resultRow>
		      <tr>
			  	<td>${resultRow_index+1}. ${resultRow.question.content}</td>
				<#list resultRow.answersPerRow as answerPair>
			  	<td>
			  		<#if answerPair.count??>
			  		${answerPair.count}
			  		<#else>
			  		''
			  		</#if>
			  	</td>
			    </#list>		
		      </tr>
		      </#list>
		    </table>
		</form>
      </div>
    </div> 
    <#include "/common/footer.ftl">
  </body>
</html>