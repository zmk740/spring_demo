package com.example.talkweb_spring.service;

import com.example.talkweb_spring.model.Reply;
import com.example.talkweb_spring.model.Thread;
import com.example.talkweb_spring.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class DataService {
    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final Map<Integer, Thread> threads = new ConcurrentHashMap<>();
    private final Map<Integer, Reply> replies = new ConcurrentHashMap<>();

    private final AtomicInteger threadIdGenerator = new AtomicInteger(1);
    private final AtomicInteger replyIdGenerator = new AtomicInteger(1);

    @PostConstruct
    public void init() {
        // 初始化测试数据
        users.put("admin", new User("admin", "admin123"));
        users.put("test", new User("test", "test123"));

        Thread thread1 = new Thread(threadIdGenerator.getAndIncrement(),
                "欢迎来到讨论区", "这是一个测试讨论话题，欢迎大家参与讨论！", "admin");
        threads.put(thread1.getId(), thread1);

        Thread thread2 = new Thread(threadIdGenerator.getAndIncrement(),
                "JavaWeb开发经验分享", "分享一些JavaWeb开发的经验和技巧", "test");
        threads.put(thread2.getId(), thread2);

        addReply(new Reply(0, 1, "感谢管理员创建这个讨论区！", "test"));
        addReply(new Reply(0, 1, "期待更多精彩的讨论！", "admin"));
    }

    public boolean addUser(User user) {
        if (users.containsKey(user.getUsername())) {
            return false;
        }
        users.put(user.getUsername(), user);
        return true;
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public boolean validateUser(String username, String password) {
        User user = users.get(username);
        return user != null && user.getPassword().equals(password);
    }

    public List<Thread> getAllThreads() {
        return new ArrayList<>(threads.values());
    }

    public Thread getThread(int id) {
        return threads.get(id);
    }

    public void addThread(Thread thread) {
        thread.setId(threadIdGenerator.getAndIncrement());
        threads.put(thread.getId(), thread);
    }

    public void addReply(Reply reply) {
        reply.setId(replyIdGenerator.getAndIncrement());
        replies.put(reply.getId(), reply);
        Thread thread = threads.get(reply.getThreadId());
        if (thread != null) {
            thread.addReply(reply);
        }
    }
}
