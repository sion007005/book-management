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
			<h2>설문 등록</h2>
			<br />
			<table class="table table-hover">
				<tr>
					<th>설문 시작일</th>
					<td><input type="date" name="startDate"/></td>
				</tr>
				<tr>
					<th>설문 마감일</th>
					<td><input type="date" name="endDate"/></td>
				</tr>
				<tr>
					<th>설문 주제</th>
					<td><input type="text" name="subject" value=""/></td>
				</tr>
				<tr>
					<th>설문 대상</th>
					<td><input type="text" name="target" value=""/></td>
				</tr>
				<tr>
					<th>설문 마감여부</th>
					<td>
						<select name="isClosed">
							<option value="0" selected>진행</option>
							<option value="1">마감</option>
						</select>
					</td>
				</tr> 
			
				
			</table>
			<br />
			<table>
				<tr>
					<th>문항1</th>
					<td><input type="text" name="question1" size="70"
						value="" /></td>
				</tr>
				<tr>
					<th>문항2</th>
					<td><input type="text" name="question2" size="70"
						value="" /></td>
				</tr>
				<tr>
					<th>문항3</th>
					<td><input type="text" name="question3" size="70"
						value="" /></td>
				</tr>
				<tr>
					<th>문항4</th>
					<td><input type="text" name="question4" size="70"
						value="" /></td>
				</tr>
				<tr>
					<th>문항5</th>
					<td><input type="text" name="question5" size="70"
						value="" /></td>
				</tr>
				<tr>
					<th>문항6</th>
					<td><input type="text" name="question6" size="70"
						value="" /></td>
				</tr>
				<tr>
					<th>문항7</th>
					<td><input type="text" name="question7" size="70"
						value="" /></td>
				</tr>
				<tr>
					<th>문항8</th>
					<td><input type="text" name="question8" size="70"
						value="" /></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="등록"/>
						<input type="reset" value="다시입력"/>
					</td>
				</tr>
			</table>
		</form>
      </div>
    </div> 
    <#include "/common/footer.ftl">
  </body>
</html>