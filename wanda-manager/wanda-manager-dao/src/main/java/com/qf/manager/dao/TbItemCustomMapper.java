package com.qf.manager.dao;

import com.qf.manager.pojo.dto.ItemQuery;
import com.qf.manager.pojo.dto.PageParam;
import com.qf.manager.pojo.vo.ItemCustom;
import com.qf.manager.pojo.vo.TbItemIndex;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbItemCustomMapper {
    long getCount(@Param("itemQuery") ItemQuery itemQuery);

    List<ItemCustom> selectItemsByPage(@Param("pageParam") PageParam pageParam, @Param("itemQuery") ItemQuery itemQuery);

    List<TbItemIndex> listIndexByTwo();
}
