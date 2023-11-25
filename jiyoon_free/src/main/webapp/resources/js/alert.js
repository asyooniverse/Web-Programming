(function (type, message) {
    let alertDiv = document.createElement('div');
    alertDiv.classList.add('alert', 'alert-' + type, 'd-flex', 'align-items-center');
    alertDiv.setAttribute('role', 'alert');

    let svgIcon = document.createElementNS('http://www.w3.org/2000/svg', 'svg');
    svgIcon.classList.add('bi', 'flex-shrink-0', 'me-2');
    svgIcon.setAttribute('width', '24');
    svgIcon.setAttribute('height', '24');
    svgIcon.setAttribute('role', 'img');
    svgIcon.setAttribute('aria-label', type.charAt(0).toUpperCase() + type.slice(1) + ':');

    let useElement = document.createElementNS('http://www.w3.org/2000/svg', 'use');
    useElement.setAttribute('xlink:href', '#' + type + '-fill');

    svgIcon.appendChild(useElement);

    let messageDiv = document.createElement('div');
    messageDiv.textContent = message;

    alertDiv.appendChild(svgIcon);
    alertDiv.appendChild(messageDiv);

    document.body.appendChild(alertDiv);

    setTimeout(function () {
        console.log('알림이 사라지는 중...');
        alertDiv.classList.add('fade');
        setTimeout(function () {
            console.log('알림이 사라짐.');
            alertDiv.remove();
        }, 500);
    }, 5000);
})('success', 'This is a success message');