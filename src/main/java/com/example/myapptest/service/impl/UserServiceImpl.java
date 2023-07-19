package com.example.myapptest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myapptest.mapper.UserMapper;
import com.example.myapptest.service.UserService;
import com.example.myapptest.pojo.User;
import org.springframework.stereotype.Service;

/**
 * @Date 2023/7/18 11:46
 * @Author HGA
 * @Class UserServiceImpl
 * @Package com.example.myapp.service.impl
 * Description:
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
}
