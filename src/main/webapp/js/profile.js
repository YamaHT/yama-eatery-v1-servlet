function onFileSelected(event) {
    var selectedFile = event.target.files[0];
    var reader = new FileReader();

    var image = document.getElementById("avatar");
    image.title = selectedFile.name;

    reader.onload = function (event) {
        image.src = event.target.result;
    };
    reader.readAsDataURL(selectedFile);
}
function updateBirthdayDisplay() {
    var selectedDate = document.getElementById('birthday-input').value;
    if (selectedDate !== '') {
        var formattedDate = `${selectedDate.split("-")[2]}-${selectedDate.split("-")[1]}-${selectedDate.split("-")[0]}`;
        var birthdayDisplay = document.getElementById('birthday');
        birthdayDisplay.setAttribute('value', formattedDate);
    }
}

function setMaxDate() {
    var today = new Date().toISOString().split('T')[0];
    document.getElementById('birthday-input').setAttribute('max', today);
}

setMaxDate();
