package tukorea.jiyoon_free.service;

import tukorea.jiyoon_free.domain.ArticleVO;
import tukorea.jiyoon_free.domain.UserVO;
import tukorea.jiyoon_free.persistence.ArticleDAO;
import tukorea.jiyoon_free.persistence.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class LoginService {
    public boolean login(UserVO userVO, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        boolean status = userDAO.login(userVO);

        if (status) {
            HttpSession session = request.getSession();

            if (session.isNew() || session.getAttribute("id") == null) {
                session.setAttribute("id", request.getParameter("id"));
            }

            ArticleDAO dao = new ArticleDAO();
            ArrayList<ArticleVO> articleList = dao.getArticleList();
            request.setAttribute("articleList", articleList);
            RequestDispatcher view = request.getRequestDispatcher("article.jsp");
            view.forward(request, response);
            return true;

        } else {
            return false;
        }
    }
}
