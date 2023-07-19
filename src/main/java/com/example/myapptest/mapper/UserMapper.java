package com.example.myapptest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.myapptest.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Date 2023/7/18 11:42
 * @Author HGA
 * @Class UserMapper
 * @Package com.example.myapp.mapper
 * Description:
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
