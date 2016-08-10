var SideUtil = {
	show:function() {
		$('body').removeClass('menu-close').addClass('menu-open');
		$('.scrollable-wrapper').css({'overflow':'hidden'}).removeClass('wrapper-left-clear').addClass('wrapper-left-move');
	}
	, hide:function() {
		if($('body').hasClass('menu-open')){
			$('body').removeClass('menu-open').addClass('menu-close');
			$('#SideBody').one('webkitAnimationEnd oanimationend oAnimationEnd msAnimationEnd animationend', function(e) {
				//ie는 사이드 애니메이션 버그가 존재해서 setTimeout을 제한다.
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

$('.scrollable-wrapper').scroll(function(){
	var height = $('.scrollable-wrapper').scrollTop();
	height > 0 ? $('#TopButton').show() : $('#TopButton').hide();
});

$(document).ready(function() {

});