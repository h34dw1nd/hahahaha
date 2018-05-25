package com.tctf.controller;

import com.tctf.entity.Post;
import com.tctf.entity.User;
import com.tctf.mapper.PostMapper;
import com.tctf.mapper.UserMapper;
import com.tctf.service.PostService;
import com.tctf.utils.HttpReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PostService postService;

    @Autowired
    private HttpSession session;

    @Autowired
    private PostMapper postMapper;

    @RequestMapping(value = "/")
    public String Index(Model model){
        User user = (User) session.getAttribute("user");
        User userinfo = userMapper.FindOne(user.getId());
        List<Post> postlist = postService.GetPost(user.getId());
        model.addAttribute("postlist",postlist);
        model.addAttribute("user",userinfo);
        return "user";
    }

    @RequestMapping(value = "/headimg.do",method = RequestMethod.GET)
    public void UpdateHead(@RequestParam("url")String url){
        String downloadPath = request.getSession().getServletContext().getRealPath("/")+"/headimg/";
        String headurl = "/headimg/"+ HttpReq.Download(url,downloadPath);
        User user = (User) session.getAttribute("user");
        Integer uid = user.getId();
        userMapper.UpdateHeadurl(headurl,uid);
    }

    @RequestMapping(value = "/delpost.do")
    public String DelPost(@RequestParam("pid") Integer pid){
        User user = (User) session.getAttribute("user");
        postMapper.DelPost(pid,user.getId());
        return "redirect:/user/";
    }
}
