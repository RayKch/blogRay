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
							<p class="post-meta pull-right" style="margin-bottom:0; font-size:12px; clear:both"><%="${categoryName}"%> - <%="${categoryDescription}"%></p>

							<div class="clearfix"></div>
							<hr class="list-hr" style="margin-top:5px">

							<p class="post-meta pull-right" style="font-size:12px;">Posted by <a href="#"><%="${nickname}"%></a> on <%="${regDate}"%></p>
							<div class="clearfix"></div>
						</div>
						<div class="text-left">{{html content.replace(/\n/gi, '<br/>')}}</div>
					</script>
					<div id="divContentWrap" class="col-sm-10 col-sm-offset-1">
						<div class="text-center" style="padding:100px;"><img src="/image/common/ajaxloader.gif"/></div>
					</div>

					<c:if test="${sessionScope.loginSeq eq vo.memberSeq}">
						<div class="col-sm-10 col-sm-offset-1" style="margin-top:20px">
							<i class="fa fa-times pull-right pointer" aria-hidden="true" style="margin-left:0.6em;" onclick="BoardDeleteUtil.proc('${seq}', 'view')"></i>
							<a href="/board/form?seq=${seq}"><i class="fa fa-pencil pull-right pointer" aria-hidden="true"></i></a>
						</div>
					</c:if>
				</div>
			</div>
		</div>

		<div class="container" style="margin-top:100px">
			<div class="row">
				<%--댓글리스트--%>
				<script id="commentTemplate" type="text/html">
					{{each(i, item1) parentList}}
						{{if item1.delYn === 'Y' && (item1.childCount === 0)}}
						{{else}}
							{{if item1.parentSeq === null || item1.parentSeq === 0}}
							<li class="media">
								<div class="media-body">
									<div class="well well-lg">
										<h4 class="media-heading text-uppercase reviews">
											{{if item1.nonSignUpNickname === null}}
												<%="${item1.nickname}"%>
											{{else}}
												<%="${item1.nonSignUpNickname}"%>
											{{/if}}
										</h4>
										<ul class="media-date text-uppercase reviews list-inline">
											<li class="dd"><%="${item1.regDate}"%></li>
										</ul>
										<p class="media-comment">
											{{if item1.delYn === 'N'}}
												{{html item1.content.replace(/\n/gi, '<br/>')}}
											{{else}}
												삭제된 댓글 입니다.
											{{/if}}
										</p>
										<div>
											<a class="btn btn-info btn-circle text-uppercase" href="#" onclick="BoardCommentSubmitUtil.openChildForm(<%="${item1.seq}"%>)" class="reply"><span class="glyphicon glyphicon-share-alt"></span> reply</a>
											<c:choose>
												<c:when test="${sessionScope.loginSeq eq null}"> <%--비회원일 경우--%>
													{{if item1.memberSeq === null}}
														<i class="fa fa-times pull-right remove-btn pointer" aria-hidden="true" style="margin-top:10px;" onclick="BoardCommentDeleteUtil.auth('nonMember', '<%="${item1.seq}"%>')"></i>
													{{/if}}
												</c:when>
												<c:otherwise> <%--회원일 경우--%>
													{{if ${sessionScope.loginSeq} === item1.memberSeq}}
														<i class="fa fa-times pull-right remove-btn pointer" aria-hidden="true" style="margin-top:10px;" onclick="BoardCommentDeleteUtil.auth('member', '<%="${item1.seq}"%>')"></i>
													{{/if}}
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
								<form name="childForm<%="${item1.seq}"%>" class="form-horizontal"></form>
								{{each(j, item2) childList}}
									{{if item2.delYn === 'N' && item1.seq === item2.parentSeq}}
									<div>
										<ul class="media-list">
											<li class="media media-replied">
												<div class="media-body">
													<div class="well well-lg">
														<h4 class="media-heading text-uppercase reviews">
															<span class="glyphicon glyphicon-share-alt"></span>
															{{if item2.nonSignUpNickname === null}}
																<%="${item2.nickname}"%>
															{{else}}
																<%="${item2.nonSignUpNickname}"%>
															{{/if}}
														</h4>
														<ul class="media-date text-uppercase reviews list-inline">
															<li class="dd"><%="${item2.regDate}"%></li>
														</ul>
														<p class="media-comment">
															{{html item2.content.replace(/\n/gi, '<br/>')}}
														</p>
														<div>
															<c:choose>
																<c:when test="${sessionScope.loginSeq eq null}"> <%--비회원일 경우--%>
																	{{if item2.memberSeq === null}}
																		<i class="fa fa-times pull-right remove-btn pointer" aria-hidden="true" style="margin-top:-20px;" onclick="BoardCommentDeleteUtil.auth('nonMember', '<%="${item2.seq}"%>')"></i>
																	{{/if}}
																</c:when>
																<c:otherwise> <%--회원일 경우--%>
																	{{if ${sessionScope.loginSeq} === item2.memberSeq}}
																		<i class="fa fa-times pull-right remove-btn pointer" aria-hidden="true" style="margin-top:-20px;" onclick="BoardCommentDeleteUtil.auth('member', '<%="${item2.seq}"%>')"></i>
																	{{/if}}
																</c:otherwise>
															</c:choose>
														</div>
													</div>
												</div>
											</li>
										</ul>
									</div>
									{{/if}}
								{{/each}}
							</li>
							{{/if}}
						{{/if}}
					{{/each}}
				</script>
				<%--non댓글리스트--%>
				<script id="nonCommentTemplate" type="text/html">
					<div class="well well-lg text-center" style="font-size:12px;">댓글이 없습니다.</div>
				</script>

				<div class="col-sm-10 col-sm-offset-1">
					<div class="page-header">
						<h3 class="reviews">Comment (<span id="commentCount"></span>)</h3>
					</div>

					<ul id="ulCommentWrap" class="media-list">
						<li class="media">
							<div class="media-body">
								<div class="well well-lg"><div class="text-center"><img src="/image/common/ajaxloader.gif"/></div></div>
							</div>
						</li>
					</ul>

					<%--고정 댓글--%>
					<form name="parentForm" class="form-horizontal"></form>
				</div>
			</div>
		</div>
		<%@ include file="/WEB-INF/view/include/footer.jsp" %>
	</div>
</div>
<%@ include file="/WEB-INF/view/comment_auth_modal.jsp" %>
<%@ include file="/WEB-INF/view/comment_form.jsp" %>
<script src="/scripts/board/board.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		BoardUtil.boardSeq = "${seq}";
		$("form[name=parentForm]").html($("#commentFormTemplate").tmpl());

		BaordRenderUtil.render();
		BoardCommentRenderUtil.renderList();
		BoardCommentDeleteUtil.deleteBtnHandler();
	});
</script>
</body>
</html>
