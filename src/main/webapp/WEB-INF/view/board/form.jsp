<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@ include file="/WEB-INF/view/include/header.jsp" %>
	<link href="${pageContext.request.contextPath}/styles/board/board.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/styles/plugin/summernote.css" rel="stylesheet">
</head>

<body>
<div id="wrapper">
	<%@ include file="/WEB-INF/view/include/side_menu.jsp" %>
	<div class="scrollable-wrapper">
		<%@ include file="/WEB-INF/view/include/header_hero.jsp" %>
		<div class="container">
			<div class="row">
				<c:set var="action" value=""/>
				<c:choose>
					<c:when test="${vo eq null}">
						<c:set var="action" value="insert"/>
					</c:when>
					<c:otherwise>
						<c:set var="action" value="update"/>
					</c:otherwise>
				</c:choose>
				<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
					<form action="/board/${action}/proc" method="post" onsubmit="return BoardUtil.submitProc(this)" target="zeroframe">
						<input type="hidden" name="seq" value="${seq}"/>
						<div class="form-group">
							<label for="categorySeq">카테고리</label>
							<select id="categorySeq" name="categorySeq" class="form-control" alt="카테고리">
								<option value="">카테고리를 선택하세요</option>
								<c:forEach var="item" items="${categoryList}">
									<option value="${item.seq}" ${vo ne null && vo.categorySeq eq item.seq ? "selected" :  ""}>${item.title}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label for="title">제목</label>
							<input type="text" class="form-control" id="title" name="title" value="${vo.title}" alt="제목" placeholder="제목을 입력하세요">
						</div>
						<div class="form-group">
							<label for="content">내용</label>
							<div id="content">${vo.content}</div>
							<div class="hide"><textarea name="content"></textarea></div>
						</div>
						<button type="submit" class="btn btn-info pull-right">
							<c:choose>
								<c:when test="${vo eq null}">
									등록
								</c:when>
								<c:otherwise>
									수정
								</c:otherwise>
							</c:choose>
						</button>
						<div id="editorImgData" class="hide"></div>
					</form>
				</div>
			</div>
		</div>
		<%@ include file="/WEB-INF/view/include/footer.jsp" %>
	</div>
</div>
<script src="/scripts/plugin/summernote.min.js"></script>
<script src="/scripts/plugin/summernote-ko-KR.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#content").summernote({
			height:350,
			minHeight:350,
			lang: 'ko-KR',
			callbacks: {
				onImageUpload: function(files, editor, welEditable) {
					for (var i = files.length - 1; i >= 0; i--) {
						BoardUtil.sendFile(files[i], this);
					}
				}
			}
		});
	});

	var BoardUtil = {
		tempList:[]
		, submitProc:function(obj) {
			var flag = true;
			$(obj).find("input[alt], textarea[alt], select[alt]").each( function() {
				if(flag && $(this).val() == "" || flag&& $(this).val() == 0) {
					alert($(this).attr("alt") + "란을 채워주세요.");
					flag = false;
					$(this).focus();
				}
			});

			if(flag) {
				//codeview 상태라면 강제로 에디터 상태로 변경한다
				if($("#content").parent().find(".codeview").length > 0) {
					$("#content").parent().find("button[data-event=codeview]").click();
					alert("에디터 상태를 일반 모드로 변경합니다. 확인을 눌러주세요");
				}

				//에디터내용을 실제 등록될 태그에 넣기
				$("textarea[name=content]").val( $("#content").summernote('code') );

				//임시 등록된 이미지 데이터 생성
				var html = '', vo = {}, list = BoardUtil.tempList;
				for(var i=0; i<list.length; i++ ){
					vo = $.extend(true, {}, list[i]); // deep copy

					for(var prop in vo) {
						html += "<textarea name='fileList["+i+"]."+prop+"'>"+vo[prop]+"</textarea>";
					}
				}
				$('#editorImgData').html( html );
			}

			return flag;
		}
		, sendFile:function(file, obj) {
			var data = new FormData();
			data.append("file", file);
			$.ajax({
				data: data,
				type: "POST",
				url: '/file/editor/image/upload',
				cache: false,
				contentType: false,
				enctype: 'multipart/form-data',
				processData: false,
				success: function(data) {
					//포스트 등록시 같이 전송되어져야할 이미지 데이터
					var img = {};
					img.contentType = data.contentType;
					img.fileName = data.fileName;
					img.tempFileName = data.tempFileName;
					BoardUtil.tempList.push(img);

					//editor에 임시 등록된 이미지 썸네일로 보여주도록
					var url = data.url + '?typeCode=temp&fileName=' + data.tempFileName + '&contentType=' + data.contentType;
					$(obj).summernote('editor.insertImage', url);
				}
			});
		}
	}
</script>
</body>
</html>
