<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.talkweb_spring.model.Thread" %>
<%
    // 获取线程列表 - Spring将数据放入request attributes中
    List<Thread> threads = (List<Thread>) request.getAttribute("threads");
%>
<html>
<head>
    <title>讨论话题列表</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; padding: 20px; }
        .header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; }
        .thread-list { border: 1px solid #ddd; }
        .thread-item { padding: 15px; border-bottom: 1px solid #eee; }
        .thread-item:last-child { border-bottom: none; }
        .thread-title { font-size: 18px; font-weight: bold; margin-bottom: 5px; }
        .thread-meta { color: #666; font-size: 14px; }
        .btn {
            background: #007bff; color: white; padding: 8px 15px;
            text-decoration: none; border: none; cursor: pointer;
        }
        .btn-danger { background: #dc3545; }
        .create-btn { margin-bottom: 20px; }
    </style>
</head>
<body>
<div class="header">
    <h1>讨论区</h1>
    <div>
        <span>欢迎, ${user.username}</span>
        <a href="logout" class="btn btn-danger" style="margin-left: 10px;">退出</a>
    </div>
</div>

<a href="createThread" class="btn create-btn">创建新话题</a>

<div class="thread-list">
    <%
        if (threads != null && !threads.isEmpty()) {
            for (Thread thread : threads) {
    %>
    <div class="thread-item">
        <div class="thread-title">
            <a href="thread?id=<%= thread.getId() %>"><%= thread.getTitle() %></a>
        </div>
        <div class="thread-meta">
            作者: <%= thread.getAuthor() %> |
            时间: <%= thread.getCreateTime() %> |
            回复: <%= thread.getReplies().size() %>
        </div>
    </div>
    <%
        }
    } else {
    %>
    <div class="thread-item" style="text-align: center;">
        暂无讨论话题，<a href="createThread">创建第一个话题</a>
    </div>
    <%
        }
    %>
</div>
</body>
</html>
