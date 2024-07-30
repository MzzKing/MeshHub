package com.cqnu.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqnu.bean.Model;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ModelMapper extends BaseMapper<Model> {
}
