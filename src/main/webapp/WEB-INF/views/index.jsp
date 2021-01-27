<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <title>Insert title here</title>
    <style type="text/css">
        div {
            border: 1px solid gray
        }

        #contents {
            height: 100%;
            width: 100%;
        }

        .body {
            height: 50%;
        }

        .footer {
            text-align: right
        }

        input {
            width: 100%
        }
    </style>
</head>
<body>

<div class="wrapper d-flex align-items-stretch">
    <%@ include file="/WEB-INF/views/sidebar/sidebar.jsp" %>   <!-- Page Content  -->
    <div id="content" class="p-4 p-md-5 pt-5">
        <!-- 지영 -->
        <button type="button" onclick="fn_board()">게시판 바로가기</button>
        <!-- 의진: 메신저 연락처-->
        <button type="button" onclick="fn_messenger()">메신저 바로가기</button>
        <button type="button" onclick="fn_stompMessenger()">Stomp</button>
        <button type="button" onclick="fn_chatProf()">강사님</button>

        <!-- 효경: 이부분 살려놔주시면 감사하겠습니다..ㅎ -->
        <input type=button value="저장된" id=temporaryBtn><br>
        <input type=button value="상신한" id=raiseBtn><br>
        <input type=button value="승인된" id=approvalBtn><br>
        <input type=button value="반려된" id=rejectBtn><br>
        <input type=button value="회수한" id=returnBtn><br>
        <script>
            let temporaryBtn = document.getElementById("temporaryBtn");
            temporaryBtn.onclick = function () {
                location.href = "/document/d_searchTemporary.document?";
            }
            let raiseBtn = document.getElementById("raiseBtn");
            raiseBtn.onclick = function () {
                location.href = "/document/d_searchRaise.document";
            }
            let approvalBtn = document.getElementById("approvalBtn");
            approvalBtn.onclick = function () {
                location.href = "/document/d_searchApproval.document";
            }
            let rejectBtn = document.getElementById("rejectBtn");
            rejectBtn.onclick = function () {
                location.href = "/document/d_searchReject.document";
            }
            let returnBtn = document.getElementById("returnBtn");
            returnBtn.onclick = function () {
                location.href = "/document/d_searchReturn.document";
            }

            /*지영 부분*/
            function fn_board() {
                location.href = "/noBoard/notificationBoardList.no"
            }

            /*의진 부분*/
            function fn_messenger() {
                var popup = window.open('/messenger/contactList','','width=450px, height=660px, resizable=no, scrollbars=no, fullscreen=yes');
            }

            function fn_stompMessenger() {
                var popup = window.open('/messenger/chatStomp','','width=450px, height=660px, resizable=no, scrollbars=no, fullscreen=yes');
            }

            function fn_chatProf() {
                location.href = "/messenger/chatProfTest";
            }

        </script>

    </div>
</div>
</body>
</html>