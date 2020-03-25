package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    @Override
    public boolean regist(User user) {
        User u = dao.findByUserName(user.getUsername());
        //用户名已存在，注册失败
        if (u != null) {

            return false;
        }
        //设置激活码以及激活状态
        user.setStatus("N");
        user.setCode(UuidUtil.getUuid());
        dao.save(user);

        String text = "<a href='http://localhost/travel/user/active?code=" + user.getCode() + "'>点击激活【黑马旅游网】</a>";
        MailUtils.sendMail(user.getEmail(), text, "激活邮件");
        return true;
    }

    @Override
    public Boolean active(String code) {

        User user = dao.findByCode(code);
        if (user != null) {
            dao.upadateStatus(user);
            return true;
        }
        return false;
    }

    @Override
    public User login(User user) {
        return dao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
