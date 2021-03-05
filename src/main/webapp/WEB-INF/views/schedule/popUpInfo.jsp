<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

	<script src="/js/bootstrap.min.js"></script>

<style type="text/css">
.container {
	border-top: 1px solid lightgray;
	padding-top: 20px;
	margin: center;
}

#contents {
	border: 1px solid lightgray;
	min-width: 500px;
	max-width: 500px;
	min-height: 580px;
	max-height: 580px;
}

.body {
	height: 50%;
}

.footer {
	text-align: right
}

h2 {
	margin: 0;
	padding: 20px;
	background-color: #6749b930;
}

.dataGroup {
	margin-top: 20px;
}

.row {
	padding: 20px;
	float: left;
}

.left {
	text-align: right;
	width: 80px;
	float: left;
}

.right {
	text-align: left;
	width: 350px;
	float: left;
	margin-left: 20px;
}

.contentsBox {
	height: 200px;
	border: 1px solid lightgray;
	overflow: y-scroll;
}

.buttonGroup {
	margin-left: 185px;
}

.buttonGroup input {
	margin: 5px;
	margin-left: 10px;
}

.btn {
	background-color: #6749b930;
	padding: 3px 8px 3px 8px;
	border: 1px solid lightgray;
}
</style>
</head>
<body>
	<div id="contents" class="p-4 p-md-5 pt-5">
		<h2>세부일정</h2>
		<div class="container">
			<div class="row">
				<div class="left">
					<b>일정명</b>
				</div>
				<div class="right">
					<c:out value="${dto.title }"></c:out>
				</div>
			</div>
			<div class="row">
				<div class="left">
					<b>시작 날짜</b>
				</div>
				<div class="right">${dto.start_time }</div>
			</div>
			<div class="row">
				<div class="left">
					<b>마감 날짜</b>
				</div>
				<div class="right">${dto.end_time }</div>
			</div>
			<div class="row">
				<div class="left">
					<b>내용</b>
				</div>
				<div class="right contentsBox">
					<c:out value="${dto.contents }"></c:out>
				</div>
			</div>
			<c:if test="${empCode eq dto.writer }">
				<div class="buttonGroup">
					<input type="button" class=btn value="수정" id="revise"> <input
						type="button" class=btn value="삭제" id="delete">
				</div>
			</c:if>
			<script>
					window.onload = function(){
							if(${didUpdate eq 'true'}){
								opener.document.location.href="/schedule/toScheduleMain.schedule";
							}
						}
					var reviseBtn = document.getElementById("revise");
					reviseBtn.onclick = function() {
		               location.href = "/schedule/toUpdate.schedule?seq=${dto.seq}";
		            }
		            var deleteBtn = document.getElementById("delete");
					deleteBtn.onclick = function() {
						var confirmResult = confirm("일정을 삭제하시겠습니까?");
						if(confirmResult == true){
							$.ajax({
			               		url: "/schedule/deleteSchedule.schedule?seq=${dto.seq}",
			               		type: "post",
			               		success: function(data){
			               			if(data == 1){
					               		alert("삭제되었습니다.");
			               			}else{
			               				alert("삭제를 실패했습니다.");
			               			}
			               			opener.document.location.href="/schedule/toScheduleMain.schedule";
			               			window.close();
					               },
					            error: function(){
					               		alert("에러발생");
					               }
				               })
							}
			            }
				</script>
		</div>
	</div>
   <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="/js/bootstrap.min.js"></script>
</body>
</html>