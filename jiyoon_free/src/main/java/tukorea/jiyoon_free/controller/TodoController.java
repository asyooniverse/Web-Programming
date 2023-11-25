package tukorea.jiyoon_free.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import tukorea.jiyoon_free.service.TodoService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "todo", value = "/todo")
public class TodoController extends HttpServlet {
    private final TodoService service = new TodoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        Long articleId = Long.valueOf(request.getParameter("articleId"));
        String cmd = request.getParameter("cmd");
        request.setAttribute("todoList", service.findTodoList(articleId));

        RequestDispatcher view;
        if (cmd == null) {
            view = request.getRequestDispatcher("todoList.jsp");
        } else {
            view = request.getRequestDispatcher("myTodoList.jsp");
        }
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }

        String jsonString = stringBuilder.toString();
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray todoDataArray = jsonObject.getJSONArray("todoData");
        String title = jsonObject.getString("title");

        HttpSession session = setSession(request);
        service.addArticle(title, session);
        service.addTodo(todoDataArray);
    }

    private static HttpSession setSession(HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (session.isNew() || session.getAttribute("id") == null) {
            session.setAttribute("id", request.getParameter("id"));
        }
        return session;
    }
}
