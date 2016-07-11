$.fn.Ipt_defult_word = function(options) {
	var defults = {
		cls : "f_mor",
		word : "none"
	};
	var opts = $.extend({}, defults, options);
	var ths = $(this);
	if (ths.length > 0) {
		if (ths.val() == "" || ths.val().length <= 0 || ths.val() == opts.word) {
			ths.addClass(opts.cls);
			ths.val(opts.word);
		}

		ths.data('defaultWord', opts.word);

		ths.on("focus", function() {

			if (ths.val() == opts.word) {
				ths.val("");
				ths.removeClass(opts.cls);
			}
		});
		ths.on("blur", function() {
			if (ths.val() == "" || ths.val().length <= 0
					|| ths.val() == opts.word) {
				ths.addClass(opts.cls);
				ths.val(opts.word);
			}
		});
	}

};

function click2CloseTab() {
	$(".tab_zz").addClass("hide");
	$(".tab").addClass("hide");
};

$(function() {
	$(".t_close").click(function() {
		click2CloseTab();
	});
});