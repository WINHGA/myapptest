package com.example.myapptest.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.myapptest.common.R;
import com.example.myapptest.pojo.User;
import com.example.myapptest.service.UserService;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Date 2023/7/18 11:40
 * @Author HGA
 * @Class UserController
 * @Package com.example.myapp.controller
 * Description:
 */

@Controller
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/addUser")
    public R save(@RequestBody User user){

        if(user == null){
            return R.error("添加失败！");
        }
        boolean save = userService.save(user);
        if(save){
            return R.success("添加成功！");
        }
        return R.error("系统错误");
    }

    @GetMapping("/getUserById/{id}")
    public R<User> getUserById(@PathVariable Integer id){
        User u = userService.getById(id);
        if(u == null){
            return R.error("查找失败");
        }
        return R.success(u);
    }

    @GetMapping("/getAll")
    public R getAll(Integer pageSize ,Integer currentPage){
        IPage<User> pageinfo = new Page<>(currentPage,pageSize);

        pageinfo = userService.page(pageinfo);
        if(pageinfo.getTotal() == 0){
            return R.error("查询失败");
        }
        return R.success(pageinfo);
    }

    @PutMapping("/updateUser")
    public R update(@RequestBody User user){
        boolean b = userService.updateById(user);
        if(b){
            return R.success("更新成功！");
        }else {
            return R.error("更新失败");
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    public R delete(@PathVariable Integer id){
        boolean b = userService.removeById(id);
        if(b){
            return R.success("删除成功");
        }else {
            return R.error("更新失败！");
        }
    }
    @GetMapping("/queryList")
    public R query(String keyword,Integer pageSize,Integer currentPage){
        if(keyword == null || "".equals(keyword)){
            return getAll(pageSize,currentPage);
        }

        Page<User> page = new Page<>(currentPage,pageSize);
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getId,keyword).or()
                .like(User::getName,keyword).or()
                .like(User::getAddr,keyword).or()
                .eq(User::getSex,keyword).or()
                .like(User::getBirth,keyword);

        page = userService.page(page, lambdaQueryWrapper);

        if(page.getTotal() == 0){
            return R.error("查询失败");
        }
        return R.success(page);
    }
}
