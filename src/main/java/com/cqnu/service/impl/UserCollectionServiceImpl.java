package com.cqnu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqnu.bean.Model;
import com.cqnu.bean.UserCollection;
import com.cqnu.dao.ModelMapper;
import com.cqnu.dao.UserCollectionMapper;

import com.cqnu.service.UserCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserCollectionServiceImpl extends ServiceImpl<UserCollectionMapper, UserCollection> implements UserCollectionService {
    @Autowired
    ModelMapper modelMapper;
    @Override
    @Transactional
    public Page<Model> getPage(Integer currentPage, Integer pageSize, Model model, List<Integer> arr) {
        Page page =new Page(currentPage,pageSize);
        System.out.println(page.getSize()+page.getCurrent());
        LambdaQueryWrapper<Model> lqw = new LambdaQueryWrapper<>();
        lqw.in(Model::getId,arr);
        return modelMapper.selectPage(page,lqw);
    }
}
