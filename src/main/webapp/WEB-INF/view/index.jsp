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

					<div id="divContentList" class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
						<script id="contentTemplate" type="text/html">
							<div class="post-preview list-top-margin">
								<a href="/view?seq=<%="${seq}"%>">
									<h2 class="post-title"><%="${title}"%></h2>
									<h3 class="post-subtitle"><%="${content}"%></h3>
								</a>
								<p class="post-meta">
									Posted by <a href="#"><%="${nickname}"%></a> on <%="${regDate}"%>
									<c:if test="${sessionScope.loginSeq ne null and sessionScope.loginSeq ne ''}">
										{{if  ${sessionScope.loginSeq} === memberSeq}}
											<i class="fa fa-times pull-right remove-btn pointer" aria-hidden="true" onclick="BoardDeleteUtil.proc('<%="${seq}"%>')"></i>
										{{/if}}
									</c:if>
								</p>
							</div>
							<hr>
						</script>
						<script id="emptyContentTemplate" type="text/html">
							<div class="post-preview list-top-margin">
								<h2 class="post-title text-center empty-post">포스트가 없습니다.</h2>
							</div>
						</script>
						<div class="text-center" style="padding:100px;"><img src="/image/common/ajaxloader.gif"/></div>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="/WEB-INF/view/include/footer.jsp" %>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		BoardUtil.vo = "${vo}";
	});
</script>
</body>
</html>
