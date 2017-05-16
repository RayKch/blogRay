var CategoryUtil = {
	seq:0
	, groupSeq:0
	, select:function(obj, actionType) {
		var currentSeq = $(obj).parents('tr').data('seq');
		var seq = (actionType === 'Group') ? CategoryUtil.groupSeq : CategoryUtil.seq;

		if(currentSeq === seq) {
			//0보다 크다면 현재 선택된 td가 존재 아니라면 존재하지 않기때문에 선택
			if($(obj).parents('tbody').find('.tr-current').length > 0) {
				$(obj).parents('tbody').find('.tr-current').removeClass('tr-current');
				CategorySubmitUtil.formReset();
			} else {
				$(obj).addClass('tr-current');
				CategoryRenderUtil.render(currentSeq, actionType);
			}
		} else {
			$(obj).parents('tbody').find('.tr-current').removeClass('tr-current');
			$(obj).addClass('tr-current');

			//업데이트폼 랜더링
			CategoryRenderUtil.render(currentSeq, actionType);
		}

		/** 한개의 article이 select 되기전에 클릭되었던 모든 article을 초기화 */
		if($(obj).parents('tbody').data('action-type') === 'Group') {
			$('#tbodyCategoryList').find('.tr-current').removeClass('tr-current');
		} else {
			$('#tbodyGroupList').find('.tr-current').removeClass('tr-current');
		}
	}
	, saveOrderNo: function(id) {
		var current = 1;
		var length = $(id+">tr[data-seq]").length;
		if(length === 0) {
			alert("카테고리가 하나도 없습니다");
			return;
		}

		$("#orderNoProgressModal").modal().find(".progress-bar").width(0);
		var t = setTimeout(function(){
			$("#orderNoProgressModal").modal("hide");
		},10000);

		$(id+">tr[data-seq]").each(function(idx){
			$.ajax({
				url:"/category/update/order/proc",
				type:"get",
				data:{seq:$(this).attr("data-seq"), orderNo:idx},
				dataType:"text",
				success:function(data) {
					$("#orderNoProgressModal").find(".progress-bar").width((current++/length*100)+"%");

					if(current === length) {
						setTimeout(function(){
							$("#orderNoProgressModal").modal("hide").find(".progress-bar").width(0);
							alert('변경되었습니다');
						}, 1000);
						clearTimeout(t);
					}
				},
				error:function(error) {
					console.log( error.status + ":" +error.statusText );
				}
			});
		});

		SideCategoryUtil.renderList();
		CategoryRenderUtil.renderList(CategoryRenderUtil.pageNum, 'Category', 'Y');
		CategoryRenderUtil.renderList(CategoryRenderUtil.pageNum, 'Group', 'Y');
	}
}

var CategoryRenderUtil = {
	list:[]
	, groupList:[]
	, pageNum:0
	, groupPageNum:0
	, render:function(seq, actionType) {
		var list = [];
		if(actionType === 'Group') {
			list = CategoryRenderUtil.groupList;
			CategoryUtil.groupSeq = seq;
		} else {
			list = CategoryRenderUtil.list;
			CategoryUtil.seq = seq;
		}

		for(var vo in list) {
			if(list[vo].seq === parseInt(seq, 10)) {
				for(var name in list[vo]) {
					$('#mainForm').find("input[data-name="+name+"], select[data-name="+name+"]").val(list[vo][name]);
					if(name === 'typeCode') {
						$('#typeCode' + list[vo][name]).prop('checked', true);
					}

					if(name === 'description') {
						$('#description').val(list[vo][name]);
					}
				}
			}
		}
	}
	, renderSelect:function(seq, actionType) {
		var id = '';
		if(actionType === 'Group') {
			id = 'tbodyGroupList';
		} else {
			id = 'tbodyList';
		}
		$('#' + id + ' tr').each(function() {
			var currentSeq = $(this).data('seq');
			if(currentSeq === seq) {
				$(this).find('td').hasClass('select').addClass('tr-current');
			}
		});
	}
	, renderList:function(pageNum, actionType, sortYn) {
		$.ajax({
			url:"/category/list/json",
			type:"get",
			data:{'pageNum': pageNum, 'actionType': actionType},
			dataType:"text",
			success:function(data) {
				var list = $.parseJSON(data);

				var guideText = '';
				if(actionType === 'Group') {
					guideText = '그룹이';
					CategoryRenderUtil.groupList = list;
				} else if(actionType === 'Category') {
					guideText = '카테고리가';
					CategoryRenderUtil.list = list;
				}

				if(pageNum > 0 && list.length === 0) {
					alert('더이상 ' + guideText + ' 존재하지 않습니다');
					return;
				}

				/** 순서변경을 한뒤 카테고리 리스트를 호출하면 기존 게시글에 더 추가가 되기때문에
				 * 순서 변경이 아닐경우에만 게시글을 추가한다 **/
				if(sortYn !== 'Y') {
					if(list.length > 0) {
						if(pageNum === 0) {
							$("#tbody" + actionType + "List").html($("#tbody" + actionType + "Template").tmpl(list));
						} else {
							$("#tbody" + actionType + "List").append($("#tbody" + actionType + "Template").tmpl(list));
						}

						$("#tbody" + actionType + "List").tableDnD({onDragClass:"drag"});

						/* 더보기를 클릭했을때 다음페이지의 데이터를 불리오기위한 페이지 카운트 */
						if(actionType === 'Group') {
							CategoryRenderUtil.groupPageNum = pageNum;
						} else if(actionType === 'Category') {
							CategoryRenderUtil.pageNum = pageNum;
						}

					} else {
						$("#tbody" + actionType + "List").html('<tr><td class="text-center" colspan="2">' + guideText + ' 존재하지 않습니다.</td></tr>');
					}
				}
			},
			error:function(error) {
				console.log( error.status + ":" +error.statusText );
			}
		});
	}
	, renderAddArticle:function(actionType) {
		/* 더보기를 클릭했을때 다음페이지의 데이터를 불리오기 위해 1을 더한다 */
		var pageNum = 0;
		if(actionType === 'Group') {
			pageNum = CategoryRenderUtil.groupPageNum;
		} else {
			pageNum = CategoryRenderUtil.pageNum;
		}
		CategoryRenderUtil.renderList(pageNum + 1, actionType, 'N');
	}
};

