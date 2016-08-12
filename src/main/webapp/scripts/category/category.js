var CategoryUtil = {
	select: function (obj) {
//			var seq = parseInt($(obj).parents("tr").attr("data-seq"), 10) || 0;
//			var depth = parseInt($(obj).parents("tr").attr("data-depth"), 10) || 0;
//			EBCategory.renderList(depth + 1, seq);

		$(obj).parents("tbody").find(".tr-current").removeClass("tr-current");
		$(obj).addClass("tr-current");
	}
}

var CategorySubmitUtil = {
	validation:function() {
		var flag = true;
		$('.area-right').find("input[alt], textarea[alt], select[alt]").each( function() {
			if(flag && $(this).val() == "" || flag&& $(this).val() == 0) {

				alert($(this).attr("alt") + "란을 채워주세요.");
				flag = false;
				$(this).focus();
			}
		});
		return flag;
	}
	, mappingVo:function() {
		var data;
		data = {
			'title':$('#title').val()
			, 'description':$('#description').val()
		}
		return data;
	}
	, formReset:function() {
		$('#title').val('');
		$('#description').val('');
	}
	, submit:function(callback) {
		if(CategorySubmitUtil.validation()) {
			callback();
		}
	}
	, submitProc:function() {
		$.ajax({
			url:"/category/insert/proc",
			type:"post",
			data:CategorySubmitUtil.mappingVo(),
			dataType:"text",
			success:function(data) {
				if(data === 'success') {
					alert('등록되었습니다.');
				} else {
					alert('실패하였습니다.');
				}
				CategorySubmitUtil.formReset();
			},
			error:function(error) {
				console.log( error.status + ":" +error.statusText );
			}
		});
	}
}

$(document).ready(function() {
	$('#tbodyList').tableDnD({onDragClass:"drag"});

	$('#categoryInsertBtn').on('click', function() {
		CategorySubmitUtil.submit(CategorySubmitUtil.submitProc);
	});
});