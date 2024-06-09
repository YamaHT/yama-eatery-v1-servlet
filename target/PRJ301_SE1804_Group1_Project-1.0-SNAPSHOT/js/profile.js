/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function onFileSelected(event) {
    var selectedFile = event.target.files[0];
    var reader = new FileReader();

    var imgtag = document.getElementById("avatar");
    imgtag.title = selectedFile.name;

    reader.onload = function (event) {
        imgtag.src = event.target.result;
    };
    reader.readAsDataURL(selectedFile);
}
function updateBirthdayDisplay(event) {
    var selectedDate = document.getElementById('birthday-input').value;
    var formattedDate = `${selectedDate.split("-")[2]}-${selectedDate.split("-")[1]}-${selectedDate.split("-")[0]}`;
    var birthdayDisplay = document.getElementById('birthday');
    birthdayDisplay.setAttribute('value', formattedDate);
}