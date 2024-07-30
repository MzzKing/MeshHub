package com.cqnu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqnu.bean.Model;
import com.cqnu.bean.User;
import org.springframework.stereotype.Service;

import java.util.List;



public interface ModelService extends IService<Model> {
    Page<Model> getPage(Integer currentPage, Integer pageSize, Model model,String userId);
    Page<Model> getSearchPage(Integer currentPage, Integer pageSize, Model model,String searchInfo);
    public Integer insert(Model model);
}
