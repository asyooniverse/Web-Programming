package tukorea.jiyoon_free.controller;

import tukorea.jiyoon_free.domain.UserVO;
import tukorea.jiyoon_free.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "login", value = "/login")
public class LoginController extends HttpServlet {
    private final LoginService service = new LoginService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        UserVO userVO = new UserVO();

        userVO.setId(request.getParameter("id"));
        userVO.setPassword(request.getParameter("password"));

        if (!service.login(userVO, request, response)) {
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('로그인 실패! 아이디와 비밀번호를 확인해 주세요.');");
            out.println("history.back();");
            out.println("</script>");
        }
    }
}
