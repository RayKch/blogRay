var BoardUtil = {
	vo:{}
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
				if(list.length > 0) {
					$("#divContentList").html($("#contentTemplate").tmpl(list));
				} else {
					$("#divContentList").html($("#emptyContentTemplate"));
				}
			},
			error:function(error) {
				console.log( error.status + ":" +error.statusText );
			}
		});
	}
};

var BoardDeleteUtil = {
	proc:function(seq) {
		if(confirm('정말 삭제하시겠습니까?')) {
			$.ajax({
				url:"/board/delete/proc",
				type:"post",
				data:{'seq':seq},
				dataType:"text",
				success:function(data) {
					if(data === 'success') {
						alert('삭제되었습니다.');
					} else {
						alert('실패하였습니다.');
					}
					CategoryUtil.renderList();
					SideCategoryUtil.renderList();
					BaordRenderUtil.renderList(BoardUtil.vo);
				},
				error:function(error) {
					console.log( error.status + ":" +error.statusText );
				}
			});
		}
	}
};

$(document).ready(function() {
	BaordRenderUtil.renderList(BoardUtil.vo);
});