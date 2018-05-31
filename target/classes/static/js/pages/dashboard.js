
(function ($) {
    "use strict";

    $(".counter").counterUp({
        delay: 100,
        time: 1000
    });
    
    [].slice.call(document.querySelectorAll('.sttabs')).forEach(function(el) {
        new CBPFWTabs(el);
    });
	
})(jQuery);