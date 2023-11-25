package tukorea.jiyoon_free.controller;

import tukorea.jiyoon_free.service.RestService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "JsonServlet", value = "/jiyoon_free/JsonServlet")
public class RestController extends HttpServlet {
    private final RestService restService = new RestService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        PrintWriter out = response.getWriter();

        String cmdReq = request.getParameter("key");
        out.println(restService.putJson(cmdReq));
    }
}
