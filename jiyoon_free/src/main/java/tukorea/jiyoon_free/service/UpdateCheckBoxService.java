package tukorea.jiyoon_free.service;

import tukorea.jiyoon_free.persistence.TodoDAO;

import javax.servlet.http.HttpServletRequest;

public class UpdateCheckBoxService {
    public void updateTodo(HttpServletRequest request) {
        String content = request.getParameter("content");
        boolean isChecked = Boolean.parseBoolean(request.getParameter("isChecked"));

        TodoDAO todoDAO = new TodoDAO();
        todoDAO.updateTodo(content, isChecked);
    }
}
