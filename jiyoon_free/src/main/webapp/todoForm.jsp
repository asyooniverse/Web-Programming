<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link rel="stylesheet" href="resources/css/all.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>TO DO LIST</title>
</head>
<body>
<h1 style="margin-top:80px; margin-bottom: 50px">To Do List</h1>
<div style="width: 350px; margin-right: auto; margin-left:auto;">
    <div class="mb-3">
        <label class="form-label">제목</label>
        <input id="title" type="text" class="form-control">
    </div>
    <label class="form-label">계획</label>
    <div>
        <input id="toDo" class="form-control" type="text" style="width:80%; margin-right:15px;float:left">
        <button id="addButton" class="btn btn-dark">추가</button>
    </div>
    <div style="width: 100%" id="toDoList">
    </div>
    <div>
        <button style="width:100%; margin-top:40px"
                onclick="window.location.href='${pageContext.request.contextPath}/article'" type="button"
                id="completeButton" class="btn btn-success">완료
        </button>
    </div>
</div>
<script>
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

</script>
</body>
</html>

