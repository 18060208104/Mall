package com.javapandeng.controller;

import com.javapandeng.base.BaseController;
import com.javapandeng.base.BaseDao;
import com.javapandeng.mapper.ItemOrderMapper;
import com.javapandeng.po.ItemOrder;
import com.javapandeng.service.ItemOrderService;
import com.javapandeng.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//订单管理
@Controller
@RequestMapping("/itemOrder")
public class ItemOrderController  extends BaseController {
    @Autowired
    private ItemOrderService itemOrderService;

    /**
     * 订单管理列表
     */
    @RequestMapping("/findBySql")
    public String findBySql(ItemOrder itemOrder, Model model){
        //分页查询
        String sql = "select * from item_order where 1=1 ";
        if(!(isEmpty(itemOrder.getCode()))){
            sql +=" and code like '%"+itemOrder.getCode()+"%' ";
        }
        sql += " order by id desc";
        Pager<ItemOrder> pagers = itemOrderService.findBySqlRerturnEntity(sql);
        model.addAttribute("pagers",pagers);
        //存储查询条件
        model.addAttribute("obj",itemOrder);
        return "itemOrder/itemOrder";
    }
}


