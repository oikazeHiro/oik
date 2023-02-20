package com.oik.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.service.MPJJoinService;
import com.oik.dao.entity.Dict;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author oik
 * @since 2022-11-18
 */
public interface DictService extends MPJJoinService<Dict> {

    List<Dict> getdicts();

    Page<Dict> findDictList(Page<Dict> page, Dict dict);

    Page<Dict> findDictList2(Page<Dict> page, Dict dict);

    Boolean saveDict(Dict dict);

    boolean deleteDict(String id);
}
