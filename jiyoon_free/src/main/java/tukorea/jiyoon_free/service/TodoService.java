package tukorea.jiyoon_free.service;

import org.json.JSONArray;
import tukorea.jiyoon_free.domain.ArticleVO;
import tukorea.jiyoon_free.domain.TodoVO;
import tukorea.jiyoon_free.persistence.ArticleDAO;
import tukorea.jiyoon_free.persistence.TodoDAO;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.IntStream;

public class TodoService {

    public void addArticle(String title, HttpSession session) {

        ArticleDAO articleDAO = new ArticleDAO();
        ArticleVO articleVO = new ArticleVO();
        int size = articleDAO.getArticleMaxIndex();

        articleVO.setArticleId(size + 1);
        articleVO.setUserId((String) session.getAttribute("id"));
        articleVO.setTitle(title);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        articleVO.setCreatedAt(Date.valueOf(dtf.format(now)));

        articleDAO.add(articleVO);
    }

    public void addTodo(JSONArray todoDataArray) {
        IntStream.range(0, todoDataArray.length()).mapToObj(todoDataArray::getJSONObject).forEach(todoObject -> {
            String todoContent = todoObject.getString("value");
            boolean isChecked = todoObject.getBoolean("checked");

            TodoVO todoVO = new TodoVO();
            ArticleDAO articleDAO = new ArticleDAO();
            int index = articleDAO.getArticleMaxIndex();

            todoVO.setChecked(isChecked);
            todoVO.setContent(todoContent);
            todoVO.setArticleId(index + 1);

            TodoDAO todoDAO = new TodoDAO();
            todoDAO.add(todoVO);
        });
    }

    public List<TodoVO> findTodoList(Long articleId) {
        TodoDAO dao = new TodoDAO();
        List<TodoVO> todoList = dao.getTodoList(articleId);
        return todoList;
    }
}
