document.addEventListener('DOMContentLoaded', () => {
    // DOMContentLoaded 이벤트에서 titleValue 초기화
    const titleValue = document.querySelector('#title').value;

    const toDo = document.querySelector('#toDo');
    const addButton = document.querySelector('#addButton');
    const toDoList = document.querySelector('#toDoList');
    const completeButton = document.querySelector('#completeButton');

    addButton.addEventListener('click', () => {
        const item = document.createElement('div');

        const checkBox = document.createElement('input');
        checkBox.setAttribute('type', 'checkbox');
        checkBox.setAttribute('name', 'todoContent');
        checkBox.classList.add('form-check-input');
<%--        checkBox.style.margin = '10px';--%>

        const text = document.createElement('text');
        text.textContent = toDo.value;

        item.appendChild(checkBox);
        item.appendChild(text);
        toDoList.appendChild(item);

        toDo.value = '';
    });

    completeButton.addEventListener('click', () => {
        const titleValue = document.querySelector('#title').value;

        const todoItems = document.querySelectorAll('input[name="todoContent"]');
        const todoData = [];

        todoItems.forEach(item => {
            todoData.push({
                value: item.parentElement.textContent.trim(),
                checked: item.checked
            });
        });

        const xhr = new XMLHttpRequest();
        xhr.open('POST', 'todo', true);
        xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    console.log(xhr.responseText);
                } else {
                    console.error('Error:', xhr.status);
                    console.log(JSON.stringify({ title: titleValue, todoData: todoData }));
                }
            }
        };
        xhr.send(JSON.stringify({ title: titleValue, todoData: todoData }));
    });
});