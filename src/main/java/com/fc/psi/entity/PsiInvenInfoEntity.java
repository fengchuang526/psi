package com.fc.psi.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name="T_PSI_INVEN_INFO")
public class PsiInvenInfoEntity extends IdEntity{

    /**
     * 物品ID
     */
    @Column(name = "GOODS_ID")
    private String goodsId;
    /**
     *  流水标识
     */
    @Column(name = "RUN_FLAG")
    private String runFlag;
    /**
     *  流水数量
     */
    @Column(name = "RUN_NUM")
    private Double runNum;
    /**
     *  商家编码
     */
    @Column(name = "MERCHANT_CODE")
    private String merchantCode;
    /**
     *  物品单位
     */
    @Column(name = "GOODS_UNIT")
    private String goodsUnit;
    /**
     *  物品单价
     */
    @Column(name = "GOODS_PRICE")
    private Double goodsPrice;
    /**
     *  总金额
     */
    @Column(name = "TOTAL_AMT")
    private Double totalAmt;

}
