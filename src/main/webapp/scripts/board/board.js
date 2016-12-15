var BoardUtil = {
	boardSeq:null
	, categorySeq:null
	, count:0
};

var BaordRenderUtil = {
	renderCategory:function() {
		$.ajax({
			url:"/category/data/json",
			type:"get",
			data:{'seq':BoardUtil.categorySeq},
			dataType:"text",
			success:function(data) {
				var vo = $.parseJSON(data);
				if(vo.title === null) {
					var obj = {'title':'전체', 'description':'', 'boardCount':vo.boardCount};
					$("#divCategoryWrap").html($("#categoryTemplate").tmpl(obj));
				} else {
					$("#divCategoryWrap").html($("#categoryTemplate").tmpl(vo));
				}

				SeoUtil.render(vo.title, vo.description, "category");
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
				SeoUtil.render(vo.title, vo.title, "board");
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
	, renderPaging:function(pageNum, callback) {
		$.ajax({
			url:"/board/list/paging/json",
			type:"get",
			data:{'categorySeq':BoardUtil.categorySeq, 'pageNum': pageNum},
			dataType:"text",
			success:function(data) {
				$("#divPaging").html(data);
				$("#divPaging").addClass("pagination");

				if (typeof callback === "function") {
					callback();
				}
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
							BaordRenderUtil.renderList(0, (function () {
								BaordRenderUtil.renderPaging(0);
							})());
						} else {
							location.href='/?categorySeq='+BoardUtil.categorySeq;
						}
					} else {
						alert(data);
					}
				},
				error:function(error) {
					console.log( error.status + ":" +error.statusText );
				}
			});
		}
	}
};

//코맨트 로직 처리부분
var BoardCommentUtil = {
	seq:null
	, parentSeq:null
	, typeCode:''
	, parameter:{}
};

var BoardCommentRenderUtil = {
	renderList:function() {
		$.ajax({
			url:"/board/comment/list/json",
			type:"get",
			data:BoardCommentUtil.parameter,
			dataType:"text",
			success:function(data) {
				var list = $.parseJSON(data);
				if(list.length > 0) {
					var obj = {'parentList':list, 'childList':list};
					$("#ulCommentWrap").html($("#commentTemplate").tmpl(obj));
				} else {
					$("#ulCommentWrap").html($("#nonCommentTemplate").tmpl());
				}

				var sizeCnt = 0;
				for(var i=0; i<list.length; i++) {
					if(list[i].delYn === 'N') {
						sizeCnt++;
					}
				}
				$('#commentCount').text(sizeCnt);
			},
			error:function(error) {
				console.log( error.status + ":" +error.statusText );
			}
		});
	}
};

var BoardCommentSubmitUtil = {
	openChildForm:function(seq) {
		BoardCommentUtil.parentSeq = seq;
		var flag = $("form[name=childForm"+seq+"]").find('div').hasClass('comment-form-wrapper');
		if(flag) {
			$("form[name=childForm"+seq+"]").find('.comment-form-wrapper').remove();
		} else {
			$("form[name=childForm"+seq+"]").html($("#commentFormTemplate").tmpl());
		}
	}
	, validation:function(obj) {
		var formName = $(obj).parents('form').attr('name');
		var flag = true;
		$('form[name='+formName+']').find("input[alt], textarea[alt], select[alt]").each( function() {
			if(flag && $(this).val() == "" || flag&& $(this).val() == 0) {

				alert($(this).attr("alt") + "란을 채워주세요.");
				flag = false;
				$(this).focus();
			}
		});
		return flag;
	}
	, mappingVo:function(obj) {
		var formName = $(obj).parents('form').attr('name');
		var data = {
			'nickname':$('form[name='+formName+']').find('input[name=nickname]').val()
			, 'content':$('form[name='+formName+']').find('textarea[name=content]').val()
			, 'password':$('form[name='+formName+']').find('input[name=password]').val()
			, 'boardSeq':BoardUtil.boardSeq
			, 'parentSeq':BoardCommentUtil.parentSeq
			, 'categorySeq':BoardUtil.categorySeq
		}
		return data;
	}
	, formReset:function(obj) {
		var formName = $(obj).parents('form').attr('name');
		$($('form[name='+formName+']')).find('input[name=nickname]').val('');
		$($('form[name='+formName+']')).find('textarea[name=content]').val('');
		$($('form[name='+formName+']')).find('input[name=password]').val('');
		BoardCommentUtil.parentSeq = null;
	}
	, submit:function(callback, type, obj) {
		if(BoardCommentSubmitUtil.validation(obj)) {
			callback(type, obj);
		}
	}
	, proc:function(type, obj) {
		$.ajax({
			url:"/board/comment/"+type+"/proc",
			type:"post",
			data:BoardCommentSubmitUtil.mappingVo(obj),
			dataType:"text",
			success:function(data) {
				if(data === 'success') {
					if(type === 'insert') {
						alert('등록되었습니다');
					} else {
						alert('수정되었습니다');
					}
				} else {
					alert(data);
				}
				BoardCommentRenderUtil.renderList();
				BoardCommentSubmitUtil.formReset(obj);
			},
			error:function(error) {
				console.log( error.status + ":" +error.statusText );
			}
		});
	}
}

var BoardCommentDeleteUtil = {
	deleteBtnHandler:function() {
		$('#authSubmitBtn').on('click', function() {
			BoardCommentDeleteUtil.submit(BoardCommentDeleteUtil.proc);
		});
	}
	, auth:function(type, seq) {
		BoardCommentUtil.seq = seq;
		if(type === 'nonMember') {
			$('#authModal').modal();
		} else {
			BoardCommentDeleteUtil.proc(seq);
		}
	}
	, validation:function() {
		var flag = true;
		$('#authModal').find("input[alt], textarea[alt], select[alt]").each( function() {
			if(flag && $(this).val() == "" || flag&& $(this).val() == 0) {

				alert($(this).attr("alt") + "란을 채워주세요.");
				flag = false;
				$(this).focus();
			}
		});
		return flag;
	}
	, formReset:function() {
		$('#commentPassword').val('');
	}
	, submit:function(callback) {
		if(BoardCommentDeleteUtil.validation()) {
			callback(BoardCommentUtil.seq, $('#commentPassword').val());
		}
	}
	, proc:function(seq, password) {
		if(confirm('정말 삭제하시겠습니까?')) {
			$.ajax({
				url:"/board/comment/delete/proc",
				type:"post",
				data:{'seq':seq, 'password':password},
				dataType:"text",
				success:function(data) {
					if(data === 'success') {
						alert('삭제되었습니다.');
						BaordRenderUtil.render();
						BoardCommentRenderUtil.renderList();
					} else {
						alert(data);
					}
					$('#authModal').modal('hide');
					BoardCommentDeleteUtil.formReset();
				},
				error:function(error) {
					console.log( error.status + ":" +error.statusText );
				}
			});
		}
	}
};

var SeoUtil = {
	render:function(title, description, typeCode) {
		var typeText = '';
		if(typeCode === 'category') {
			typeText = ' 카테고리';
		}
		if(title === null) {
			$('#headTitle').text('찬퐁의 개발 블로그');
			$('#headDescription, #ogTitle, #ogDescription').attr('content', '찬퐁의 개발 블로그');
		} else {
			$('#headTitle').text('"' + title + '" ' + typeText);
			$('#ogTitle').attr('content', title + typeText);
			$('#headDescription, #ogDescription').attr('content', description);
		}
		$('#ogUrl').attr('content', location.href);
	}
}

var goPage = function (page) {
	BaordRenderUtil.renderList(page, (function () {
		BaordRenderUtil.renderPaging(page, (function () {
			BaordRenderUtil.renderCategory();
		})());
	})());
};