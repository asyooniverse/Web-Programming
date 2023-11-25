package tukorea.jiyoon_free.persistence;

import tukorea.jiyoon_free.domain.UserVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
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

    public boolean login(UserVO userVO) {
        connect();
        String sql = "select * from user where id = ?";
        boolean status = true;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userVO.getId());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String password = rs.getString("password");

                if (!password.equals(userVO.getPassword())) {
                    status = false;
                }

            } else {
                status = false;
            }

            rs.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return status;
    }

    public boolean add(UserVO userVO) {
        connect();
        String sql = "insert into user values (?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userVO.getId());
            pstmt.setString(2, userVO.getPassword());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            disconnect();
        }
        return true;
    }

    public List<UserVO> getStudentList() {
        connect();

        ArrayList<UserVO> userList = new ArrayList<>();
        String sql = "select * from user";

        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                UserVO userVO = new UserVO();
                userVO.setId(rs.getString("id"));
                userVO.setPassword(rs.getString("password"));
                userList.add(userVO);
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return userList;
    }
}
