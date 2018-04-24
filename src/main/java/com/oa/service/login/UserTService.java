package com.oa.service.login;

import com.oa.pojo.UserT;
import net.sf.json.JSONObject;

public interface UserTService {
    UserT login(String username, String password);
    void registered(String username, String password, String phone);
    boolean repeatUsername (String username);
}
