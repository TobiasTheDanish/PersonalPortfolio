let scrolled = false;
window.addEventListener("scroll", function() {
    if ($(window).scrollTop() > 50 && !scrolled) {
        scrolled = true;
        $('#header').addClass("sticky");
        // $('.header-text').toggle();
    } else if ($(window).scrollTop() <= 50 && scrolled) {
        scrolled = false;
        $('#header').removeClass("sticky");
        // $('.header-text').toggle("fast");
    }
})