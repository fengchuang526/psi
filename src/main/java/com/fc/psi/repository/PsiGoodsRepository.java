package com.fc.psi.repository;

import com.fc.psi.entity.PsiGoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PsiGoodsRepository extends JpaRepository<PsiGoodsEntity,String> {

    /**
     * 通过是否可用查询商品
     * @param isUse 是否可用
     * @return
     */
    public List<PsiGoodsEntity> findByIsUse(String isUse);


}
