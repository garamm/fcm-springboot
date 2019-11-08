<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <title>FCM 관리자 페이지</title>
</head>

<body>
    <div class="container">

        <!-- 타이틀 -->
        <div class="wrapper">
            <span class="label">메세지 설정</span>
        </div>

        <!-- 제목 입력 -->
        <div class="wrapper">
            <span class="label">제목</span>
            <input type="text" class="input-text" id="titleInput" placeholder="제목을 입력해주세요." />
        </div>

        <!-- 내용 입력 -->
        <div class="wrapper">
            <span class="label">내용</span>
            <input type="text" class="input-text" id="msgInput" placeholder="내용을 입력해주세요." />
        </div>

        <!-- 이미지 유무 선택 -->
        <div class="wrapper">
            <span class="label">이미지</span>
            <input type="radio" name="haveImg" value="true">&nbsp;<sapn class="radio">포함</sapn>&nbsp;&nbsp;
            <input type="radio" name="haveImg" value="false" checked>&nbsp;<span class="radio">미포함</span>
        </div>

        <!-- 이미지 포함인 경우 이미지 타입 선택-->
        <div class="wrapper hide" id="imgDiv">
            <span class="label"></span>
            <input type="radio" name="imgType" value="upload" checked>&nbsp;<sapn class="radio">업로드</sapn>&nbsp;&nbsp;
            <input type="radio" name="imgType" value="url">&nbsp;<span class="radio">이미지 URL</span>&nbsp;&nbsp;&nbsp;
            <button id="imgUploadBtn">이미지 가져오기</button>
            <input class="input-text hide" type="text" id="imgUrlInput" placeholder="이미지 URL을 입력해주세요." />
        </div>
    </div>

    <div class="container">

        <div class="wrapper">
            <span class="label">수신자 설정</span>
        </div>

        <!-- 앱 선택 -->
        <div class="wrapper">
            <span class="label">앱 선택</span>
            <select id="appSelect">
                <option>선택해주세요</option>
                <option>앱 이름1</option>
                <option>앱 이름2</option>
            </select>
        </div>

        <!-- 수신자 타입 선택 -->
        <div class="wrapper">
            <span class="label">수신자</span>
            <input type="radio" name="receiverType" value="all">&nbsp;<sapn class="radio">전체</sapn>&nbsp;&nbsp;
            <input type="radio" name="receiverType" value="android">&nbsp;<sapn class="radio">Android 유저</sapn>
            &nbsp;&nbsp;
            <input type="radio" name="receiverType" value="ios">&nbsp;<sapn class="radio">iOS 유저</sapn>&nbsp;&nbsp;
            <input type="radio" name="receiverType" value="topic">&nbsp;<sapn class="radio">주제별</sapn>&nbsp;&nbsp;
            <input type="radio" name="receiverType" value="individual">&nbsp;<span class="radio">개인</span>
        </div>


        <!-- 주제선택 -->
        <div class="wrapper hide" id="topicDiv">
            <div>
                <span class="label"></span>
                <span class="label">검색</span>
                <input type="text" class="input-text" placeholder="토픽을 입력해주세요.">
            </div>
            <div>
                <span class="label"></span>
                <span class="label"></span>
                <table>
                    <thead>
                        <tr>
                            <td>선택</td>
                            <td>토픽</td>
                            <td>메모</td>
                        </tr>

                    </thead>
                    <tbody id="topicTbody">
                        <tr>
                            <td><input type="checkbox"></td>
                            <td>tester</td>
                            <td>테스터1</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- 개인선택 -->
        <div class="wrapper hide" id="indDiv">
            <div>
                <span class="label"></span>
                <span class="label">검색</span>
                <select>
                    <option>이름</option>
                    <option>연락처</option>
                </select>
                &nbsp;
                <input type="text" class="input-text" placeholder="이름 혹은 연락처를 입력해주세요.">
            </div>
            <div>
                <span class="label"></span>
                <span class="label"></span>
                <table>
                    <thead>
                        <tr>
                            <td>선택</td>
                            <td>이름</td>
                            <td>연락처</td>
                            <td>메모</td>
                        </tr>

                    </thead>
                    <tbody id="userTbody">
                        <tr>
                            <td><input type="checkbox"></td>
                            <td>tester</td>
                            <td>tester</td>
                            <td>테스터1</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <button onclick="sendCheck()">전송</button>
</body>

</html>

