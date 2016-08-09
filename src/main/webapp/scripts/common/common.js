var SideUtil = {
	show:function() {
		$('body').removeClass('menu-close').addClass('menu-open');
		$('.scrollable-wrapper').css({'overflow':'hidden'});
	}
	, hide:function() {
		if($('body').hasClass('menu-open')){
			$('body').removeClass('menu-open').addClass('menu-close');
			$('#SideBody').one('webkitAnimationEnd oanimationend oAnimationEnd msAnimationEnd animationend', function(e) {
				setTimeout( function(){
					$('body').removeClass('menu-open menu-close');
				}, 50);
			});
			$('.scrollable-wrapper').css({'overflow':'auto'});
		}
	}
}

$('.scrollable-wrapper').scroll(function(){
	var height = $('.scrollable-wrapper').scrollTop();
	height > 0 ? $('#TopButton').show() : $('#TopButton').hide();
});

$(document).ready(function() {

});