package com.qf.manager.service.impl;

import com.qf.manager.dao.TbItemCustomMapper;
import com.qf.manager.dao.TbItemMapper;
import com.qf.manager.pojo.dto.ItemQuery;
import com.qf.manager.pojo.dto.ItemResult;
import com.qf.manager.pojo.dto.PageParam;
import com.qf.manager.pojo.po.TbItem;
import com.qf.manager.pojo.po.TbItemExample;
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
    @Autowired
    private TbItemMapper itemDao;

    @Override
    public ItemResult<ItemCustom> listItems(PageParam pageParam,ItemQuery itemQuery) {

        ItemResult<ItemCustom> result = new ItemResult<>();
        result.setCode(0);
        result.setMsg("select success");
        try {
            long count = itemCustomDao.getCount(itemQuery);
            result.setCount(count);
            List<ItemCustom> data = itemCustomDao.selectItemsByPage(pageParam,itemQuery);
            result.setData(data);
        }catch (Exception e){
            result.setCode(-1);
            result.setMsg("select failed");
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int updateItemsByIds(List<Long> ids) {

        int i = 0;
        try {
            //创建一个新对象，这个对象的状态是3（删除状态）
            System.out.println(ids.get(0));
            TbItem tbItem = new TbItem();
            tbItem.setStatus((byte) 3);
            //创建模板对象
            TbItemExample tbItemExample = new TbItemExample();
            TbItemExample.Criteria criteria = tbItemExample.createCriteria();
            criteria.andIdIn(ids);
            //执行更新操作
            i = itemDao.updateByExampleSelective(tbItem, tbItemExample);
        } catch (RuntimeException re) {
            logger.error(re.getMessage(), re);
            re.printStackTrace();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }

        return i;
    }
}
