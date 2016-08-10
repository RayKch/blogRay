var SideUtil = {
	show:function() {
		$('body').removeClass('menu-close').addClass('menu-open');
		$('.scrollable-wrapper').css({'overflow':'hidden'}).removeClass('wrapper-left-clear').addClass('wrapper-left-move');
	}
	, hide:function() {
		if($('body').hasClass('menu-open')){
			$('body').removeClass('menu-open').addClass('menu-close');
			$('#SideBody').one('webkitAnimationEnd oanimationend oAnimationEnd msAnimationEnd animationend', function(e) {
				setTimeout( function(){
					$('body').removeClass('menu-open menu-close');
				}, 50);
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