<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div id="SideBody">
	<div class="profile">
		<div class="avatar"></div>
		<div class="description">developer</div>
	</div>
	<div id="circle"></div>

	<script id="liTemplate" type="text/html">
		<li><a href="/board/list/<%="${seq}"%>"><%="${title}"%></a></li>
	</script>
	<ul id="ulList">
		<img src="/image/common/ajaxloader.gif"/>
	</ul>
</div>
<div id="BackDrop" onclick="SideUtil.hide();"></div>