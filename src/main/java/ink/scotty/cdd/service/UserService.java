package ink.scotty.cdd.service;

import ink.scotty.cdd.pojo.User;

public interface UserService {

    // 用户根据账号和密码登录，并返回User
    User login(String account, String password);

    // 删除用户
    void deleteUser(int userId);

    // 用户注册
    User register(User user);

    // 更新用户信息
    void updateUserInfo(User user);

}
