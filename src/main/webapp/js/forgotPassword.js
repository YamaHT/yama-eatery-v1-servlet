const otpInputs = document.querySelectorAll('.form-box-otpInput input');
const otpButton = document.querySelector('.form-box.otp .form-box-button');
otpInputs.forEach(input => {
    let lastInputStatus = 0;
    input.onkeyup = (e) => {
        const currentElement = e.target;
        const nextElement = input.nextElementSibling;
        const prevElement = input.previousElementSibling;
        if (prevElement && e.keyCode === 8) {
            if (lastInputStatus === 1) {
                currentElement.value = '';
                prevElement.focus();
            }
            otpButton.setAttribute('disabled', true);
            lastInputStatus = 1;
        } else {
            const regex = /^[0-9]+$/;
            if (!regex.test(currentElement.value)) {
                currentElement.value = currentElement.value.replace(/\D/g, '');
            } else if (currentElement.value) {
                if (nextElement) {
                    nextElement.focus();
                } else {
                    otpButton.removeAttribute('disabled');
                    lastInputStatus = 0;
                }
            }
        }
    }
});

$(".form-box.forgotPassword").submit(function (e) {
    e.preventDefault(); // avoid to execute the actual submit of the form.
    var form = $(this);
    var actionUrl = form.attr('action');
    $.ajax({
        type: "POST",
        url: actionUrl,
        data: form.serialize(), // serializes the form's elements.
        success: function (data)
        {
            console.log(data);
            if (data) {
                window.location.reload();
            } else {
                document.querySelector(".form-box.forgotPassword").classList.remove('active');
                document.querySelector('.form-box.otp').classList.add('active');
            }
        }
    });

});
