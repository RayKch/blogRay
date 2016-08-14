<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<header class="intro-header" style="background-image: url('/image/main/summer/home-bg.jpg');">
	<div class="navigation-menu-btn">
		<c:if test="${sessionScope.loginSeq > 0}">
			<div id="sessionNickname">${sessionScope.nickname} 님</div>
		</c:if>

		<c:choose>
			<c:when test="${sessionScope.loginSeq > 0}"><i id="logoutBtn" class="fa fa-sign-out fa-2x pointer" aria-hidden="true"></i></c:when>
			<c:otherwise><i id="loginModalBtn" class="fa fa-sign-in fa-2x pointer" aria-hidden="true"></i></c:otherwise>
		</c:choose>
		<i class="fa fa-bars fa-2x pointer" onclick="SideUtil.show();"></i>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
				<div class="site-heading">
					<h1><a href="/">CP Blog</a></h1>
					<hr class="small">
					<span class="subheading">welcome~</span>
				</div>
			</div>
		</div>
	</div>
</header>