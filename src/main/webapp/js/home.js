var slideIndex = 0;

var autoSlide = setInterval(function () {
    showDivs(++slideIndex);
}, 3000);

function showDivs(n) {
    var i;
    var x = document.getElementsByClassName("banner-image");
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
    if (slideIndex >= x.length) { slideIndex = 0; }
    x[slideIndex].style.display = "block";
    x[slideIndex].style.opacity = "0";
    setTimeout(function () {
        x[slideIndex].style.opacity = "1";
    }, 250);
}


window.addEventListener('load', function () {
    showDivs(slideIndex);
});
