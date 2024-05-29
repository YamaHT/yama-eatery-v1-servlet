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
            const regex = /^[0-9]+$/
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

try {
    const changePasswordForm = document.querySelector('.form-box.changePassword');
    changePasswordForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const formData = new FormData(changePasswordForm);

        const formQuery = new URLSearchParams();
        for (const pair of formData.entries()) {
            formQuery.append(pair[0], pair[1]);
        }
        try {
            const response = fetch('/auth/changePassword', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: formQuery.toString()
            });
            changePasswordForm.classList.remove('active');
            document.querySelector('.form-box.otp').classList.add('active');

        } catch (error) {
        }
    })
} catch (error) {

}


const forgotForm = document.querySelector('.form-box.forgotPassword');
forgotForm.addEventListener('submit', (e) => {
    e.preventDefault();
    const formData = new FormData(forgotForm);

    const formQuery = new URLSearchParams();
    for (const pair of formData.entries()) {
        formQuery.append(pair[0], pair[1]);
    }
    try {
        const response = fetch('/auth/changePassword', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: formQuery.toString()
        });
        forgotForm.classList.remove('active');
        document.querySelector('.form-box.otp').classList.add('active');
    } catch (error) {
    }
})