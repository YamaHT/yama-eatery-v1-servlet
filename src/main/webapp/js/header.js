var searchBar = document.querySelector('.header-button-search-bar');
function setSearchBarPosition() {
    var headerHeight = document.querySelector('header').offsetHeight;
    searchBar.style.top = headerHeight + 'px';
}
setSearchBarPosition();
window.addEventListener('resize', function () {
    setSearchBarPosition();
});

window.addEventListener('scroll', function () {
    searchBar.style.display = 'none';
    document.querySelector('.header-button-search').className = 'fa-solid fa-magnifying-glass header-button-search';
});

function showSearchBar(icon) {
    if (searchBar.style.display === 'none') {
        searchBar.style.display = 'flex';
        icon.className = 'fa-solid fa-xmark header-button-search';
    } else {
        searchBar.style.display = 'none';
        icon.className = 'fa-solid fa-magnifying-glass header-button-search';
    }
}

function showSidenavSubmenu(link) {
    var submenu = document.querySelector('.' + link.parentElement.parentElement.className + ' .subnav');
    if (submenu.style.display === 'none' || submenu.style.display === '') {
        submenu.style.display = 'block';
    } else {
        submenu.style.display = 'none';
    }
    return false;
}

function showSidenav() {
    document.querySelector('.header-navigation-side').classList.add('open');
    document.querySelector('.blur').style.display = 'block';
}

function hideSidenav() {
    document.querySelector('.header-navigation-side').classList.remove('open');
    setTimeout(function () {
        document.querySelector('.blur').style.display = 'none';
    }, 300);
}

function emptySearchInput() {
    document.querySelector('#name').value = '';
}

function submitSearchForm() {
    document.querySelector('.header-button-search-bar').submit();
}
