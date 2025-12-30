package com.example.talkweb_spring.model;

import java.io.Serializable;
import java.util.Date;

public class Reply implements Serializable {
    private int id;
    private int threadId;
    private String content;
    private String author;
    private Date replyTime;

    public Reply() {
        this.replyTime = new Date();
    }

    public Reply(int id, int threadId, String content, String author) {
        this();
        this.id = id;
        this.threadId = threadId;
        this.content = content;
        this.author = author;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getThreadId() { return threadId; }
    public void setThreadId(int threadId) { this.threadId = threadId; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public Date getReplyTime() { return replyTime; }
    public void setReplyTime(Date replyTime) { this.replyTime = replyTime; }
}
