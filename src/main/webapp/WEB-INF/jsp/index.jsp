<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <title>欢迎页</title>
</head>
<body>
    <div>Hello World</div>
    <div id="user"></div>
    <div id="balance"></div>
    <input type="button" name="btn_logout" value="退出登录"/>
    <input type="button" name="btn_balance" value="获取余额">
</body>
<script src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
    $(function(){
        $("input[name='btn_logout']").click(function(){
            $.ajax({
                type:"POST",
                url:"/logout.json",
                success:function(data) {
                    if (data.status == 1) {
                        window.location.href = "/login.html";
                    } else {
                        alert(data.message);
                    }
                },
                error:function() {
                    alert("请求出错");
                }
            });
        });
    });

    $(function(){
        $("input[name='btn_balance']").click(function(){
            $.ajax({
                type:"GET",
                url:"/user/getUserInfo",
                success:function(data) {
                    if (data.status == 1) {
                        var user = data.data;
                        $("#balance").after("<p><label>姓名:</label><em>"+user.username+"&nbsp;&nbsp;</em><label>余额:</label><em>"+user.wallet+"&nbsp;&nbsp;</em><label>平台编码:</label><em>"+user.cagent+"</em></p>");
                    } else {
                        alert(data.message);
                    }
                },
                error:function() {
                    alert("请求出错");
                }
            });
        });
    });

    //异步获取用户信息
    $.ajax({
        type:"GET",
        url:"user/findAll",
        data:{"pageNo":0, "pageSize":20},
        success:function(data) {
            if (data.status==1) {
                var divs = document.getElementById("user");//获取页面中所有div
                var user = data.data;
                for (var i=0; i<user.length; i++){
                    var username = user[i].username;
                    var wallet = user[i].wallet;
                    var cagent = user[i].cagent;
                    $("#user").after("<p><label>姓名:</label><em>"+username+"&nbsp;&nbsp;</em><label>余额:</label><em>"+wallet+"&nbsp;&nbsp;</em><label>平台编码:</label><em>"+cagent+"</em></p>");
                }
            } else {
                alert(data.message);
            }
        },
        error:function() {
            alert("请求出错");
        }
    })
</script>
</html>