package tukorea.jiyoon_free.service;

import tukorea.jiyoon_free.domain.ArticleVO;
import tukorea.jiyoon_free.persistence.ArticleDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class ArticleService {
    ArrayList<ArticleVO> articleList;
    ArticleDAO dao = new ArticleDAO();

    public void findArticleList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        articleList = dao.getArticleList();
        request.setAttribute("articleList", articleList);
        RequestDispatcher view = request.getRequestDispatcher("article.jsp");
        view.forward(request, response);
    }

    public void findMyArticleList(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        articleList = dao.getMyArticleList((String) session.getAttribute("id"));
        request.setAttribute("articleList", articleList);
        RequestDispatcher view = request.getRequestDispatcher("myArticle.jsp");
        view.forward(request, response);
    }

    public void deleteMyArticleList(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dao.delete(request.getParameter("articleId"));

        articleList = dao.getMyArticleList((String) session.getAttribute("id"));
        request.setAttribute("articleList", articleList);

        RequestDispatcher view = request.getRequestDispatcher("myArticle.jsp");
        view.forward(request, response);
    }
}
