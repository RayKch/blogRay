var Browser = {
	agent:navigator.userAgent.toLowerCase()
};

Browser = {
	ie:Browser.agent.search("trident") === -1
	, edge:Browser.agent.search("edge/") === -1
	, chrome:Browser.agent.search("chrome") === -1
	, firefox:Browser.agent.search("firefox") === -1
};

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
//				밑의 $('body').removeClass('menu-open menu-close'); 코드때문에 ie, 크롬, firefox는 사이드메뉴 버그가 발생하니 ie, 크롬, firefox은 무시한다.(ie11이하는 지원하지 않는다.)
				if (Browser.ie && Browser.edge && Browser.chrome && Browser.firefox) {
					setTimeout( function(){
						$('body').removeClass('menu-open menu-close');
					}, 50);
				}

			});
			$('.scrollable-wrapper').removeClass('wrapper-left-move').addClass('wrapper-left-clear').css({'overflow':'auto'});
		}
	}
};

var StatsUtil = {
	render:function() {
		$.ajax({
			url:"/stats/data/json",
			type:"get",
			data:{},
			dataType:"text",
			success:function(data) {
				var vo = $.parseJSON(data);
				$('#todayCnt').text(vo.todayCnt);
				$('#allDayCnt').text(vo.allDayCnt);
			},
			error:function(error) {
				console.log( error.status + ":" +error.statusText );
			}
		});
	}
};

var SideCategoryUtil = {
	renderList:function() {
		$.ajax({
			url:"/category/title/list/json",
			type:"get",
			data:{},
			dataType:"text",
			success:function(data) {
				var list = $.parseJSON(data);
				if(list.length > 0) {
					$("#ulList").html($("#liTemplate").tmpl({list:list, subList:list}));
				} else {
					$("#ulList").html('<li style="padding:40% 0 0 45px; color:#fff">카테고리가<br/>없습니다.</li>');
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
};

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

		//아이디저장 체크되어있으면 쿠키저장
		if($("#rememberId").prop("checked")) {
			$.cookie('loginId', $("#loginId").val(), {expires: 365 * 10});
			//아이디저장 미체크면 쿠키에 정보가 있던간에 삭제
		} else {
			$.removeCookie("loginId");
		}
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
					$('#loginPassword').val('');
				}
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
};

$(document).ready(function() {
	LoginSubmitUtil.loginBtnHandler();
});