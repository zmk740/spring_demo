package com.example.talkweb_spring.controller;

import com.example.talkweb_spring.model.Reply;
import com.example.talkweb_spring.model.Thread;
import com.example.talkweb_spring.model.User;
import com.example.talkweb_spring.service.DataService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ThreadController {

    @Autowired
    private DataService dataService;

    // 检查登录的辅助方法
    private User getSessionUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }

    @GetMapping({"/", "/threads"})
    public String listThreads(Model model, HttpSession session) {
        if (getSessionUser(session) == null) return "redirect:/login";

        model.addAttribute("threads", dataService.getAllThreads());
        return "threadList";
    }

    @GetMapping("/thread")
    public String threadDetail(@RequestParam("id") int id, Model model, HttpSession session) {
        if (getSessionUser(session) == null) return "redirect:/login";

        Thread thread = dataService.getThread(id);
        if (thread != null) {
            model.addAttribute("thread", thread);
            return "threadDetail";
        }
        return "redirect:/threads";
    }

    @GetMapping("/createThread")
    public String createThreadPage(HttpSession session) {
        if (getSessionUser(session) == null) return "redirect:/login";
        return "createThread";
    }

    @PostMapping("/createThread")
    public String doCreateThread(@RequestParam("title") String title,
                                 @RequestParam("content") String content,
                                 HttpSession session) {
        User user = getSessionUser(session);
        if (user == null) return "redirect:/login";

        if (title != null && !title.trim().isEmpty() && content != null && !content.trim().isEmpty()) {
            Thread thread = new Thread();
            thread.setTitle(title.trim());
            thread.setContent(content.trim());
            thread.setAuthor(user.getUsername());
            dataService.addThread(thread);
        }
        return "redirect:/threads";
    }

    @PostMapping("/reply")
    public String doReply(@RequestParam("threadId") int threadId,
                          @RequestParam("content")
                          String content,
                          HttpSession session) {
        User user = getSessionUser(session);
        if (user == null) return "redirect:/login";

        if (content != null && !content.trim().isEmpty()) {
            Reply reply = new Reply();
            reply.setThreadId(threadId);
            reply.setContent(content.trim());
            reply.setAuthor(user.getUsername());
            dataService.addReply(reply);
        }
        return "redirect:/thread?id=" + threadId;
    }
}
