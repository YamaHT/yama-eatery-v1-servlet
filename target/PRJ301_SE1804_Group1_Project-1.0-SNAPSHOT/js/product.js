window.onload = function () {
    var filterDisplay = document.querySelector('#filter-display');
    filterDisplay.innerHTML = `Order By: ` + document.getElementById(filterDisplay.getAttribute('data-bind')).innerHTML;
    sliderOne.value = sliderOne.getAttribute("lastminprice") === "" ? sliderMinValue : sliderOne.getAttribute("lastminprice");
    sliderTwo.value = sliderTwo.getAttribute("lastmaxprice") === "" ? sliderMaxValue : sliderTwo.getAttribute("lastmaxprice");
    slideOne();
    slideTwo();
    fillColor();
    sliderOne.step = minGap;
    sliderTwo.step = minGap;
};
let sliderOne = document.getElementById("slider-1");
let sliderTwo = document.getElementById("slider-2");
let displayValOne = document.getElementById("filter-price-description-range1");
let displayValTwo = document.getElementById("filter-price-description-range2");
let sliderTrack = document.querySelector(".filter-price-input-slider-track");
let sliderMinValue = document.getElementById("slider-1").min;
let sliderMaxValue = document.getElementById("slider-2").max;
let minGap = (sliderMaxValue - sliderMinValue) / 10;

function slideOne() {
    if (parseInt(sliderTwo.value) - parseInt(sliderOne.value) <= minGap) {
        sliderOne.value = parseInt(sliderTwo.value) - minGap;
    }
    displayValOne.textContent = '$' + Math.round(sliderOne.value);
    fillColor();
}
function slideTwo() {
    if (parseInt(sliderTwo.value) - parseInt(sliderOne.value) <= minGap) {
        sliderTwo.value = parseInt(sliderOne.value) + minGap;
    }
    displayValTwo.textContent = '$' + sliderTwo.value;
    fillColor();
}
function fillColor() {
    percent1 = ((sliderOne.value - sliderMinValue) / (sliderMaxValue - sliderMinValue)) * 100;
    percent2 = ((sliderTwo.value - sliderMinValue) / (sliderMaxValue - sliderMinValue)) * 100;
    sliderTrack.style.background = `linear-gradient(to right, #dadae5 ${percent1}% , #3264fe ${percent1}% , #3264fe ${percent2}%, #dadae5 ${percent2}%)`;
}