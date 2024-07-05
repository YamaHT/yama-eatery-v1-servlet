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
    if (slideIndex >= x.length) {
        slideIndex = 0;
    }
    x[slideIndex].style.display = "block";
    x[slideIndex].style.opacity = "0";
    setTimeout(function () {
        x[slideIndex].style.opacity = "1";
    }, 100);
}

window.addEventListener('load', function () {
    showDivs(slideIndex);
});


async function loadMoreMostSold() {
    document.querySelector(".mostSoldProduct-button-loadmore").style.display = "none";
    var fetchPage = await fetch("/home?action=mostSold");
    var text = await fetchPage.text();
    document.querySelector(".mostSoldProduct-groupOfProduct").innerHTML += text;
}

async function loadMoreNewProduct() {
    document.querySelector(".newProduct-button-loadmore").style.display = "none";
    var fetchPage = await fetch("/home?action=newProduct");
    var text = await fetchPage.text();
    document.querySelector(".newProduct-groupOfProduct").innerHTML += text;
}