/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

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
    var formattedDate = `${selectedDate.split("-")[2]}-${selectedDate.split("-")[1]}-${selectedDate.split("-")[0]}`;
    var birthdayDisplay = document.getElementById('birthday');
    birthdayDisplay.setAttribute('value', formattedDate);
}