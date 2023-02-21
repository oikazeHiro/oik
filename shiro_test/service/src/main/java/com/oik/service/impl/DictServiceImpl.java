package com.oik.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.oik.dao.entity.Dict;
import com.oik.dao.mapper.DictMapper;
import com.oik.service.service.DictService;
import com.oik.util.exception.MyException;
import com.oik.util.exception.ResultEnum;
import com.oik.util.redis.CacheClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.oik.util.redis.RedisConstants.CACHE_TTL;
import static com.oik.util.redis.RedisConstants.SYS_DICT;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author oik
 * @since 2022-11-18
 */
@Service
public class DictServiceImpl extends MPJBaseServiceImpl<DictMapper, Dict> implements DictService {

    @Resource
    private CacheClient cacheClient;

    @Override
    public List<Dict> getdicts() {
        LambdaQueryWrapper<Dict> wrapper = new QueryWrapper<Dict>().lambda();
        wrapper.eq(Dict::getFatherId, 0)
                .orderByAsc(Dict::getSort);
        List<Dict> father = list(wrapper);
        wrapper.clear();
        List<String> fatherId = father
                .stream().map(Dict::getDictId).collect(Collectors.toList());
        wrapper.in(Dict::getFatherId, fatherId).orderByAsc(Dict::getSort);
        List<Dict> children = list(wrapper);
        List<String> childrenId = children.stream().map(Dict::getDictId).collect(Collectors.toList());
        wrapper.clear();
        wrapper.in(Dict::getFatherId, childrenId).orderByAsc(Dict::getSort);
        List<Dict> grandson = list(wrapper);
        Map<String, List<Dict>> grandsonMap = grandson.stream()
                .collect(Collectors.groupingBy(Dict::getFatherId,
                        Collectors.mapping(Function.identity(), Collectors.toList())));
        children.forEach(e -> e.setChildren(grandsonMap.get(e.getDictId())));
        Map<String, List<Dict>> childrenMap = children.stream()
                .collect(Collectors.groupingBy(Dict::getFatherId,
                        Collectors.mapping(Function.identity(), Collectors.toList())));
        father.forEach(e -> e.setChildren(childrenMap.get(e.getDictId())));
        cacheClient.set(SYS_DICT, father, CACHE_TTL, TimeUnit.MINUTES);
        return father;
    }

    @Override
    public Page<Dict> findDictList(Page<Dict> page, Dict dict) {
        LambdaQueryWrapper<Dict> wrapper = new QueryWrapper<Dict>().lambda();
        wrapper.eq(Dict::getFatherId, 0)
                .eq(StringUtils.isNotBlank(dict.getTableName()), Dict::getTableName, dict.getTableName())
                .orderByAsc(Dict::getSort);
        Page<Dict> father = baseMapper.selectPage(page, wrapper);
        wrapper.clear();
        List<String> fatherId = father.getRecords()
                .stream().map(Dict::getDictId).collect(Collectors.toList());
        wrapper.in(Dict::getFatherId, fatherId).orderByAsc(Dict::getSort);
        List<Dict> children = list(wrapper);
        List<String> childrenId = children.stream().map(Dict::getDictId).collect(Collectors.toList());
        wrapper.clear();
        wrapper.in(Dict::getFatherId, childrenId).orderByAsc(Dict::getSort);
        List<Dict> grandson = list(wrapper);
        Map<String, List<Dict>> grandsonMap = grandson.stream()
                .collect(Collectors.groupingBy(Dict::getFatherId,
                        Collectors.mapping(Function.identity(), Collectors.toList())));
        children.forEach(e -> e.setChildren(grandsonMap.get(e.getDictId())));
        Map<String, List<Dict>> childrenMap = children.stream()
                .collect(Collectors.groupingBy(Dict::getFatherId,
                        Collectors.mapping(Function.identity(), Collectors.toList())));
        father.getRecords().forEach(e -> e.setChildren(childrenMap.get(e.getDictId())));
        cacheClient.set(SYS_DICT, father, CACHE_TTL, TimeUnit.MINUTES);
        return father;
    }

    @Override
    public Page<Dict> findDictList2(Page<Dict> page, Dict dict) {
        MPJLambdaWrapper<Dict> mapper = new MPJLambdaWrapper<Dict>()
                .eq(StringUtils.isNotEmpty(dict.getFatherId()),Dict::getFatherId,dict.getFatherId());
        return (Page<Dict>) selectJoinListPage(page,Dict.class,mapper);
    }

    @Override
    public Boolean saveDict(Dict dict) {
        LambdaQueryWrapper<Dict> wrapper = new LambdaQueryWrapper<>();
        if (dict.getFatherId().equals("0")){
            wrapper.clear();
            wrapper.eq(Dict::getTableName,dict.getTableName());
            List<Dict> list = list(wrapper);
            if (list.size() > 0) {
                throw new MyException(ResultEnum.DICT_CODE_OR_KEY);
            }
        }
        if (StringUtils.isNotEmpty(dict.getFieldName())){
            wrapper.clear();
            wrapper.eq(Dict::getFieldName,dict.getFieldName());
            List<Dict> list = list(wrapper);
            if (list.size() > 0) {
                throw new MyException(ResultEnum.DICT_CODE_OR_KEY);
            }
        }
        if (StringUtils.isNotEmpty(dict.getKeyy())){
            wrapper.clear();
            wrapper.eq(Dict::getKeyy,dict.getKeyy());
            List<Dict> list = list(wrapper);
            if (list.size() > 0) {
                throw new MyException(ResultEnum.DICT_CODE_OR_KEY);
            }
        }
        return save(dict);
    }

    @Override
    public boolean deleteDict(String id) {
        LambdaQueryWrapper<Dict> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Dict::getFatherId,id);
        List<Dict> list = list(wrapper);
        if(list.size() > 0){
            remove(wrapper);
            removeBatchByIds(list.stream().map(Dict::getDictId).collect(Collectors.toList()));
        }
        return removeById(id);
    }
}
