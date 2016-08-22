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
						<c:if test="${categoryVo ne null}">
							<div class="list-header-wrap text-center">
								<h2>${categoryVo.title}</h2>
								<hr class="list-hr">
								<span class="list-description">${categoryVo.description}</span>
							</div>
						</c:if>

						<c:forEach var="item" items="${list}">
							<div class="post-preview list-top-margin">
								<a href="/view?seq=${item.seq}">
									<h2 class="post-title">
										${item.title}
									</h2>
									<h3 class="post-subtitle">
										${item.content}
									</h3>
								</a>
								<p class="post-meta">
									Posted by <a href="#">${item.nickname}</a> on ${fn:substring(item.regDate, 0, 10)}
									<i class="fa fa-times pull-right" aria-hidden="true"></i>
								</p>
							</div>
							<hr>
						</c:forEach>
						<c:if test="${fn:length(list) eq 0}">
							<div class="post-preview list-top-margin">
								<h2 class="post-title text-center">
									포스트가 없습니다.
								</h2>
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="/WEB-INF/view/include/footer.jsp" %>
	</div>
</div>
</body>
</html>
