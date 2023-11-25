<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link rel="stylesheet" href="resources/css/all.css">
</head>
<body>

<ul class="list-group" style=" margin-top:150px;margin-left: auto; margin-right: auto; width:300px">
    <c:forEach var="vo" items="${todoList}">
        <li class="list-group-item d-flex justify-content-between align-items-center">
            <label class="form-check-label" for="checkbox_${vo.articleId}">
                    ${vo.content}
            </label>
            <input class="form-check-input" type="checkbox" value=""
                   id="checkbox_${vo.articleId}" ${vo.checked ? 'checked' : ''}
                   onclick="updateCheckbox('${vo.content}', this.checked)">
        </li>
    </c:forEach>
</ul>
<div style="margin-top: 30px; margin-bottom: 50px;text-align: center">
    <a onclick="window.history.back()" class="btn btn-link" target="_self">목록으로 돌아가기</a>
</div>
</body>
<script>
        function updateCheckbox(content, isChecked) {
            var xhr = new XMLHttpRequest();
            xhr.open('POST', 'updateCheckBox', true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            xhr.onreadystatechange = function () {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        console.log('Update successful');
                    } else {
                        console.error('Error:', xhr.status);
                    }
                }
            };
            xhr.send('content=' + encodeURIComponent(content) + '&isChecked=' + isChecked);
        }

</script>
</html>
