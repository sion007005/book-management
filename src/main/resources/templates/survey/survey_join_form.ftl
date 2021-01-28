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
  		<form name="myform" method="post" action="/survey/answer/create">
			<h2>${survey.subject}</h2>
			<br/>
			<table class="table table-hover">
				<tr>
        			<th>설문 기간</th>
        			<td class="tblleft">${survey.startDate?substring(0,12)} ~ ${survey.endDate?substring(0,12)}</td>
     			</tr>
				<tr>
        			<th>설문대상</th>
        			<td class="tblleft">${survey.target}</td>
     			</tr>				  			
			</table>
			
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
		      <#list questionList as question>
		      	<tr>
		      		<input type="hidden" name="question${question_index+1}" value="${question.idx}" />
		      		<td>${question_index+1}. ${question.content}</td>
		      		<#list 1..5 as j>
		      			<td><input type="radio" name="answer${question_index+1}" value="${j}"/></td>
		      		</#list>	
		      	</tr>
		      </#list>
		      <tr>
		        <td colspan="6">
		          <input type="submit" value="설문참여"/> &nbsp;
		        </td>
		      </tr>
		    </table>
		    <input type="hidden" name="questionSize" value="${questionList?size}">
		    <input type="hidden" name="memberIdx" value="${memberIdx}">
		    <input type="hidden" name="surveyIdx" value="${survey.idx}">
		</form>
      </div>
    </div> 
    <#include "/common/footer.ftl">
  </body>
</html>