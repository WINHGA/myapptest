package com.example.myapptest.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Date 2023/7/18 11:42
 * @Author HGA
 * @Class User
 * @Package com.example.myapp.pojo
 * Description:
 */

public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String sex;
    private String addr;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", addr='" + addr + '\'' +
                ", birth=" + birth +
                ", age=" + age +
                '}';
    }
}
