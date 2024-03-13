function submitLoginForm(event) {
    event.preventDefault();

    var name = document.getElementById('name').value;
    var password = document.getElementById('password').value;

    var formData = {
        name: name,
        password: password
    };

    fetch('/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
        .then(response => {
            if (response.ok) {
                return response.text(); // 토큰을 받아옴
            } else {
                throw new Error('Login failed'); // 로그인 실패 시 에러 처리
            }
        })
        .then(token => {
            // 토큰을 받아서 쿠키에 저장
            document.cookie = `token=${token}`;
            window.location.href = '/plans'; // 리다이렉트할 경로로 이동
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

document.addEventListener("DOMContentLoaded", function() {
    document.getElementById('loginForm').addEventListener('submit', submitLoginForm);
});