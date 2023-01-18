package com.oik.api.controller;

import com.oik.dao.entity.ChatMsg;
import com.oik.service.exception.Result;
import com.oik.service.service.ChatMsgService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author oik
 * @since 2022-12-22
 */
@RestController
@RequestMapping("/chatMsg")
public class ChatMsgController {

    @Resource
    private ChatMsgService chatMsgService;

    @GetMapping("")
    public Result<List<ChatMsg>> get() {
        return chatMsgService.getMsg();
    }

//    @PostMapping
//    public Result save(@RequestBody ChatMsg msg){
//        chatMsgService.save(msg);
//        return ResultUtil.getSuccess();
//    }
}
