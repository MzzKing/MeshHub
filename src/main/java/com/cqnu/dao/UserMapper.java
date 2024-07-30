package com.cqnu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqnu.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;



@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where username=#{name}")
    User getByName(@Param("name") String name);
}
