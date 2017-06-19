var CategoryUtil = {
	seq:0
	, groupSeq:0
	, submitType:''
	, select:function(obj, actionType) {
		CategoryUtil.typeCodeControl(actionType);

		var currentSeq = $(obj).parents('tr').data('seq');
		var seq = (actionType === 'Group') ? CategoryUtil.groupSeq : CategoryUtil.seq;

		if(currentSeq === seq) {
			//0보다 크다면 현재 선택된 td가 존재, 아니라면 존재하지 않기때문에 선택
			if($(obj).parents('tbody').find('.tr-current').length > 0) {
				$(obj).parents('tbody').find('.tr-current').removeClass('tr-current');
				CategorySubmitUtil.formReset();
				CategoryUtil.categoryTrInit();
			} else {
				$(obj).addClass('tr-current');
				CategoryRenderUtil.render(currentSeq, actionType);
				CategoryRenderUtil.renderChild(obj, currentSeq);
			}
		} else {
			$(obj).parents('tbody').find('.tr-current').removeClass('tr-current');
			$(obj).addClass('tr-current');

			//업데이트폼 랜더링
			CategoryRenderUtil.render(currentSeq, actionType);
			CategoryRenderUtil.renderChild(obj, currentSeq);
		}
	}
	, saveOrderNo: function(id) {
		var current = 1;
		var length = $(id+">tr[data-seq]").length;
		if(length === 0) {
			alert("카테고리가 존재하지 않습니다");
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
		CategoryRenderUtil.renderList(CategoryRenderUtil.pageNum, 'Group', 'Y');
	}
	, typeCodeControl:function(actionType) {
		var groupTag = 'label[for=typeCodeG]';
		var categoryTag = 'label[for=typeCodeL], label[for=typeCodeC]';

		if(actionType === 'Group') {
			$(groupTag).show();
			$(categoryTag).hide();
		} else {
			$(categoryTag).show();
			$(groupTag).hide();
		}
	}
	, modalShow:function(seq, actionType) {
		CategoryUtil.typeCodeControl(actionType);
		CategorySubmitUtil.formReset();
		CategoryUtil.submitType = actionType;

		var list = [];
		if(actionType === 'Group') {
			list = CategoryRenderUtil.groupList;
			CategoryUtil.groupSeq = seq;
			$('#modalTitle').text('그룹');
		} else {
			list = CategoryRenderUtil.list;
			CategoryUtil.seq = seq;
			$('#modalTitle').text('카테고리');
		}

		for(var vo in list) {
			if(list[vo].seq === parseInt(seq, 10)) {
				for(var name in list[vo]) {
					$('#submitModal').find("input[data-name="+name+"], select[data-name="+name+"]").val(list[vo][name]);
					if(name === 'typeCode') {
						$('#typeCode' + list[vo][name]).prop('checked', true);
					}
				}
			}
		}
		$('#submitModal').modal();
	}
	, categoryInit:function(actionType) {
		if(actionType === 'Category') {
			if($('#tbodyGroupList').find('.tr-current').length === 0) {
				alert("카테고리 등록시 그룹리스트에서 그룹을 먼저 선택해주세요.");
				return;
			}
			CategoryUtil.groupSeq = $('#tbodyGroupList').find('.tr-current').parents('tr').data('seq');
			$("#typeCodeL:radio[value='L']").prop("checked", true) ;
		} else {
			$("#typeCodeG:radio[value='G']").prop("checked", true) ;
		}
		CategoryUtil.modalShow(0, actionType);
	}
	, categoryTrInit:function() {
		$("#tbodyCategoryList").html('<tr><td class="text-center" colspan="2">그룹을 선택해 주세요.</td></tr>');
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
	, renderList:function(pageNum, actionType, sortYn, groupSeq, callback) {
		$.ajax({
			url:"/category/list/json",
			type:"get",
			data:{'pageNum': pageNum, 'actionType': actionType, 'groupSeq': groupSeq},
			dataType:"text",
			success:function(data) {
				var list = $.parseJSON(data);

				/** concat() : 현재 배열끝에 데이터를 추가하는 메서드 */
				var guideText = '';
				if(actionType === 'Group') {
					guideText = '그룹이';

					if(pageNum === 0) {
						CategoryRenderUtil.groupList = list;
					} else {
						CategoryRenderUtil.groupList = CategoryRenderUtil.groupList.concat(list);
					}
				} else if(actionType === 'Category') {
					guideText = '카테고리가';

					if(pageNum === 0) {
						CategoryRenderUtil.list = list;
					} else {
						CategoryRenderUtil.list = CategoryRenderUtil.list.concat(list);
					}
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

				if(typeof callback === 'function') {
					callback(actionType);
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
		CategoryRenderUtil.renderList(pageNum + 1, actionType, 'N', CategoryUtil.groupSeq);
	}
	, initRenderList:function(actionType) {
		/** 그룹에 속한 카테고리 리스트 갱신 */
		$('#tbodyGroupList').find('tr').each(function() {
			if($(this).data('seq') === CategoryUtil.groupSeq) {
				$('#tbodyCategoryList').find('tr').remove().html('<tr><td class="text-center" colspan="2"><img src="/image/common/ajaxloader.gif"/></td></tr>');

				/** 카테고리 리스트 갱신 */
				for(var i=0; i<=CategoryRenderUtil.pageNum; i++) {
					CategoryRenderUtil.renderList(i, 'Category', 'N', $(this).data('seq'));
				}

				/** 카테고리 등록후 카테고리가 속한 그룹 자동 select */
				if(actionType === 'Group') {
					$("#tbodyGroupList").find("td[data-seq="+$(this).data('seq')+"]").addClass('tr-current');
				}
			}
		});
	}
	, renderChild:function(obj, currentSeq) {
		/** 한개의 article이 select 되기전에 클릭되었던 모든 article을 초기화 */
		if($(obj).parents('tbody').data('action-type') === 'Group') {
			$('#tbodyCategoryList').find('.tr-current').removeClass('tr-current');

			/** 그룹일 경우에만 카테고리 호출 */
			CategoryRenderUtil.renderList(0, 'Category', 'N', currentSeq);
		} else {
			$('#tbodyGroupList').find('.tr-current').removeClass('tr-current');
		}
	}
};

var CategorySubmitUtil = {
	validation:function(type) {
		var flag = true;
		if(flag && $('input:radio[name=typeCode]:checked').length === 0) {
			alert("유형을 선택해주세요.");
			flag = false;
		} else if(flag && type === 'insert' && $('input:radio[name=typeCode]:checked').val() !== 'G' && $('#tbodyGroupList').find('.tr-current').length === 0) {
			alert("카테고리 등록시 그룹을 먼저 선택해주세요.");
			flag = false;
		}

		$('#submitModal').find("input[alt], textarea[alt], select[alt]").each( function() {
			if(flag && $(this).val() == "" || flag&& $(this).val() == 0) {

				alert($(this).attr("alt") + "란을 채워주세요.");
				flag = false;
				$(this).focus();
			}
		});
		return flag;
	}
	, mappingVo:function(type, seq) {
		var typeCode = $('input:radio[name=typeCode]:checked').val();
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
	}
	, submit:function(callback, type, seq) {
		if(CategorySubmitUtil.validation(type)) {
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
					} else {
						alert('수정되었습니다');
					}
				} else {
					alert('실패하였습니다.');
				}

				/** 카테고리 초기 세팅 */
				CategoryUtil.categoryTrInit();

				/** 이곳에 그룹일경우 그룹 리스트호출, 카테고리 일경우 카테고리 리스트 호출 후 현재 tr-current를 유지시키도록 한다 */
				/** 카테고리를 등록했을경우 그룹리스트를 유지시킨다 */
				CategoryRenderUtil.renderList(0, 'Group', 'N', '', CategoryRenderUtil.initRenderList);

				/** 사이드 메뉴 갱신 */
				SideCategoryUtil.renderList();

				if(type === 'insert') {
					CategorySubmitUtil.formReset();
				}
				$('#submitModal').modal('hide');
			},
			error:function(error) {
				console.log( error.status + ":" +error.statusText );
			}
		});
	}
}

var CategoryDeleteUtil = {
	proc:function(seq, actionType) {
		if(confirm('카테고리 삭제시 해당 카테고리에 등록된 게시물도 모두 삭제됩니다!!\n정말 삭제하시겠습니까?')) {
			$.ajax({
				url:"/category/delete/proc",
				type:"post",
				data:{'seq':seq, 'actionType':actionType},
				dataType:"text",
				success:function(data) {
					if(data === 'success') {
						alert('삭제되었습니다.');
					} else {
						alert('실패하였습니다.');
					}

					/** 이곳에 그룹일경우 그룹 리스트호출, 카테고리 일경우 카테고리 리스트 호출 후 현재 tr-current를 유지시키도록 한다 */
					/** 카테고리를 등록했을경우 그룹리스트를 유지시킨다 */
					/** 그룹 리스트 갱신 */
					CategoryRenderUtil.renderList(0, 'Group', 'N', '', CategoryRenderUtil.initRenderList);

					/** 사이드 메뉴 갱신 */
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
	CategoryRenderUtil.renderList(0, 'Group', 'N');
	/** 페이지 진입시 카테고리 문구 노출 */
	CategoryUtil.categoryTrInit();

	$('#saveBtn').on('click', function() {
		var saveType = 'insert';
		var saveSeq = 0;
		if(CategoryUtil.submitType === 'Group') {
			saveSeq = CategoryUtil.groupSeq;
		} else {
			saveSeq = CategoryUtil.seq;
		}

		if(saveSeq > 0) {
			saveType = 'update';
		}

		CategorySubmitUtil.submit(CategorySubmitUtil.proc, saveType, saveSeq);
	});

	$('#saveBtn').on('click', function() {
		CategorySubmitUtil.formReset();
	});
});