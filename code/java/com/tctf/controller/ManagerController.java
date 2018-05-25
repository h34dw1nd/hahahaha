package com.tctf.controller;


import com.tctf.entity.Post;
import com.tctf.entity.User;
import com.tctf.mapper.PostMapper;
import com.tctf.utils.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/manage")
@Controller
public class ManagerController {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private RedisClient redisClient;

    @RequestMapping(value = "/allpost.do")
    public String ListPost(Model model,HttpSession session){
        User user = (User)session.getAttribute("user");
        try{
        if(user.getIsadmin()) {
            List<Post> list = postMapper.GetAllPost();
            model.addAttribute("allpost", list);
            return "manager";
        }
         }catch (Exception e){
                return "redirect:/";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/audit.do")
    public String AuditPost(@RequestParam("pid") Integer pid,HttpSession session) {
        User user = (User) session.getAttribute("user");
        try {
            if (user.getIsadmin()) {
                postMapper.AuditPost(pid);
                Post post = postMapper.GetOne(pid);
                redisClient.set(pid,post);
                return "manager";
            }
        }catch (Exception e){
            return "redirect:/";
        }
            return "redirect:/";
    }

    @RequestMapping(value = "/check.do")
    public String CheckPost(@RequestParam("pid") Integer pid, HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        try {
            if (user.getIsadmin()) {
                Post post = redisClient.getObject(pid);
                model.addAttribute("post", post);
                return "manager";
            }
        }catch(Exception e){
                return "redirect:/";
            }
                return "redirect:/";
        }

}
