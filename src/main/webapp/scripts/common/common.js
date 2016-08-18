var SideUtil = {
	loginSeq:0
	, loginId:''
	, show:function() {
		$('body').removeClass('menu-close').addClass('menu-open');
		$('.scrollable-wrapper').css({'overflow':'hidden'}).removeClass('wrapper-left-clear').addClass('wrapper-left-move');
	}
	, hide:function() {
		if($('body').hasClass('menu-open')){
			$('body').removeClass('menu-open').addClass('menu-close');
			$('#SideBody').one('webkitAnimationEnd oanimationend oAnimationEnd msAnimationEnd animationend', function(e) {
				//ie, 크롬은 사이드 애니메이션 버그가 존재해서 setTimeout을 제한다.
				var agent = navigator.userAgent.toLowerCase();
				//밑의 $('body').removeClass('menu-open menu-close'); 코드때문에 ie, 크롬은 사이드메뉴 버그가 발생하니 ie는 무시한다.(ie11이하는 지원하지 않는다.)
				if (agent.search("trident") === -1 && agent.search("edge/") === -1 && agent.search("chrome") === -1) {
					setTimeout( function(){
						$('body').removeClass('menu-open menu-close');
					}, 50);
				}

			});
			$('.scrollable-wrapper').removeClass('wrapper-left-move').addClass('wrapper-left-clear').css({'overflow':'auto'});
		}
	}
}

var SideCategoryUtil = {
	renderList:function() {
		$.ajax({
			url:"/category/list",
			type:"get",
			data:{},
			dataType:"text",
			success:function(data) {
				var list = $.parseJSON(data);
				if(list.length > 0) {
					$("#ulList").html($("#liTemplate").tmpl(list));
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
}

$('.scrollable-wrapper').scroll(function(){
	var height = $('.scrollable-wrapper').scrollTop();
	height > 0 ? $('#TopButton').show() : $('#TopButton').hide();
});

var LoginSubmitUtil = {
	loginBtnHandler:function() {
		//view modal
		$('#loginModalBtn').on('click', function() {
			$('#loginModal').modal();
		});

		//logout
		$('#logoutBtn').on('click', function() {
			if(confirm('로그아웃 하시겠습니까?')) {
				LoginSubmitUtil.logoutProc();
			}
		});

		//submit
		$('#loginSubmitBtn').on('click', function() {
			LoginSubmitUtil.submit(LoginSubmitUtil.loginProc);
		});
	}
	, validation:function() {
		var flag = true;
		$('#loginModal').find("input[alt], textarea[alt], select[alt]").each( function() {
			if(flag && $(this).val() == "" || flag&& $(this).val() == 0) {

				alert($(this).attr("alt") + "란을 채워주세요.");
				flag = false;
				$(this).focus();
			}
		});
		return flag;
	}
	, mappingVo:function() {
		var data = {
			'id':$('#loginId').val()
			, 'password':$('#loginPassword').val()
		}
		return data;
	}
	, formReset:function() {
		$('#loginId').val('');
		$('#loginPassword').val('');
	}
	, submit:function(callback) {
		if(LoginSubmitUtil.validation()) {
			callback();
		}
	}
	, loginProc:function() {
		$.ajax({
			url:"/login/proc",
			type:"post",
			data:LoginSubmitUtil.mappingVo(),
			dataType:"text",
			success:function(data) {
				if(data === 'success') {
					location.reload();
				} else {
					alert(data);
				}
				LoginSubmitUtil.formReset();
			},
			error:function(error) {
				console.log( error.status + ":" +error.statusText );
			}
		});
	}
	, logoutProc:function() {
		$.ajax({
			url:"/login/logout/proc",
			type:"post",
			data:{},
			dataType:"text",
			success:function(data) {
				if(data === 'success') {
					location.href = '/';
				} else {
					alert('로그아웃이 실패하였습니다.');
				}
			},
			error:function(error) {
				console.log( error.status + ":" +error.statusText );
			}
		});
	}
}

$(document).ready(function() {
	LoginSubmitUtil.loginBtnHandler();
});