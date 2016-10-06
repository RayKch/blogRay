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
					<script id="contentTemplate" type="text/html">
						<div class="post-preview list-top-margin list-header-wrap">
							<h2 class="text-center"><%="${title}"%></h2>
							<p class="post-meta pull-right" style="margin-top:15px; margin-bottom:0; font-size:12px; clear:both"><%="${categoryName}"%> - <%="${categoryDescription}"%></p>

							<div class="clearfix"></div>
							<hr class="list-hr" style="margin-top:5px">

							<p class="post-meta pull-right" style="margin:0; font-size:12px;">Posted by <a href="#"><%="${nickname}"%></a> on <%="${regDate}"%></p>
							<div class="clearfix"></div>
							<div class="post-meta pull-right" style="font-size:12px;"><%="${viewCnt}"%> views</div>
							<br/><br/>
							<div class="clearfix"></div>
						</div>
						<div class="text-left">{{html content.replace(/\n/gi, '<br/>')}}</div>
					</script>
					<div id="divContentWrap" class="col-sm-10 col-sm-offset-1">
						<div class="text-center" style="padding:100px;"><img src="/image/common/ajaxloader.gif"/></div>
					</div>

					<div class="col-sm-10 col-sm-offset-1" style="margin-top:20px">
						<hr style="margin-bottom:12px; border:1px solid #eee">
					</div>

					<c:if test="${sessionScope.loginSeq eq vo.memberSeq}">
						<div class="col-sm-10 col-sm-offset-1">
							<i class="fa fa-times pull-right pointer" aria-hidden="true" style="margin-left:0.6em;" onclick="BoardDeleteUtil.proc('${seq}', 'view')"></i>
							<a href="/board/form?seq=${seq}"><i class="fa fa-pencil pull-right pointer" aria-hidden="true"></i></a>
						</div>
					</c:if>
				</div>
			</div>
		</div>

		<%@ include file="/WEB-INF/view/comment_list.jsp" %>
		<%@ include file="/WEB-INF/view/include/footer.jsp" %>
	</div>
</div>
<%@ include file="/WEB-INF/view/comment_auth_modal.jsp" %>
<%@ include file="/WEB-INF/view/comment_form.jsp" %>
<script src="/scripts/board/board.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		BoardUtil.boardSeq = "${seq}";
		BoardCommentUtil.parameter = {'boardSeq':BoardUtil.boardSeq};
		$("form[name=parentForm]").html($("#commentFormTemplate").tmpl());

		BaordRenderUtil.render();
		BoardCommentRenderUtil.renderList();
		BoardCommentDeleteUtil.deleteBtnHandler();
	});
</script>
</body>
</html>