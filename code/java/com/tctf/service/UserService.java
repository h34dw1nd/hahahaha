package com.tctf.service;

import com.tctf.entity.User;
import com.tctf.mapper.UserMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service("UserService")
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public String register(User user,String repassword) {
        String username = user.getUsername();
        String password = user.getPassword();

        if(StringUtils.isBlank(username.trim()) || StringUtils.isBlank(password.trim())){
            return "You need set username and password";
        }

        int uid = userMapper.SelectIdByUsername(username);

        if(uid>0){
            return "This username has been registered!";
        }

        if(!password.equals(repassword)){
            return "repassword";
        }

        userMapper.InsertUser(user);

        return "ok";
    }

    public Map<String,Object> Login(String username, String password){
        Map<String,Object> map = new HashMap<>();
        if(StringUtils.isNotBlank(username) || StringUtils.isNotBlank(password)){
            User user = userMapper.Login(username,password);
            if(user == null){
                map.put("status","fail");
                map.put("message","username or password is incorrect");
            }else{
                map.put("user",user);
                map.put("status","success");
            }
        }else{
            map.put("status","fail");
            map.put("message","username or password is blank");
        }
            return map;
    }
}
