package com.qf.manager.dao;

import com.qf.manager.pojo.dto.PageParam;
import com.qf.manager.pojo.po.TbItem;

import java.util.List;

public interface TbItemCustomMapper {
    long getCount();

    List<TbItem> selectItemsByPage(PageParam pageParam);
}
