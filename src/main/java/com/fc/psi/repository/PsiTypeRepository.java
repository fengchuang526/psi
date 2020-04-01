package com.fc.psi.repository;

import com.fc.psi.entity.PsiTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PsiTypeRepository extends JpaRepository<PsiTypeEntity,String> {

    /**
     * 删除类型
     * @param pId 父ID
     */
    public void deleteByPId(String pId);

    /**
     * 根据类型编码查询
     * @param typeCode 类型编码
     * @return
     */
    public List<PsiTypeEntity> findByTypeCode(String typeCode);

}
