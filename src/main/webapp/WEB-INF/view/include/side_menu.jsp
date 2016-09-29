<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div id="SideBody">
	<div class="profile">
		<div class="avatar"></div>
		<div class="description">developer</div>
	</div>
	<div id="circle"></div>

	<script id="liTemplate" type="text/html">
		<li><a href="/?categorySeq=<%="${seq}"%>"><%="${title}"%> (<%="${boardCount}"%>)</a></li>
	</script>
	<ul id="ulList" style="margin-bottom:0">
		<img src="/image/common/ajaxloader.gif" style="margin:40% 0 0 75px"/>
	</ul>
</div>
<div id="BackDrop" onclick="SideUtil.hide();"></div>