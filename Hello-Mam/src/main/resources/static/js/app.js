document.addEventListener('DOMContentLoaded', (event) => {
    document.querySelector('form').addEventListener('submit', function(event) {
        event.preventDefault();

        let formData = new FormData(event.target);
        let jsonObject = {};

        for (const [key, value]  of formData.entries()) {
            jsonObject[key] = value;
        }

        let csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
        let csrfHeaderName = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

        fetch('/updateuser', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                [csrfHeaderName]: csrfToken
            },
            body: JSON.stringify(jsonObject)
        }).then(response => {
            if (response.redirected) {
                window.location.href = response.url;
            }
        }).catch(error => console.error('Error:', error));
    });
});