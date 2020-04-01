package com.fc.psi.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Data
@Entity
@Table(name = "T_PSI_TYPE")
public class PsiTypeEntity extends IdEntity {
    /**
     *  类型编码
     */
    @Column(name="TYPE_CODE")
    private String typeCode;
    /**
     *  类型名称
     */
    @Column(name="TYPE_NAME")
    private String typeName;
    /**
     *  类型描述
     */
    @Column(name="TYPE_DESC")
    private String typeDesc;
    /**
     *  父编码
     */
    @Column(name="P_ID")
    private String pId;

    /**
     * 是否有子节点
     */
    @Transient
    private List<PsiTypeEntity> children;
}
