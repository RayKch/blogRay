<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<hr>
<iframe id="zeroframe" name="zeroframe" src="about:blank" width="0" height="0" frameborder="0"></iframe>
<script src="/scripts/plugin/jquery.js"></script>
<script src="/scripts/plugin/bootstrap.min.js"></script>
<script src="/scripts/plugin/clean-blog.min.js"></script>
<script src="/scripts/common/common.js"></script>
<%@ include file="/WEB-INF/view/include/login_modal.jsp" %>
<footer>
	<div class="container">
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
				<ul class="list-inline text-center">
					<li>
						<a href="#">
							<span class="fa-stack fa-lg">
								<i class="fa fa-circle fa-stack-2x"></i>
								<i class="fa fa-twitter fa-stack-1x fa-inverse"></i>
							</span>
						</a>
					</li>
					<li>
						<a href="#">
							<span class="fa-stack fa-lg">
								<i class="fa fa-circle fa-stack-2x"></i>
								<i class="fa fa-facebook fa-stack-1x fa-inverse"></i>
							</span>
						</a>
					</li>
					<li>
						<a href="#">
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