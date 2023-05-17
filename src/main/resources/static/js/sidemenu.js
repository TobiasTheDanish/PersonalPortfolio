let target = document.getElementById('toggle-target');

let inMobileSize = false;
const minWidth = 975;

window.addEventListener("resize", () => toggleHamMenuVisibility());
window.addEventListener("load", () => toggleHamMenuVisibility());

function toggleHamMenu(menu) {
    $(menu).toggleClass("open");
    $('#toggle-target').toggleClass("hide");
}

function toggleHamMenuVisibility() {
    if (window.innerWidth < minWidth && !inMobileSize) {
        inMobileSize = true;
        $('#toggle-target').addClass("hide");
        $(".sidemenu-item-icon").addClass("hide");
        $('#ham-menu').removeClass("open");

    } else if (window.innerWidth >= minWidth && inMobileSize) {
        inMobileSize = false;
        $('#toggle-target').removeClass("hide");
        $(".sidemenu-item-icon").removeClass("hide");
    }
}