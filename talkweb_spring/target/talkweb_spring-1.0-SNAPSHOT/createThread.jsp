<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>创建新话题</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input[type="text"], textarea {
            width: 100%; padding: 10px; box-sizing: border-box;
            border: 1px solid #ddd; border-radius: 4px;
        }
        input[type="text"] { font-size: 16px; }
        textarea { height: 300px; font-family: Arial, sans-serif; }
        .btn {
            background: #007bff; color: white; padding: 10px 20px;
            border: none; cursor: pointer; text-decoration: none;
            display: inline-block;
        }
        .btn-danger { background: #dc3545; }
        .form-actions { margin-top: 20px; }
    </style>
</head>
<body>
<div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px;">
    <h1>创建新话题</h1>
    <div>
        <a href="threads" class="btn">返回列表</a>
        <a href="logout" class="btn btn-danger" style="margin-left: 10px;">退出</a>
    </div>
</div>

<form action="createThread" method="post">
    <div class="form-group">
        <label for="title">标题:</label>
        <input type="text" id="title" name="title" required maxlength="100">
    </div>

    <div class="form-group">
        <label for="content">内容:</label>
        <textarea id="content" name="content" required></textarea>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn">发布话题</button>
        <a href="threads" class="btn" style="background: #6c757d; margin-left: 10px;">取消</a>
    </div>
</form>
</body>
</html>
