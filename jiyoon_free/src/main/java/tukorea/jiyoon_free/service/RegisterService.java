package tukorea.jiyoon_free.service;

import tukorea.jiyoon_free.domain.UserVO;
import tukorea.jiyoon_free.persistence.UserDAO;

import java.io.IOException;

public class RegisterService {
    public boolean register(UserVO userVO) throws IOException {
        UserDAO userDAO = new UserDAO();
        return userDAO.add(userVO);
    }
}
