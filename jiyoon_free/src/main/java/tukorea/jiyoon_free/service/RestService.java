package tukorea.jiyoon_free.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tukorea.jiyoon_free.domain.UserVO;
import tukorea.jiyoon_free.persistence.UserDAO;

import java.util.List;

public class RestService {
    public JSONArray putJson(String cmdReq) {
        UserDAO userDAO = new UserDAO();
        JSONArray arrayJson = new JSONArray();

        if (cmdReq == null) {
            return null;
        }

        if (cmdReq.equals("list")) {
            try {
                List<UserVO> userList = userDAO.getStudentList();
                for (UserVO vo : userList) {
                    JSONObject json = new JSONObject();
                    json.put("id", vo.getId());
                    json.put("passwd", vo.getPassword());
                    arrayJson.put(json);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayJson;
    }
}
