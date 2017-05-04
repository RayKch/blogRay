var CategoryUtil = {
	seq:0
	, list:[]
	, select:function(obj) {
		var currentSeq = $(obj).parents('tr').data('seq');

		if(currentSeq === CategoryUtil.seq) {
			//0보다 크다면 현재 선택된 td가 존재 아니라면 존재하지 않기때문에 선택
			if($(obj).parents('tbody').find('.tr-current').length > 0) {
				$(obj).parents('tbody').find('.tr-current').removeClass('tr-current');
				CategorySubmitUtil.formReset();
			} else {
				$(obj).addClass('tr-current');
				CategorySubmitUtil.updateRender(currentSeq);
			}
		} else {
			$(obj).parents('tbody').find('.tr-current').removeClass('tr-current');
			$(obj).addClass('tr-current');

			//업데이트폼 랜더링
			CategorySubmitUtil.updateRender(currentSeq);
		}
	}
	, renderList:function() {
		$.ajax({
			url:"/category/list/json",
			type:"get",
			data:{},
			dataType:"text",
			success:function(data) {
				var list = $.parseJSON(data);
				CategoryUtil.list = list;
				if(list.length > 0) {
					$("#tbodyList").html($("#tbodyTemplate").tmpl(list));
					$('#tbodyList').tableDnD({onDragClass:"drag"});
				} else {
					$("#tbodyList").html('<tr><td class="text-center" colspan="2">카테고리가 존재하지 않습니다.</td></tr>');
				}
			},
			error:function(error) {
				console.log( error.status + ":" +error.statusText );
			}
		});
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
						}, 1000);
						clearTimeout(t);
					}
					SideCategoryUtil.renderList();
					CategoryUtil.renderList();
				},
				error:function(error) {
					console.log( error.status + ":" +error.statusText );
				}
			});
		});
	}
}

var CategorySubmitUtil = {
	updateRender:function(seq) {
		for(var vo in CategoryUtil.list) {
			if(CategoryUtil.list[vo].seq === parseInt(seq, 10)) {
				for(var name in CategoryUtil.list[vo]) {
					$('#mainForm').find("input[data-name="+name+"], select[data-name="+name+"]").val(CategoryUtil.list[vo][name]);
					if(name === 'typeCode') {
						$('#typeCode' + CategoryUtil.list[vo][name]).prop('checked', true);
					}

					if(name === 'description') {
						$('#description').val(CategoryUtil.list[vo][name]);
					}
				}
			}
		}

		CategoryUtil.seq = seq;
	}
	, validation:function() {
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
		var data = {
			'title':$('#title').val()
			, 'description':$('#description').val()
			, 'typeCode':$('.area-right').find('input:radio[name=typeCode]:checked').val()
			, 'seq':seq
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
					}
				} else {
					alert('실패하였습니다.');
				}
				CategoryUtil.renderList();
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
					CategoryUtil.renderList();
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
	CategoryUtil.renderList();

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