var CategorySubmitUtil = {
	validation:function() {
		var flag = true;
		var validationTag = '.area-right';

		if(flag && $(validationTag).find('input:radio[name=typeCode]:checked').length === 0) {
			alert("카테고리 유형을 선택해주세요.");
			flag = false;
		}
		$(validationTag).find("input[alt], textarea[alt], select[alt]").each( function() {
			if(flag && $(this).val() == "" || flag&& $(this).val() == 0) {

				alert($(this).attr("alt") + "란을 채워주세요.");
				flag = false;
				$(this).focus();
			}
		});
		return flag;
	}
	, mappingVo:function(type, seq) {
		var typeCode = $('.area-right').find('input:radio[name=typeCode]:checked').val();
		var actionType = '';
		/** typeCode 값이 G면 그룹형이므로 group_seq는 0을 적용한다 */

		if(typeCode === 'G') {
			CategoryUtil.groupSeq = 0;
			actionType = 'Group';
		} else {
			actionType = 'Category';
		}

		var data = {
			'title':$('#title').val()
			, 'description':$('#description').val()
			, 'typeCode':typeCode
			, 'seq':seq
			, 'groupSeq':CategoryUtil.groupSeq
			, 'actionType':actionType
		}
		return data;
	}
	, formReset:function() {
		$('#title').val('');
		$('#description').val('');
		$('.area-right').find('input:radio[name=typeCode]').prop('checked', false);
		CategoryUtil.seq = 0;
	}
	, submit:function(callback, type, seq) {
		if(CategorySubmitUtil.validation()) {
			callback(type, seq);
		}
	}
	, proc:function(type, seq) {
		$.ajax({
			url:"/category/"+type+"/proc",
			type:"post",
			data:CategorySubmitUtil.mappingVo(type, seq),
			dataType:"text",
			success:function(data) {
				if(data === 'success') {
					if(type === 'insert') {
						alert('등록되었습니다');
						$('#typeCodeL, #typeCodeC').prop('checked', false);
					} else {
						alert('수정되었습니다');
						// 수정시 선택되었던 카테고리 자동 select
						CategoryRenderUtil.renderSelect(seq, CategorySubmitUtil.mappingVo.actionType);
					}
				} else {
					alert('실패하였습니다.');
				}

				// 좌측 카테고리 리스트 갱신
				CategoryRenderUtil.renderList(CategoryRenderUtil.pageNum, 'Category', 'N');
				CategoryRenderUtil.renderList(CategoryRenderUtil.pageNum, 'Group', 'N');
				SideCategoryUtil.renderList();
				CategorySubmitUtil.formReset();
			},
			error:function(error) {
				console.log( error.status + ":" +error.statusText );
			}
		});
	}
}

var CategoryDeleteUtil = {
	proc:function(seq) {
		if(confirm('카테고리 삭제시 해당 카테고리에 등록된 게시물도 모두 삭제됩니다!!\n정말 삭제하시겠습니까?')) {
			$.ajax({
				url:"/category/delete/proc",
				type:"post",
				data:{'seq':seq},
				dataType:"text",
				success:function(data) {
					if(data === 'success') {
						alert('삭제되었습니다.');
					} else {
						alert('실패하였습니다.');
					}

					CategoryRenderUtil.renderList(CategoryRenderUtil.pageNum, 'Category', 'N');
					CategoryRenderUtil.renderList(CategoryRenderUtil.pageNum, 'Group', 'N');
					SideCategoryUtil.renderList();
				},
				error:function(error) {
					console.log( error.status + ":" +error.statusText );
				}
			});
		}
	}
}

$(document).ready(function() {
	CategoryRenderUtil.renderList(0, 'Category', 'N');
	CategoryRenderUtil.renderList(0, 'Group', 'N');

	$('#saveBtn').on('click', function() {
		var saveType = 'insert';
		var saveSeq = 0;
		if(CategoryUtil.seq > 0) {
			saveType = 'update';
			saveSeq = CategoryUtil.seq;
		}

		CategorySubmitUtil.submit(CategorySubmitUtil.proc, saveType, saveSeq);
	});

	$('#formResetBtn').on('click', function() {
		$('.area-left').find('.tr-current').removeClass('tr-current');
		CategorySubmitUtil.formReset();
	});
});