package com.qf.manager.dao;

import com.qf.manager.pojo.dto.PageParam;
import com.qf.manager.pojo.vo.ItemCustom;

import java.util.List;

public interface TbItemCustomMapper {
    long getCount();

    List<ItemCustom> selectItemsByPage(PageParam pageParam);
}
