package com.oik.api.controller;

import com.oik.dao.entity.Dict;
import com.oik.service.exception.Result;
import com.oik.service.exception.ResultUtil;
import com.oik.service.service.CacheService;
import com.oik.service.service.DictService;
import com.oik.util.redis.CacheClient;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CacheService cacheService;
    @Resource
    private DictService dictService;

    @GetMapping("/dicts")
    @RequiresPermissions("dict:view")
    public Result dict() {
        List<Dict> dict = CacheClient.selectCacheByTemplate(
                () -> cacheService.getDict(),
                () -> dictService.getDicts()
        );
        return ResultUtil.getSuccess(dict);
    }

    @PostMapping("/dict")
    public Result add(Dict dict) {
        cacheService.delete(SYS_DICT);
        return ResultUtil.getSuccess(dictService.saveOrUpdate(dict));
    }

    @DeleteMapping("/dict/{id}")
    public Result delete(@NotNull(message = "id is not null") @PathVariable("id") Long id) {
        return ResultUtil.getSuccess(dictService.removeById(id));
    }

}
