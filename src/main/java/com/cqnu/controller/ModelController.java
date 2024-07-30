package com.cqnu.controller;

import com.cqnu.bean.Model;
import com.cqnu.bean.User;
import com.cqnu.service.ModelService;


import com.cqnu.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/models")
public class ModelController {

    @Autowired
    private ModelService service;
    @GetMapping({"{id}"})
    public AjaxResult getByID(@PathVariable Integer id) {
        return new AjaxResult(true, service.getById(id));
    }

    @GetMapping
    public AjaxResult getAll() {
        return new AjaxResult(true, service.list());
    }

    @PostMapping
    public AjaxResult save(@RequestBody Model model) {
        return new AjaxResult(service.save(model));
    }

    @PutMapping
    public AjaxResult update(@RequestBody Model model) {
        return new AjaxResult(service.updateById(model));
    }

    @DeleteMapping("{id}")
    public AjaxResult remove(@PathVariable Integer id) {

        return new AjaxResult(service.removeById(id));
    }

    @GetMapping("{currentPage}/{pageSize}")
    public AjaxResult getPage(@PathVariable Integer currentPage, @PathVariable Integer pageSize,Model model,String searchInfo) {
//        System.out.println(model);
        System.out.println(searchInfo);
        return new AjaxResult(true, service.getSearchPage(currentPage, pageSize,model,searchInfo));
    }
    @GetMapping("{userId}/{currentPage}/{pageSize}")
    public AjaxResult getModelPage(@PathVariable Integer currentPage, @PathVariable Integer pageSize,Model model,@PathVariable String userId) {
        return new AjaxResult(true, service.getPage(currentPage, pageSize,model,userId));
    }
}
