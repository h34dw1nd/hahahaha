package com.tctf.controller;

import com.tctf.entity.User;
import com.tctf.mapper.UserMapper;
import com.tctf.service.UserService;
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
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/doregister.do",method = RequestMethod.POST)
    public String DoRegister(User user, String repassword, Model model){
        String result = userService.register(user,repassword);
        if(result.equals("ok")){
            return "login";
        }else{
            model.addAttribute("message",result);
            return "register";
        }
    }

    @RequestMapping(value = "/register.do",method = RequestMethod.GET)
    public String Register(){
       return "register";
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public String Login(HttpSession session, Model model,@RequestParam("username")String username, @RequestParam("password")String password){
        Map<String,Object> map = userService.Login(username,password);
        if(map.get("status").equals("success")){
            User user = (User) map.get("user");
            session.setAttribute("user",user);
            //attr.addFlashAttribute("headurl",user.getHeadurl());
            return "redirect:/user/";
        }else{
            model.addAttribute("message",map.get("message"));
            return "login";
        }

    }

    @RequestMapping(value = "/logout.do")
    public String Logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/";
    }

}
