package com.fc.psi.enums;

/**
 * 进销存系统枚举类
 */
public enum PsiEnum {
    /**
     * 默认账号
     */
    DEFAULT_USERNAME("admin"),
    /**
     * 默认密码
     */
    DEFAULT_PASSWORD("admin"),
    /**
     * 默认分隔符
     */
    DEFAULT_SEPARATOR("-"),
    /**
     * 物品种类大类编码
     */
    GOODS_TYPE_CODE("goodsType"),
    /**
     * 是否大类编码
     */
    YN("YN"),
    /**
     * 是
     */
    Y("Y"),
    /**
     * 单位大类编码
     */
    UNIT("UNIT"),
    /**
     * 库存初始化数据key
     */
    INVEN_INIT_DATA("invenInitData"),
    /**
     * 物品集合
     */
    GOODS("goods"),
    /**
     *  买家集合
     */
    BUYER("buyer"),
    /**
     * 流水标识
     */
    RUN_FLAG("runFlag");

    private String value;

    PsiEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
