package tukorea.jiyoon_free.controller;

import tukorea.jiyoon_free.domain.UserVO;
import tukorea.jiyoon_free.service.RegisterService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "register", value = "/register")
public class RegisterController extends HttpServlet {
    private final RegisterService registerService = new RegisterService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        UserVO userVO = new UserVO();

        userVO.setId(request.getParameter("id"));
        userVO.setPassword(request.getParameter("password"));

        PrintWriter out = response.getWriter();
        out.println("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\" %>");
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css\" rel=\"stylesheet\"");
        out.println("          integrity=\"sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9\" crossorigin=\"anonymous\">");
        out.println("    <title>Your Page Title</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <h1>Hello, World!</h1>");
        out.println("</body>");
        out.println("</html>");

        if (registerService.register(userVO)) {
            out.println("<script>");
            out.println("alert('가입 성공! 서비스 이용을 위해 로그인 해주세요.');");
            out.println("</script>");
            out.println("<script>location.href='login.html';</script>");
        } else {
            out.println("<script>");
            out.println("alert('이미 존재하는 아이디입니다. 다시 입력해 주세요.');");
            out.println("history.back();");
            out.println("</script>");
        }
    }
}
