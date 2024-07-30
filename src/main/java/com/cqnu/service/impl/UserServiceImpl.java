package com.cqnu.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqnu.bean.Model;
import com.cqnu.bean.User;
import com.cqnu.dao.UserMapper;
import com.cqnu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService{

    @Autowired
    UserMapper mapper;
    @Override
    public Page<User> getPage(Integer currentPage, Integer pageSize) {

        Page page = new Page(currentPage,pageSize);
        return mapper.selectPage(page,null);
    }

    @Override
    public User getUserByName(String name) {
        return mapper.getByName(name);
    }
}
