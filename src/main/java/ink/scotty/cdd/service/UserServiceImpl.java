package ink.scotty.cdd.service;

import ink.scotty.cdd.mapper.UserMapper;
import ink.scotty.cdd.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User login(String account, String password) {
        User user = userMapper.getUserByAccount(account);
        if (user == null || !password.equals(user.getPassword())) return null;
        return user;
    }

    @Override
    public void deleteUser(int userId) {
        userMapper.deleteUser(userId);
    }

    @Override
    public User register(User user) {
        User newUser = userMapper.getUserByAccount(user.getAccount());
        if (newUser != null) return null;
        userMapper.addUser(user);
        return user;
    }

    @Override
    public void updateUserInfo(User user) {
        userMapper.updateUserInfo(user);
    }
}
