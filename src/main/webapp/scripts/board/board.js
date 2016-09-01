var BoardUtil = {
	loginSeq:0
	, vo:{}
};

var BaordRenderUtil = {
	renderList:function(vo) {
		$.ajax({
			url:"/board/list/json",
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