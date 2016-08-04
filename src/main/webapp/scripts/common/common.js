$(document).ready(function() {
	$(".navigation-menu-btn").click(function(e) {
		e.preventDefault();

		$('#wrapper').addClass("toggled");
		$('.main-content-warpper').addClass('pointer').addClass('back-drop');
	});

	$(".side-menu-close-btn").click(function(e) {
		e.preventDefault();

		$('#wrapper').removeClass("toggled");
		$('.main-content-warpper').removeClass('pointer').removeClass('back-drop');
	});
});