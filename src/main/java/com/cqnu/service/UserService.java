package com.cqnu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqnu.bean.User;

public interface UserService extends IService<User> {
    Page<User> getPage(Integer currentPage,Integer pageSize);
    User getUserByName(String name);
}
