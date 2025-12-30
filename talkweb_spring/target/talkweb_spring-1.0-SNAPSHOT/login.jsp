<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>登录 - 讨论区</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        .container { max-width: 400px; margin: 0 auto; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; }
        input[type="text"], input[type="password"] {
            width: 100%; padding: 8px; box-sizing: border-box;
        }
        .captcha-group { display: flex; align-items: center; }
        .captcha-input { flex: 1; margin-right: 10px; }
        .captcha-img { border: 1px solid #ddd; cursor: pointer; }
        .btn {
            background: #007bff; color: white; padding: 10px 20px;
            border: none; cursor: pointer; width: 100%;
        }
        .error { color: red; margin-bottom: 15px; }
    </style>
</head>
<body>
<div class="container">
    <h2>用户登录</h2>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <form action="login" method="post">
        <div class="form-group">
            <label>用户名:</label>
            <input type="text" name="username" required>
        </div>

        <div class="form-group">
            <label>密码:</label>
            <input type="password" name="password" required>
        </div>

        <div class="form-group">
            <label>验证码:</label>
            <div class="captcha-group">
                <input type="text" name="captcha" class="captcha-input" required>
                <img src="captcha" class="captcha-img" onclick="this.src='captcha?t=' + new Date().getTime()"
                     title="点击刷新验证码">
            </div>
        </div>

        <button type="submit" class="btn">登录</button>
    </form>

    <p style="text-align: center; margin-top: 20px;">
        <small>测试账号: admin/admin123 或 test/test123</small>
    </p>
</div>

<script>
    // 点击验证码图片刷新
    document.querySelector('.captcha-img').addEventListener('click', function() {
        this.src = 'captcha?t=' + new Date().getTime();
    });
</script>
</body>
</html>
