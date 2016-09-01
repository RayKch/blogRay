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
					<c:if test="${categoryVo ne null}">
						<div class="col-sm-10 col-sm-offset-1">
							<div class="post-preview list-top-margin list-header-wrap">
								<h2 class="text-center">${categoryVo.title}</h2>
								<p class="post-meta pull-right" style="margin-bottom:0; font-size:12px; clear:both">${categoryVo.description}</p>
								<div class="clearfix"></div>
								<hr class="list-hr" style="margin-top:5px">
							</div>
						</div>
					</c:if>

					<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
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
									<c:if test="${sessionScope.loginSeq eq item.memberSeq}">
										<i class="fa fa-times pull-right remove-btn" aria-hidden="true" data-seq="${item.seq}"></i>
									</c:if>
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
<script src="/scripts/board/board.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.remove-btn').on('click', function() {
			$(this).attr('data-seq')

		});
	});
</script>
</body>
</html>
