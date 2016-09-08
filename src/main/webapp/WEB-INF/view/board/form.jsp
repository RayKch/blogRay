<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@ include file="/WEB-INF/view/include/header.jsp" %>
	<link href="${pageContext.request.contextPath}/styles/board/board.css" rel="stylesheet">
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
							<textarea class="form-control" id="content" name="content" rows="5" alt="내용">${vo.content}</textarea>
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
					</form>
				</div>
			</div>
		</div>
		<%@ include file="/WEB-INF/view/include/footer.jsp" %>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
	});

	var BoardUtil = {
		submitProc:function(obj) {
			var flag = true;
			$(obj).find("input[alt], textarea[alt], select[alt]").each( function() {
				if(flag && $(this).val() == "" || flag&& $(this).val() == 0) {

					alert($(this).attr("alt") + "란을 채워주세요.");
					flag = false;
					$(this).focus();
				}
			});
			return flag;
		}
	}
</script>
</body>
</html>
