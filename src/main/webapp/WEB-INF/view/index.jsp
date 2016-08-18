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
						<c:forEach var="item" items="${list}">
							<div class="post-preview">
								<a href="post.html">
									<h2 class="post-title">
										Man must explore, and this is exploration at its greatest
									</h2>
									<h3 class="post-subtitle">
										Problems look mighty small from 150 miles up
									</h3>
								</a>
								<p class="post-meta">Posted by <a href="#">Start Bootstrap</a> on September 24, 2014</p>
							</div>
							<hr>
						</c:forEach>

						<!-- Pager -->
						<ul class="pager">
							<li class="next">
								<a href="#">Older Posts &rarr;</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="/WEB-INF/view/include/footer.jsp" %>
	</div>
</div>
</body>
</html>
