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
					<div class="col-sm-10 col-sm-offset-1">
						<div class="post-preview list-top-margin list-header-wrap">
							<h2 class="text-center">${vo.title}</h2>
							<p class="post-meta pull-right" style="margin-bottom:0; font-size:12px; clear:both">${vo.categoryName} - ${vo.categoryDescription}</p>

							<div class="clearfix"></div>
							<hr class="list-hr" style="margin-top:5px">

							<p class="post-meta pull-right" style="font-size:12px;">Posted by <a href="#">${vo.nickname}</a> on ${fn:substring(vo.regDate, 0, 10)}</p>
							<div class="clearfix"></div>
						</div>
						<div class="text-left">${vo.content}</div>
					</div>
				</div>
			</div>
		</div>
		<div class="container" style="margin-top:100px">
			<div class="row">
				<div class="col-sm-10 col-sm-offset-1">
					<div class="page-header">
						<h3 class="reviews">your comment</h3>
					</div>
					<ul class="media-list">
						<li class="media">
							<a class="pull-left" href="#">
								<img class="media-object img-circle" src="https://s3.amazonaws.com/uifaces/faces/twitter/dancounsell/128.jpg" alt="profile">
							</a>
							<div class="media-body">
								<div class="well well-lg">
									<h4 class="media-heading text-uppercase reviews">Marco </h4>
									<ul class="media-date text-uppercase reviews list-inline">
										<li class="dd">22</li>
										<li class="mm">09</li>
										<li class="aaaa">2014</li>
									</ul>
									<p class="media-comment">
										Great snippet! Thanks for sharing.
									</p>
									<a class="btn btn-info btn-circle text-uppercase" href="#" id="reply"><span class="glyphicon glyphicon-share-alt"></span> Reply</a>
									<a class="btn btn-warning btn-circle text-uppercase" data-toggle="collapse" href="#replyOne"><span class="glyphicon glyphicon-comment"></span> 2 comments</a>
								</div>
							</div>
							<div class="collapse" id="replyOne">
								<ul class="media-list">
									<li class="media media-replied">
										<a class="pull-left" href="#">
											<img class="media-object img-circle" src="https://s3.amazonaws.com/uifaces/faces/twitter/ManikRathee/128.jpg" alt="profile">
										</a>
										<div class="media-body">
											<div class="well well-lg">
												<h4 class="media-heading text-uppercase reviews"><span class="glyphicon glyphicon-share-alt"></span> The Hipster</h4>
												<ul class="media-date text-uppercase reviews list-inline">
													<li class="dd">22</li>
													<li class="mm">09</li>
													<li class="aaaa">2014</li>
												</ul>
												<p class="media-comment">
													Nice job Maria.
												</p>
												<a class="btn btn-info btn-circle text-uppercase" href="#"><span class="glyphicon glyphicon-share-alt"></span> Reply</a>
											</div>
										</div>
									</li>
									<li class="media media-replied" id="replied">
										<a class="pull-left" href="#">
											<img class="media-object img-circle" src="https://pbs.twimg.com/profile_images/442656111636668417/Q_9oP8iZ.jpeg" alt="profile">
										</a>
										<div class="media-body">
											<div class="well well-lg">
												<h4 class="media-heading text-uppercase reviews"><span class="glyphicon glyphicon-share-alt"></span> Mary</h4></h4>
												<ul class="media-date text-uppercase reviews list-inline">
													<li class="dd">22</li>
													<li class="mm">09</li>
													<li class="aaaa">2014</li>
												</ul>
												<p class="media-comment">
													Thank you Guys!
												</p>
												<a class="btn btn-info btn-circle text-uppercase" href="#"><span class="glyphicon glyphicon-share-alt"></span> Reply</a>
											</div>
										</div>
									</li>
								</ul>
							</div>
						</li>
					</ul>
					<form action="#" method="post" class="form-horizontal" id="commentForm" role="form">
						<div class="form-group">
							<label for="uploadMedia" class="col-sm-2 control-label">NickName</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="uploadMedia" id="uploadMedia">
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-sm-2 control-label">Comment</label>
							<div class="col-sm-10">
								<textarea class="form-control" name="addComment" id="addComment" rows="5"></textarea>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button class="btn btn-success btn-circle text-uppercase" type="submit" id="submitComment"><span class="glyphicon glyphicon-send"></span> Summit comment</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<%@ include file="/WEB-INF/view/include/footer.jsp" %>
	</div>
</div>
<script src="/scripts/board/board.js"></script>
</body>
</html>
