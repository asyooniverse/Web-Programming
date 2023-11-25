package tukorea.jiyoon_free.persistence;

import tukorea.jiyoon_free.domain.ArticleVO;

import java.sql.*;
import java.util.ArrayList;

public class ArticleDAO {
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

    public ArrayList<ArticleVO> getArticleList() {
        connect();

        ArrayList<ArticleVO> articleList = new ArrayList<>();
        String sql = "select * from article";

        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ArticleVO articleVO = new ArticleVO();
                articleVO.setArticleId(rs.getInt("article_id"));
                articleVO.setTitle(rs.getString("title"));
                articleVO.setUserId(rs.getString("user_id"));
                articleVO.setCreatedAt(rs.getDate("created_at"));

                articleList.add(articleVO);
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return articleList;
    }

    public ArrayList<ArticleVO> getMyArticleList(String userId) {
        connect();

        ArrayList<ArticleVO> articleList = new ArrayList<>();
        String sql = "select * from article where user_id = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ArticleVO articleVO = new ArticleVO();
                articleVO.setArticleId(rs.getInt("article_id"));
                articleVO.setTitle(rs.getString("title"));
                articleVO.setUserId(rs.getString("user_id"));
                articleVO.setCreatedAt(rs.getDate("created_at"));

                articleList.add(articleVO);
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return articleList;
    }

    public void delete(String strId) {
        connect();
        String sql = "delete from article where article_id = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, strId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public void add(ArticleVO articleVO) {
        connect();
        String sql = "insert into article values (?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setLong(1, articleVO.getArticleId());
            pstmt.setString(2, articleVO.getTitle());
            pstmt.setString(3, articleVO.getUserId());
            pstmt.setDate(4, articleVO.getCreatedAt());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public int getArticleMaxIndex() {
        connect();
        String sql = "select max(article_id) as article_id from article";
        int maxIndex = 0;

        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                maxIndex = rs.getInt("article_id");
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return maxIndex;
    }
}
