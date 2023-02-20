package com.oik.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oik.dao.entity.ChatMsg;
import com.oik.util.exception.Result;
import com.oik.util.exception.ResultUtil;
import com.oik.service.service.ChatMsgService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author oik
 * @since 2022-12-22
 */
@RestController
@RequestMapping("/")
public class ChatMsgController {

    @Resource
    private ChatMsgService chatMsgService;

    @GetMapping("/chatMsg")
    public Result<Page<ChatMsg>> get(Page<ChatMsg> page,ChatMsg chatMsg) {
        page = chatMsgService.getChatMsg(page,chatMsg);
        return ResultUtil.getSuccess(page);
    }

    @PostMapping("/send-private-msg")
    public Result sendPrivateMsg(@RequestBody ChatMsg chatMsg){
        return chatMsgService.sendPrivateMsg(chatMsg);
    }

    @PostMapping("/send-sys-msg")
    public Result sendSysMsg(@RequestBody ChatMsg chatMsg){
        chatMsgService.sendSysMsg(chatMsg);
        return ResultUtil.getSuccess();
    }

    @GetMapping("/sendAll")
    public Result sendExpandMsgTestToAllUser(){
        chatMsgService.sendExpandMsgTestToAllUser();
        return ResultUtil.getSuccess();
    }

    @GetMapping("/sendOne/{userId}")
    public Result sendExpandMsgTestToOneUser(@PathVariable("userId") String userId){
        chatMsgService.sendExpandMsgTestToOneUser(userId);
        return ResultUtil.getSuccess();
    }
}
