package com.qf.manager.web;

import com.qf.manager.pojo.dto.ItemResult;
import com.qf.manager.pojo.dto.PageParam;
import com.qf.manager.pojo.vo.ItemCustom;
import com.qf.manager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class ItemAction {

    @Autowired
    private ItemService itemService;

    /**
     * 分页查询数据（商品分类，商品状态显示）
     * @param  pageParam
     * @return result
     */
    @RequestMapping(value = "/items",method = RequestMethod.GET)
    @ResponseBody
    public ItemResult<ItemCustom> listItems(PageParam pageParam){

        ItemResult<ItemCustom> result = null;
        try {
            result = itemService.listItems(pageParam);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "/item/batch",method = RequestMethod.POST)
    @ResponseBody
    public int remove(@RequestParam("ids[]") List<Long> ids){

        System.out.println(ids);
        int i = 0;
        try {
            i = itemService.updateItemsByIds(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(i);
        return i;
    }
}
