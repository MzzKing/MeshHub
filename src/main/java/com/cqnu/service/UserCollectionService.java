package com.cqnu.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqnu.bean.Model;
import com.cqnu.bean.UserCollection;

import java.util.List;

public interface UserCollectionService extends IService<UserCollection> {
    Page<Model> getPage(Integer currentPage, Integer pageSize, Model model, List<Integer> arr);
}
