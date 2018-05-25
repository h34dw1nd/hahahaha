package com.tctf.controller;

import com.tctf.utils.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
    @Autowired
    private RedisClient redisClient;

    @RequestMapping(value = "/")
    public String Index(Model model,HttpSession session){
        if(session.getAttribute("user") == null) {
            return "login";
        }else{
            return "redirect:/user/";
        }
    }

}
