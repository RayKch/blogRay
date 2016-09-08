var BoardUtil = {
	boardSeq:0
	, categorySeq:0
};

var BaordRenderUtil = {
	renderCategory:function() {
		$.ajax({
			url:"/category/data/json",
			type:"get",
			data:{'seq':BoardUtil.categorySeq},
			dataType:"text",
			success:function(data) {
				if(data !== '') {
					var vo = $.parseJSON(data);
					$("#divCategoryWrap").html($("#categoryTemplate").tmpl(vo));
				}
			},
			error:function(error) {
				console.log( error.status + ":" +error.statusText );
			}
		});
	}
	, render:function() {
		$.ajax({
			url:"/board/data/json",
			type:"get",
			data:{'seq':BoardUtil.boardSeq},
			dataType:"text",
			success:function(data) {
				var vo = $.parseJSON(data);
				$("#divContentWrap").html($("#contentTemplate").tmpl(vo));
				BoardUtil.categorySeq = vo.categorySeq;
			},
			error:function(error) {
				console.log( error.status + ":" +error.statusText );
			}
		});
	}
	, renderList:function(pageNum, callback) {
		$.ajax({
			url:"/board/list/json",
			type:"get",
			data:{'categorySeq':BoardUtil.categorySeq, 'pageNum': pageNum},
			dataType:"text",
			success:function(data) {
				var list = $.parseJSON(data);
				if(list.length > 0) {
					$("#divContentList").html($("#contentTemplate").tmpl(list));

					if (typeof callback === "function") {
						callback();
					}
				} else {
					$('#divPaging').hide();
					$("#divContentList").html($("#emptyContentTemplate").tmpl());
				}
			},
			error:function(error) {
				console.log( error.status + ":" +error.statusText );
			}
		});
	}
	, renderPaging:function(pageNum) {
		$.ajax({
			url:"/board/list/paging/json",
			type:"get",
			data:{'categorySeq':BoardUtil.categorySeq, 'pageNum': pageNum},
			dataType:"text",
			success:function(data) {
				$("#divPaging").html(data);
				$("#divPaging").addClass("pagination");
			},
			error:function(error) {
				console.log( error.status + ":" +error.statusText );
			}
		});
	}
};

var BoardDeleteUtil = {
	proc:function(seq, type) {
		if(confirm('정말 삭제하시겠습니까?')) {
			$.ajax({
				url:"/board/delete/proc",
				type:"post",
				data:{'seq':seq},
				dataType:"text",
				success:function(data) {
					if(data === 'success') {
						alert('삭제되었습니다.');

						if(type === 'list') {
							BaordRenderUtil.renderList(0);
						} else {
							location.href='/?categorySeq='+BoardUtil.categorySeq;
						}
					} else {
						alert('실패하였습니다.');
					}
				},
				error:function(error) {
					console.log( error.status + ":" +error.statusText );
				}
			});
		}
	}
};

var goPage = function (page) {
	BaordRenderUtil.renderList(page, (function () {
		BaordRenderUtil.renderPaging(page);
	})());
};