<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div id="SideBody">
	<div id="visitor-wrap">
		<div><i class="fa fa-user fa-1x" aria-hidden="true"></i>&nbsp;TODAY&nbsp;<span id="todayCnt"></span></div>
		<div><i class="fa fa-users fa-1x" aria-hidden="true"></i>&nbsp;TOTAL&nbsp;<span id="allDayCnt"></span></div>
	</div>

	<div class="profile">
		<div class="avatar"></div>
		<div class="description">developer</div>
	</div>
	<div id="circle"></div>

	<script id="liTemplate" type="text/html">
		{{each(i, item) list}}
			{{if item.typeCode === 'G'}}
				<li><a href="/?categorySeq=<%="${seq}"%>"><%="${title}"%></a></li>

				{{each(i, item2) subList}}
					{{if item.seq === item2.groupSeq}}
						<li style="padding-left:10px"><a href="/?categorySeq=<%="${seq}"%>">-&nbsp;<%="${title}"%> (<%="${boardCount}"%>)</a></li>
					{{/if}}
				{{/each}}
			{{/if}}
		{{/each}}
	</script>
	<ul id="ulList" style="margin-bottom:0">
		<img src="/image/common/ajaxloader.gif" style="margin:40% 0 0 75px"/>
	</ul>
</div>
<div id="BackDrop" onclick="SideUtil.hide();"></div>