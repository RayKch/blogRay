<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div id="SideBody">
	<div class="profile">
		<div class="avatar"></div>
		<div class="description">developer</div>
	</div>
	<div id="circle"></div>
	<ul>
		<li>menu1</li>
		<li>menu2</li>
		<li><a href="/board/form" onclick="SideUtil.overflowControl()">Post Write</a></li>
	</ul>
</div>
<div id="BackDrop" onclick="SideUtil.hide();"></div>