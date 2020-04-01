package com.fc.psi.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "T_PSI_GOODS")
public class PsiGoodsEntity extends IdEntity{

    /**
     *  类型ID
     */
    @Column(name = "TYPE_ID")
    private String typeId;
    /**
     *  物品名称
     */
    @Column(name = "GOODS_NAME")
    private String goodsName;
    /**
     *  物品编码
     */
    @Column(name = "GOODS_CODE")
    private String goodsCode;
    /**
     *  物品描述
     */
    @Column(name = "GOODS_DESC")
    private String goodsDesc;
    /**
     *  物品单位
     */
    @Column(name = "GOODS_UNIT")
    private String goodsUnit;
    /**
     *  物品数量
     */
    @Column(name = "GOODS_NUM")
    private Double goodsNum;
    /**
     *  物品进价
     */
    @Column(name = "GOODS_IN_PRICE")
    private Double goodsInPrice;
    /**
     *  物品单价
     */
    @Column(name = "GOODS_OUT_PRICE")
    private Double goodsOutPrice;
    /**
     *  备注
     */
    @Column(name = "REMARK")
    private String remark;
    /**
     *  是否可用
     */
    @Column(name = "IS_USE")
    private String isUse;

}
