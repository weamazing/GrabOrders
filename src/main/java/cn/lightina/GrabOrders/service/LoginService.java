package cn.lightina.GrabOrders.service;

import cn.lightina.GrabOrders.pojo.User;

public interface LoginService {
    User getUserById(int id);
    int addUser(User user);
    User checkLogin(User user);
}