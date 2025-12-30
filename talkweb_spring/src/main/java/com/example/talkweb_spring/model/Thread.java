package com.example.talkweb_spring.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Thread implements Serializable {
    private int id;
    private String title;
    private String content;
    private String author;
    private Date createTime;
    private List<Reply> replies;

    public Thread() {
        this.replies = new ArrayList<>();
        this.createTime = new Date();
    }

    public Thread(int id, String title, String content, String author) {
        this();
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    public List<Reply> getReplies() { return replies; }
    public void setReplies(List<Reply> replies) { this.replies = replies; }

    public void addReply(Reply reply) {
        this.replies.add(reply);
    }
}
