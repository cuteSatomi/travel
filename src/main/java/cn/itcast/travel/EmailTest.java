package cn.itcast.travel;

import cn.itcast.travel.util.MailUtils;

public class EmailTest {
    public static void main(String[] args) {
        String context = "<a href='http://localhost:8080/bookstore/UserServlet?method=active&code='>点击激活【ZZX幸福书店】</a>";
        String title = "激活邮件";
        MailUtils.sendMail("971335574@qq.com", context, title);
    }
}
