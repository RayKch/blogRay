var BoardUtil = {
	loginSeq:0
	, vo:{}
};

var BaordRenderUtil = {
	renderList:function(vo) {
		$.ajax({
			url:"/board/list",
			type:"get",
			data:vo,
			dataType:"text",
			success:function(data) {
				var list = $.parseJSON(data);
				var obj = {};
				if(list.length > 0) {
					$("#divContentList").html($("#contentTemplate").tmpl(list));
				} else {

				}

				if((SideUtil.loginSeq === '1' && SideUtil.loginId === 'rlacksgh08@naver.com')
					|| (SideUtil.loginSeq === '2' && SideUtil.loginId === 'ejddl26@naver.com')) {
					var html = '<li><a href="/board/form" class="btn btn-info btn-sm">포스트 작성</a><a href="/category/form" class="btn btn-success btn-sm category-btn">카테고리 관리</a></li>';
					$("#ulList").prepend(html);
				}
			},
			error:function(error) {
				console.log( error.status + ":" +error.statusText );
			}
		});
	}
};

var BoardDeleteUtil = {
	deletePost:function(seq) {

	}
};

$(document).ready(function() {
	BaordRenderUtil.renderList(BoardUtil.vo);
});