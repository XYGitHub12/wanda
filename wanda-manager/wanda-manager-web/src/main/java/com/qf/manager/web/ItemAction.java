package com.qf.manager.web;

import com.qf.manager.pojo.dto.ItemResult;
import com.qf.manager.pojo.dto.PageParam;
import com.qf.manager.pojo.po.TbItem;
import com.qf.manager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemAction {

    @Autowired
    private ItemService itemService;

    /**
     * 分页查询数据
     * @param  pageParam
     * @return result
     */
    @RequestMapping(value = "/items",method = RequestMethod.GET)
    @ResponseBody
    public ItemResult<TbItem> listItems(PageParam pageParam){

        ItemResult<TbItem> result = null;
        try {
            result = itemService.listItems(pageParam);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
