package com.cqnu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqnu.bean.Model;
import com.cqnu.bean.User;
import com.cqnu.dao.ModelMapper;
import com.cqnu.service.ModelService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ModelServiceImpl extends ServiceImpl<ModelMapper, Model> implements ModelService {

    @Autowired
    ModelMapper mapper;

    @Override
    public Page<Model> getPage(Integer currentPage, Integer pageSize, Model model, String userId) {
        LambdaQueryWrapper<Model> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Model::getUserId,userId);
        Page page = new Page<>(currentPage,pageSize);
        return mapper.selectPage(page,lqw);
    }

    @Override
    public Page<Model> getSearchPage(Integer currentPage, Integer pageSize, Model model,String searchInfo) {
        LambdaQueryWrapper<Model> lqw = new LambdaQueryWrapper<>();
        lqw.eq(!model.getType().equals("全部"),Model::getType,model.getType());
        lqw.eq(!model.getFileForm().equals("全部"),Model::getFileForm,model.getFileForm());
        lqw.like(Strings.isNotEmpty(searchInfo),Model::getDescription,searchInfo);
        Page page = new Page(currentPage,pageSize);
        return mapper.selectPage(page,lqw);
    }
    @Override
    public Integer insert(Model model){
       return mapper.insert(model);
    }
}
;