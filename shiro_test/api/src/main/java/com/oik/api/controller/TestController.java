package com.oik.api.controller;

import cn.hutool.json.JSONUtil;
import com.oik.dao.entity.ChatMsg;
import com.oik.service.exception.Result;
import com.oik.service.exception.ResultUtil;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author oik
 * @since 2022-11-18
 */
@RestController
@RequestMapping("/test")
public class TestController {

//    @PostMapping("/do")
//    public Result test(@RequestBody ChatMsg chatMsg){
//        JSONUtil.toJsonStr(chatMsg);
//        return ResultUtil.getSuccess(chatMsg);
//    }
//    @GetMapping("/do2")
//    public Result test2(@RequestParam LocalDateTime time){
//        System.out.println("time ==================== " + time);
//        return ResultUtil.getSuccess(time);
//    }
}
