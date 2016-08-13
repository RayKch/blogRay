<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div id="SideBody">
	<div class="profile">
		<div class="avatar"></div>
		<div class="description">developer</div>
	</div>
	<div id="circle"></div>
	<ul>
		<c:if test="${sessionScope.loginSeq eq 1 and sessionScope.loginId eq 'rlacksgh08@naver.com'}">
			<li><a href="/board/form">포스트 작성</a></li>
			<li><a href="/category/form">카테고리 관리</a></li>
		</c:if>
	</ul>
</div>
<div id="BackDrop" onclick="SideUtil.hide();"></div>