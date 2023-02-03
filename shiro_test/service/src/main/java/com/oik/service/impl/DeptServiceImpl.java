package com.oik.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.oik.dao.entity.Dept;
import com.oik.dao.mapper.DeptMapper;
import com.oik.service.exception.MyException;
import com.oik.service.service.DeptService;
import com.oik.util.redis.CacheClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.oik.util.redis.RedisConstants.SYS_DEPT;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author oik
 * @since 2022-11-18
 */
@Service
public class DeptServiceImpl extends MPJBaseServiceImpl<DeptMapper, Dept> implements DeptService {

    @Resource
    private CacheClient cacheClient;

    @Override
    public Page<Dept> dept(Page<Dept> page, Dept dept) {
        LambdaQueryWrapper<Dept> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotEmpty(dept.getDeptName()), Dept::getDeptName, dept.getDeptName())
                .eq(Dept::getParentId, 0)
                .orderByAsc(Dept::getOrderNum)
                .orderByAsc(Dept::getCreateTime);
        Page<Dept> deptPage = baseMapper.selectPage(page, wrapper);
        wrapper.clear();
        wrapper.ne(Dept::getParentId, 0)
                .orderByAsc(Dept::getOrderNum);
        List<Dept> list = list(wrapper);
        Map<String, List<Dept>> collect = list.stream().collect(Collectors.groupingBy(Dept::getParentId,
                Collectors.mapping(Function.identity(), Collectors.toList())));
        deptPage.getRecords().forEach(e -> e.setChildren(collect.get(e.getDeptId())));
        return deptPage;
    }

    @Override
    public List<Dept> getALLDepth(Integer option) {
        try {
            List<Dept> allDepth = cacheClient.queryWithPassThroughList(SYS_DEPT, "", Dept.class, e -> this.list(), 30L, TimeUnit.MINUTES);
            if (option != 0) {
                Map<String, List<Dept>> collect = allDepth.stream()
                        .filter(e -> !e.getDeptId().equals("0"))
                        .collect(Collectors.toList())
                        .stream()
                        .collect(Collectors.groupingBy(Dept::getParentId,
                                Collectors.mapping(Function.identity(), Collectors.toList())));
                allDepth = allDepth.stream().filter(e -> e.getParentId().equals("0")).collect(Collectors.toList());
                allDepth.forEach(e -> {
                    e.setChildren(collect.get(e.getDeptId()));
                });
            }
            return allDepth;
        } catch (InterruptedException e) {
            throw new MyException(e.getMessage());
        }

    }

    @Transactional
    @Override
    public Boolean removeDept(String id) {
        remove(new LambdaQueryWrapper<Dept>().eq(Dept::getParentId, id));
        removeById(id);
        return true;
    }
}
