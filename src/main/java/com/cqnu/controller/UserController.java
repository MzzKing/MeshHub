package com.cqnu.controller;

import com.cqnu.bean.User;
import com.cqnu.service.UserService;
import com.cqnu.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/users/")
@RestController
public class UserController {

    @Autowired
    private UserService service;
    @GetMapping({"{id}"})
    public AjaxResult getByID(@PathVariable Integer id){
        return new AjaxResult(true,service.getById(id));
    }
    @GetMapping
    public AjaxResult getAll(){
        return new AjaxResult(true,service.list());
    }
    @PostMapping
    public AjaxResult save(@RequestBody User user){
        return new AjaxResult(service.save(user));
    }
    @PutMapping
    public AjaxResult update(@RequestBody User user){
        return new AjaxResult(service.updateById(user));
    }
    @DeleteMapping("{id}")
    public AjaxResult remove(@PathVariable Integer id){
        return new AjaxResult(service.removeById(id));
    }
    @GetMapping("{currentPage}/{pageSize}")
    public AjaxResult getPage(@PathVariable Integer currentPage,@PathVariable Integer pageSize){
        return new AjaxResult(true,service.getPage(currentPage,pageSize));
    }
}
