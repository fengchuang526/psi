package com.fc.psi.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.fc.psi.entity.PsiGoodsEntity;
import com.fc.psi.entity.PsiInvenInfoEntity;

import java.util.List;
import java.util.Map;

public interface PsiInvenInfoService {

    /**
     * 物品界面初始化数据
     * @return
     * @throws Exception
     */
    public Map<String,Object> goodsIndexInit() throws Exception;

    /**
     * 流水主界面初始化
     * @param runFlag 流水标识
     * @return
     * @throws Exception
     */
    public Map<String,Object> inventInfoInit(String runFlag) throws Exception;

    /**
     * 物品编辑（新增、修改）
     * @param data 数据
     * @return
     * @throws Exception
     */
    public Map<String,Object> editGoodsInfo(String data) throws Exception;

    /**
     * 删除物品
     * @param id
     * @throws Exception
     */
    public Map<String,Object> deleteGoodsById(String id) throws Exception;

    /**
     * 查询流水记录
     * @param typeId 商品类型
     * @param goodsCode 商品编码
     * @param goodsName 商品名称
     * @param merchantCode 商家编码
     * @param merchantName 商家名称
     * @param runFlag 流水标识
     * @param startDateStr 开始时间
     * @param endDateStr   结束时间
     * @return
     * @throws Exception
     */
    public List<PsiInvenInfoEntity> findInvenInfoByCondition(String typeId,String goodsCode,
           String goodsName,String merchantCode,String merchantName,String runFlag,
           String startDateStr,String endDateStr) throws Exception;

    /**
     * 保存流水
     * @param data 信息
     * @return
     * @throws Exception
     */
    public Map<String,Object> saveInvenInfo(String data) throws Exception;

    /**
     * 根据ID删除流水
     * @param id 流水ID
     * @return
     * @throws Exception
     */
    public Map<String,Object> deleteInvenInfoById(String id) throws Exception;

}
