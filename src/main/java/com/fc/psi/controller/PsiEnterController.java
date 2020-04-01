package com.fc.psi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PsiEnterController {

    private final static String VIEW_PATH = "views/";

    /**
     * 进入登录主页面
     * @return
     */
    @RequestMapping("/home.action")
    public String enterLoginIndex(){
        return "login";
    }

    /**
     * 进入首页面
     * @return
     */
    @RequestMapping("/index.action")
    public String enterIndex(){return "index";}

    /**
     * 进入类型管理界面
     * @return
     */
    @RequestMapping("/typeManage.action")
    public String enterType(){
        return VIEW_PATH + "type.html";
    }

    /**
     * 采购管理
     * @return
     */
    @RequestMapping("/inManage.action")
    public String enterIn(){
        return VIEW_PATH + "in.html";
    }

    /**
     * 销售管理
     * @return
     */
    @RequestMapping("/saleManage.action")
    public String enterSale(){
        return VIEW_PATH + "sale.html";
    }

    /**
     * 库存管理
     * @return
     */
    @RequestMapping("/inventoryManage.action")
    public String enterOut(){
        return VIEW_PATH + "inventory.html";
    }

    /**
     * 统计查询
     * @return
     */
    @RequestMapping("/statQueryIndex.action")
    public String enterStatQuery(){
        return VIEW_PATH + "statquery.html";
    }
}
