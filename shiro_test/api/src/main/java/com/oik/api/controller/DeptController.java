package com.oik.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oik.dao.entity.Dept;
import com.oik.service.exception.Result;
import com.oik.service.exception.ResultUtil;
import com.oik.service.service.DeptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author oik
 * @since 2022-11-18
 */
@RestController
@RequestMapping("")
public class DeptController {

    @Resource
    private DeptService deptService;

    @GetMapping("/dept")
    public Result<Page<Dept>> dept(Page<Dept> page, Dept dept) {
        Page<Dept> deptPage = deptService.dept(page, dept);
        return ResultUtil.getSuccess(deptPage);
    }

    @GetMapping("/all-dept/{option}")
    public Result<List<Dept>> getALLDepth(@PathVariable("option") Integer option) {
        List<Dept> allDepth = deptService.getALLDepth(option);
        return ResultUtil.getSuccess(allDepth);
    }

    @PostMapping("/dept")
    @RequiresPermissions("dept:addOrSet")
    public Result<Boolean> save(@RequestBody Dept dept) {
        boolean b = deptService.saveOrUpdate(dept);
        return ResultUtil.getSuccess(b, "success");
    }

    @DeleteMapping("/dept/{id}")
    @RequiresPermissions("dept:delete")
    public Result<Boolean> del(@PathVariable("id") String id) {
        return ResultUtil.getSuccess(deptService.removeDept(id), "success");
    }

}
