<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.talkweb_spring.model.Thread" %>
<%@ page import="com.example.talkweb_spring.model.Reply" %>
<%@ page import="java.util.List" %>
<%
    Thread thread = (Thread) request.getAttribute("thread");
    List<Reply> replies = null;
    if (thread != null) {
        replies = thread.getReplies();
    }
%>
<html>
<head>
    <title><%= thread != null ? thread.getTitle() : "讨论详情" %></title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; padding: 20px; }
        .header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; }
        .thread-content { background: #f8f9fa; padding: 20px; border-radius: 5px; margin-bottom: 20px; }
        .thread-meta { color: #666; margin-bottom: 10px; }
        .thread-body { white-space: pre-wrap; line-height: 1.6; }
        .reply-list { margin-top: 30px; }
        .reply-item { border: 1px solid #ddd; padding: 15px; margin-bottom: 15px; border-radius: 5px; }
        .reply-meta { color: #666; font-size: 14px; margin-bottom: 10px; }
        .reply-body { white-space: pre-wrap; line-height: 1.6; }
        .reply-form { margin-top: 30px; }
        textarea { width: 100%; height: 100px; padding: 10px; box-sizing: border-box; }
        .btn {
            background: #007bff; color: white; padding: 8px 15px;
            text-decoration: none; border: none; cursor: pointer;
        }
        .btn-danger { background: #dc3545; }
    </style>
</head>
<body>
<div class="header">
    <h1><%= thread != null ? thread.getTitle() : "讨论话题" %></h1>
    <div>
        <a href="threads" class="btn">返回列表</a>
        <a href="logout" class="btn btn-danger" style="margin-left: 10px;">退出</a>
    </div>
</div>

<% if (thread != null) { %>
<div class="thread-content">
    <div class="thread-meta">
        作者: <%= thread.getAuthor() %> | 时间: <%= thread.getCreateTime() %>
    </div>
    <div class="thread-body"><%= thread.getContent() %></div>
</div>

<div class="reply-list">
    <h3>回复 (<%= replies != null ? replies.size() : 0 %>)</h3>

    <%
        if (replies != null && !replies.isEmpty()) {
            for (Reply reply : replies) {
    %>
    <div class="reply-item">
        <div class="reply-meta">
            <%= reply.getAuthor() %> 回复于 <%= reply.getReplyTime() %>
        </div>
        <div class="reply-body"><%= reply.getContent() %></div>
    </div>
    <%
        }
    } else {
    %>
    <p>暂无回复，成为第一个回复的人吧！</p>
    <%
        }
    %>
</div>

<div class="reply-form">
    <h3>发表回复</h3>
    <form action="reply" method="post">
        <input type="hidden" name="threadId" value="<%= thread.getId() %>">
        <textarea name="content" placeholder="请输入回复内容..." required></textarea>
        <button type="submit" class="btn" style="margin-top: 10px;">发表回复</button>
    </form>
</div>
<% } else { %>
<p>讨论话题不存在。</p>
<a href="threads" class="btn">返回列表</a>
<% } %>
</body>
</html>
