<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<hr>
<iframe id="zeroframe" name="zeroframe" src="about:blank" width="0" height="0" frameborder="0"></iframe>
<script src="/scripts/plugin/jquery.js"></script>
<script src="/scripts/plugin/jquery.tmpl.js"></script>
<script src="/scripts/plugin/jquery.cookie.js"></script>
<script src="/scripts/plugin/bootstrap.min.js"></script>
<script src="/scripts/plugin/clean-blog.min.js"></script>
<script src="/scripts/common/common.js"></script>
<div class="sr-only">
	<script>
		(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
			(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
				m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
		})(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

		ga('create', 'UA-71358174-2', 'auto');
		ga('send', 'pageview');

	</script>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		//최초 쿠키에 loginId라는 쿠키값이 존재하면
		var loginId = $.cookie('loginId');
		if(loginId != undefined) {
			//아이디에 쿠키값을 담는다
			$("#loginId").val(loginId);
			//아이디저장 체크박스 체크를 해놓는다
			$("#rememberId").prop("checked",true);
		}

		SideUtil.loginSeq = "${sessionScope.loginSeq}";
		SideUtil.loginId = "${sessionScope.loginId}";
		SideCategoryUtil.renderList();
		StatsUtil.render();
	});
</script>
<%@ include file="/WEB-INF/view/include/login_modal.jsp" %>
<footer>
	<div class="container">
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
				<ul class="list-inline text-center">
					<li>
						<a href="https://www.facebook.com/chanpong.kim" target="_blank">
							<span class="fa-stack fa-lg">
								<i class="fa fa-circle fa-stack-2x"></i>
								<i class="fa fa-facebook fa-stack-1x fa-inverse"></i>
							</span>
						</a>
					</li>
					<li>
						<a href="https://github.com/RayKch" target="_blank">
							<span class="fa-stack fa-lg">
								<i class="fa fa-circle fa-stack-2x"></i>
								<i class="fa fa-github fa-stack-1x fa-inverse"></i>
							</span>
						</a>
					</li>
				</ul>
				<p class="copyright text-muted">Copyright &copy; Your Website 2014</p>
			</div>
		</div>
	</div>
</footer>