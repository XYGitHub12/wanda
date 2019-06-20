package com.qf.manager.service.impl;

import com.qf.manager.dao.TbItemCustomMapper;
import com.qf.manager.pojo.dto.ItemResult;
import com.qf.manager.pojo.dto.PageParam;
import com.qf.manager.pojo.vo.ItemCustom;
import com.qf.manager.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TbItemCustomMapper itemCustomDao;

    @Override
    public ItemResult<ItemCustom> listItems(PageParam pageParam) {

        ItemResult<ItemCustom> result = new ItemResult<>();
        result.setCode(0);
        result.setMsg("select success");
        try {
            long count = itemCustomDao.getCount();
            result.setCount(count);
            List<ItemCustom> data = itemCustomDao.selectItemsByPage(pageParam);
            result.setData(data);
        }catch (Exception e){
            result.setCode(-1);
            result.setMsg("select failed");
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return result;
    }
}
