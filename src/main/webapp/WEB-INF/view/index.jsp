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
					<script id="categoryTemplate" type="text/html">
						<div class="post-preview list-top-margin list-header-wrap">
							<h2 class="text-center"><%="${title}"%> (<%="${boardCount}"%>)</h2>
							<p class="post-meta pull-right" style="margin-bottom:0; font-size:12px; clear:both"><%="${description}"%></p>
							<div class="clearfix"></div>
							<hr class="list-hr" style="margin-top:5px">
						</div>
					</script>
					<div id="divCategoryWrap" class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1"></div>

					<script id="contentTemplate" type="text/html">
						<div class="post-preview list-top-margin">
							<a href="/view/<%="${seq}"%>" style="text-decoration: none"><h2 class="post-title"><%="${title}"%> <span style="font-size:25px">(<%="${commentCount}"%>)</span></h2></a>
							<%--<h3 class="post-subtitle">{{html content.replace(/\n/gi, '<br/>')}}</h3>--%>
							<p class="post-meta" style="margin-bottom:0">
								Posted by <a href="#"><%="${nickname}"%></a> on <%="${regDate}"%>
							</p>
							<div class="post-meta"><%="${viewCnt}"%> view</div>
							<c:if test="${sessionScope.loginSeq ne null and sessionScope.loginSeq ne ''}">
								{{if  ${sessionScope.loginSeq} === memberSeq}}
									<i class="fa fa-times pull-right remove-btn pointer" style="margin-top:-20px" aria-hidden="true" onclick="BoardDeleteUtil.proc('<%="${seq}"%>', 'list')"></i>
								{{/if}}
							</c:if>
						</div>
						<hr>
					</script>
					<script id="emptyContentTemplate" type="text/html">
						<div class="post-preview list-top-margin">
							<h2 class="post-title text-center empty-post">포스트가 없습니다.</h2>
						</div>
					</script>
					<div id="divContentList" class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
						<div class="text-center" style="padding:100px;"><img src="/image/common/ajaxloader.gif"/></div>
					</div>
					<div class="text-center"><div id="divPaging"></div></div>
				</div>
			</div>
		</div>
		<%@ include file="/WEB-INF/view/include/footer.jsp" %>
	</div>
</div>
<script src="/scripts/board/board.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		BoardUtil.categorySeq = "${vo.categorySeq}";
		BaordRenderUtil.renderList(0, (function () {
			BaordRenderUtil.renderPaging(0, (function () {
				BaordRenderUtil.renderCategory();
			})());
		})());
	});
</script>
</body>
</html>
