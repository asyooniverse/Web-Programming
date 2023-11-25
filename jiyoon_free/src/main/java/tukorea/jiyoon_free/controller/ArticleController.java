package tukorea.jiyoon_free.controller;

import tukorea.jiyoon_free.service.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "article", value = "/article")
public class ArticleController extends HttpServlet {
    private final ArticleService service = new ArticleService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        HttpSession session = setSession(request);

        String target = request.getParameter("target");

        if (target == null) {
            service.findArticleList(request, response);
        } else if (target.equals("me")) {
            service.findMyArticleList(session, request, response);
        } else if (target.equals("delete")) {
            service.deleteMyArticleList(session, request, response);
        }
    }

    private static HttpSession setSession(HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (session.isNew() || session.getAttribute("id") == null) {
            session.setAttribute("id", request.getParameter("id"));
        }
        return session;
    }
}
