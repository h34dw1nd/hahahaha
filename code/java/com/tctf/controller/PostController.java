package com.tctf.controller;

import com.tctf.entity.Post;
import com.tctf.entity.User;
import com.tctf.mapper.PostMapper;
import com.tctf.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping(value = "/post")
public class PostController {
    @Autowired
    private HttpSession session;

    @Autowired
    private PostService postService;

    @Autowired
    private PostMapper postMapper;

    @RequestMapping(value = "/")
    public String Post(){
        return "post";
    }


    @RequestMapping(value = "/add.do", method = RequestMethod.POST)
    public String AddPost(Post post, RedirectAttributes attr){
        User user = (User) session.getAttribute("user");
        post.setUid(user.getId());
        Map map = postService.AddPost(post);
        attr.addFlashAttribute("message",map.get("status"));
        return "redirect:/user/";
    }

    @RequestMapping(value = "/del.do")
    public String DelPost(Model model, @RequestParam("pid") Integer pid){
        User user = (User) session.getAttribute("user");
        postMapper.DelPost(pid,user.getId());
        return "user";
    }
}
