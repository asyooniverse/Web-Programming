package tukorea.jiyoon_free.controller;

import tukorea.jiyoon_free.service.UpdateCheckBoxService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "updateCheckBox", value = "/updateCheckBox")
public class UpdateCheckBoxController extends HttpServlet {
    private final UpdateCheckBoxService service = new UpdateCheckBoxService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        service.updateTodo(request);
    }
}
