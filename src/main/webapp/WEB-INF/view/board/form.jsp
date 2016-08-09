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
					<form method="post">
						<div class="form-group">
							<label for="boardManageSeq">카테고리</label>
							<select id="boardManageSeq" name="boardManageSeq" class="form-control">
								<option>1</option>
								<option>2</option>
								<option>3</option>
								<option>4</option>
								<option>5</option>
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