<script>

    var haveImg = '';
    var receiverType = '';
    var imgUrl = '';
    var imgType = 'upload';
    var topicList = [];
    var userList = [];

    /* 토픽 정보 조회 */
    $.ajax({
        url: "topics.json",
        //data: { name: "홍길동" },
        method: "GET",
        dataType: "json"
    })
    .done(function (json) {
        alert(json);
    })
    .fail(function(xhr, status, errorThrown) {
        alert("topic 목록 조회 오류");
    })

    /* 이미지 유무 라디오 선택 이벤트 */
    $('input[name="haveImg"]').change(function () {
        $('input[name="haveImg"]').each(function () {
            var value = $(this).val();
            var checked = $(this).prop('checked');
            var $label = $(this).next();

            if (checked) {
                haveImg = value;
                if (value == 'true') {
                    $('#imgDiv').removeClass('hide');
                } else {
                    $('#imgDiv').addClass('hide');
                }
            }
        });
    });

    /* 이미지 타입 선택 이벤트 */
    $('input[name="imgType"]').change(function () {
        $('input[name="imgType"]').each(function () {
            var value = $(this).val();
            var checked = $(this).prop('checked');
            var $label = $(this).next();

            if (checked) {
                imgType = value;
                if (value == 'upload') {
                    $('#imgUrlInput').addClass('hide');
                    $('#imgUploadBtn').removeClass('hide');
                } else {
                    $('#imgUrlInput').removeClass('hide');
                    $('#imgUploadBtn').addClass('hide');
                }
            }
        });
    });

    /* 수신자 타입 라디오 선택 이벤트 */
    $('input[name="receiverType"]').change(function () {
        $('input[name="receiverType"]').each(function () {
            var value = $(this).val();
            var checked = $(this).prop('checked');
            var $label = $(this).next();

            if (checked) {
                receiverType = value;
                if (value == 'topic') {
                    $('#topicDiv').removeClass('hide');
                    $('#indDiv').addClass('hide');
                } else if (value == 'individual') {
                    $('#topicDiv').addClass('hide');
                    $('#indDiv').removeClass('hide');
                } else {
                    $('#topicDiv').addClass('hide');
                    $('#indDiv').addClass('hide');
                }
            }
        });
    });

    /* 전송 버튼 클릭시 내용 검증 이벤트 */
    function sendCheck() {
        if ($('#titleInput').val() == '') {
            alert("제목을 입력해주세요.");
            return;
        }

        if ($('#msgInput').val() == '') {
            alert("내용을 입력해주세요.");
            return;
        }

        if (haveImg == 'true') {
            if (imgType == 'url') {
                imgUrl = $('#imgUrlInput').val();
                if (imgUrl == '') {
                    alert("이미지 URL을 입력해주세요.")
                    return
                }
            } else {
                if (imgUrl == '') {
                    alert("이미지를 업로드해주세요.")
                    return
                }
            }
        }

        if ($('#appSelect').val() == '선택해주세요') {
            alert("앱을 선택해주세요.");
            return;
        }

        if (receiverType == 'topic') {
            alert("Ttt");
        } else if (receiverType == 'individual') {
            alert("Ttt22");
        }


    }

</script>

<style>
    html {
        margin: 0 auto;
        padding: 0 auto;
        font-size: 13px;
    }

    select {
        width: 100px;
    }

    body {
        width: 800px;
        text-align: center;
        margin: 0 auto;
    }

    table {
        margin-left: 70px;
        width: calc(100% - 70px);
        text-align: center;
        border: 1px #cdcdcd solid;
        border-collapse: collapse;
    }

    tbody {
        display: block;
        max-height: 300px;
        overflow-y: scroll;
    }

    thead,
    tbody tr {
        display: table;
        width: calc(100% - 1px);
        table-layout: fixed;
    }

    tr,
    td {
        height: 40px;
        border: 1px #cdcdcd solid;
    }

    tr td:nth-child(1) {
        width: 40px;
    }

    tr td:nth-child(2) {
        width: 200px;
    }

    .td-check {
        width: 40px;
    }

    .td-content {
        width: 200px;
    }

    .td-phone {
        width: 200px;
    }

    .container {
        text-align: left;
        width: 800px;
        padding: 10px;
        border: #e0e2e5 solid 1px;
        border-radius: 10px;
        margin: 30px 0px;
        margin-left: calc(50% - 400px)
    }

    .wrapper {
        padding: 5px 0px;
    }

    .label {
        width: 70px;
        display: inline-block;
    }

    .input-text {
        width: 200px;
    }

    .hide {
        display: none;
    }
</style>