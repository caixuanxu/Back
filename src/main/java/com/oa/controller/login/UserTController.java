package com.oa.controller.login;

import com.oa.common.CommonResult;
import com.oa.common.config.JedisUtil;
import com.oa.pojo.UserT;
import com.oa.service.login.UserTService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequestMapping(value = "user")
@Controller
public class UserTController {

    @Autowired
    private UserTService userService;
    //登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject login(@RequestParam("username") String username, @RequestParam("password") String password) {

        CommonResult<JSONObject> result = new CommonResult<>();
       UserT user = userService.login(username, password);
        if(user != null){
            String token = UUID.randomUUID().toString();
            result.setStatus(200);
            result.setMsg("登录成功");
            Map<String, String> tokenMap = new HashMap<>();
            tokenMap.put("token",token);
            result.setData(JSONObject.fromObject(tokenMap));
            JedisUtil.setString("token",token);
        }else {
            result.setStatus(400);
            result.setMsg("账号或密码错误");
            result.setData(JSONObject.fromObject("{}"));
        }
        return JSONObject.fromObject(result);
    }
    //注册
    @RequestMapping(value = "/registered", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject registered(@RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 @RequestParam("phone") String phone){
        CommonResult<JSONObject> result = new CommonResult<>();
        boolean isHas = userService.repeatUsername(username);
        try {
            if("" == username){
                result.setStatus(400);
                result.setMsg("请输入用户名");
                result.setData(JSONObject.fromObject("{}"));
            }else if("" == password){
                result.setStatus(400);
                result.setMsg("请输入密码");
                result.setData(JSONObject.fromObject("{}"));
            }else if("" == phone){
                result.setStatus(400);
                result.setMsg("请输入手机");
                result.setData(JSONObject.fromObject("{}"));
            }else {
                if(isHas){
                    result.setStatus(400);
                    result.setMsg("账户已存在");
                    result.setData(JSONObject.fromObject("{}"));
                }else {
                    userService.registered(username,password,phone);
                    result.setStatus(200);
                    result.setMsg("注册成功");
                    result.setData(JSONObject.fromObject("{}"));
                }
            }
        } catch (Exception ex){
            result.setData(JSONObject.fromObject("服务器错误"));
            result.setStatus(1000);
        }

        return JSONObject.fromObject(result);
    }
    //查询账号是否存在
    @RequestMapping(value = "/repeatUsername", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject repeatUsername(@RequestParam("username") String username){
        CommonResult<JSONObject> result = new CommonResult<>();
        boolean flg =  userService.repeatUsername(username);
        try {
           if(flg){
               result.setStatus(400);
               result.setMsg("账户已存在");
               result.setData(JSONObject.fromObject("{}"));
           }else {
               result.setStatus(200);
               result.setMsg("可以创建");
               result.setData(JSONObject.fromObject("{}"));
           }
        } catch (Exception ex){
            result.setData(JSONObject.fromObject("服务器错误"));
            result.setStatus(1000);
        }
        return JSONObject.fromObject(result);
    }
}
