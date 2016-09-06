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
//				$("#contentTemplate").tmpl(vo).appendTo("#divContentWrap");
//				$("#divContentWrap").append($("#contentTemplate").tmpl(vo));
				$("#divContentWrap").html($("#contentTemplate").tmpl(vo))
			},
			error:function(error) {
				console.log( error.status + ":" +error.statusText );
			}
		});
	}
	, renderList:function() {
		$.ajax({
			url:"/board/list/json",
			type:"get",
			data:{'categorySeq':BoardUtil.categorySeq},
			dataType:"text",
			success:function(data) {
				var list = $.parseJSON(data);
				if(list.length > 0) {
					$("#divContentList").html($("#contentTemplate").tmpl(list));
				} else {
					$("#divContentList").html($("#emptyContentTemplate").tmpl());
				}
				BaordRenderUtil.renderPaging(list.length);
			},
			error:function(error) {
				console.log( error.status + ":" +error.statusText );
			}
		});
	}
	, renderPaging:function(totall) {
		$('#divPaging').bootpag({
			total: totall,
			page: 2,
			maxVisible: 5,
			leaps: true,
			firstLastUse: true,
			first: '←',
			last: '→',
			wrapClass: 'pagination',
			activeClass: 'active',
			disabledClass: 'disabled',
			nextClass: 'next',
			prevClass: 'prev',
			lastClass: 'last',
			firstClass: 'first'
		}).on("page", function(event, num){
//			$(".content4").html("Page " + num); // or some ajax content loading...
		});
	}
};

var BoardDeleteUtil = {
	proc:function(seq, typeCode) {
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
					if(typeCode === 'list') {
						BaordRenderUtil.renderList();
					} else {
						BaordRenderUtil.render(BoardUtil.boardSeq);
					}
				},
				error:function(error) {
					console.log( error.status + ":" +error.statusText );
				}
			});
		}
	}
};