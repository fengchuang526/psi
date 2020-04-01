package com.fc.psi.service;

import com.fc.psi.entity.PsiGoodsEntity;

import java.util.Map;

public interface PsiGoodsService {

    /**
     * 物品主界面搜索
     * @param data 数据
     * @return
     * @throws Exception
     */
    public Map<String,Object> goodsIndexSearch(String data) throws Exception;

}
