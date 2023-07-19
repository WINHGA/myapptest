package com.example.myapptest.config;

import com.example.myapptest.common.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Date 2023/7/18 17:11
 * @Author HGA
 * @Class HandleExceptions
 * @Package com.example.myapptest.config
 * Description:
 */

@RestControllerAdvice
public class HandleExceptions {

    @ExceptionHandler(Exception.class)
    public R doException(Exception exception){
        // 记录日志

        // 通知运维

        // 通知开发
        exception.printStackTrace();
        return R.error("服务器开小差了-_-");
    }
}
