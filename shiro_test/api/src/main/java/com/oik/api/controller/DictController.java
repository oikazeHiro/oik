package com.oik.api.controller;

import com.oik.dao.entity.Dict;
import com.oik.service.exception.Result;
import com.oik.service.exception.ResultUtil;
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
    public Result<Boolean> add(Dict dict) {
        cacheService.delete(SYS_DICT);
        return ResultUtil.getSuccess(dictService.saveOrUpdate(dict));
    }

    @DeleteMapping("/dict/{id}")
    @RequiresPermissions("dict:delete")
    public Result<Boolean> delete(@NotNull(message = "id is not null") @PathVariable("id") String id) {
        return ResultUtil.getSuccess(dictService.removeById(id));
    }

}
