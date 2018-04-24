package com.oa.dao;

import com.oa.pojo.UserT;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface UserTMapper extends Mapper<UserT> {
    UserT login(@Param("username") String username, @Param("password") String password);

    void registered(@Param("username") String username,@Param("password") String password,@Param("phone") String phone);

    String repeatUsername(@Param("username") String username);
}