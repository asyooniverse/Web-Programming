package tukorea.jiyoon_free.persistence;

import tukorea.jiyoon_free.domain.TodoVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {
    Connection conn = null;
    PreparedStatement pstmt = null;

    String jdbc_driver = "com.mysql.cj.jdbc.Driver";
    String jdbc_url = "jdbc:mysql://localhost/jspdb?allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC";

    private void connect() {
        try {
            Class.forName(jdbc_driver);
            conn = DriverManager.getConnection(jdbc_url, "jspbook", "passwd");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void disconnect() {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean add(TodoVO todoVO) {
        connect();
        String sql = "insert into todo values (?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setBoolean(1, todoVO.getChecked());
            pstmt.setString(2, todoVO.getContent());
            pstmt.setLong(3, todoVO.getArticleId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            disconnect();
        }
        return true;
    }

    public List<TodoVO> getTodoList(Long articleId) {
        connect();

        ArrayList<TodoVO> todoList = new ArrayList<>();
        String sql = "select * from todo where article_id = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, articleId + 1L);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                TodoVO todoVO = new TodoVO();
                todoVO.setArticleId(rs.getInt("article_id"));
                todoVO.setChecked(rs.getBoolean("is_checked"));
                todoVO.setContent(rs.getString("content"));

                todoList.add(todoVO);
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return todoList;
    }

    public boolean updateTodo(String content, boolean isChecked) {
        connect();

        String sql = "update todo set is_checked = ? where content = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setBoolean(1, isChecked);
            pstmt.setString(2, content);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            disconnect();
        }
        return true;
    }
}