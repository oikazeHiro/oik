package com.oik.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oik.dao.entity.Greet;
import com.oik.util.exception.Result;
import com.oik.util.exception.ResultUtil;
import com.oik.service.service.GreetService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author oik
 * @since 2022-12-15
 */
@RestController
@RequestMapping("")
public class GreetController {
    @Resource
    private GreetService greetService;

    @GetMapping("/greets")
    public Result<Page<Greet>> getGreet(Page<Greet> page) {
        return greetService.getGreet(page);
    }

    @PostMapping("/greet")
    public Result<Boolean> set(@RequestBody Greet greet) {
        return ResultUtil.getSuccess(greetService.saveOrUpdate(greet));
    }

    @DeleteMapping("/greet/{id}")
    public Result<Boolean> remove(@PathVariable("id") String id) {
        return ResultUtil.getSuccess(greetService.removeById(id));
    }
}
