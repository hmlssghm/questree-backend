function submitForm(event) {
    event.preventDefault();

    var name = document.getElementById('name').value;
    var email = document.getElementById('email').value;
    var password = document.getElementById('password').value;

    var formData = {
        name: name,
        email: email,
        password: password
    };

    fetch('/members/new', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
        .then(response => {
            if (response.redirected) {
                window.location.href = response.url; // 리다이렉트 수행
            } else {
                // 리다이렉트가 아닌 경우에 대한 처리
                console.log(response);
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

document.addEventListener("DOMContentLoaded", function() {
    document.getElementById('memberForm').addEventListener('submit', submitForm);
});