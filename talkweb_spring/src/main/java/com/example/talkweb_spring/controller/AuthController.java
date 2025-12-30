package com.example.talkweb_spring.controller;

import com.example.talkweb_spring.model.User;
import com.example.talkweb_spring.service.DataService;
import com.example.talkweb_spring.util.CaptchaUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class AuthController {

    @Autowired
    private DataService dataService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("captcha") String captcha,
                          HttpSession session,
                          Model model) {

        String sessionCaptcha = (String) session.getAttribute("captcha");
        session.removeAttribute("captcha"); // 一次性使用

        if (sessionCaptcha == null || !sessionCaptcha.equalsIgnoreCase(captcha)) {
            model.addAttribute("error", "验证码错误！");
            return "login";
        }

        if (dataService.validateUser(username, password)) {
            session.setAttribute("user", dataService.getUser(username));
            return "redirect:/threads";
        } else {
            model.addAttribute("error", "用户名或密码错误！");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/captcha")
    public void getCaptcha(HttpServletResponse response, HttpSession session) throws IOException {
        CaptchaUtil.CaptchaResult result = CaptchaUtil.generateCaptcha();
        session.setAttribute("captcha", result.getCode());

        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        response.getOutputStream().write(result.getImageData());
    }
}
