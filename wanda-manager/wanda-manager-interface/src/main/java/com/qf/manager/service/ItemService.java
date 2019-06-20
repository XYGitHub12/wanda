package com.qf.manager.service;

import com.qf.manager.pojo.dto.ItemResult;
import com.qf.manager.pojo.dto.PageParam;
import com.qf.manager.pojo.vo.ItemCustom;

public interface ItemService {
    ItemResult<ItemCustom> listItems(PageParam pageParam);
}
