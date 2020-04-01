package com.fc.psi.controller;

import com.fc.psi.entity.PsiGoodsEntity;
import com.fc.psi.service.PsiGoodsService;
import com.fc.psi.service.PsiInvenInfoService;
import com.fc.psi.util.PsiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/psi/goods/")
public class PsiGoodsController {

    @Autowired
    private PsiInvenInfoService psiInvenInfoService;
    @Autowired
    private PsiGoodsService psiGoodsService;

    /**
     * 库存管理界面初始化数据
     * @return
     */
    @RequestMapping("/goodsIndexInit.action")
    public Map<String,Object> goodsIndexInit(){
        try{
            return psiInvenInfoService.goodsIndexInit();
        }catch (Exception e){
            return PsiUtil.getMap(false,e.getMessage());
        }
    }

    /**
     * 编辑物品（新增、修改）
     * @return
     */
    @RequestMapping("/editGoodsInfo.action")
    public Map<String,Object> editGoodsInfo(String data){
        try{
            return psiInvenInfoService.editGoodsInfo(data);
        }catch (Exception e){
            e.printStackTrace();
            return PsiUtil.getMap(false,e.getMessage());
        }
    }

    /**
     * 删除物品
     * @param id
     * @return
     */
    @RequestMapping("/deleteGoods.action")
    public Map<String,Object> deleteGoods(String id){
        try{
            return psiInvenInfoService.deleteGoodsById(id);
        }catch(Exception e){
            e.printStackTrace();
            return PsiUtil.getMap(false,e.getMessage());
        }
    }

    /**
     * 物品主界面搜索
     * @param data
     * @return
     */
    @RequestMapping("/goodsIndexSearch.action")
    public Map<String,Object> goodsIndexSearch(String data){
        try{
            return psiGoodsService.goodsIndexSearch(data);
        }catch(Exception e){
            e.printStackTrace();
            return PsiUtil.getMap(false,e.getMessage());
        }
    }

}
