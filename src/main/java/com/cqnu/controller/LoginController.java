package com.cqnu.controller;

import com.cqnu.service.UserService;
import com.cqnu.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/login")
public class LoginController {

    @Autowired
    UserService userService;
    @GetMapping("{name}")
    public AjaxResult login(@PathVariable String name){
        return new AjaxResult(true,userService.getUserByName(name));
    }
}