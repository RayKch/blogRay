<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@ include file="/WEB-INF/view/include/header.jsp" %>
</head>

<body>
<div id="wrapper">
	<%@ include file="/WEB-INF/view/include/side_menu.jsp" %>
	<div class="scrollable-wrapper">
		<%@ include file="/WEB-INF/view/include/header_hero.jsp" %>
		<div id="page-content-wrapper">
			<div class="container">
				<div class="row">
					<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
						<div class="post-preview list-top-margin text-center list-header-wrap">
							<h2>${vo.title}</h2>
							<hr class="list-hr">
							<p class="post-meta pull-right">Posted by <a href="#">${vo.nickname}</a> on ${fn:substring(vo.regDate, 0, 10)}</p>
							<span class="list-description">${vo.content}</span>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="/WEB-INF/view/include/footer.jsp" %>
	</div>
</div>
</body>
</html>
