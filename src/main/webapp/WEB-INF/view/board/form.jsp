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
				<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
					<form method="post" onsubmit="submitProc(this)" target="zeroframe">
						<div class="form-group">
							<label for="categorySeq">카테고리</label>
							<select id="categorySeq" name="categorySeq" class="form-control">
								<option value="">카테고리를 선택하세요</option>
								<c:forEach var="item" items="${categoryList}">
									<option value="${item.seq}">${item.title}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label for="title">제목</label>
							<input type="text" class="form-control" id="title" name="title" placeholder="제목을 입력하세요">
						</div>
						<div class="form-group">
							<label for="content">내용</label>
							<textarea class="form-control" id="content" name="content" rows="5"></textarea>
						</div>
						<button type="submit" class="btn btn-info pull-right">제출</button>
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
</script>
</body>
</html>
