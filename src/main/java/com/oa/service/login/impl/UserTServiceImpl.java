package com.oa.service.login.impl;

import com.oa.dao.UserTMapper;
import com.oa.pojo.UserT;
import com.oa.service.login.UserTService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserTServiceImpl implements UserTService {
    @Resource
    private UserTMapper userTMapper;

    @Override
    public UserT login(String username, String password) {

        return userTMapper.login(username, password);
    }

    @Override
    public void registered(String username, String password, String phone) {
         userTMapper.registered(username,password,phone);
    }

    @Override
    public boolean repeatUsername(String username) {
        String isHas = userTMapper.repeatUsername(username);
        if(null == isHas){
            return  false;
        }else {
            return  true;
        }
    }
}
