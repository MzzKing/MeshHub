package com.cqnu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cqnu.bean.Model;
import com.cqnu.bean.UserCollection;
import com.cqnu.service.ModelService;
import com.cqnu.service.impl.UserCollectionServiceImpl;
import com.cqnu.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/userCollection")
public class UserCollectionController {

    @Autowired
    private UserCollectionServiceImpl userCollectionService;


    @GetMapping("{id}/{currentPage}/{pageSize}")
    public AjaxResult getModelListByID(@PathVariable Integer id, @PathVariable Integer currentPage, Model model, @PathVariable Integer pageSize){
        List<Integer> arr = new ArrayList<>();
        LambdaQueryWrapper<UserCollection> lqw = new LambdaQueryWrapper<>();
        lqw.eq(UserCollection::getUserId,id);
        List<UserCollection> list = userCollectionService.list(lqw);
        for (UserCollection item: list)
        arr.add(item.getModelId());

        IPage<Model> ipage = userCollectionService.getPage(currentPage, pageSize, model, arr);

        return new AjaxResult(true,ipage);
    }
    @DeleteMapping("{userId}/{modelId}")
    public AjaxResult removeModel(@PathVariable Integer userId,@PathVariable Integer modelId){
        LambdaQueryWrapper<UserCollection> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(true,UserCollection::getUserId,userId);
        lambdaQueryWrapper.eq(true,UserCollection::getModelId,modelId);
        return new AjaxResult(userCollectionService.remove(lambdaQueryWrapper));
    }
}
