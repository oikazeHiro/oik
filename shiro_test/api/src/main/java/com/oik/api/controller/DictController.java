package com.oik.api.controller;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oik.dao.entity.Dict;
import com.oik.util.exception.Result;
import com.oik.util.exception.ResultUtil;
import com.oik.service.service.CacheService;
import com.oik.service.service.DictService;
import com.oik.util.redis.CacheClient;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.oik.util.redis.RedisConstants.SYS_DICT;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author oik
 * @since 2022-11-18
 */
@RestController
@RequestMapping("")
public class DictController {

    @Resource
    private CacheService cacheService;
    @Resource
    private DictService dictService;

    @GetMapping("/dicts")
//    @RequiresPermissions("dict:view")
    public Result<List<Dict>> dict() {
        List<Dict> dict = CacheClient.selectCacheByTemplate(
                () -> cacheService.getDict(),
                () -> dictService.getdicts()
        );
        return ResultUtil.getSuccess(dict);
    }

    @PostMapping("/dict")
    @RequiresPermissions("dict:addOrSet")
    public Result<Boolean> add(@RequestBody Dict dict) {
        cacheService.delete(SYS_DICT);
        return ResultUtil.getSuccess(dictService.saveDict(dict));
    }
    @PutMapping("/dict")
    @RequiresPermissions("dict:addOrSet")
    public Result<Boolean> set(@RequestBody Dict dict) {
        cacheService.delete(SYS_DICT);
        return ResultUtil.getSuccess(dictService.updateById(dict));
    }

    @DeleteMapping("/dict/{id}")
    @RequiresPermissions("dict:delete")
    public Result<Boolean> delete(@NotNull(message = "id is not null") @PathVariable("id") String id) {
        cacheService.delete(SYS_DICT);
        return ResultUtil.getSuccess(dictService.deleteDict(id));
    }

    @GetMapping("/dict-list")
    @RequiresPermissions("dict:view")
    public Result<Page<Dict>> findDictList(Page<Dict> page, Dict dict) {
        return ResultUtil.getSuccess(dictService.findDictList(page, dict));
    }

    @GetMapping("/dict-list2")
    @RequiresPermissions("dict:view")
    public Result<Page<Dict>> findDictList2(Page<Dict> page, Dict dict) {
        return ResultUtil.getSuccess(dictService.findDictList2(page, dict));
    }
}
