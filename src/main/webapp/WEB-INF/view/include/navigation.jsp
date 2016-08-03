<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<nav class="navbar navbar-default navbar-custom navbar-fixed-top">
	<%--아래 모바일버전 PC 버전 메뉴가 따로따로 구동되는거 하나로 합칠수 있도록 할것 급선무.--%>
	<div class="container-fluid">
		<!-- 모바일버전 일때 보이는 메뉴 -->
		<div class="navbar-header page-scroll">
			<button type="button" id="menu-toggle" class="navbar-toggle header-menu-btn" data-toggle="collapse">
				<i class="fa fa-bars fa-2x"></i>
			</button>
		</div>

		<!-- PC버전일때 보이는 메뉴 -->
		<div id="bs-example-navbar-collapse-1" class="collapse navbar-collapse">
			<div class="nav navbar-nav navbar-right pc-header-bars">
				<div class="header-menu-btn pointer" data-toggle="collapse">
					<i class="fa fa-bars fa-2x" style="color:#fff"></i>
				</div>
			</div>
		</div>
	</div>
</nav>